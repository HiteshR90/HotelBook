package com.hr.hotel.dto;

import java.util.Date;

public class SellRoomMailData {

	private String hotelName;
	private Date startDate;
	private Date endDate;
	private String userName;
	private String email;
	private Double price;

	public SellRoomMailData(String hotelName, Date startDate, Date endDate,
			String userName, String email, Double price) {
		super();
		this.hotelName = hotelName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userName;
		this.email = email;
		this.price = price;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
