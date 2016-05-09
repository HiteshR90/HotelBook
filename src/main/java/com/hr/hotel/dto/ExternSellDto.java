package com.hr.hotel.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.hr.hotel.constraint.DateValid;

@DateValid(checkInDate = "checkInDate", checkOutDate = "checkOutDate", checkInPropertyName = "checkInDate", checkOutPropertyName = "checkOutDate")
public class ExternSellDto {

	// @NotEmpty(message = "Please Enter Hotel Name")
	// private String hotelName;

	// @NotEmpty(message = "Please Enter Hotel Address")
	// private String hotelAddress;

	@NotNull(message = "Please Select Hotel")
	private Long hotelId;

	@NotEmpty(message = "Please Enter Room type")
	private String roomType;

	@NotNull(message = "Please Enter Checkin Date")
	private String checkInDate;

	@NotNull(message = "Please Enter Checkout Date")
	private String checkOutDate;

	@NotNull(message = "Please enter price")
	@Min(value = 10, message = "value must not less than 10")
	@Max(value = 9999, message = "value must not greater than 9999")
	private Double price;

	@NotEmpty(message = "Please Enter Reservation Token")
	private String confirmationCode;

	private String confirmationCodeSecond;

	// @NotEmpty(message = "Please Enter City")
	// private String city;

	@NotNull(message = "Please Enter Number of adult")
	@Min(value = 1, message = "Value must not less than one")
	@Max(value = 6, message = "value must not greater than six")
	private Integer adult;

	@NotNull(message = "Please Enter Number of children")
	@Min(value = 0, message = "Value must not less than zero")
	@Max(value = 6, message = "value must not greater than six")
	private Integer childeren;

	@NotEmpty(message = "Please Enter Firstname")
	private String firstName;

	@NotEmpty(message = "Please Enter Lastname")
	private String lastName;

	@NotEmpty(message = "Please Enter Email")
	@Email(message = "Please Enter Valid email")
	private String email;

	// @NotEmpty(message = "Please Enter phone number")
	// private String phoneNumber;

	@NotEmpty(message = "Please Enter Book From")
	private String bookFrom;

	@NotEmpty(message = "Please Select payment type")
	private String paymentType;
	
	@NotEmpty(message = "Please Select payment Status")
	private String paymentStatus;

	// public String getHotelName() {
	// return hotelName;
	// }
	//
	// public void setHotelName(String hotelName) {
	// this.hotelName = hotelName;
	// }
	//
	// public String getHotelAddress() {
	// return hotelAddress;
	// }
	//
	// public void setHotelAddress(String hotelAddress) {
	// this.hotelAddress = hotelAddress;
	// }
	//
	// public String getCity() {
	// return city;
	// }
	//
	// public void setCity(String city) {
	// this.city = city;
	// }

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
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// public String getPhoneNumber() {
	// return phoneNumber;
	// }
	//
	// public void setPhoneNumber(String phoneNumber) {
	// this.phoneNumber = phoneNumber;
	// }

	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getChilderen() {
		return childeren;
	}

	public void setChilderen(Integer childeren) {
		this.childeren = childeren;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getBookFrom() {
		return bookFrom;
	}
	
	public void setBookFrom(String bookFrom) {
		this.bookFrom = bookFrom;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
