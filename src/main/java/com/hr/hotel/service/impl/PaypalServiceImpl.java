package com.hr.hotel.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hotel.common.Constant;
import com.hr.hotel.config.ApplicationConfig;
import com.hr.hotel.service.ApplicationMailerService;
import com.hr.hotel.service.PaypalService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptiveAccountsService;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.aa.AccountIdentifierType;
import com.paypal.svcs.types.aa.GetVerifiedStatusRequest;
import com.paypal.svcs.types.aa.GetVerifiedStatusResponse;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Token;

@Service(Constant.SERVICE_PAYPAL)
public class PaypalServiceImpl implements PaypalService {

	private static final Logger logger = LoggerFactory
			.getLogger(PaypalServiceImpl.class);

	@Resource(name = Constant.SERVICE_APPLICATION_MAIL)
	private ApplicationMailerService applicationMailerService;

	@Autowired
	private AdaptiveAccountsService adaptiveAccountsService;

	@Autowired
	private AdaptivePaymentsService adaptivePaymentsService;

	@Autowired
	private OAuthTokenCredential oAuthTokenCredential;

	@Autowired
	private HashMap<String, String> paypalSdkConfig;

	@Autowired
	private ApplicationConfig config;

	public Boolean isValidBankAccount(String country, String bankAccountNumber,
			String bankRoutingNumber) {
		Boolean isVarified = false;
		try {
			Stripe.apiKey = "sk_live_pZnnRRRjgD3YjooP2He0OkfF";
			Map<String, Object> tokenParams = new HashMap<String, Object>();
			Map<String, Object> bank_accountParams = new HashMap<String, Object>();
			bank_accountParams.put("country", country);
			bank_accountParams.put("routing_number", bankRoutingNumber);
			bank_accountParams.put("account_number", bankAccountNumber);
			tokenParams.put("bank_account", bank_accountParams);
			// Token token=Token.create(tokenParams);
			Token.create(tokenParams);
			isVarified = true;
		} catch (AuthenticationException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (APIConnectionException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (CardException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (APIException e) {
			isVarified = false;
			e.printStackTrace();
		} finally {

		}
		return isVarified;
	}

	public Boolean isPaypalVarifiedAccount(String email) {
		Boolean isVarified = false;
		RequestEnvelope objRequestEnvelope = null;
		GetVerifiedStatusRequest objGetVerifiedStatusRequest = null;
		// Map<String, String> objConfigurationMap = null;
		// AdaptiveAccountsService objAdaptiveAccountsService = null;
		AccountIdentifierType objAccountIdentifierType = null;
		GetVerifiedStatusResponse objGetVerifiedStatusResponse = null;
		try {

			objRequestEnvelope = new RequestEnvelope();
			objRequestEnvelope.setErrorLanguage("en_US");

			objGetVerifiedStatusRequest = new GetVerifiedStatusRequest(
					objRequestEnvelope, "NONE");

			/*
			 * (Optional - must be present if the emailAddress field above is
			 * not) The identifier of the PayPal account holder. If present,
			 * must be one (and only one) of these account identifier types: 1.
			 * emailAddress 2. mobilePhoneNumber 3. accountId
			 */
			objAccountIdentifierType = new AccountIdentifierType();

			/*
			 * (Required)Email address associated with the PayPal account: one
			 * of the unique identifiers of the account.
			 */
			objAccountIdentifierType.setEmailAddress(email);

			objGetVerifiedStatusRequest
					.setAccountIdentifier(objAccountIdentifierType);

			objGetVerifiedStatusResponse = this.adaptiveAccountsService
					.getVerifiedStatus(objGetVerifiedStatusRequest);

			if (objGetVerifiedStatusResponse != null) {
				if (objGetVerifiedStatusResponse.getResponseEnvelope().getAck()
						.toString().equalsIgnoreCase("SUCCESS")) {
					isVarified = true;
				} else {
					isVarified = false;
				}
			}

		} catch (SSLConfigurationException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (HttpErrorException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (OAuthException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (IOException e) {
			isVarified = false;
			e.printStackTrace();
		} catch (InterruptedException e) {
			isVarified = false;
			e.printStackTrace();
		} finally {

		}
		return isVarified;
	}

	// @Transactional
	// public String createPaypalLink(UserRoomBooking userRoomBooking,
	// String accessToken, String redirectUrl)
	// throws UnsupportedEncodingException, PayPalRESTException, Exception {
	// // object of StringBuilder
	// StringBuilder objSB = null;
	// // object of Payment
	// Payment objPayment = null;
	// // object of Amount
	// Amount objAmount = null;
	// // object of Details
	// Details objDetails = null;
	// // object of Transaction
	// Transaction objTransaction = null;
	// // object of List
	// List<Transaction> objListTransactions = null;
	// // object of Payer
	// Payer objPayer = null;
	// // object of RedirectUrls
	// RedirectUrls objRedirectUrls = null;
	//
	// // object of String
	// String strAccessToken = null;
	// // object of OAuthTokenCredential
	// OAuthTokenCredential objAuthTokenCredential = null;
	// // object of String
	// String strRequestID = null;
	// // object of APIContext
	// APIContext objApiContext = null;
	// // object of PaymentMaster
	// PaymentMaster objPaymentMaster = null;
	// // cost
	// Integer cost = 0;
	// String returnView = null;
	// Map<String, String> sdkConfig = null;
	// try {
	// logger.info("payWithpaypal start");
	// objSB = new StringBuilder();
	//
	// sdkConfig = new HashMap<String, String>();
	// sdkConfig.put("mode", "sandbox");
	//
	// cost = (int) (userRoomBooking.getRoomId().getCost() * getDateDifference(
	// userRoomBooking.getStartDate(),
	// userRoomBooking.getEndDate()));
	// // object of Details
	// objDetails = new Details();
	// // set price of room
	// objDetails.setSubtotal(String.valueOf(cost));
	// // set tax if applicable
	// // objDetails.setTax("");
	//
	// // object of Amount
	// objAmount = new Amount();
	// // set currency for transaction
	// objAmount.setCurrency(environment
	// .getProperty(Constant.ENV_PAYPAL_CURRENCY));
	// // set detail for transaction by passing object of Details
	// objAmount.setDetails(objDetails);
	// // set total cost after all shipping charge +tax+room price
	// objAmount.setTotal(String.valueOf(cost));
	//
	// // if paypal payment
	//
	// logger.info("enter in paypal payment");
	// // object of RedirectUrls
	// objRedirectUrls = new RedirectUrls();
	// // set cancel URL
	// objSB.delete(0, objSB.length())
	// .append(environment
	// .getProperty(Constant.ENV_PAYPAL_REDIRECT_RETURN))
	// .append(userRoomBooking.getUserRoomBookingId())
	// .append(Constant.STRING_SLASH)
	// .append(Constant.STRING_PAYPAL_STATUS_CANCEL)
	// .append("?access_token=")
	// .append(URLEncoder.encode(accessToken, Constant.UTF8))
	// .append("&redirect_url=")
	// .append(URLEncoder.encode(redirectUrl, Constant.UTF8));
	// objRedirectUrls.setCancelUrl(objSB.toString());
	// // set return URL
	// objSB.delete(0, objSB.length())
	// .append(environment
	// .getProperty(Constant.ENV_PAYPAL_REDIRECT_CANCLE))
	// .append(userRoomBooking.getUserRoomBookingId())
	// .append(Constant.STRING_SLASH)
	// .append(Constant.STRING_PAYPAL_STATUS_APPROVE)
	// .append("?access_token=")
	// .append(URLEncoder.encode(accessToken, Constant.UTF8))
	// .append("&redirect_url=")
	// .append(URLEncoder.encode(redirectUrl, Constant.UTF8));
	// objRedirectUrls.setReturnUrl(objSB.toString());
	//
	// // object of Payer
	// objPayer = new Payer();
	// // set payer payment method as paypal
	// objPayer.setPaymentMethod(environment
	// .getProperty(Constant.ENV_PAYPAL_PAYMENY_PAYPAL));
	//
	// logger.info("paypal payment end");
	//
	// // object of Transaction
	// objTransaction = new Transaction();
	// // set amount for transaction by passing Amount object
	// objTransaction.setAmount(objAmount);
	// // set description for transaction
	// objTransaction.setDescription("Room Details");
	//
	// // create list to store Transaction objects
	// objListTransactions = new ArrayList<Transaction>();
	// // add Transaction to list
	// objListTransactions.add(objTransaction);
	//
	// // object of Payment
	// objPayment = new Payment();
	// // set intent for Payment
	// objPayment.setIntent(environment
	// .getProperty(Constant.ENV_PAYPAL_INTENT_SALE));
	// // set payer
	// objPayment.setPayer(objPayer);
	// // set redirect URL
	// if (objRedirectUrls != null)
	// objPayment.setRedirectUrls(objRedirectUrls);
	// // set transaction
	// objPayment.setTransactions(objListTransactions);
	//
	// // object of OAuthTokenCredential by passing paypal application
	// // clientId and clientSecret
	// objAuthTokenCredential = new OAuthTokenCredential(
	// environment.getProperty(Constant.ENV_PAYPAL_CLIENT_ID),
	// environment.getProperty(Constant.ENV_PAYPAL_CLIENT_SECRET),
	// sdkConfig);
	//
	// // generate access token from OAuthTokenCredential object
	// strAccessToken = objAuthTokenCredential.getAccessToken();
	//
	// // generate request id
	// strRequestID = UUID.randomUUID().toString();
	//
	// // object of APIContext by passing accessToken and requestID
	// objApiContext = new APIContext(strAccessToken, strRequestID);
	// objApiContext.setConfigurationMap(sdkConfig);
	//
	// objPayment = objPayment.create(objApiContext);
	// // add data in paymentmaster object
	// objPaymentMaster = new PaymentMaster();
	// objPaymentMaster.setAmount(Double.parseDouble(objPayment
	// .getTransactions().get(0).getAmount().getTotal()));
	// objPaymentMaster.setDate(new Date());
	// objPaymentMaster.setDescription(objPayment.getTransactions().get(0)
	// .getDescription());
	// objPaymentMaster.setPaymentId(objPayment.getId());
	// objPaymentMaster.setState(objPayment.getState());
	// objPaymentMaster.setUserRoomBookingId(userRoomBooking);
	//
	// // save payment in database
	// this.commonDao.save(objPaymentMaster);
	//
	// // if payment method is paypal redirect to paypal site
	// // redirect to paypal site
	// objSB.delete(0, objSB.length()).append(Constant.REDIRECT)
	// .append(Constant.STRING_COLON)
	// .append(this.getApprovalURL(objPayment));
	// // redirect to rooms page
	// returnView = objSB.toString();
	// logger.info("createPaymentLink end");
	// // free system resource
	// } finally {
	// objAmount = null;
	// objDetails = null;
	// objTransaction = null;
	// objListTransactions = null;
	// objPayer = null;
	// objRedirectUrls = null;
	//
	// strAccessToken = null;
	// objAuthTokenCredential = null;
	// strRequestID = null;
	// objApiContext = null;
	// }
	// // return objPaymentReturn;
	// return returnView;
	// }

	/**
	 * executePaymet method execute paypal transaction after paypal response
	 * 
	 * @param payerId
	 *            object of String
	 * @param paymentMaster
	 *            object of PaymentMaster to get paymentId
	 * @return void
	 * @throws PayPalRESTException
	 *             ,Exception
	 */
	// public void executePaymet(String payerId, PaymentMaster paymentMaster)
	// throws PayPalRESTException, Exception {
	// Payment objPayment = null;
	// PaymentExecution objPaymentExecution = null;
	// // object of String
	// String strAccessToken = null;
	// // object of OAuthTokenCredential
	// OAuthTokenCredential objAuthTokenCredential = null;
	// // object of String
	// String strRequestID = null;
	// // object of APIContext
	// APIContext objApiContext = null;
	// Map<String, String> sdkConfig = null;
	// try {
	// sdkConfig = new HashMap<String, String>();
	// sdkConfig.put("mode", "sandbox");
	// objPayment = new Payment();
	// objPayment.setId(paymentMaster.getPaymentId());
	//
	// objPaymentExecution = new PaymentExecution();
	// objPaymentExecution.setPayerId(payerId);
	//
	// // object of OAuthTokenCredential by passing paypal application
	// // clientId and clientSecret
	// objAuthTokenCredential = new OAuthTokenCredential(
	// environment.getProperty(Constant.ENV_PAYPAL_CLIENT_ID),
	// environment.getProperty(Constant.ENV_PAYPAL_CLIENT_SECRET),
	// sdkConfig);
	//
	// // generate access token from OAuthTokenCredential object
	// strAccessToken = objAuthTokenCredential.getAccessToken();
	//
	// // generate request id
	// strRequestID = UUID.randomUUID().toString();
	//
	// // object of APIContext by passing accessToken and requestID
	// objApiContext = new APIContext(strAccessToken, strRequestID);
	// objApiContext.setConfigurationMap(sdkConfig);
	// objPayment = objPayment.execute(objApiContext, objPaymentExecution);
	// } finally {
	//
	// }
	// }

	/**
	 * getApprovalURL method return URL for redirection
	 * 
	 * @param payment
	 *            object of Payment
	 * @return redirect URL
	 * @throws UnsupportedEncodingException
	 *             ,Exception
	 */
	@Override
	public String getApprovalURL(Payment payment)
			throws UnsupportedEncodingException {
		// store redirect URL
		String strRedirectUrl = null;
		// object of List
		List<Links> objListLinks = null;
		try {
			// get list of Links from payment object
			objListLinks = payment.getLinks();
			// iterate throw list
			for (Links objLinks : objListLinks) {
				// check if link contains "approval_url"
				if (objLinks.getRel().equalsIgnoreCase(
						Constant.STRING_PAYPAL_APPROVAL_URL)) {
					// store decoded URL from Links
					strRedirectUrl = URLDecoder.decode(objLinks.getHref(),
							Constant.UTF8);
					break;
				}
			}
			// free system resource
		} finally {
			if (objListLinks != null) {
				objListLinks.clear();
				objListLinks = null;
			}
		}
		// return redirect URL string
		return strRedirectUrl;
	}

	// private static Long getDateDifference(Date startDate, Date endDate) {
	// Long diff = (endDate.getTime() - startDate.getTime())
	// / (1000 * 60 * 60 * 24);
	// return diff;
	// }

	// @Override
	// @Transactional
	// public boolean isPaymentDone(PaymentCreditcardModel creditcardModel,
	// User user, Integer roomId) throws PayPalRESTException,
	// Exception {
	// Boolean isPaymentDone = false;
	// // object of Payment
	// Payment objPayment = null;
	// // object of Amount
	// Amount objAmount = null;
	// // object of Details
	// Details objDetails = null;
	// // object of Transaction
	// Transaction objTransaction = null;
	// // object of List
	// List<Transaction> objListTransactions = null;
	// // object of Payer
	// Payer objPayer = null;
	//
	// // object of String
	// String strAccessToken = null;
	// // object of OAuthTokenCredential
	// OAuthTokenCredential objAuthTokenCredential = null;
	// // object of String
	// String strRequestID = null;
	// // object of APIContext
	// APIContext objApiContext = null;
	// // object of FundingInstrument
	// FundingInstrument objFundingInstrument = null;
	// // object of list contains FundingInstrument objects
	// List<FundingInstrument> objFundingInstrumentList = null;
	// // object of CreditCard
	// CreditCard objCreditCard = null;
	// HotelRoom objHotelRoom = null;
	// // cost
	// Integer cost = 0;
	// Map<String, String> sdkConfig = null;
	// UserRoomBooking objUserRoomBooking = null;
	// HotelRating objHotelRatingHotel = null;
	// HotelRating objHotelRatingUser = null;
	// Map<String, Object> objMapTemplate = null;
	// String startDate = null;
	// String endDate = null;
	// SimpleDateFormat simpleDateFormat = null;
	//
	// try {
	// objHotelRoom = (HotelRoom) this.commonDao.get(HotelRoom.class,
	// roomId);
	// if (objHotelRoom != null
	// && objHotelRoom.getBlock()
	// && objHotelRoom.getBlockedBy().getUserId() == user
	// .getUserId()) {
	// sdkConfig = new HashMap<String, String>();
	// sdkConfig.put("mode", "sandbox");
	//
	// cost = (int) (objHotelRoom.getCost() * getDateDifference(
	// creditcardModel.getStartDate(),
	// creditcardModel.getEndDate()));
	// // object of Details
	// objDetails = new Details();
	// // set price of room
	// objDetails.setSubtotal(String.valueOf(cost));
	// // set tax if applicable
	// // objDetails.setTax("");
	//
	// // object of Amount
	// objAmount = new Amount();
	// // set currency for transaction
	// objAmount.setCurrency(environment
	// .getProperty(Constant.ENV_PAYPAL_CURRENCY));
	// // set detail for transaction by passing
	// // object
	// // of Details
	// objAmount.setDetails(objDetails);
	// // set total cost after all shipping charge
	// // +tax+room price
	// objAmount.setTotal(String.valueOf(cost));
	// objFundingInstrument = new FundingInstrument();
	//
	// /*
	// * Address objBillingAddress = new Address();
	// * objBillingAddress.setLine1("");
	// * objBillingAddress.setLine2("");
	// * objBillingAddress.setCity("");
	// * objBillingAddress.setCountryCode("US");
	// * objBillingAddress.setPostalCode("43210");
	// * objBillingAddress.setState("OH");
	// */
	// objCreditCard = new CreditCard();
	// objCreditCard.setNumber(creditcardModel.getCardNumber());
	// objCreditCard.setType(creditcardModel.getCardType());
	// objCreditCard.setExpireMonth(Integer.parseInt(creditcardModel
	// .getExpMonth()));
	// objCreditCard.setExpireYear(Integer.parseInt(creditcardModel
	// .getExpYear()));
	// objCreditCard.setCvv2(creditcardModel.getCvv());
	// // objCreditCard.setFirstName("Joe");
	// // objCreditCard.setLastName("Shopper");
	// // objCreditCard.setBillingAddress("billingAddress");
	//
	// objFundingInstrument.setCreditCard(objCreditCard);
	//
	// objFundingInstrumentList = new ArrayList<FundingInstrument>();
	// objFundingInstrumentList.add(objFundingInstrument);
	//
	// // object of Payer
	// objPayer = new Payer();
	// // set payer payment method as creditcard
	// objPayer.setPaymentMethod(environment
	// .getProperty(Constant.ENV_PAYPAL_PAYMENY_CREDIT_CARD));
	// objPayer.setFundingInstruments(objFundingInstrumentList);
	//
	// // object of Transaction
	// objTransaction = new Transaction();
	// // set amount for transaction by passing
	// // Amount
	// // object
	// objTransaction.setAmount(objAmount);
	// // set description for transaction
	// objTransaction.setDescription("Room Details");
	//
	// // create list to store Transaction objects
	// objListTransactions = new ArrayList<Transaction>();
	// // add Transaction to list
	// objListTransactions.add(objTransaction);
	//
	// // object of Payment
	// objPayment = new Payment();
	// // set intent for Payment
	// objPayment.setIntent(environment
	// .getProperty(Constant.ENV_PAYPAL_INTENT_SALE));
	// // set payer
	// objPayment.setPayer(objPayer);
	// // set transaction
	// objPayment.setTransactions(objListTransactions);
	//
	// // object of OAuthTokenCredential by passing
	// // paypal application
	// // clientId and clientSecret
	// objAuthTokenCredential = new OAuthTokenCredential(
	// environment.getProperty(Constant.ENV_PAYPAL_CLIENT_ID),
	// environment
	// .getProperty(Constant.ENV_PAYPAL_CLIENT_SECRET),
	// sdkConfig);
	//
	// // generate access token from
	// // OAuthTokenCredential object
	// strAccessToken = objAuthTokenCredential.getAccessToken();
	//
	// // generate request id
	// strRequestID = UUID.randomUUID().toString();
	//
	// // object of APIContext by passing
	// // accessToken
	// // and requestID
	// objApiContext = new APIContext(strAccessToken, strRequestID);
	// objApiContext.setConfigurationMap(sdkConfig);
	//
	// objPayment = objPayment.create(objApiContext);
	//
	// objUserRoomBooking = new UserRoomBooking();
	// objUserRoomBooking.setActive(true);
	// objUserRoomBooking.setCreatedBy(user.getUserId());
	// objUserRoomBooking.setCreatedDate(new Date());
	// objUserRoomBooking.setEndDate(creditcardModel.getEndDate());
	// objUserRoomBooking.setExternSell(false);
	// objUserRoomBooking.setRoomId(objHotelRoom);
	// objUserRoomBooking.setSellFlag(false);
	// objUserRoomBooking.setStartDate(creditcardModel.getStartDate());
	// objUserRoomBooking.setUserId(user);
	// this.commonDao.save(objUserRoomBooking);
	//
	// objHotelRoom.setBlock(false);
	// objHotelRoom.setBlockedBy(null);
	// // TODO change room to deactive after check
	// // selling flag is true
	// if (objHotelRoom.getUserRoomSellingFlag())
	// objHotelRoom.setActive(false);
	// // TODO
	// this.commonDao.update(objHotelRoom);
	//
	// objHotelRatingHotel = new HotelRating();
	// objHotelRatingHotel.setActive(true);
	// objHotelRatingHotel.setHotelId(objHotelRoom.getHotelId());
	// objHotelRatingHotel.setRoomId(objHotelRoom);
	// objHotelRatingHotel.setUserId(user);
	// objHotelRatingHotel.setBookId(objUserRoomBooking);
	// this.commonDao.save(objHotelRatingHotel);
	//
	// if (objHotelRoom.getUserRoomSellingFlag()) {
	// objHotelRatingUser = new HotelRating();
	// objHotelRatingUser.setActive(true);
	// objHotelRatingUser.setHotelId(objHotelRoom.getHotelId());
	// objHotelRatingUser.setRoomId(objHotelRoom);
	// objHotelRatingUser.setUserId(user);
	// objHotelRatingUser.setSelleruserId(objHotelRoom
	// .getUserRoomSellingId().getUserRoomBookingId()
	// .getUserId());
	// objHotelRatingUser.setBookId(objUserRoomBooking);
	// this.commonDao.save(objHotelRatingUser);
	// }
	//
	// simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
	// startDate = simpleDateFormat.format(creditcardModel
	// .getStartDate());
	// endDate = simpleDateFormat.format(creditcardModel.getEndDate());
	// isPaymentDone = true;
	// // set property for mail
	// objMapTemplate = new HashMap<String, Object>();
	// objMapTemplate.put("name", user.getFirstName());
	// objMapTemplate.put("startdate", startDate);
	// objMapTemplate.put("enddate", endDate);
	// objMapTemplate.put("cost", objHotelRoom.getCost());
	// // send mail
	// this.applicationMailerService.sendTemplateMail(
	// user.getEmail(), "Room Confirm", "bookroom.ftl",
	// objMapTemplate);
	// }
	// } finally {
	//
	// }
	// return isPaymentDone;
	// }

	// TODO change design
	@Override
	public Payment createPayapalPaymentObject(Long cartId, Double cost,
			String returnUrl, String cancelUrl)
			throws UnsupportedEncodingException, PayPalRESTException {
		// object of StringBuilder
		// StringBuilder objSB = null;
		// object of Payment
		Payment objPayment = null;
		// object of Amount
		Amount objAmount = null;
		// object of Details
		Details objDetails = null;
		// object of Transaction
		Transaction objTransaction = null;
		// object of List
		List<Transaction> objListTransactions = null;
		// object of Payer
		Payer objPayer = null;
		// object of RedirectUrls
		RedirectUrls objRedirectUrls = null;

		// object of String
		String strAccessToken = null;
		// object of String
		String strRequestID = null;
		// object of APIContext
		APIContext objApiContext = null;
		NumberFormat objNumberFormat = null;
		try {
			logger.info("payWithpaypal start");
			// objSB = new StringBuilder();
			objNumberFormat = new DecimalFormat("#0.00");
			// object of Details
			objDetails = new Details();
			// set price of room
			objDetails.setSubtotal(objNumberFormat.format(cost));
			// set tax if applicable
			// objDetails.setTax("");

			// object of Amount
			objAmount = new Amount();
			// set currency for transaction
			objAmount.setCurrency(this.config.getPaypalCurrency());
			// set detail for transaction by passing object of Details
			objAmount.setDetails(objDetails);
			// set total cost after all shipping charge +tax+room price
			objAmount.setTotal(objNumberFormat.format(cost));

			logger.info("enter in paypal payment");
			// object of RedirectUrls
			objRedirectUrls = new RedirectUrls();
			/*
			 * // set cancel URL objSB.delete(0, objSB.length())
			 * .append(environment
			 * .getProperty(Constant.ENV_PAYPAL_REDIRECT_RETURN))
			 * .append(cartId).append(Constant.STRING_SLASH)
			 * .append(Constant.STRING_PAYPAL_STATUS_CANCEL)
			 * .append("?access_token=") .append(URLEncoder.encode(accessToken,
			 * Constant.UTF8)) .append("&redirect_url=")
			 * .append(URLEncoder.encode(cancelUrl, Constant.UTF8));
			 */
			objRedirectUrls.setCancelUrl(cancelUrl);
			/*
			 * // set return URL objSB.delete(0, objSB.length())
			 * .append(environment
			 * .getProperty(Constant.ENV_PAYPAL_REDIRECT_CANCLE))
			 * .append(cartId).append(Constant.STRING_SLASH)
			 * .append(Constant.STRING_PAYPAL_STATUS_APPROVE)
			 * .append("?access_token=") .append(URLEncoder.encode(accessToken,
			 * Constant.UTF8)) .append("&redirect_url=")
			 * .append(URLEncoder.encode(returnUrl, Constant.UTF8));
			 */
			objRedirectUrls.setReturnUrl(returnUrl);

			// object of Payer
			objPayer = new Payer();
			// set payer payment method as paypal
			objPayer.setPaymentMethod(this.config.getPaypalPaypalPayment());

			logger.info("paypal payment end");

			// object of Transaction
			objTransaction = new Transaction();
			// set amount for transaction by passing Amount object
			objTransaction.setAmount(objAmount);
			// set description for transaction
			objTransaction.setDescription("Room Details");

			// create list to store Transaction objects
			objListTransactions = new ArrayList<Transaction>();
			// add Transaction to list
			objListTransactions.add(objTransaction);

			// object of Payment
			objPayment = new Payment();
			// set intent for Payment
			objPayment.setIntent(this.config.getPaypalInitSale());
			// set payer
			objPayment.setPayer(objPayer);
			// set redirect URL
			if (objRedirectUrls != null)
				objPayment.setRedirectUrls(objRedirectUrls);
			// set transaction
			objPayment.setTransactions(objListTransactions);

			// generate access token from OAuthTokenCredential object
			strAccessToken = this.oAuthTokenCredential.getAccessToken();

			// generate request id
			strRequestID = UUID.randomUUID().toString();

			// object of APIContext by passing accessToken and requestID
			objApiContext = new APIContext(strAccessToken, strRequestID);
			objApiContext.setConfigurationMap(paypalSdkConfig);

			objPayment = objPayment.create(objApiContext);

			logger.info("createPaymentLink end");
			// free system resource
		} finally {
			objAmount = null;
			objDetails = null;
			objTransaction = null;
			objListTransactions = null;
			objPayer = null;
			objRedirectUrls = null;

			strAccessToken = null;
			strRequestID = null;
			objApiContext = null;
		}
		// return objPaymentReturn;
		return objPayment;
	}

	/**
	 * executePaymet method execute paypal transaction after paypal response
	 * 
	 * @param payerId
	 *            object of String
	 * @param paymentMaster
	 *            object of PaymentMaster to get paymentId
	 * @return void
	 * @throws PayPalRESTException
	 *             ,Exception
	 */
	@Override
	public Payment executePaymet(String payerId, String transactionId)
			throws PayPalRESTException {
		Payment objPayment = null;
		PaymentExecution objPaymentExecution = null;
		// object of String
		String strAccessToken = null;
		// object of String
		String strRequestID = null;
		// object of APIContext
		APIContext objApiContext = null;
		try {
			objPayment = new Payment();
			objPayment.setId(transactionId);

			objPaymentExecution = new PaymentExecution();
			objPaymentExecution.setPayerId(payerId);

			// generate access token from OAuthTokenCredential object
			strAccessToken = this.oAuthTokenCredential.getAccessToken();

			// generate request id
			strRequestID = UUID.randomUUID().toString();

			// object of APIContext by passing accessToken and requestID
			objApiContext = new APIContext(strAccessToken, strRequestID);
			objApiContext.setConfigurationMap(this.paypalSdkConfig);
			objPayment = objPayment.execute(objApiContext, objPaymentExecution);
		} finally {
			objPaymentExecution = null;
			// object of String
			strAccessToken = null;
			// object of String
			strRequestID = null;
			// object of APIContext
			objApiContext = null;
		}
		return objPayment;
	}

	@Override
	public PayResponse paymentToSeller(Double amount, String email)
			throws SSLConfigurationException, InvalidCredentialException,
			UnsupportedEncodingException, HttpErrorException,
			InvalidResponseDataException, ClientActionRequiredException,
			MissingCredentialException, OAuthException, IOException,
			InterruptedException {
		PayResponse objPayResponse = null;
		PayRequest objPayRequest = null;
		RequestEnvelope objRequestEnvelope = null;
		List<Receiver> objReceivers = null;
		Receiver objReceiver = null;
		ReceiverList objReceiverList = null;
		try {
			objRequestEnvelope = new RequestEnvelope();
			objRequestEnvelope.setErrorLanguage("en_US");
			objReceiver = new Receiver();
			objReceiver.setEmail(email);
			objReceiver.setAmount(amount);
			objReceivers = new ArrayList<Receiver>(1);
			objReceivers.add(objReceiver);
			objReceiverList = new ReceiverList(objReceivers);
			objPayRequest = new PayRequest();
			objPayRequest.setReceiverList(objReceiverList);
			// TODO change url
			objPayRequest.setCancelUrl("https://www.google.com");
			objPayRequest.setReturnUrl("https://www.google.com");
			objPayRequest.setSenderEmail(this.config.getPaypalEmailAddress());
			objPayRequest.setActionType("PAY");
			objPayRequest.setCurrencyCode(this.config.getPaypalCurrency());
			objPayRequest.setRequestEnvelope(objRequestEnvelope);
			objPayResponse = this.adaptivePaymentsService.pay(objPayRequest);
		} finally {
			objPayRequest = null;
			objRequestEnvelope = null;
			objReceivers = null;
			objReceiver = null;
			objReceiverList = null;
		}
		return objPayResponse;
	}
}
