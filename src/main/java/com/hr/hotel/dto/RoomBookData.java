package com.hr.hotel.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RoomBookData {
	private Long roomId;
	// private Long bookId;
	private Double oneNightCost;
	private String hotelName;
	private Long hotelId;
	private Boolean isFullSuite;
	private String city;
	private String roomType;
	private Integer hotelBrand;
	private String roomDesc;
	// private String path;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private Date startDate;
	private Date endDate;

	// private Long premierPrice;
	// private Number releaseInSecond;

	public RoomBookData(Long roomId, Double oneNightCost, String hotelName,
			Long hotelId, Boolean isFullSuite, String city, String roomType,
			Integer hotelBrand, String roomDesc, BigDecimal longitude,
			BigDecimal latitude, Date startDate, Date endDate) {
		this.roomId = roomId;
		this.oneNightCost = oneNightCost;
		this.hotelName = hotelName;
		this.hotelId = hotelId;
		this.isFullSuite = isFullSuite;
		this.city = city;
		this.roomType = roomType;
		this.hotelBrand = hotelBrand;
		this.roomDesc = roomDesc;
		this.longitude = longitude;
		this.latitude = latitude;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Boolean getIsFullSuite() {
		return isFullSuite;
	}

	public void setIsFullSuite(Boolean isFullSuite) {
		this.isFullSuite = isFullSuite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Integer getHotelBrand() {
		return hotelBrand;
	}

	public void setHotelBrand(Integer hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
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

}
