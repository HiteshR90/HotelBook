package com.hr.hotel.dto;

import com.hr.hotel.constraint.PreferenceValid;

@PreferenceValid(hotelBrand = "hotelBrand", hotelBrandError = "Not Valid Hotel Brand", hotelBrandProperty = "hotelBrand", roomType = "roomType", roomTypeError = "Not Valid Roomtype", roomTypeProperty = "roomType")
public class UserPreferenceDto {

	private Boolean highFloor;

	private Boolean earlyCheckIn;

	private Boolean lateCheckOut;

	private Integer hotelStar;

	private String[] roomType;

	private String[] hotelBrand;

	public Boolean getHighFloor() {
		return highFloor;
	}

	public void setHighFloor(Boolean highFloor) {
		this.highFloor = highFloor;
	}

	public Boolean getEarlyCheckIn() {
		return earlyCheckIn;
	}

	public void setEarlyCheckIn(Boolean earlyCheckIn) {
		this.earlyCheckIn = earlyCheckIn;
	}

	public Boolean getLateCheckOut() {
		return lateCheckOut;
	}

	public void setLateCheckOut(Boolean lateCheckOut) {
		this.lateCheckOut = lateCheckOut;
	}

	public Integer getHotelStar() {
		return hotelStar;
	}

	public void setHotelStar(Integer hotelStar) {
		this.hotelStar = hotelStar;
	}

	public String[] getRoomType() {
		return roomType;
	}

	public void setRoomType(String[] roomType) {
		this.roomType = roomType;
	}

	public String[] getHotelBrand() {
		return hotelBrand;
	}

	public void setHotelBrand(String[] hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

}
