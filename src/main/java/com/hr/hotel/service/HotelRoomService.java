package com.hr.hotel.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.hr.hotel.dto.BookHistoryDto;
import com.hr.hotel.dto.BookRoomForSellDto;
import com.hr.hotel.dto.CartRoomDto;
import com.hr.hotel.dto.ConfirmedRoomDto;
import com.hr.hotel.dto.ExternSellDto;
import com.hr.hotel.dto.HotelRoomData;
import com.hr.hotel.dto.PendingTransactionDto;
import com.hr.hotel.dto.RatingDto;
import com.hr.hotel.dto.RoomBook;
import com.hr.hotel.dto.RoomListDto;
import com.hr.hotel.dto.SellExternResponse;
import com.hr.hotel.dto.SellHistoryDto;
import com.hr.hotel.dto.SellInternDto;
import com.hr.hotel.model.User;

public interface HotelRoomService {

	public BookRoomForSellDto getBookRoomDataForSell(Long orderId, Long roomId,
			User loginUser, String assignerUserEmail);

	public List<ConfirmedRoomDto> getConfirmBookedRooms(User loginUser,
			String masterUserEmail);

	public List<String> getCityName();
	
	public String getStateName(String cityName);

	public HotelRoomData getHotelData(Long roomId)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	public Map<String, Object> getRating(User loginUser, Long orderId,
			Long roomId);

	// public OrderHistoryDto getOrderHistory(User loginUser,
	// String masterUserEmail);

	public Map<String, Object> updateRating(Long orderId, Long roomId,
			RatingDto rating, User loginUser, Boolean isHotelRating);

	public SellExternResponse sellRoomExtern(ExternSellDto externSellDto,
			User loginUser, String masterUserEmail);

	// TODO change new
	public void addToCart(User loginUser, RoomBook roomBook)
			throws IllegalAccessException, InvocationTargetException;

	public void removeFromCart(User loginUser, Long roomId)
			throws IllegalAccessException, InvocationTargetException;

	public List<CartRoomDto> getCartData(User loginUser);

	public Map<String, Object> extendTime(User loginUser, Long roomId);

	public List<String> getAmenities();

	public RoomListDto getHotelRooms(Integer sortBy, Integer pageNo,
			Integer noOfRecord, Integer[] noOfRoom, Integer priceMin,
			Integer priceMax, Integer[] adults, Integer[] childrens,
			Integer[] hotelBrand, Integer[] typeOfBookings,
			Integer[] sellerRatings, Integer[] hotelRatings,
			String[] amenities, String cityName, String startDate,
			String endDate, String address, Integer pincode, String phoneNumber);

	public void sellRoomInternal(User loginUser, String masterUserEmail,
			Long orderId, Long roomId, SellInternDto sellInternDto);

	public Double getCartAmount(User loginUser);

	public List<SellHistoryDto> getSellHistory(User loginUser,
			String assignerUserEmail);

	public List<BookHistoryDto> getBookHistory(User loginUser,
			String assignerUserEmail);

	public List<PendingTransactionDto> getPendingTransaction(User user);

}
