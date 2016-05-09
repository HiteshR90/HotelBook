package com.hr.hotel.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import net.authorize.Merchant;
import net.authorize.TransactionType;
import net.authorize.aim.Result;
import net.authorize.aim.Transaction;
import net.authorize.data.creditcard.CreditCard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.BookRoomMailData;
import com.hr.hotel.dto.CartPaymentDto;
import com.hr.hotel.dto.CreditcardModel;
import com.hr.hotel.dto.PaymentToSellerData;
import com.hr.hotel.dto.PaymentToSellerTransterDto;
import com.hr.hotel.dto.SellRoomMailData;
import com.hr.hotel.exception.CardDeclineException;
import com.hr.hotel.exception.CostLimitException;
import com.hr.hotel.exception.EmptyCartException;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.Cart;
import com.hr.hotel.model.Order;
import com.hr.hotel.model.OrderDetail;
import com.hr.hotel.model.PaymentFromBuyer;
import com.hr.hotel.model.PaymentToSeller;
import com.hr.hotel.model.User;
import com.hr.hotel.repository.CartDetailRepository;
import com.hr.hotel.repository.CartRepository;
import com.hr.hotel.repository.HotelRoomRepository;
import com.hr.hotel.repository.OrderDetailRepository;
import com.hr.hotel.repository.OrderRepository;
import com.hr.hotel.repository.OrganizationChargeRepository;
import com.hr.hotel.repository.OrganizationRepository;
import com.hr.hotel.repository.PaymentFromBuyerChargeRepository;
import com.hr.hotel.repository.PaymentFromBuyerRepository;
import com.hr.hotel.repository.PaymentToSellerChargeRepository;
import com.hr.hotel.repository.PaymentToSellerRepository;
import com.hr.hotel.repository.RoomPhotoRepository;
import com.hr.hotel.service.PaymentService;
import com.hr.hotel.service.PaypalService;
import com.hr.hotel.service.SendMailService;
import com.hr.hotel.util.DateUtil;
import com.paypal.api.payments.Payment;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.common.AckCode;

import freemarker.template.TemplateException;

