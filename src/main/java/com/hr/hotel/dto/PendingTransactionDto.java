package com.hr.hotel.dto;

import java.util.Date;
import java.util.List;

public class PendingTransactionDto {

	private String hotelName;
	private Long roomId;
	private Date purchaseDate;
	private Date dipositDate;
	private Double dipositAmount;
	private List<String> images;

	public PendingTransactionDto(String hotelName, Long roomId,
			Date purchaseDate, Date dipositDate, Double dipositAmount) {
		super();
		this.hotelName = hotelName;
		this.roomId = roomId;
		this.purchaseDate = purchaseDate;
		this.dipositDate = dipositDate;
		this.dipositAmount = dipositAmount;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getDipositDate() {
		return dipositDate;
	}

	public void setDipositDate(Date dipositDate) {
		this.dipositDate = dipositDate;
	}

	public Double getDipositAmount() {
		return dipositAmount;
	}

	public void setDipositAmount(Double dipositAmount) {
		this.dipositAmount = dipositAmount;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
