package com.hr.hotel.dto;

import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class BookRoomMailData {

	private String hotelName;
	private Date startDate;
	private Date endDate;
	private Double cost;

	public BookRoomMailData(String hotelName, Date startDate, Date endDate,
			Double cost) {
		super();
		this.hotelName = hotelName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
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

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}