@Service(Constant.SERVICE_PAYMENT)
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LoggerFactory
			.getLogger(PaymentServiceImpl.class);

	@Resource(name = Constant.SERVICE_PAYPAL)
	private PaypalService paypalService;

	@Autowired
	private Merchant merchant;

	@Resource(name = Constant.SERVICE_SEND_MAIL)
	private SendMailService sendMailService;

	@Resource
	private HotelRoomRepository hotelRoomRepository;

	@Resource
	private OrderRepository orderRepository;

	@Resource
	private PaymentFromBuyerRepository paymentFromBuyerRepository;

	@Resource
	private PaymentFromBuyerChargeRepository paymentFromBuyerChargeRepository;

	@Resource
	private PaymentToSellerRepository paymentToSellerRepository;

	@Resource
	private PaymentToSellerChargeRepository paymentToSellerChargeRepository;

	@Resource
	private CartRepository cartRepository;

	@Resource
	private CartDetailRepository cartDetailRepository;

	@Resource
	private OrderDetailRepository orderDetailRepository;

	@Resource
	private OrganizationRepository organizationRepository;

	@Resource
	private OrganizationChargeRepository organizationChargeRepository;

	@Resource
	private RoomPhotoRepository roomPhotoRepository;

	private Cart getCart(User loginUser) {
		Cart objCart = null;
		Date objCurrentDate = null;
		try {
			objCart = this.cartRepository.getCartId(loginUser.getEmail());
			objCurrentDate = new Date();
			if (objCart != null) {
				if (objCart.getIsLocked()) {
					// check time is not expire for unlock
					if (objCart.getUnlockAt().after(objCurrentDate)) {
						throw new NotFoundException(NotFound.CartUnderProcess);
						// if time expire unlock cart
					} else {
						objCart.setIsLocked(false);
						// upate
						this.cartRepository.save(objCart);
						return objCart;
					}
				} else {
					return objCart;
				}
			} else {
				objCart = new Cart();
				objCart.createCart(loginUser, objCurrentDate);
				this.cartRepository.saveAndFlush(objCart);
				return objCart;
			}
		} finally {
			objCurrentDate = null;
		}
	}

	@Transactional(rollbackFor = { Throwable.class }, isolation = Isolation.READ_COMMITTED)
	@Override
	public String paypalLink(User loginUser, String returnURL, String cancelURL)
			throws UnsupportedEncodingException, PayPalRESTException {
		Cart objCart = null;
		Double tarnsactionLimit = Constant.TRANSCATION_LIMIT;
		Double cartAmount = null;
		double totalAmount = 0;
		double defaultCharges = 0;
		Payment objPayment = null;
		String paypalUrl = null;
		Integer updatedColumn = 0;
		PaymentFromBuyer objPaymentFromBuyer = null;
		Date objTodayDate = null;
		Calendar objCalendar = null;
		// Boolean isCartUnlocked = Boolean.FALSE;
		// Integer rowChanged = 0;
		try {
			/*
			 * objCart = this.cartRepository.getCartLockStatus(cartId,
			 * loginUser.getEmail()); if (objCart != null) { objTodayDate = new
			 * Date(); if (!objCart.getIsLocked()) { isCartUnlocked =
			 * Boolean.TRUE; } else if
			 * (objCart.getUnlockAt().before(objTodayDate)) { rowChanged =
			 * this.cartRepository .unlockCartWithTimeCheck(cartId); if
			 * (rowChanged > 0) { isCartUnlocked = Boolean.TRUE; } else { throw
			 * new CartNotFoundException(); } } else { throw new
			 * CartUnderProcessException(); } } else { throw new
			 * CartNotFoundException(); }
			 */
			objCart = getCart(loginUser);

			// update cartDetail table and lock cart detail
			// table columns
			objCalendar = Calendar.getInstance();
			objTodayDate = new Date();
			objCalendar.setTime(objTodayDate);
			objCalendar.add(Calendar.MINUTE, Constant.CART_UNLOCK_MINUTE);
			objCalendar.add(Calendar.MINUTE, Constant.EXTRA_RELAEASE_MINUTE);
			updatedColumn = this.cartDetailRepository.updateCartDetailTime(
					objCart.getId(), objCalendar.getTime());
			if (updatedColumn > 0) {
				cartAmount = this.cartRepository.getCartAmount(loginUser
						.getEmail());
				defaultCharges = this.organizationChargeRepository
						.getDefaultAmountForBuyer(cartAmount);
				totalAmount = this.organizationChargeRepository
						.getPaypalAmount(cartAmount + defaultCharges);
				if (totalAmount < tarnsactionLimit) {

					objPayment = this.paypalService.createPayapalPaymentObject(
							objCart.getId(), totalAmount, returnURL, cancelURL);
					paypalUrl = this.paypalService.getApprovalURL(objPayment);

					// update cart table for lock and release time
					objCalendar.setTime(objTodayDate);
					objCalendar.add(Calendar.MINUTE,
							Constant.CART_UNLOCK_MINUTE);
					updatedColumn = 0;
					updatedColumn = this.cartRepository
							.setCartLockAndReleaseTime(objCart.getId(),
									objCalendar.getTime());
					if (updatedColumn > 0) {
						// delete data from paymentFromBuyer table
						// this.paymentFromBuyerRepository.deleteByCartId(objCart
						// .getId());
						this.paymentFromBuyerRepository
								.deactiveByCartId(objCart.getId());
						// entry in payment table
						objPaymentFromBuyer = new PaymentFromBuyer();
						objPaymentFromBuyer.setCart(objCart);
						objPaymentFromBuyer.setCreatedBy(loginUser.getId());
						objPaymentFromBuyer.setState(objPayment.getState());
						objPaymentFromBuyer
								.setTransactionId(objPayment.getId());
						// objPaymentFromBuyer.setIntent(objPayment.getIntent());
						// objPaymentFromBuyer.setPaypalUrl(paypalUrl);
						objPaymentFromBuyer.setCartAmount(cartAmount);
						objPaymentFromBuyer.setTotalAmount(totalAmount);
						objPaymentFromBuyer.setActive(true);
						this.paymentFromBuyerRepository
								.save(objPaymentFromBuyer);

						// save into payment from buyer charge table
						this.paymentFromBuyerChargeRepository
								.selectInsertPaymentFromBuyerDefaultCharge(
										objPaymentFromBuyer.getId(),
										loginUser.getId());

						this.paymentFromBuyerChargeRepository
								.selectInsertPaymentFromBuyerAuthorizeCharge(
										objPaymentFromBuyer.getId(),
										loginUser.getId());

						return paypalUrl;
					} else {
						throw new NotFoundException(NotFound.CartUnderProcess);
					}
				} else {
					throw new CostLimitException();
				}
			} else {
				throw new EmptyCartException("No room in cart to checkout",
						"Checkout cant proceed without room");
			}
		} finally {

		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	public void paypalReturn(String status, String payerID, String accessToken,
			User loginUser) throws PayPalRESTException, MessagingException,
			IOException, TemplateException {
		Payment objPayment = null;
		List<PaymentToSellerData> objPaymentToSellerDatas = null;
		CartPaymentDto objCartPaymentDto = null;
		Order objOrder = null;
		Cart objCart = null;
		try {
			objCart = this.cartRepository.getCartId(loginUser.getEmail());
			if (objCart != null) {
				objCartPaymentDto = this.paymentFromBuyerRepository
						.getCartPaymentId(objCart.getId(), loginUser.getEmail());
				if (objCartPaymentDto != null
						&& StringUtils.hasText(objCartPaymentDto
								.getTransactionId())) {
					if (Constant.STRING_PAYPAL_STATUS_APPROVE
							.equalsIgnoreCase(status)) {
						objPayment = this.paypalService.executePaymet(payerID,
								objCartPaymentDto.getTransactionId());
						if (objPayment != null
								&& "approved".equalsIgnoreCase(objPayment
										.getState())) {
							this.paymentFromBuyerRepository
									.updatePaymentStateByPaymentId(
											objCartPaymentDto
													.getTransactionId(),
											objPayment.getState());
							// confirm room
							objCart.setIsCompleted(true);
							this.cartRepository.save(objCart);
							objOrder = new Order();
							objOrder.setCart(objCart);
							objOrder.setCreatedBy(loginUser.getId());
							objOrder.setOrderBy(loginUser);
							objOrder.setOrderFor(loginUser);
							this.orderRepository.save(objOrder);
							// TODO save into order detail
							this.orderDetailRepository.insertSelect(
									objOrder.getId(), objCart.getId(),
									loginUser.getId());
							objPaymentToSellerDatas = this.orderDetailRepository
									.getPaymentToSellerData(objOrder.getId(),
											loginUser.getEmail());
							paymentToSellerSave(objPaymentToSellerDatas,
									loginUser);
							// TODO send mail
							List<BookRoomMailData> bookRoomMailDatas = this.orderDetailRepository
									.getBookRoomMailData(objOrder.getId());
							this.sendMailService.sendBookRoomMail(loginUser,
									bookRoomMailDatas);
						}
					} else {
						// unlock cart
						objCart.setIsLocked(false);
						this.cartRepository.save(objCart);
						// this.cartRepository.unlockCartWithoutTimeCheck(objCart
						// .getId());
						// this.paymentFromBuyerRepository.deleteByCartId(objCart
						// .getId());
						this.paymentFromBuyerRepository
								.deactiveByCartId(objCart.getId());
					}
				} else {
					throw new NotFoundException(NotFound.CartNotFound);
				}
			}
		} finally {

		}
	}

	private void paymentToSellerSave(
			List<PaymentToSellerData> objPaymentToSellerDatas, User loginUser)
			throws MessagingException, IOException, TemplateException {
		if (objPaymentToSellerDatas != null
				&& objPaymentToSellerDatas.size() > 0) {
			for (PaymentToSellerData paymentToSellerData : objPaymentToSellerDatas) {

				// check room is fully booked if fully booked update status
				if (this.hotelRoomRepository.isFullyBooked(paymentToSellerData
						.getRoomId())) {

					this.hotelRoomRepository
							.setIsFullBookHotelRoom(paymentToSellerData
									.getRoomId());
				}

				PaymentToSeller objPaymentToSeller = new PaymentToSeller();
				objPaymentToSeller.setTotalAmount(paymentToSellerData
						.getTotalAmount());
				double amountToTranster = paymentToSellerData.getTotalAmount()
						- this.organizationChargeRepository
								.getDefaultAmountForSeller(paymentToSellerData
										.getTotalAmount());
				objPaymentToSeller.setAmountToTransfer(amountToTranster);
				objPaymentToSeller.setCreatedBy(loginUser.getId());
				OrderDetail objOrderDetail = this.orderDetailRepository
						.findOne(paymentToSellerData.getOrderDetailId());
				// objOrderDetail.setId(paymentToSellerData.getOrderDetailId());
				objPaymentToSeller.setOrderDetail(objOrderDetail);
				objPaymentToSeller.setTransferDate(DateUtil.getAddedDate(7));
				objPaymentToSeller.setIsTransfered(false);
				this.paymentToSellerRepository.save(objPaymentToSeller);
				// TODO send mail to seller
				SellRoomMailData sellRoomMailData = this.orderDetailRepository
						.getSellRoomMailData(paymentToSellerData
								.getOrderDetailId());
				this.sendMailService.sendSellRoomMail(sellRoomMailData);
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	@Async
	public void paymentToSellerTransfer() {
		List<PaymentToSellerTransterDto> paymentToSellerTransterDtos = this.paymentToSellerRepository
				.getTransterData();
		for (PaymentToSellerTransterDto paymentToSellerTransterDto : paymentToSellerTransterDtos) {
			if (StringUtils.hasText(paymentToSellerTransterDto
					.getSellerPaypalId())) {
				PayResponse objPayResponse = null;
				double defaultCharges = this.organizationChargeRepository
						.getDefaultAmountForSeller(paymentToSellerTransterDto
								.getAmountToTranster());
				try {
					objPayResponse = this.paypalService.paymentToSeller(
							paymentToSellerTransterDto.getAmountToTranster()
									- defaultCharges,
							paymentToSellerTransterDto.getSellerPaypalId());
				} catch (SSLConfigurationException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (InvalidCredentialException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (UnsupportedEncodingException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (HttpErrorException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (InvalidResponseDataException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (ClientActionRequiredException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (MissingCredentialException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (OAuthException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (IOException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (InterruptedException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (Exception e) {
					logger.error("paymentToSellerTransfer", e);
				}
				if (objPayResponse != null
						&& objPayResponse.getResponseEnvelope().getAck() == AckCode.SUCCESS) {
					PaymentToSeller objPaymentToSeller = paymentToSellerTransterDto
							.getPaymentToSeller();
					objPaymentToSeller.setIsTransfered(true);
					objPaymentToSeller.setCorrelationID(objPayResponse
							.getResponseEnvelope().getCorrelationId());
					objPaymentToSeller.setTimeStamp(objPayResponse
							.getResponseEnvelope().getTimestamp());
					objPaymentToSeller.setPayKey(objPayResponse.getPayKey());
					objPaymentToSeller.setStatus(objPayResponse
							.getPaymentExecStatus());
					objPaymentToSeller.setModifiedDate(DateUtil
							.getCurrentDate());
					objPaymentToSeller.setCreatedBy(objPaymentToSeller.getId());
					// update
					this.paymentToSellerRepository.save(objPaymentToSeller);
					this.paymentToSellerChargeRepository
							.selectInsertPaymentToSellerDefaultCharge(objPaymentToSeller
									.getId());
					this.paymentToSellerChargeRepository
							.selectInsertPaymentToSellerPaypalCharge(objPaymentToSeller
									.getId());
					// TODO send mail
					// System.out.println("account credited");
					SellRoomMailData sellRoomMailData = this.orderDetailRepository
							.getSellRoomMailData(paymentToSellerTransterDto
									.getOrderDetailId());
					try {
						this.sendMailService
								.moneyTransferRoomMail(sellRoomMailData);
					} catch (MessagingException e) {
						logger.error("paymentToSellerTransfer", e);
					} catch (IOException e) {
						logger.error("paymentToSellerTransfer", e);
					} catch (TemplateException e) {
						logger.error("paymentToSellerTransfer", e);
					}
				}
			} else {
				// TODO send mail to set email id
				// System.out.println("set paypal mail");
				SellRoomMailData sellRoomMailData = this.orderDetailRepository
						.getSellRoomMailData(paymentToSellerTransterDto
								.getOrderDetailId());
				try {
					this.sendMailService
							.moneyTransferSetPaypalEmail(sellRoomMailData);
				} catch (MessagingException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (IOException e) {
					logger.error("paymentToSellerTransfer", e);
				} catch (TemplateException e) {
					logger.error("paymentToSellerTransfer", e);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = { Throwable.class }, isolation = Isolation.READ_COMMITTED)
	@Override
	public void creditCardPayment(User loginUser,
			CreditcardModel creditcardModel) throws MessagingException,
			IOException, TemplateException {
		Cart objCart = null;
		Double tarnsactionLimit = Constant.TRANSCATION_LIMIT;
		Double cartAmount = null;
		double defaultCharges = 0;
		double totalAmount = 0;
		Integer updatedColumn = 0;
		PaymentFromBuyer objPaymentFromBuyer = null;
		Date objTodayDate = null;
		Calendar objCalendar = null;
		CreditCard creditCard = null;
		Transaction authCaptureTransaction = null;
		Result<Transaction> result = null;
		Order objOrder = null;
		List<PaymentToSellerData> objPaymentToSellerDatas = null;
		try {

			objCart = getCart(loginUser);

			// update cartDetail table and lock cart detail
			// table columns
			objCalendar = Calendar.getInstance();
			objTodayDate = new Date();
			objCalendar.setTime(objTodayDate);
			objCalendar.add(Calendar.MINUTE, Constant.CART_UNLOCK_MINUTE);
			objCalendar.add(Calendar.MINUTE, Constant.EXTRA_RELAEASE_MINUTE);
			updatedColumn = this.cartDetailRepository.updateCartDetailTime(
					objCart.getId(), objCalendar.getTime());
			if (updatedColumn > 0) {
				cartAmount = this.cartRepository.getCartAmount(loginUser
						.getEmail());

				defaultCharges = this.organizationChargeRepository
						.getDefaultAmountForBuyer(cartAmount);
				totalAmount = this.organizationChargeRepository
						.getAuthorizeDotNetAmount(cartAmount + defaultCharges);
				if (totalAmount < tarnsactionLimit) {

					// update cart table for lock and release time
					objCalendar.setTime(objTodayDate);
					objCalendar.add(Calendar.MINUTE,
							Constant.CART_UNLOCK_MINUTE);
					updatedColumn = 0;
					updatedColumn = this.cartRepository
							.setCartLockAndReleaseTime(objCart.getId(),
									objCalendar.getTime());
					if (updatedColumn > 0) {
						// delete data from paymentFromBuyer table
						// this.paymentFromBuyerRepository.deleteByCartId(objCart
						// .getId());
						this.paymentFromBuyerRepository
								.deactiveByCartId(objCart.getId());

						// create credit card
						creditCard = CreditCard.createCreditCard();
						creditCard.setCreditCardNumber(creditcardModel
								.getCardNumber());
						creditCard.setExpirationMonth(creditcardModel
								.getExpMonth());
						creditCard.setExpirationYear(creditcardModel
								.getExpYear());

						// create transaction
						authCaptureTransaction = this.merchant
								.createAIMTransaction(
										TransactionType.AUTH_CAPTURE,
										new BigDecimal(totalAmount));
						authCaptureTransaction.setCreditCard(creditCard);

						result = (Result<Transaction>) merchant
								.postTransaction(authCaptureTransaction);

						// if payment process success entry in paymentFromBuyer
						// table
						if (result.isApproved()) {
							objPaymentFromBuyer = new PaymentFromBuyer();
							objPaymentFromBuyer.setCart(objCart);
							objPaymentFromBuyer.setCreatedBy(loginUser.getId());
							objPaymentFromBuyer.setState(result
									.getResponseCode().name());
							objPaymentFromBuyer.setTransactionId(result
									.getTarget().getTransactionId());
							objPaymentFromBuyer.setCartAmount(cartAmount);
							objPaymentFromBuyer.setActive(true);
							this.paymentFromBuyerRepository
									.save(objPaymentFromBuyer);
							// save into payment from buyer charge table
							this.paymentFromBuyerChargeRepository
									.selectInsertPaymentFromBuyerDefaultCharge(
											objPaymentFromBuyer.getId(),
											loginUser.getId());
							this.paymentFromBuyerChargeRepository
									.selectInsertPaymentFromBuyerAuthorizeCharge(
											objPaymentFromBuyer.getId(),
											loginUser.getId());
							// confirm room
							objCart.setIsCompleted(true);
							this.cartRepository.save(objCart);
							objOrder = new Order();
							objOrder.setCart(objCart);
							objOrder.setCreatedBy(loginUser.getId());
							objOrder.setOrderBy(loginUser);
							objOrder.setOrderFor(loginUser);
							this.orderRepository.save(objOrder);
							// TODO save into order detail
							this.orderDetailRepository.insertSelect(
									objOrder.getId(), objCart.getId(),
									loginUser.getId());

							objPaymentToSellerDatas = this.orderDetailRepository
									.getPaymentToSellerData(objOrder.getId(),
											loginUser.getEmail());
							paymentToSellerSave(objPaymentToSellerDatas,
									loginUser);
							// TODO send mail
							List<BookRoomMailData> bookRoomMailDatas = this.orderDetailRepository
									.getBookRoomMailData(objOrder.getId());
							this.sendMailService.sendBookRoomMail(loginUser,
									bookRoomMailDatas);
						} else {
							throw new CardDeclineException();
						}
					} else {
						throw new NotFoundException(NotFound.CartUnderProcess);
					}
				} else {
					throw new CostLimitException();
				}
			} else {
				throw new EmptyCartException("No room in cart to checkout",
						"Checkout cant proceed without room");
			}
		} finally {

		}
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public boolean creditCartPayment(CreditcardModel creditcardModel,
	// Cart cart, User loginUser, Double cartAmount) {
	// CreditCard creditCard = null;
	// Transaction authCaptureTransaction = null;
	// Result<Transaction> result = null;
	// PaymentFromBuyer objPaymentFromBuyer = null;
	// try {
	// // create credit card
	// creditCard = CreditCard.createCreditCard();
	// creditCard.setCreditCardNumber(creditcardModel.getCardNumber());
	// creditCard.setExpirationMonth(creditcardModel.getExpMonth());
	// creditCard.setExpirationYear(creditcardModel.getExpYear());
	//
	// Merchant merchant = Merchant.createMerchant(Environment.SANDBOX,
	// "5DwU8sF9Kp8y", "53u4f29Urc2ZGKf5");
	// // create transaction
	// authCaptureTransaction = merchant.createAIMTransaction(
	// TransactionType.AUTH_CAPTURE, new BigDecimal(1.99));
	// authCaptureTransaction.setCreditCard(creditCard);
	//
	// result = (Result<Transaction>) merchant
	// .postTransaction(authCaptureTransaction);
	//
	// // if payment process success
	// if (result.isApproved()) {
	// objPaymentFromBuyer = new PaymentFromBuyer();
	// objPaymentFromBuyer.setCart(cart);
	// objPaymentFromBuyer.setCreatedBy(loginUser.getId());
	// objPaymentFromBuyer.setState(result.getResponseCode().name());
	// objPaymentFromBuyer.setTransactionId(result.getTarget()
	// .getTransactionId());
	// objPaymentFromBuyer.setCartAmount(cartAmount);
	// this.paymentFromBuyerRepository.save(objPaymentFromBuyer);
	// return true;
	// } else if (result.isDeclined()) {
	// return false;
	// } else {
	// return false;
	// }
	// } finally {
	//
	// }
	// }

}
