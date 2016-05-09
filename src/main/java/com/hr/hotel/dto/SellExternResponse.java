package com.hr.hotel.dto;

public class SellExternResponse {

	private String email;
	private String userName;
	private String hotelName;
	private String hotelAddress;
	private String checkInDate;
	private String checkOutDate;
	private Double price;
	private String contactEmail;

	public SellExternResponse(String email, String userName, String hotelName,
			String hotelAddress, String checkInDate, String checkOutDate,
			Double price, String contactEmail) {
		this.email = email;
		this.userName = userName;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.price = price;
		this.contactEmail = contactEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

}
