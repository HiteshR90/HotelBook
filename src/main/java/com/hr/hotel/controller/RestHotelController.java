package com.hr.hotel.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.BookHistoryDto;
import com.hr.hotel.dto.BookRoomForSellDto;
import com.hr.hotel.dto.CartRoomDto;
import com.hr.hotel.dto.ConfirmedRoomDto;
import com.hr.hotel.dto.ExternSellDto;
import com.hr.hotel.dto.HotelDetailDto;
import com.hr.hotel.dto.HotelDto;
import com.hr.hotel.dto.HotelRoomData;
import com.hr.hotel.dto.RatingDto;
import com.hr.hotel.dto.ResponseMessageDto;
import com.hr.hotel.dto.RoomBook;
import com.hr.hotel.dto.RoomListDto;
import com.hr.hotel.dto.SellExternResponse;
import com.hr.hotel.dto.SellHistoryDto;
import com.hr.hotel.dto.SellInternDto;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.PathParamMissingException;
import com.hr.hotel.model.User;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.HotelRoomService;
import com.hr.hotel.service.HotelService;
import com.hr.hotel.service.UserService;

/**
 * HotelController handle all hotel related stuff
 * 
 * @author HiteshR
 * @version 1.0.0
 * @date 2013-10-03
 */
@Controller
@RequestMapping(value = "/rest/hotel")
public class RestHotelController {

	private static final Logger logger = LoggerFactory
			.getLogger(RestHotelController.class);

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	@Resource(name = Constant.SERVICE_HOTEL_ROOM)
	private HotelRoomService hotelRoomService;

	@Resource(name = Constant.SERVICE_HOTEL)
	private HotelService hotelService;

