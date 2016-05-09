package com.hr.hotel.dto;

import java.io.Serializable;
import java.util.Date;

public class BookRoomForSellDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roomId;
	private Long hotelId;
	private String hotelName;
	private Integer hotelBrand;
	private String city;
	private Double oneNightCost;
	private String roomType;
	private String roomDesc;
	private Date startDate;
	private Date endDate;
	private Integer nights;
	private Double totalCost;

	public BookRoomForSellDto() {
	}

	public BookRoomForSellDto(Long roomId, Long hotelId,
			String hotelName, Integer hotelBrand, String city,
			Double oneNightCost, String roomType, String roomDesc,
			Date startDate, Date endDate, Integer nights, Double totalCost) {
		this.roomId = roomId;
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelBrand = hotelBrand;
		this.city = city;
		this.oneNightCost = oneNightCost;
		this.roomType = roomType;
		this.roomDesc = roomDesc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.nights = nights;
		this.totalCost = totalCost;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getHotelBrand() {
		return hotelBrand;
	}

	public void setHotelBrand(Integer hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
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

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

}
