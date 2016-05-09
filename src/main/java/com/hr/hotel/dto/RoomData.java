package com.hr.hotel.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RoomData {

	private Long roomId;
	private Double oneNightCost;
	private Date startDate;
	private Date endDate;
	private String hotelName;
	private String address;
	private Integer pincode; 
	private String phonenumber;
	private Long hotelId;
	private Boolean isFullSuite;
	private String city;
	private String roomType;
	private Integer hotelBrand;
	private String roomDesc;
	private String imagePath;
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	public RoomData(Long roomId, Double oneNightCost, Date startDate,
			Date endDate, String hotelName, Long hotelId, Boolean isFullSuite,
			String city, String roomType, Integer hotelBrand, String roomDesc,
			String imagePath, BigDecimal latitude, BigDecimal longitude) {
		this.roomId = roomId;
		this.oneNightCost = oneNightCost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelName = hotelName;
		this.hotelId = hotelId;
		this.isFullSuite = isFullSuite;
		this.city = city;
		this.roomType = roomType;
		this.hotelBrand = hotelBrand;
		this.roomDesc = roomDesc;
		this.imagePath = imagePath;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public RoomData(Long roomId, Double oneNightCost, Date startDate,
			Date endDate, String hotelName,String address, Integer pincode,
			String phonenumber, Long hotelId, Boolean isFullSuite,
			String city, String roomType, Integer hotelBrand, String roomDesc,
			String imagePath, BigDecimal latitude, BigDecimal longitude) {
		this.roomId = roomId;
		this.oneNightCost = oneNightCost;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelName = hotelName;
		this.address = address;
		this.pincode = pincode;
		this.phonenumber = phonenumber;
		this.hotelId = hotelId;
		this.isFullSuite = isFullSuite;
		this.city = city;
		this.roomType = roomType;
		this.hotelBrand = hotelBrand;
		this.roomDesc = roomDesc;
		this.imagePath = imagePath;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
