package com.hr.hotel.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dao.HotelRoomDao;
import com.hr.hotel.dto.BookHistoryDto;
import com.hr.hotel.dto.BookRoomForSellDto;
import com.hr.hotel.dto.CartRoomDto;
import com.hr.hotel.dto.ConfirmedRoomDto;
import com.hr.hotel.dto.ExternSellDto;
import com.hr.hotel.dto.HotelRoomData;
import com.hr.hotel.dto.PendingTransactionDto;
import com.hr.hotel.dto.RatingDto;
import com.hr.hotel.dto.RoomBook;
import com.hr.hotel.dto.RoomData;
import com.hr.hotel.dto.RoomListDto;
import com.hr.hotel.dto.SellExternResponse;
import com.hr.hotel.dto.SellHistoryDto;
import com.hr.hotel.dto.SellInternDto;
import com.hr.hotel.dto.SellRoomMailData;
import com.hr.hotel.dto.ValidationErrorDTO;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.CostLimitException;
import com.hr.hotel.exception.EmptyCartException;
import com.hr.hotel.exception.FieldErrorException;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.PathParamMissingException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.Cart;
import com.hr.hotel.model.CartDetail;
import com.hr.hotel.model.City;
import com.hr.hotel.model.HotelMaster;
import com.hr.hotel.model.HotelRoom;
import com.hr.hotel.model.Order;
import com.hr.hotel.model.OrderDetail;
import com.hr.hotel.model.OrderedSell;
import com.hr.hotel.model.Organization;
import com.hr.hotel.model.Rating;
import com.hr.hotel.model.Sell;
import com.hr.hotel.model.TempSellConfirm;
import com.hr.hotel.model.User;
import com.hr.hotel.repository.AmenityRepository;
import com.hr.hotel.repository.CartDetailRepository;
import com.hr.hotel.repository.CartRepository;
import com.hr.hotel.repository.CityRepository;
import com.hr.hotel.repository.DelegateUserRepository;
import com.hr.hotel.repository.HotelMasterRepository;
import com.hr.hotel.repository.HotelRoomRepository;
import com.hr.hotel.repository.OrderDetailRepository;
import com.hr.hotel.repository.OrderedSellRepository;
import com.hr.hotel.repository.OrganizationRepository;
import com.hr.hotel.repository.PaymentToSellerRepository;
import com.hr.hotel.repository.RatingRepository;
import com.hr.hotel.repository.RoomAmenityRepository;
import com.hr.hotel.repository.RoomPhotoRepository;
import com.hr.hotel.repository.SellRepository;
import com.hr.hotel.repository.StateRepository;
import com.hr.hotel.repository.TempSellRepository;
import com.hr.hotel.repository.UserPaymentInfoRepository;
import com.hr.hotel.service.ApplicationMailerService;
import com.hr.hotel.service.CommonService;
import com.hr.hotel.service.HotelRoomService;
import com.hr.hotel.service.SendMailService;
import com.hr.hotel.util.DateUtil;

import freemarker.template.TemplateException;

@Service(Constant.SERVICE_HOTEL_ROOM)
public class HotelRoomServiceImpl implements HotelRoomService {

	private static final Logger logger = LoggerFactory
			.getLogger(HotelRoomServiceImpl.class);

	@Resource(name = Constant.DAO_HOTEL_ROOM)
	private HotelRoomDao hotelRoomDao;

	@Resource(name = Constant.SERVICE_SEND_MAIL)
	private SendMailService sendMailService;

	@Resource
	private HotelRoomRepository hotelRoomRepository;

	@Resource
	private AmenityRepository amenityRepository;

	@Resource
	private OrderDetailRepository orderDetailRepository;

	@Resource(name = Constant.SERVICE_COMMON)
	private CommonService commonService;

	@Resource(name = Constant.SERVICE_APPLICATION_MAIL)
	private ApplicationMailerService applicationMailerService;

	@Resource
	private DelegateUserRepository delegateUserRepository;

	@Resource
	private HotelMasterRepository hotelMasterRepository;

	@Resource
	private RoomPhotoRepository roomPhotoRepository;

	@Resource
	private RoomAmenityRepository roomAmenityRepository;

	@Resource
	private RatingRepository ratingRepository;

	@Resource
	private UserPaymentInfoRepository userPaymentInfoRepository;

	@Resource
	private TempSellRepository tempSellRepository;

	@Resource
	private SellRepository sellRepository;

	@Resource
	private OrderedSellRepository orderedSellRepository;

	@Resource
	private CartRepository cartRepository;

	@Resource
	private CartDetailRepository cartDetailRepository;

	@Resource
	private OrganizationRepository organizationRepository;

	@Resource
	private CityRepository cityRepository;
	
	@Resource
	private StateRepository stateRepository;

	@Resource
	private PaymentToSellerRepository paymentToSellerRepository;

