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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.CartRoomDto;
import com.hr.hotel.dto.ExternSellDto;
import com.hr.hotel.dto.HotelDetailDto;
import com.hr.hotel.dto.HotelDto;
import com.hr.hotel.dto.ResponseMessageDto;
import com.hr.hotel.dto.RoomBook;
import com.hr.hotel.dto.RoomListDto;
import com.hr.hotel.dto.SellExternResponse;
import com.hr.hotel.dto.SellInternDto;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.PathParamMissingException;
import com.hr.hotel.model.User;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.CommonService;
import com.hr.hotel.service.HotelRoomService;
import com.hr.hotel.service.HotelService;
import com.hr.hotel.service.UserService;

@Controller
@RequestMapping("/hotel")
public class HotelController {

	private static final Logger logger = LoggerFactory
			.getLogger(HotelController.class);

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	@Resource(name = Constant.SERVICE_HOTEL_ROOM)
	private HotelRoomService hotelRoomService;

	@Resource(name = Constant.SERVICE_COMMON)
	private CommonService commonService;

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

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Long addHotel(@RequestBody @Valid HotelDto hotelDto) {
		return this.hotelService.add(hotelDto);
	}

	@RequestMapping(value = "/find-hotel-by-name", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
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

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public String hotelRooms(ModelMap modelMap) {
		// System.out.println(modelMap.get(Constant.SESSION_ACCESS_TOKEN));
		// System.out.println(session.getAttribute(Constant.SESSION_ACCESS_TOKEN));
		logger.info("hotelRooms");
		User objUser = getUserDetail();
		if (objUser != null) {
			// TODO put user master object for login and registration
			modelMap.put("isLogin", true);
			modelMap.put("userName", objUser.getUserName());
		} else {
			modelMap.put("isLogin", false);
		}
		// modelMap.put("roomList", this.hotelRoomService.getHotelRooms(null,
		// null, 12, null, null, null, null, null, null, null, null, null,
		// null, null, null, null));
		// modelMap.put("amenities", this.hotelRoomService.getAmenities());
		// modelMap.put("cities", this.hotelRoomService.getCityName());
		return "hotel/roomLists";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartDetails(ModelMap modelMap) {
		logger.info("cart");
		User objUser = getUserDetail();
		if (objUser != null) {
			// TODO put user master object for login and registration
			modelMap.put("isLogin", true);
			modelMap.put("userName", objUser.getUserName());
		} else {
			modelMap.put("isLogin", false);
		}
		return "hotel/cart";
	}
	
	//TODO map start
	@RequestMapping(value = "/rooms-map", method = RequestMethod.GET)
	public String hotelRoomsOnMap(ModelMap modelMap) {
		// System.out.println(modelMap.get(Constant.SESSION_ACCESS_TOKEN));
		// System.out.println(session.getAttribute(Constant.SESSION_ACCESS_TOKEN));
		logger.info("hotelRooms");
		User objUser = getUserDetail();
		if (objUser != null) {
			// TODO put user master object for login and registration
			modelMap.put("isLogin", true);
			modelMap.put("userName", objUser.getUserName());
		} else {
			modelMap.put("isLogin", false);
		}
		return "hotel/roomListsMap";
	}
	//TODO map end

	@RequestMapping(value = "cityName", method = RequestMethod.GET)
	public @ResponseBody List<String> getCityName() {
		return this.hotelRoomService.getCityName();
	}
	
	@RequestMapping(value = "stateName", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto getStateName(@RequestParam String cityName) {
		return new ResponseMessageDto(this.hotelRoomService.getStateName(cityName));
	}

	/**
	 * 
	 * 
	 * @param roomBook
	 * @param session
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addToCart(@Valid @RequestBody RoomBook roomBook)
			throws IllegalAccessException, InvocationTargetException {
		logger.info("addToCart start");
		User objUser = getUserDetail();
		if (objUser != null) {
			this.hotelRoomService.addToCart(objUser, roomBook);
			Map<String, String> response = new HashMap<String, String>();
			response.put("message", "success");
			return response;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
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
		logger.info("removeFromCart start");
		User objUser = getUserDetail();
		if (objUser != null) {
			this.hotelRoomService.removeFromCart(objUser, roomId);
			Map<String, String> objResponse = new HashMap<String, String>();
			objResponse.put("message", "success");
			return objResponse;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
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
		logger.info("getCartRooms");
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.hotelRoomService.getCartData(objUser);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "/sell", method = RequestMethod.GET)
	public String sellExtern(ModelMap modelMap) {
		User objUser = getUserDetail();
		if (objUser != null) {
			modelMap.put("isLogin", true);
			modelMap.put("userName", objUser.getUserName());
		} else {
			modelMap.put("isLogin", false);
		}
		return "hotel/sell";
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
		logger.info("sellRoomExtern controller start");
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.hotelRoomService.sellRoomExtern(externSellDto, objUser,
					assignerUserEmail);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
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
			@RequestBody @Valid SellInternDto sellInternDto,
			@PathVariable Long orderId, @PathVariable Long roomId)
			throws Exception {
		logger.info("sellRoomIntern start");
		User objUser = getUserDetail();
		if (objUser != null) {
			if (orderId != null && orderId > 0 && roomId != null && roomId > 0) {
				this.hotelRoomService.sellRoomInternal(objUser, null, orderId,
						roomId, sellInternDto);
				return new ResponseMessageDto("Room sell successfully");
			} else {
				throw new PathParamMissingException();
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

}
