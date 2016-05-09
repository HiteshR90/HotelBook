package com.hr.hotel.dto;

import java.util.Date;
import java.util.List;

public class HotelRoomData {
	private String hotelName;
	private String address;
	private String city;
	private Long roomId;
	private String roomType;
	private Date startDate;
	private Date endDate;
	private Number oneNightCost;
	private Integer guests;
	private Integer hotelBrand;
	private Boolean isFullSuite;
	// private Integer hotelRating;
	// private Integer userRating;
	private List<String> roomDetails;
	private List<String> roomPhotos;
	private List<BookHistoryDto> bookHistory;

	public HotelRoomData(String hotelName, String address, String roomType, Long roomId,
			Date startDate, Date endDate, Integer guests, Number oneNightCost,
			Integer hotelBrand, String city, Boolean isFullSuite) {
		this.hotelName = hotelName;
		this.address = address;
		this.city = city;
		this.roomId = roomId;
		this.roomType = roomType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.oneNightCost = oneNightCost;
		this.guests = guests;
		this.hotelBrand = hotelBrand;
		this.isFullSuite = isFullSuite;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Number getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Number oneNightCost) {
		this.oneNightCost = oneNightCost;
	}

	public Integer getGuests() {
		return guests;
	}

	public void setGuests(Integer guests) {
		this.guests = guests;
	}

	public Integer getHotelBrand() {
		return hotelBrand;
	}

	public void setHotelBrand(Integer hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

	public List<String> getRoomDetails() {
		return roomDetails;
	}

	public void setRoomDetails(List<String> roomDetails) {
		this.roomDetails = roomDetails;
	}

	public List<String> getRoomPhotos() {
		return roomPhotos;
	}

	public void setRoomPhotos(List<String> roomPhotos) {
		this.roomPhotos = roomPhotos;
	}

	public Boolean getIsFullSuite() {
		return isFullSuite;
	}

	public void setIsFullSuite(Boolean isFullSuite) {
		this.isFullSuite = isFullSuite;
	}

	public List<BookHistoryDto> getBookHistory() {
		return bookHistory;
	}

	public void setBookHistory(List<BookHistoryDto> bookHistory) {
		this.bookHistory = bookHistory;
	}
	
}