	@Override
	@Transactional
	public BookRoomForSellDto getBookRoomDataForSell(Long orderId, Long roomId,
			User loginUser, String assignerUserEmail) {
		User assignerUser = null;
		try {
			if (loginUser != null) {
				if (orderId != null && orderId > 0 && roomId != null
						&& roomId > 0) {
					if (null != assignerUserEmail) {
						assignerUser = this.delegateUserRepository
								.getAssignerFromDelegate(loginUser.getEmail(),
										assignerUserEmail);
						if (null != assignerUser) {
							return this.hotelRoomRepository.getBookedRoom(
									orderId, roomId, assignerUser.getEmail());
						} else {
							throw new NotFoundException(NotFound.NotAssigner);
						}
					} else {
						return this.hotelRoomRepository.getBookedRoom(orderId,
								roomId, loginUser.getEmail());
					}
				} else {
					throw new PathParamMissingException();
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	private User getUser(User loginUser, String assignerUserEmail) {
		if (loginUser != null) {
			if (StringUtils.hasText(assignerUserEmail)) {
				User assignerUser = this.delegateUserRepository
						.getAssignerFromDelegate(loginUser.getEmail(),
								assignerUserEmail);
				if (null != assignerUser) {
					return assignerUser;
				} else {
					throw new NotFoundException(NotFound.NotAssigner);
				}
			} else {
				return loginUser;
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	public void sellRoomInternal(User loginUser, String assignerUserEmail,
			Long orderId, Long roomId, SellInternDto sellInternDto) {
		User tempUser = null;
		OrderDetail orderDetail = null;
		HotelRoom objHotelRoom = null;
		HotelMaster objHotel = null;
		HotelRoom objHotelRoomTemp = null;
		Sell objSell = null;
		OrderedSell objOrderedSell = null;
		ValidationErrorDTO objValidationErrorDTO = null;
		try {
			tempUser = getUser(loginUser, assignerUserEmail);
			orderDetail = this.orderDetailRepository.getOrderDetailForSell(
					tempUser.getEmail(), roomId, orderId);
			if (orderDetail != null && !orderDetail.getSellFlag()) {
				objHotelRoomTemp = orderDetail.getHotelRoom();
				// enter cost must be less than or equal room's original
				// cost
				if (objHotelRoomTemp.getOneNightCost() >= sellInternDto
						.getOneNightCost()) {
					// if
					// (this.orderDetailRepository.setSellFlagTrue(orderDetail
					// .getId()) == 1) {
					orderDetail.setSellFlag(true);
					this.orderDetailRepository.save(orderDetail);
					// save hotel room data
					objHotelRoom = new HotelRoom();
					objHotel = objHotelRoomTemp.getHotel();
					objHotelRoom.setRoomType(objHotelRoomTemp.getRoomType());
					objHotelRoom.setCheckInTime(objHotelRoomTemp
							.getCheckInTime());
					objHotelRoom.setCheckOutTime(objHotelRoomTemp
							.getCheckOutTime());
					objHotelRoom.setCreatedBy(loginUser.getId());
					objHotelRoom.setHotel(objHotel);
					objHotelRoom.setIsFullBooked(false);
					objHotelRoom.setIsFullSuite(true);
					objHotelRoom
							.setNoOfAdults(objHotelRoomTemp.getNoOfAdults());
					objHotelRoom.setNoOfChildren(objHotelRoomTemp
							.getNoOfChildren());
					objHotelRoom.setNoOfRooms(objHotelRoomTemp.getNoOfRooms());
					objHotelRoom.setOneNightCost(sellInternDto
							.getOneNightCost());
					objHotelRoom.setRoomDesc(objHotelRoomTemp.getRoomDesc());
					objHotelRoom.setStartDate(orderDetail.getStartDate());
					objHotelRoom.setEndDate(orderDetail.getEndDate());
					this.hotelRoomRepository.save(objHotelRoom);
					// set reference of amenity
					this.roomAmenityRepository.copyAmenities(
							objHotelRoomTemp.getId(), objHotelRoom.getId());
					// set reference of photos
					this.roomPhotoRepository.copyPhotos(
							objHotelRoomTemp.getId(), objHotelRoom.getId());
					// save data in sell table
					objSell = new Sell();
					objSell.setCreatedBy(loginUser.getId());
					objSell.setHotelRoom(objHotelRoom);
					objSell.setSellBy(loginUser);
					objSell.setSellFor(tempUser);
					Organization objOrganization = this.organizationRepository
							.getPaymentType("Paypal");
					objSell.setOrganization(objOrganization);
					this.sellRepository.save(objSell);
					// save data in ordered sell table
					objOrderedSell = new OrderedSell();
					objOrderedSell.setCreatedBy(loginUser.getId());
					objOrderedSell.setOrderDetail(orderDetail);
					objOrderedSell.setSell(objSell);
					objOrderedSell.setActive(true);
					this.orderedSellRepository.save(objOrderedSell);
					// TODO send email for resell
					SellRoomMailData sellRoomMailData = new SellRoomMailData(
							objHotel.getHotelName(),
							objHotelRoom.getStartDate(),
							objHotelRoom.getEndDate(), tempUser.getUserName(),
							tempUser.getEmail(), objHotelRoom.getOneNightCost());
					try {
						this.sendMailService
								.sendSellInternRoomMail(sellRoomMailData);
					} catch (MessagingException e) {
						logger.error("sellRoomInternal", e);
					} catch (IOException e) {
						logger.error("sellRoomInternal", e);
					} catch (TemplateException e) {
						logger.error("sellRoomInternal", e);
					}
					// } else {
					// throw new NotFoundException(
					// NotFound.RoomNotFoundForSell);
					// }
				} else {
					objValidationErrorDTO = new ValidationErrorDTO();
					objValidationErrorDTO.addFieldError("oneNightCost",
							"Cost must be less than or equal"
									+ objHotelRoomTemp.getOneNightCost());
					throw new FieldErrorException(objValidationErrorDTO);
				}
			} else {
				throw new NotFoundException(NotFound.RoomNotFoundForSell);
			}
		} finally {

		}
	}

	@Override
	@Transactional
	public List<ConfirmedRoomDto> getConfirmBookedRooms(User loginUser,
			String assignerUserEmail) {
		User assignerUser = null;
		try {
			if (loginUser != null) {
				if (null != assignerUserEmail) {
					assignerUser = this.delegateUserRepository
							.getAssignerFromDelegate(loginUser.getEmail(),
									assignerUserEmail);
					if (null != assignerUser) {
						return this.orderDetailRepository
								.getConfirmBookedRooms(assignerUser.getEmail());
					} else {
						throw new NotFoundException(NotFound.NotAssigner);
					}
				} else {
					return this.orderDetailRepository
							.getConfirmBookedRooms(loginUser.getEmail());
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@Override
	@Transactional
	public List<String> getCityName() {
		return this.hotelMasterRepository.getCities();
	}
	
	@Override
	@Transactional
	public String getStateName(String cityName) {
		City objCity = this.cityRepository.getCityByName(cityName);
		return this.stateRepository.getState(objCity);
	}

	@Override
	@Transactional
	public HotelRoomData getHotelData(Long roomId)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// Response objResponse = null;
		List<String> objRoomPhotoList = null;
		List<String> objRoomDetails = null;
		List<BookHistoryDto> objBookHistory = null;
		HotelRoomData objHotelRoomData = null;
		Long hotelId = null;
		try {
			objHotelRoomData = this.hotelRoomRepository
					.getHotelRoomData(roomId);
			if (null != objHotelRoomData) {
				objRoomPhotoList = this.roomPhotoRepository
						.getRoomPhotosPath(roomId);

				objRoomDetails = this.roomAmenityRepository
						.getRoomAmenities(roomId);
				hotelId = this.hotelRoomRepository.getHotelFromRoomId(roomId);
				if(hotelId != null)
				{
					objBookHistory = this.orderDetailRepository.getBookHistoryId(hotelId);
				}
				objHotelRoomData.setRoomDetails(objRoomDetails);
				objHotelRoomData.setRoomPhotos(objRoomPhotoList);
				objHotelRoomData.setBookHistory(objBookHistory); 
				return objHotelRoomData;
				// objResponse = new Response("Success", objHotelRoomData,
				// false);
			} else {
				throw new NotFoundException(NotFound.DataNotFound);
			}
		} finally {

		}
	}

	@Override
	@Transactional
	public Map<String, Object> getRating(User loginUser, Long orderId,
			Long roomId) {
		Map<String, Object> objHotelRateList = null;
		Map<String, Object> objUserRateList = null;
		Map<String, Object> responseData = null;
		Rating objRating = null;
		HotelRoom objHotelRoom = null;
		Order objOrder = null;
		try {
			if (this.orderDetailRepository.isRoomOrderExist(
					loginUser.getEmail(), roomId, orderId)) {
				objHotelRateList = this.ratingRepository.getRating(orderId,
						roomId, true);
				// if data not found add to database
				if (objHotelRateList == null) {
					objRating = new Rating();
					objHotelRoom = new HotelRoom();
					objHotelRoom.setId(roomId);
					objRating.setHotelRoom(objHotelRoom);
					objOrder = new Order();
					objOrder.setId(orderId);
					objRating.setOrder(objOrder);
					objRating.setComment(Constant.STRING_EMPTY);
					objRating.setIsHotelRating(true);
					objRating.setRating(0);
					objRating.setCreatedBy(loginUser.getId());
					this.ratingRepository.save(objRating);
					objHotelRateList = new HashMap<String, Object>();
					objHotelRateList.put("rate", objRating.getRating());
					objHotelRateList.put("comment", objRating.getComment());
				}
				objUserRateList = this.ratingRepository.getRating(orderId,
						roomId, false);
				// if data not found add to database
				if (objUserRateList == null) {
					objRating = new Rating();
					objHotelRoom = new HotelRoom();
					objHotelRoom.setId(roomId);
					objRating.setHotelRoom(objHotelRoom);
					objOrder = new Order();
					objOrder.setId(orderId);
					objRating.setOrder(objOrder);
					objRating.setComment(Constant.STRING_EMPTY);
					objRating.setIsHotelRating(false);
					objRating.setRating(0);
					objRating.setCreatedBy(loginUser.getId());
					this.ratingRepository.save(objRating);
					objUserRateList = new HashMap<String, Object>();
					objUserRateList.put("rate", objRating.getRating());
					objUserRateList.put("comment", objRating.getComment());
				}
				responseData = new HashMap<String, Object>();
				responseData.put("hotelRating", objHotelRateList);
				responseData.put("userRating", objUserRateList);
				return responseData;
			} else {
				throw new NotFoundException(NotFound.DataNotFound);
			}
		} finally {

		}
	}

	@Override
	@Transactional
	public Map<String, Object> updateRating(Long orderId, Long roomId,
			RatingDto rating, User loginUser, Boolean isHotelRating) {
		Long ratingId = null;
		Rating objRating = null;
		HotelRoom objHotelRoom = null;
		Order objOrder = null;
		Map<String, Object> responseData = null;
		try {
			if (this.orderDetailRepository.isRoomOrderExist(
					loginUser.getEmail(), roomId, orderId)) {
				ratingId = this.ratingRepository
						.getRatingIdByOrderRoomAndEmail(orderId, roomId,
								isHotelRating);
				if (ratingId != null) {
					this.ratingRepository.update(
							ratingId,
							rating.getRate(),
							StringUtils.hasText(rating.getComment()) ? rating
									.getComment() : Constant.STRING_EMPTY);
					responseData = new HashMap<String, Object>();
					responseData.put("message", "Data Updated Successfully");
					return responseData;
				} else {
					objRating = new Rating();
					objHotelRoom = new HotelRoom();
					objHotelRoom.setId(roomId);
					objRating.setHotelRoom(objHotelRoom);
					objOrder = new Order();
					objOrder.setId(orderId);
					objRating.setOrder(objOrder);
					objRating.setComment(rating.getComment());
					objRating.setIsHotelRating(isHotelRating);
					objRating.setRating(rating.getRate());
					objRating.setCreatedBy(loginUser.getId());
					this.ratingRepository.save(objRating);
					responseData = new HashMap<String, Object>();
					responseData.put("message", "Data Updated Successfully");
					return responseData;
				}
			} else {
				throw new NotFoundException(NotFound.DataNotFound);
			}
		} finally {

		}
	}

	@Transactional(rollbackFor = { Throwable.class }, isolation = Isolation.READ_COMMITTED)
	@Override
	public List<PendingTransactionDto> getPendingTransaction(User user) {
		if (user != null) {
			List<PendingTransactionDto> pendingTransactions = this.paymentToSellerRepository
					.getPendingTransactionHistory(user.getUserName());
			for (PendingTransactionDto pendingTransaction : pendingTransactions) {
				pendingTransaction.setImages(this.roomPhotoRepository
						.getRoomPhotosPath(pendingTransaction.getRoomId()));
			}
			return pendingTransactions;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Override
	@Transactional
	public List<SellHistoryDto> getSellHistory(User loginUser,
			String assignerUserEmail) {
		if (loginUser != null) {
			if (StringUtils.hasText(assignerUserEmail)) {
				User assignerUser = this.delegateUserRepository
						.getAssignerFromDelegate(loginUser.getEmail(),
								assignerUserEmail);
				if (null != assignerUser) {

					List<SellHistoryDto> sellHistory = this.sellRepository
							.getSellHistory(assignerUser.getEmail());
					for (SellHistoryDto sellHistoryDto : sellHistory) {
						List<String> images = this.roomPhotoRepository
								.getRoomPhotosPath(sellHistoryDto.getRoomId());
						sellHistoryDto.setImages(images);
					}
					return sellHistory;
				} else {
					throw new NotFoundException(NotFound.NotAssigner);
				}
			} else {
				List<SellHistoryDto> sellHistory = this.sellRepository
						.getSellHistory(loginUser.getEmail());
				for (SellHistoryDto sellHistoryDto : sellHistory) {
					List<String> images = this.roomPhotoRepository
							.getRoomPhotosPath(sellHistoryDto.getRoomId());
					sellHistoryDto.setImages(images);
				}
				return sellHistory;
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Override
	@Transactional
	public List<BookHistoryDto> getBookHistory(User loginUser,
			String assignerUserEmail) {
		User assignerUser = null;
		if (loginUser != null) {
			if (StringUtils.hasText(assignerUserEmail)) {
				assignerUser = this.delegateUserRepository
						.getAssignerFromDelegate(loginUser.getEmail(),
								assignerUserEmail);
				if (null != assignerUser) {
					List<BookHistoryDto> bookHistory = this.orderDetailRepository
							.getBookHistory(assignerUser.getEmail());
					for (BookHistoryDto bookHistoryDto : bookHistory) {
						List<String> images = this.roomPhotoRepository
								.getRoomPhotosPath(bookHistoryDto.getRoomId());
						bookHistoryDto.setImages(images);
					}
					return bookHistory;
				} else {
					throw new NotFoundException(NotFound.NotAssigner);
				}
			} else {
				List<BookHistoryDto> bookHistory = this.orderDetailRepository
						.getBookHistory(loginUser.getEmail());
				for (BookHistoryDto bookHistoryDto : bookHistory) {
					List<String> images = this.roomPhotoRepository
							.getRoomPhotosPath(bookHistoryDto.getRoomId());
					bookHistoryDto.setImages(images);
				}
				return bookHistory;
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	// @Override
	// @Transactional
	// public OrderHistoryDto getOrderHistory(User loginUser,
	// String assignerUserEmail) {
	// List<BookHistoryDto> bookHistoryList = null;
	// List<SellHistoryDto> sellHistoryList = null;
	// User assignerUser = null;
	// try {
	// if (loginUser != null) {
	// if (null != assignerUserEmail) {
	// assignerUser = this.delegateUserRepository
	// .getAssignerFromDelegate(loginUser.getEmail(),
	// assignerUserEmail);
	// if (null != assignerUser) {
	// bookHistoryList = this.orderDetailRepository
	// .getBookHistory(assignerUser.getEmail());
	// sellHistoryList = this.sellRepository
	// .getSellHistory(assignerUser.getEmail());
	// return new OrderHistoryDto(bookHistoryList,
	// sellHistoryList);
	// } else {
	// throw new NotFoundException(NotFound.NotAssigner);
	// }
	// } else {
	// bookHistoryList = this.orderDetailRepository
	// .getBookHistory(loginUser.getEmail());
	// sellHistoryList = this.sellRepository
	// .getSellHistory(loginUser.getEmail());
	// return new OrderHistoryDto(bookHistoryList, sellHistoryList);
	// }
	// } else {
	// throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
	// }
	// } finally {
	//
	// }
	// }

	/*@Transactional
	@Override
	public SellExternResponse sellRoomExtern(ExternSellDto externSellDto,
			User loginUser, String assignerUserEmail) {

		User assignerUser = null;
		User tempUser = null;
		ProfilePaypal objPaypal = null;
		Boolean isUserFound = false;
		ProfileBankAccount objBankAccount = null;
		Boolean hasAccountSet = false;
		FieldErrorDTO objFieldErrorDTO = null;
		Date checkInDate = null;
		Date checkOutDate = null;
		Boolean hasError = Boolean.FALSE;
		List<FieldErrorDTO> fieldErrorDTOs = null;
		ValidationErrorDTO objValidationErrorDTO = null;
		TempSellConfirm tempSellConfirm = null;
		Organization objOrganization = null;
		City objCity = null;
		try {

			fieldErrorDTOs = new ArrayList<FieldErrorDTO>();

			// todayDate = this.commonService.currentDateWithoutTime();
			// checkInDate =
			DateUtil.getDate(externSellDto.getCheckInDate(),
					Constant.DATE_FORMAT);

			checkOutDate = DateUtil.getDate(externSellDto.getCheckOutDate(),
					Constant.DATE_FORMAT);

			objOrganization = this.organizationRepository
					.getPaymentType(externSellDto.getPaymentType());
			if (objOrganization == null) {
				objFieldErrorDTO = new FieldErrorDTO("paymentType",
						"Not valid payment type");
				fieldErrorDTOs.add(objFieldErrorDTO);
				hasError = Boolean.TRUE;
			}

			objCity = this.cityRepository
					.getCityByName(externSellDto.getCity());
			if (objCity == null) {
				objFieldErrorDTO = new FieldErrorDTO("city",
						"City not found in list");
				fieldErrorDTOs.add(objFieldErrorDTO);
				hasError = Boolean.TRUE;
			}

			if (!hasError) {
				if (loginUser != null) {
					if (null != assignerUserEmail) {
						assignerUser = this.delegateUserRepository
								.getAssignerFromDelegate(loginUser.getEmail(),
										assignerUserEmail);
						if (null != assignerUser) {
							tempUser = assignerUser;
							isUserFound = true;
						} else {
							throw new NotAssignerException();
						}
					} else {
						tempUser = loginUser;
						isUserFound = true;
					}
					if (isUserFound) {
						if ("paypal".equalsIgnoreCase(externSellDto
								.getPaymentType())) {
							objPaypal = this.userPaymentInfoRepository
									.getPaypalIdByEmail(tempUser.getEmail());
							if (null != objPaypal
									&& StringUtils.hasText(objPaypal
											.getPaypalId())) {
								hasAccountSet = true;
							}

							else {
								throw new MessageException(
										MessageExceptionErroCode.PAYPALIDNOTSET);
							}
						} else if ("bank".equalsIgnoreCase(externSellDto
								.getPaymentType())) {
							objBankAccount = this.userPaymentInfoRepository
									.getBankInfoByEmail(tempUser.getEmail());
							if (null != objBankAccount
									&& StringUtils.hasText(objBankAccount
											.getBankAccountNumber())
									&& StringUtils.hasText(objBankAccount
											.getBankRoutingNumber())) {
								hasAccountSet = true;
							} else {
								throw new MessageException(
										MessageExceptionErroCode.BANKACCOUNTNOTSET);
							}
						}
					}
					if (hasAccountSet) {
						tempSellConfirm = new TempSellConfirm();
						tempSellConfirm.setActive(true);
						tempSellConfirm.setAdultGuests(externSellDto
								.getAdultGuests());
						tempSellConfirm
								.setBookFrom(externSellDto.getBookFrom());
						tempSellConfirm.setCheckInDate(checkInDate);
						tempSellConfirm.setCheckOutDate(checkOutDate);
						tempSellConfirm.setChildGuests(externSellDto
								.getChildGuests());
						tempSellConfirm.setCity(objCity);
						tempSellConfirm.setConfirmationCode(externSellDto
								.getConfirmationCode());
						tempSellConfirm.setConfirmationCodeSecond(externSellDto
								.getConfirmationCodeSecond());
						tempSellConfirm.setCreatedBy(loginUser.getId());
						tempSellConfirm.setEmail(externSellDto.getEmail());
						tempSellConfirm.setFirstName(externSellDto
								.getFirstName());
						tempSellConfirm.setHotelName(externSellDto
								.getHotelName());
						tempSellConfirm.setHotelAddress(externSellDto
								.getHotelAddress());
						tempSellConfirm.setIsConfirmationMailReived(false);
						tempSellConfirm.setIsConfirmed(false);
						tempSellConfirm.setIsRemoved(false);
						tempSellConfirm
								.setLastName(externSellDto.getLastName());
						tempSellConfirm.setOrganization(objOrganization);
						tempSellConfirm.setPhoneNumber(externSellDto
								.getPhoneNumber()); //
						tempSellConfirm.setPremiarPrice(ex);
						tempSellConfirm.setPrice(externSellDto.getPrice());
						tempSellConfirm
								.setRoomType(externSellDto.getRoomType());
						tempSellConfirm.setSellBy(loginUser);
						tempSellConfirm.setSellFor(tempUser);
						this.tempSellRepository.save(tempSellConfirm);

						return new SellExternResponse(tempUser.getEmail(),
								tempUser.getFirstName(),
								tempUser.getLastName(),
								tempSellConfirm.getHotelName(),
								tempSellConfirm.getHotelAddress(),
								externSellDto.getCheckInDate(),
								externSellDto.getCheckOutDate(),
								tempSellConfirm.getPrice(),
								Constant.CONTACT_EMAIL);

					}
				} else {
					throw new AuthorizationException(

					Constant.ERROR_LOGIN_MESSAGE);
				}

			} else {
				objValidationErrorDTO = new ValidationErrorDTO();
				objValidationErrorDTO.setFieldErrors(fieldErrorDTOs);
				throw new FieldErrorException(objValidationErrorDTO);
			}
		} finally {

		}
	}// return responseData; return null; }
*/
	private User getAssigner(User loginUser, String assignerUserEmail) {
		User assignerUser = this.delegateUserRepository
				.getAssignerFromDelegate(loginUser.getEmail(),
						assignerUserEmail);
		if (assignerUser != null) {
			return assignerUser;
		} else {
			throw new NotFoundException(NotFound.NotAssigner);
		}
	}

	@Transactional
	@Override
	public SellExternResponse sellRoomExtern(ExternSellDto externSellDto,
			User loginUser, String assignerUserEmail) {
		User assignerUser = null;
		User tempUser = null;
		Date checkInDate = null;
		Date checkOutDate = null;
		TempSellConfirm tempSellConfirm = null;
		HotelMaster objHotelMaster = null;
		Organization objOrganization = null;
		try {

			checkInDate = DateUtil.getDate(externSellDto.getCheckInDate(),
					Constant.DATE_FORMAT);

			checkOutDate = DateUtil.getDate(externSellDto.getCheckOutDate(),
					Constant.DATE_FORMAT);

			if (loginUser != null) {
				objHotelMaster = this.hotelMasterRepository
						.findOne(externSellDto.getHotelId());
				if (objHotelMaster != null) {
					if (StringUtils.hasText(assignerUserEmail)) {
						assignerUser = this.getAssigner(loginUser,
								assignerUserEmail);
						tempUser = assignerUser;
					} else {
						tempUser = loginUser;
					}
					objOrganization = this.organizationRepository
							.getPaymentType(externSellDto.getPaymentType());
					tempSellConfirm = new TempSellConfirm();
					tempSellConfirm.setActive(true);
					tempSellConfirm.setAdultGuests(externSellDto.getAdult());
					tempSellConfirm
							.setChildGuests(externSellDto.getChilderen());
					tempSellConfirm.setCheckInDate(checkInDate);
					tempSellConfirm.setCheckOutDate(checkOutDate);
					tempSellConfirm.setFirstName(externSellDto.getFirstName());
					tempSellConfirm.setLastName(externSellDto.getLastName());
					tempSellConfirm.setEmail(externSellDto.getEmail());
					tempSellConfirm.setConfirmationCode(externSellDto
							.getConfirmationCode());
					tempSellConfirm.setConfirmationCodeSecond(externSellDto
							.getConfirmationCodeSecond());
					tempSellConfirm.setBookFrom(externSellDto.getBookFrom());
					tempSellConfirm.setCreatedBy(loginUser.getId());
					tempSellConfirm.setIsConfirmationMailReived(false);
					tempSellConfirm.setIsConfirmed(false);
					tempSellConfirm.setIsRemoved(false);
					tempSellConfirm.setPrice(externSellDto.getPrice());
					tempSellConfirm.setRoomType(externSellDto.getRoomType());
					tempSellConfirm.setSellBy(loginUser);
					tempSellConfirm.setSellFor(tempUser);
					tempSellConfirm.setHotel(objHotelMaster);
					tempSellConfirm.setOrganization(objOrganization);
					tempSellConfirm.setPaymentStatus(externSellDto.getPaymentStatus());
					this.tempSellRepository.save(tempSellConfirm);

					// TODO send mail
					SellRoomMailData sellRoomMailData = new SellRoomMailData(
							objHotelMaster.getHotelName(),
							tempSellConfirm.getCheckInDate(),
							tempSellConfirm.getCheckOutDate(),
							tempUser.getUserName(), tempUser.getEmail(),
							tempSellConfirm.getPrice());
					try {
						this.sendMailService
								.sendSellExternRoomMail(sellRoomMailData);
					} catch (MessagingException e) {
						logger.error("sellRoomInternal", e);
					} catch (IOException e) {
						logger.error("sellRoomInternal", e);
					} catch (TemplateException e) {
						logger.error("sellRoomInternal", e);
					}

					return new SellExternResponse(tempUser.getEmail(),
							tempUser.getUserName(),
							objHotelMaster.getHotelName(),
							objHotelMaster.getAddress(),
							externSellDto.getCheckInDate(),
							externSellDto.getCheckOutDate(),
							tempSellConfirm.getPrice(), Constant.CONTACT_EMAIL);

				} else {
					// hotel not found exception
					throw new NotFoundException(NotFound.HotelNotFound);
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
		// return responseData;
	}

	private boolean isRoomAvailable(Long roomId, Date startDate, Date endDate) {
		Long count = null;
		count = this.hotelRoomRepository.getAvailableRoomFromHotelRoom(roomId,
				startDate, endDate);
		if (count > 0) {
			count = this.hotelRoomRepository
					.getAvailableRoomFromCartDetail(roomId);
			if (count == 0) {
				count = this.hotelRoomRepository.getAvailableFromOrderDetail(
						roomId, startDate, endDate);
				if (count == 0)
					return true;
			}
		}
		return false;
	}

	private void validAddToCartData(HotelRoom hotelRoom, RoomBook roomBook) {
		Date currentDateWithoutTime = null;
		Date objStartDate = null;
		Date objEndDate = null;
		ValidationErrorDTO objValidationErrorDTO = null;
		boolean hasError = false;
		try {
			if (!hotelRoom.getIsFullSuite()) {
				objValidationErrorDTO = new ValidationErrorDTO();
				if (!StringUtils.hasText(roomBook.getCheckInDate())) {
					objValidationErrorDTO.addFieldError("checkInDate",
							"Please Enter CheckIn Date");
					hasError = true;
				} else {
					objStartDate = DateUtil.getDate(roomBook.getCheckInDate(),
							Constant.DATE_FORMAT);
					if (objStartDate != null) {
						currentDateWithoutTime = DateUtil
								.currentDateWithoutTime();
						if (!objStartDate.equals(currentDateWithoutTime)
								&& objStartDate.before(currentDateWithoutTime)) {
							objValidationErrorDTO
									.addFieldError("checkInDate",
											"Date Must Be Greate or equal Today's Date");
							hasError = true;
						}
					} else {
						objValidationErrorDTO.addFieldError("checkInDate",
								"Date must be in dd/MM/yyyy in format");
						hasError = true;
					}
				}
				if (!StringUtils.hasText(roomBook.getCheckOutDate())) {
					objValidationErrorDTO.addFieldError("checkOutDate",
							"Please Enter CheckOut Date");
					hasError = true;
				} else {
					objEndDate = DateUtil.getDate(roomBook.getCheckOutDate(),
							Constant.DATE_FORMAT);
					if (objEndDate != null) {
						if (objStartDate != null
								&& (objEndDate.before(objStartDate) || objEndDate
										.equals(objStartDate))) {
							objValidationErrorDTO.addFieldError("checkOutDate",
									"Date must be greater than CheckIn date");
							hasError = true;
						}
					} else {
						objValidationErrorDTO.addFieldError("checkOutDate",
								"Date must be in dd/MM/yyyy in format");
						hasError = true;
					}
				}
				if (hasError) {
					throw new FieldErrorException(objValidationErrorDTO);
				}
			}
		} finally {
			currentDateWithoutTime = null;
			objStartDate = null;
			objEndDate = null;
		}
	}

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
					}
				}
			} else {
				objCart = new Cart();
				objCart.createCart(loginUser, objCurrentDate);
				this.cartRepository.saveAndFlush(objCart);
			}
		} finally {
			objCurrentDate = null;
		}
		return objCart;
	}

	// TODO change booking
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Throwable.class })
	@Override
	public void addToCart(User loginUser, RoomBook roomBook)
			throws IllegalAccessException, InvocationTargetException {
		Cart objCart = null;
		CartDetail objCartDetail = null;
		Date startDate = null;
		Date endDate = null;
		HotelRoom objHotelRoom = null;
		// List<CartRoomDto> cartRoomData = null;
		// Map<String, Object> resposeData = null;
		Double cartCost = 0D;
		Double totalCost = 0D;
		try {
			if (loginUser != null) {
				objHotelRoom = this.hotelRoomRepository.getHotelRoom(roomBook
						.getRoomId());

				if (objHotelRoom != null) {
					// validate cart data
					validAddToCartData(objHotelRoom, roomBook);
					if (objHotelRoom.getIsFullSuite()) {
						startDate = objHotelRoom.getStartDate();
						endDate = objHotelRoom.getEndDate();
					} else {
						startDate = DateUtil.getDate(roomBook.getCheckInDate(),
								Constant.DATE_FORMAT);
						endDate = DateUtil.getDate(roomBook.getCheckOutDate(),
								Constant.DATE_FORMAT);
					}

					// check hotel room is available
					if (isRoomAvailable(objHotelRoom.getId(), startDate,
							endDate)) {

						// get login user cart data
						objCart = getCart(loginUser);

						// check room cost and cart cost is less
						// than transaction limit

						cartCost = this.cartRepository.getCartAmount(loginUser
								.getEmail());
						totalCost += objHotelRoom.getRoomCost(startDate,
								endDate);

						if (cartCost != null)
							totalCost += cartCost;

						if (totalCost < Constant.TRANSCATION_LIMIT) {

							// add room to cartDetail table
							objCartDetail = new CartDetail();
							objCartDetail.setCartDetail(objCart, loginUser,
									startDate, endDate, objHotelRoom);
							this.cartDetailRepository.save(objCartDetail);
							objHotelRoom.setBookAt(DateUtil.getCurrentDate());
							this.hotelRoomRepository.save(objHotelRoom);
						} else {
							throw new CostLimitException();
						}
					} else {
						throw new NotFoundException(NotFound.RoomNotAvailable);
					}
				} else {
					throw new NotFoundException(NotFound.RoomNotFound);
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
			objCart = null;
			objCartDetail = null;
			startDate = null;
			endDate = null;
			objHotelRoom = null;
			// cartRoomData = null;
			// resposeData = null;
			cartCost = 0D;
			totalCost = 0D;
		}
	}

	// @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {
	// Throwable.class })
	// @Override
	// public Map<String, Object> addToCart(User loginUser, RoomBook roomBook)
	// throws IllegalAccessException, InvocationTargetException, Exception {
	// Cart objCart = null;
	// Date objCurrentDate = null;
	// CartDetail objCartDetail = null;
	// Date startDate = null;
	// Date endDate = null;
	// HotelRoom objHotelRoom = null;
	// List<CartRoomDto> cartRoomData = null;
	// Map<String, Object> resposeData = null;
	// Double cartCost = 0D;
	// Double totalCost = 0D;
	// try {
	// if (loginUser != null) {
	// objCurrentDate = new Date();
	// startDate = DateUtil.getDate(roomBook.getCheckInDate(),
	// Constant.DATE_FORMAT);
	// endDate = DateUtil.getDate(roomBook.getCheckOutDate(),
	// Constant.DATE_FORMAT);
	//
	// objHotelRoom = this.hotelRoomRepository.getHotelRoom(roomBook
	// .getRoomId());
	// if (objHotelRoom != null) {
	// // check hotel room is available
	// if (isRoomAvailable(objHotelRoom.getId(), startDate,
	// endDate)) {
	// // get login user cart data
	// objCart = this.cartRepository.getCartId(loginUser
	// .getEmail());
	// if (objCart != null) {
	// if (objCart.getIsLocked()) {
	// // check time is not expire for unlock
	// if (objCart.getUnlockAt().after(objCurrentDate)) {
	// throw new CartUnderProcessException();
	// // if time expire unlock cart
	// } else {
	// objCart.setIsLocked(false);
	// // upate
	// this.cartRepository.save(objCart);
	//
	// // check room cost and cart cost is less
	// // than transaction limit
	//
	// cartCost = this.cartRepository
	// .getCartAmount(objCart.getId(),
	// loginUser.getEmail());
	// totalCost += objHotelRoom.getRoomCost(
	// startDate, endDate);
	//
	// if (cartCost != null)
	// totalCost += cartCost;
	//
	// if (totalCost < Constant.TRANSCATION_LIMIT) {
	//
	// // add room to cartDetail table
	// objCartDetail = new CartDetail();
	// objCartDetail.setCart(objCart);
	// // objCartDetail.setIsUnderProcess(false);
	// objCartDetail.setCreatedBy(loginUser
	// .getId());
	// objCartDetail.setEndDate(endDate);
	// objCartDetail
	// .setHotelRoom(objHotelRoom);
	// objCartDetail.setStartDate(startDate);
	// objCartDetail
	// .setReleaseAt(DateUtil
	// .getAddedDate(
	// 0,
	// Constant.RELEASE_MINUTE,
	// 0));
	// this.cartDetailRepository
	// .save(objCartDetail);
	//
	// cartRoomData = this.cartDetailRepository
	// .getCartRoomDetail(loginUser
	// .getEmail());
	//
	// resposeData = new HashMap<String, Object>();
	// resposeData.put("cartId",
	// objCart.getId());
	// resposeData.put("roomData",
	// cartRoomData);
	// return resposeData;
	// } else {
	// throw new CostLimitException();
	// }
	// }
	// // process if cart is unlocked
	// } else {
	// totalCost += objHotelRoom.getRoomCost(
	// startDate, endDate);
	// cartCost = this.cartRepository.getCartAmount(
	// objCart.getId(), loginUser.getEmail());
	//
	// if (cartCost != null)
	// totalCost += cartCost;
	//
	// if (totalCost < Constant.TRANSCATION_LIMIT) {
	//
	// // add room to cartDetail table
	// objCartDetail = new CartDetail();
	// objCartDetail.setCart(objCart);
	// objCartDetail.setCreatedBy(loginUser
	// .getId());
	// objCartDetail.setEndDate(endDate);
	// // objCartDetail.setIsUnderProcess(false);
	// objCartDetail.setHotelRoom(objHotelRoom);
	// objCartDetail.setStartDate(startDate);
	// objCartDetail
	// .setReleaseAt(DateUtil
	// .getAddedDate(
	// 0,
	// Constant.RELEASE_MINUTE,
	// 0));
	// this.cartDetailRepository
	// .save(objCartDetail);
	// // get cart data from database
	// cartRoomData = this.cartDetailRepository
	// .getCartRoomDetail(loginUser
	// .getEmail());
	//
	// resposeData = new HashMap<String, Object>();
	// resposeData.put("cartId", objCart.getId());
	// resposeData.put("roomData", cartRoomData);
	// return resposeData;
	// } else {
	// throw new CostLimitException();
	// }
	// }
	// // create cart
	// } else {
	// objCart = new Cart();
	// objCart.setAddBy(loginUser);
	// objCart.setCreatedBy(loginUser.getId());
	// objCart.setIsCompleted(false);
	// objCart.setIsLocked(false);
	// objCart.setUnlockAt(objCurrentDate);
	// this.cartRepository.save(objCart);
	// // add room to cartDetail table
	// objCartDetail = new CartDetail();
	// objCartDetail.setCart(objCart);
	// // objCartDetail.setIsUnderProcess(false);
	// objCartDetail.setCreatedBy(loginUser.getId());
	// objCartDetail.setEndDate(endDate);
	// objCartDetail.setHotelRoom(objHotelRoom);
	// objCartDetail.setStartDate(startDate);
	// objCartDetail.setReleaseAt(DateUtil.getAddedDate(0,
	// Constant.RELEASE_MINUTE, 0));
	// this.cartDetailRepository.save(objCartDetail);
	// // get cart data from database
	// cartRoomData = this.cartDetailRepository
	// .getCartRoomDetail(loginUser.getEmail());
	//
	// resposeData = new HashMap<String, Object>();
	// resposeData.put("cartId", objCart.getId());
	// resposeData.put("roomData", cartRoomData);
	// return resposeData;
	// }
	// } else {
	// throw new RoomNotAvailableException();
	// }
	// } else {
	// throw new RoomNotFoundException();
	// }
	// } else {
	// throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
	// }
	// } finally {
	//
	// }
	// }

	private Cart getCartByRoomId(User loginUser, Long roomId) {
		Cart objCart = null;
		Date objCurrentDate = null;
		try {
			objCart = this.cartRepository.getCartRoomLockRleaseStatus(
					loginUser.getEmail(), roomId);
			if (objCart != null) {
				if (objCart.getIsLocked()) {
					objCurrentDate = new Date();
					// check time is not expire for unlock
					if (objCart.getUnlockAt().after(objCurrentDate)) {
						throw new NotFoundException(NotFound.CartUnderProcess);
						// if time expire unlock cart
					} else {
						objCart.setIsLocked(false);
						// update
						this.cartRepository.save(objCart);
					}
				}
			}
		} finally {

		}
		return objCart;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Throwable.class })
	public void removeFromCart(User loginUser, Long roomId)
			throws IllegalAccessException, InvocationTargetException {
		Cart objCart = null;
		// List<CartRoomDto> cartRoomData = null;
		// Map<String, Object> responseData = null;
		try {
			if (loginUser != null) {
				objCart = getCartByRoomId(loginUser, roomId);
				if (objCart != null) {

					// remove room from cart detail table
					this.cartDetailRepository.deleteByRoomIdCartId(roomId,
							objCart.getId());
					// get cart room detail
					// cartRoomData = this.cartDetailRepository
					// .getCartRoomDetail(loginUser.getEmail());
					// add cart id and cart room detail to response
					// responseData = new HashMap<String, Object>();
					// responseData.put("roomData", cartRoomData);
					// responseData.put("cartId", objCart.getId());
					// return responseData;
				} else {
					throw new EmptyCartException("No room found for remove");
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
			objCart = null;
			// cartRoomData = null;
			// responseData = null;
		}
	}

	@Override
	@Transactional
	public Double getCartAmount(User loginUser) {
		return this.cartRepository.getCartAmount(loginUser.getEmail());
	}

	@Override
	@Transactional
	public List<CartRoomDto> getCartData(User loginUser) {
		List<CartRoomDto> cartRoomData = null;
		// List<Long> cartIds = null;
		// Long cartId = null;
		// Cart objCart = null;
		// Date objCurrentDate = null;
		// Boolean isNewCartNeed = Boolean.FALSE;
		// Map<String, Object> responseData = null;
		List<String> roomPhotos = null;
		try {
			if (loginUser != null) {
				cartRoomData = this.cartDetailRepository
						.getCartRoomDetail(loginUser.getEmail());
				for (CartRoomDto cartRoom : cartRoomData) {
					roomPhotos = this.roomPhotoRepository
							.getRoomPhotosPath(cartRoom.getRoomId());
					cartRoom.setImages(roomPhotos);
				}
				return cartRoomData;
				// cartIds = this.cartRepository.getActiveCartIds(loginUser
				// .getEmail());
				// if (cartIds != null && cartIds.size() > 0) {
				// if (cartIds.size() == 1) {
				// cartId = cartIds.get(0);
				// cartRoomData = this.cartDetailRepository
				// .getCartRoomDetail(loginUser.getEmail());
				// for (CartRoomDto cartRoom : cartRoomData) {
				// roomPhotos = this.roomPhotoRepository
				// .getRoomPhotosPath(cartRoom.getRoomId());
				// cartRoom.setImages(roomPhotos);
				// }
				// } else {
				// this.cartRepository.deActiveAllCart(loginUser
				// .getEmail());
				// isNewCartNeed = Boolean.TRUE;
				// }
				// } else {
				// isNewCartNeed = Boolean.TRUE;
				// }
				//
				// if (isNewCartNeed) {
				// cartRoomData = new ArrayList<CartRoomDto>();
				// objCurrentDate = new Date();
				// objCart = new Cart();
				// objCart.createCart(loginUser, objCurrentDate);
				// this.cartRepository.saveAndFlush(objCart);
				// cartId = objCart.getId();
				// }
				// // add cart id and cart room detail to response
				// responseData = new HashMap<String, Object>();
				// responseData.put("roomData", cartRoomData);
				// responseData.put("cartId", cartId);
				// return responseData;
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@Override
	@Transactional
	public Map<String, Object> extendTime(User loginUser, Long roomId) {
		CartDetail cartDetail = null;
		List<CartDetail> cartDetailIds = null;
		Date objCurrentDate = null;
		Date objExtendedDate = null;
		Calendar objCalendar = null;
		Map<String, Object> responseData = null;
		try {
			if (loginUser != null) {
				cartDetailIds = this.cartDetailRepository.getCartRoom(
						loginUser.getEmail(), roomId);
				if (cartDetailIds != null && !cartDetailIds.isEmpty()) {
					if (cartDetailIds.size() == 1) {
						cartDetail = cartDetailIds.get(0);
						objCurrentDate = new Date();
						objCalendar = Calendar.getInstance();
						objCalendar.setTime(objCurrentDate);
						objCalendar.add(Calendar.MINUTE,
								Constant.RELEASE_MINUTE);
						objExtendedDate = objCalendar.getTime();

						cartDetail.setReleaseAt(objExtendedDate);
						cartDetail.setModifiedBy(loginUser.getId());
						cartDetail.setModifiedDate(objCurrentDate);
						// for update
						this.cartDetailRepository.save(cartDetail);
						responseData = new HashMap<String, Object>();
						responseData.put("releaseIn", objExtendedDate);
						responseData.put("roomId", roomId);
						return responseData;
					} else {
						// delete by roomid and emailid if multiple record found
						this.cartDetailRepository.deleteByRoomIdEmail(roomId,
								loginUser.getEmail());
						throw new NotFoundException(NotFound.RoomNotFoundInCart);
					}
				} else {
					throw new NotFoundException(NotFound.RoomNotFoundInCart);
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@Override
	@Transactional
	public List<String> getAmenities() {
		return this.amenityRepository.getAmenity();
	}

	@Override
	@Transactional
	public RoomListDto getHotelRooms(Integer sortBy, Integer pageNo,
			Integer noOfRecord, Integer[] noOfRoom, Integer priceMin,
			Integer priceMax, Integer[] adults, Integer[] childrens,
			Integer[] hotelBrand, Integer[] typeOfBookings,
			Integer[] sellerRatings, Integer[] hotelRatings,
			String[] amenities, String cityName, String startDate,
			String endDate, String address, Integer pincode, String phoneNumber) {
		// Response response = null;
		Integer totalPage = 0;
		List<RoomData> objRoomList = null;
		try {
			if (noOfRecord != null && noOfRecord != 0) {
			} else if (pageNo != null && pageNo != 0) {

			} else {
				noOfRecord = 12;
				pageNo = 1;
			}

			totalPage = this.hotelRoomDao.getTotalPages(sortBy, pageNo,
					noOfRecord, noOfRoom, priceMin, priceMax, adults,
					childrens, hotelBrand, typeOfBookings, sellerRatings,
					hotelRatings, amenities, cityName, startDate, endDate);
			objRoomList = this.hotelRoomDao.getHotelRooms(sortBy, pageNo,
					noOfRecord, noOfRoom, priceMin, priceMax, adults,
					childrens, hotelBrand, typeOfBookings, sellerRatings,
					hotelRatings, amenities, cityName, startDate, endDate, address, pincode, phoneNumber);
			return new RoomListDto(objRoomList, noOfRecord, pageNo, totalPage);
			// free system resources
		} finally {
			if (objRoomList != null) {
				objRoomList = null;
			}
		}
	}
}
