package com.hr.hotel.dto;

import javax.validation.constraints.NotNull;

//@DateValid(checkInDate = "checkInDate", checkOutDate = "checkOutDate", checkInPropertyName = "checkInDate", checkOutPropertyName = "checkOutDate")
public class RoomBook {

	@NotNull(message = "Please Enter Room Id")
	private Long roomId;

	// @NotEmpty(message="Please Enter Start Date")
	// @DateFormat(pattern = Constant.DATE_FORMAT, emptyString =
	// "Please Enter Start Date", wrongPattern =
	// "Date must be in dd/MM/yyyy in format")
	private String checkInDate;

	// @NotEmpty(message="Please Enter End Date")
	// @DateFormat(pattern = Constant.DATE_FORMAT, emptyString =
	// "Please Enter End Date", wrongPattern =
	// "Date must be in dd/MM/yyyy in format")
	private String checkOutDate;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
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

}
