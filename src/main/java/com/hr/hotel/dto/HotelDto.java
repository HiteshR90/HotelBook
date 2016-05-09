package com.hr.hotel.dto;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class HotelDto {

	@NotEmpty(message = "Please enter hotel name")
	private String hotelName;

	@NotEmpty(message = "Please enter hotel address")
	private String address;

	@NotEmpty(message = "Please enter city name")
	private String city;
	
	@NotEmpty(message = "Please enter state name")
	private String state;
	
	@NotEmpty(message = "Enter ZIP Code")
	@Length(min = 4, max = 6, message = "Enter min-4 or max-6 character ZIP code")
	@Pattern(regexp = "[0-9]+", message = "ZIP Code must match 0-9")
	private String pincode;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
