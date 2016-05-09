package com.hr.hotel.dao;

import java.util.List;

import com.hr.hotel.dto.RoomData;

public interface HotelRoomDao {

	// /**
	// * getOrderedRoomBetween check that any confirmed room found between start
	// * date and end date
	// *
	// * @param roomId
	// * if of room to check
	// * @param startDate
	// * date start rang
	// * @param endDate
	// * date end rang
	// * @return room id if room is booked between start and end date
	// */
	// public Long getOrderedRoomBetween(Long roomId, Date startDate, Date
	// endDate);

	public List<RoomData> getHotelRooms(Integer sortBy, Integer pageNo,
			Integer noOfRecord, Integer[] noOfRoom, Integer priceMin,
			Integer priceMax, Integer[] adults, Integer[] childrens,
			Integer[] hotelBrand, Integer[] typeOfBookings,
			Integer[] sellerRatings, Integer[] hotelRatings,
			String[] amenities, String cityName, String startDate,
			String endDate, String address, Integer pincode, String phoneNumber);

	public Integer getTotalPages(Integer sortBy, Integer pageNo,
			Integer noOfRecord, Integer[] noOfRoom, Integer priceMin,
			Integer priceMax, Integer[] adults, Integer[] childrens,
			Integer[] hotelBrand, Integer[] typeOfBookings,
			Integer[] sellerRatings, Integer[] hotelRatings,
			String[] amenities, String cityName, String startDate,
			String endDate);
}
