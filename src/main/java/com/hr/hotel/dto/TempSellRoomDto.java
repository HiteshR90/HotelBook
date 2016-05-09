package com.hr.hotel.dto;

import java.util.Date;

public class TempSellRoomDto {

	private Long id;
	private String hotelName;
	private String address;
	private String roomType;
	private Date checkInDate;
	private Date checkOutDate;
	private String confirmationCode;
	private String confirmationCodeSecond;
	private Double price;
	private Boolean isConfirmed;
	private Boolean active;

	public TempSellRoomDto(Long id, String hotelName, String address,
			String roomType, Date checkInDate, Date checkOutDate,
			String confirmationCode, String confirmationCodeSecond,
			Double price, Boolean isConfirmed, Boolean active) {
		this.id = id;
		this.hotelName = hotelName;
		this.address = address;
		this.roomType = roomType;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.confirmationCode = confirmationCode;
		this.confirmationCodeSecond = confirmationCodeSecond;
		this.price = price;
		this.isConfirmed = isConfirmed;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getConfirmationCodeSecond() {
		return confirmationCodeSecond;
	}

	public void setConfirmationCodeSecond(String confirmationCodeSecond) {
		this.confirmationCodeSecond = confirmationCodeSecond;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