	private User getUserDetail() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication.getPrincipal() instanceof UserDetails) {
			CustomUserDetail userDetails = (CustomUserDetail) authentication
					.getPrincipal();
			if (userDetails != null && userDetails.getUser() != null) {
				return userDetails.getUser();
			}
		}
		return null;
	}

	@RequestMapping(value = "/getAmenity", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<String> getAmenity() throws Exception {
		try {
			logger.info("getAmenity start");
			return this.hotelRoomService.getAmenities();
		} finally {

		}
	}

	@RequestMapping(value = "/getCityName", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<String> getCityName() throws Exception {
		// Response objResponse = null;
		logger.info("getRoomFacility start");
		return this.hotelRoomService.getCityName();
		// logger.info("getRoomFacility end");
		// return objResponse;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Long addHotel(@RequestBody @Valid HotelDto hotelDto) {
		return this.hotelService.add(hotelDto);
	}

	@RequestMapping(value = "/find-hotel-by-name", method = RequestMethod.GET)
	public @ResponseBody List<HotelDetailDto> getHotels(
			@RequestParam(defaultValue = "", required = true, value = "query") String hotelName) {
		return this.hotelService.getHotelsDetail(hotelName);
	}

	@RequestMapping(value = "/getHotelRooms", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public RoomListDto getHotelRooms(
			@RequestParam(required = false) Integer sortBy,
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer noOfRecord,
			@RequestParam(required = false) Integer[] noOfRoom,
			@RequestParam(required = false) Integer priceMax,
			@RequestParam(required = false) Integer priceMin,
			@RequestParam(required = false) Integer[] adults,
			@RequestParam(required = false) Integer[] childrens,
			@RequestParam(required = false) Integer[] hotelBrand,
			@RequestParam(required = false) Integer[] typeOfBookings,
			@RequestParam(required = false) Integer[] sellerRatings,
			@RequestParam(required = false) Integer[] hotelRatings,
			@RequestParam(required = false) String[] amenities,
			@RequestParam(required = false) String cityName,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) String address,
			@RequestParam(required = false) Integer pincode,
			@RequestParam(required = false) String phoneNumber) throws Exception {
		// Response objResponse = null;
		logger.info("getHotelRooms start");
		return this.hotelRoomService.getHotelRooms(sortBy, pageNo, noOfRecord,
				noOfRoom, priceMin, priceMax, adults, childrens, hotelBrand,
				typeOfBookings, sellerRatings, hotelRatings, amenities,
				cityName, startDate, endDate, address, pincode, phoneNumber);
	}

	/**
	 * getRoomSuggesion get room database on given parameters
	 * 
	 * @param costs
	 * @param adults
	 * @param brands
	 * @param cities
	 * @return
	 */
	// @RequestMapping(value = "/getRoomSuggesion", method = RequestMethod.GET,
	// produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public Response getRoomSuggesion(
	// @RequestParam(required = false) Integer[] costs,
	// @RequestParam(required = false) Integer[] adults,
	// @RequestParam(required = false) Integer[] brands,
	// @RequestParam(required = false) String[] cities) {
	// Response objResponse = null;
	// try {
	// logger.info("getRoomSuggesion start");
	// objResponse = this.restHotelRoomService.getRoomSuggesion(costs,
	// adults, brands, cities);
	// logger.info("getRoomSuggesion end");
	// } catch (Exception exception) {
	// exception.printStackTrace();
	// logger.error("getRoomSuggesion", exception);
	// objResponse = new Response("Please Contact Administartor", "", true);
	// }
	// return objResponse;
	// }

	/**
	 * getHotelData return hotel detail based on roomid
	 * 
	 * @param roomId
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/getHotelData/{roomId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public HotelRoomData getHotelData(@PathVariable Long roomId)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// Response objResponse = null;
		logger.info("getHotelData start");
		return this.hotelRoomService.getHotelData(roomId);
		// logger.info("getHotelData end");
		// return objResponse;
	}

	/**
	 * bookRoom for login user if delegatedUserId not empty
	 * 
	 * @param roomId
	 * @param delegatedUserId
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping(value = "/bookRoom/{roomId}", method = RequestMethod.GET,
	// produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public Response bookRoom(
	// @PathVariable Integer roomId,
	// @RequestParam(value = "assignerUserEmail",
	// required = false) String assignerUserEmail,
	// Principal principal) {
	// Response objResponse = null;
	// User objUser = null;
	// try {
	// logger.info("getBookRoom start");
	// objUser = this.restUserService.getUserData(principal);
	// if (objUser != null) {
	// // TODO change
	// // objResponse = this.restHotelRoomService.bookRoom(roomId,
	// // objUser, assignerUserEmail);
	// } else {
	// objResponse = new Response(Constant.ERROR_LOGIN_MESSAGE, "", true,
	// Constant.ERROR_LOGIN_CODE);
	// }
	// logger.info("getBookRoom end");
	// } catch (Exception exception) {
	// logger.error("getBookRoom", exception);
	// exception.printStackTrace();
	// objResponse = new Response("Please Contact Administrator", "", true);
	// }
	// return objResponse;
	// }

	// @RequestMapping(value = "/releaseTime/{roomId}/{cartId}", method =
	// RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// @Deprecated
	// public Response releaseTime(@PathVariable Long roomId,
	// @PathVariable Long bookId, Principal principal) throws Exception {
	// Response objResponse = null;
	// User objUser = null;
	// try {
	// logger.info("releaseTime start");
	// objUser = this.restUserService.getUserData(principal);
	// if (objUser != null) {
	// objResponse = this.restHotelRoomService.releaseTime(roomId,
	// bookId, objUser);
	// } else {
	// throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
	// }
	// logger.info("releaseTime end");
	// } finally {
	//
	// }
	// return objResponse;
	// }

	// @RequestMapping(value = "checkDateAvailable/{roomId}", method =
	// RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	// public @ResponseBody
	// Response checkDateAvailable(
	// @PathVariable Long roomId,
	// @Validated(value = { CheckDateAvailable.class }) @RequestBody HotelRoom
	// hotelRoom,
	// BindingResult result, Principal principal) throws Exception {
	// Response objResponse = null;
	// User objUser = null;
	// Boolean isRoomAvialbale = false;
	// ValidationErrorDTO objErrorDTO = null;
	// HashMap<String, Object> objResponseData = null;
	// try {
	// logger.info("checkDateAvailable controller start");
	// objUser = this.restUserService.getUserData(principal);
	// if (objUser != null) {
	// if (hotelRoom.getStartDate().before(hotelRoom.getEndDate())) {
	// isRoomAvialbale = this.hotelRoomService.isRoomAvialable(
	// roomId, hotelRoom.getStartDate(),
	// hotelRoom.getEndDate());
	// if (isRoomAvialbale) {
	// objResponseData = new HashMap<String, Object>();
	// objResponseData.put("message", "Room is available");
	// objResponse = new Response("SUCCESS", objResponseData,
	// false);
	// } else {
	// throw new MessageException(
	// MessageExceptionErroCode.DATENOTAVAILABLE);
	// }
	// } else {
	// objErrorDTO = new ValidationErrorDTO();
	// objErrorDTO.addFieldError("endDate",
	// "EndDate must be greater then start date");
	// throw new FieldErrorException(objErrorDTO);
	// }
	// } else {
	// throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
	// }
	// logger.info("checkDateAvailable controller end");
	// } finally {
	//
	// }
	// return objResponse;
	// }

	/**
	 * getConfirmBookedRooms shows booked room
	 * 
	 * @param assignerUserEmail
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getConfirmBookedRooms", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<ConfirmedRoomDto> getConfirmBookedRooms(
			@RequestParam(value = "assignerUserEmail", required = false) String assignerUserEmail)
			throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			logger.info("getBuyRoomData start");
			objUser = getUserDetail();
			if (objUser != null) {
				return this.hotelRoomService.getConfirmBookedRooms(objUser,
						assignerUserEmail);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	/**
	 * getBookedRoomForSell used to get booked room information using booking id
	 * 
	 * @param bookingId
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBookRoomDataForSell/{orderId}/{roomId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public BookRoomForSellDto getBookRoomDataForSell(
			@PathVariable Long orderId,
			@PathVariable Long roomId,
			@RequestParam(value = "assignerUserEmail", required = false) String assignerUserEmail)
			throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			logger.info("getBookedRoomData start");
			objUser = getUserDetail();
			if (objUser != null) {
				if (orderId != null && orderId > 0 && roomId != null
						&& roomId > 0) {
					return this.hotelRoomService.getBookRoomDataForSell(
							orderId, roomId, objUser, assignerUserEmail);
				} else {
					throw new PathParamMissingException();
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			// logger.info("getBookedRoomData end");
		} finally {
		}
		// return objResponse;
	}

	/**
	 * sellRoomIntern sell room that are booked from our site
	 * 
	 * @param bookingId
	 * @param sellRoom
	 * @param assignerUserEmail
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sellRoomIntern/{orderId}/{roomId}", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto sellRoomIntern(
			@PathVariable Long orderId,
			@PathVariable Long roomId,
			@RequestBody @Valid SellInternDto sellInternDto,
			@RequestParam(value = "assignerUserEmail", required = false) String assignerUserEmail)
			throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			logger.info("sellRoomIntern start");
			objUser = getUserDetail();
			if (objUser != null) {
				if (orderId != null && orderId > 0 && roomId != null
						&& roomId > 0) {
					this.hotelRoomService.sellRoomInternal(objUser,
							assignerUserEmail, orderId, roomId, sellInternDto);
					return new ResponseMessageDto("Room sell successfully");
				} else {
					throw new PathParamMissingException();
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			// logger.info("sellRoomIntern end");
		} finally {
		}
		// return objResponse;
	}

	/**
	 * sellRoomExtern sell room that are booked from other site
	 * 
	 * @param sellRoomModel
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sellRoomExtern", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public SellExternResponse sellRoomExtern(
			@Valid @RequestBody ExternSellDto externSellDto,
			@RequestParam(value = "assignerUserEmail", required = false) String assignerUserEmail)
			throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			logger.info("sellRoomExtern controller start");
			objUser = getUserDetail();
			if (objUser != null) {
				return this.hotelRoomService.sellRoomExtern(externSellDto,
						objUser, assignerUserEmail);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	/**
	 * getBookHistory provide buy and sell room history
	 * 
	 * @param assignerUserEmail
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBookHistory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<BookHistoryDto> getBookHistory(
			@RequestParam(value = "assignerUserEmail", required = false) String assignerUserEmail) {
		logger.info("getOrderHistory start");
		User objUser = getUserDetail();

		if (objUser != null) {
			return this.hotelRoomService.getBookHistory(objUser,
					assignerUserEmail);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	/**
	 * getSellHistory provide buy and sell room history
	 * 
	 * @param assignerUserEmail
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/getSellHistory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<SellHistoryDto> getSellHistory(
			@RequestParam(value = "assignerUserEmail", required = false) String assignerUserEmail) {
		logger.info("getOrderHistory start");
		User objUser = getUserDetail();

		if (objUser != null) {
			return this.hotelRoomService.getSellHistory(objUser,
					assignerUserEmail);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	/**
	 * getRating return rating based on booking id
	 * 
	 * param bookId
	 * 
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRating/{orderId}/{roomId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> getRating(@PathVariable Long orderId,
			@PathVariable Long roomId) throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			objUser = getUserDetail();
			if (objUser != null) {
				return this.hotelRoomService
						.getRating(objUser, orderId, roomId);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@RequestMapping(value = "/updateHotelRating/{orderId}/{roomId}", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> getUpdateHotelRating(
			@Valid @RequestBody RatingDto ratingDto,
			@PathVariable Long orderId, @PathVariable Long roomId)
			throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			objUser = getUserDetail();
			if (objUser != null) {
				return this.hotelRoomService.updateRating(orderId, roomId,
						ratingDto, objUser, true);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
		// return objResponse;
	}

	@RequestMapping(value = "/updateUserRating/{orderId}/{roomId}", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> getUpdateUserRating(
			@RequestBody RatingDto ratingDto, @PathVariable Long orderId,
			@PathVariable Long roomId) throws Exception {
		// Response objResponse = null;
		User objUser = null;
		try {
			objUser = getUserDetail();
			if (objUser != null) {
				return this.hotelRoomService.updateRating(orderId, roomId,
						ratingDto, objUser, false);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
		// return objResponse;
	}

	/**
	 * bookRoom for login user if delegatedUserId not empty
	 * 
	 * @param roomId
	 * @param delegatedUserId
	 * @param principal
	 * @return
	 */
	// @RequestMapping(value = "/bookRooms", method = RequestMethod.POST,
	// produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public Response bookRoom(
	// @RequestBody RoomBookList roomBookList,
	// @RequestParam(value = "assignerUserEmail",
	// required = false) String assignerUserEmail,
	// Principal principal) {
	// Response objResponse = null;
	// User objUser = null;
	// ValidationErrorDTO objErrorDTO = null;
	// try {
	// logger.info("getBookRoom start");
	// objUser = this.restUserService.getUserData(principal);
	// if (objUser != null) {
	// if (roomBookList != null && roomBookList.getRoomIds() != null
	// && roomBookList.getRoomIds().size() > 0) {
	// objResponse = this.restHotelRoomService.bookRoom(
	// roomBookList.getRoomIds(), objUser,
	// assignerUserEmail);
	// } else {
	// objErrorDTO = new ValidationErrorDTO();
	// objErrorDTO.addFieldError("roomIds",
	// "Please Enter Room Array");
	// objResponse = new Response("Please Solve Error", objErrorDTO,
	// true);
	// }
	// } else {
	// objResponse = new Response(Constant.ERROR_LOGIN_MESSAGE, "", true,
	// Constant.ERROR_LOGIN_CODE);
	// }
	// logger.info("getBookRoom end");
	// } catch (Exception exception) {
	// logger.error("getBookRoom", exception);
	// exception.printStackTrace();
	// objResponse = new Response("Please Contact Administrator", "", true);
	// }
	// return objResponse;
	// }

	/**
	 * orderHistory provide buy and sell room history
	 * 
	 * @param assignerUserEmail
	 * @param principal
	 * @return
	 */
	// @RequestMapping(value = "/orderHistory", method = RequestMethod.GET,
	// produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public Response getOrderHistory(Principal principal) {
	// Response objResponse = null;
	// User objUser = null;
	// try {
	// logger.info("getOrderHistory start");
	// objUser = this.restUserService.getUserData(principal);
	//
	// if (objUser != null) {
	// objResponse = this.restHotelRoomService
	// .getOrderHistory(objUser);
	// } else {
	// objResponse = new Response(Constant.ERROR_LOGIN_MESSAGE, "", true,
	// Constant.ERROR_LOGIN_CODE);
	// }
	// logger.info("getOrderHistory start");
	// } catch (Exception e) {
	// e.printStackTrace();
	// logger.error("getOrderHistory", e);
	// objResponse = new Response("Please Contact Administartor", "", true);
	// } finally {
	//
	// }
	// return objResponse;
	// }

	/**
	 * 
	 * 
	 * @param roomBook
	 * @param principal
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addToCart(@Valid @RequestBody RoomBook roomBook)
			throws IllegalAccessException, InvocationTargetException, Exception {
		// Response objResponse = null;
		User objUser = null;
		Map<String, String> response = null;
		try {
			logger.info("addToCart start");
			objUser = getUserDetail();
			if (objUser != null) {
				this.hotelRoomService.addToCart(objUser, roomBook);
				response = new HashMap<String, String>();
				response.put("message", "success");
				return response;
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			// logger.info("addToCart end");
		} finally {
		}
		// return objResponse;
	}

	/**
	 * removeFromCart for login user if delegatedUserId not empty
	 * 
	 * @param roomId
	 * @param delegatedUserId
	 * @param principal
	 * @return
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/removeFromCart/{roomId}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, String> removeFromCart(
			@PathVariable("roomId") Long roomId) throws IllegalAccessException,
			InvocationTargetException, Exception {
		Map<String, String> objResponse = null;
		User objUser = null;
		try {
			logger.info("removeFromCart start");
			objUser = getUserDetail();
			if (objUser != null) {
				this.hotelRoomService.removeFromCart(objUser, roomId);
				objResponse = new HashMap<String, String>();
				objResponse.put("message", "success");
				return objResponse;
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
		}
	}

	/**
	 * getCartRooms for login user if delegatedUserId not empty
	 * 
	 * @param roomId
	 * @param delegatedUserId
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCartRooms", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<CartRoomDto> getCartRooms() throws Exception {
		// List<CartRoomDto> objResponse = null;
		User objUser = null;
		try {
			logger.info("getCartRooms start");
			objUser = getUserDetail();
			if (objUser != null) {
				return this.hotelRoomService.getCartData(objUser);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			// logger.info("removeFromCart end");
		} finally {

		}
		// return objResponse;
	}

	/**
	 * getCartRooms for login user if delegatedUserId not empty
	 * 
	 * @param roomId
	 * @param delegatedUserId
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/extendTime/{roomId}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> extendTime(@PathVariable("roomId") Long roomId)
			throws Exception {
		Map<String, Object> objResponse = null;
		User objUser = null;
		try {
			logger.info("removeFromCart start");
			objUser = getUserDetail();
			if (objUser != null) {
				objResponse = this.hotelRoomService.extendTime(objUser, roomId);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			logger.info("removeFromCart end");
		} finally {

		}
		return objResponse;
	}
}
