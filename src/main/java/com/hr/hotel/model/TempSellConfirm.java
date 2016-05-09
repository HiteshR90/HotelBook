package com.hr.hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.hr.hotel.common.Constant;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "xc_temp_sell_confirm")
@DynamicUpdate
public class TempSellConfirm extends BaseEntity {

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "id")
	// private Integer id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Column(name = "hotelName", nullable = false)
	// private String hotelName;

	// @Column(name = "hotelAddress", nullable = false)
	// private String hotelAddress;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = HotelMaster.class)
	@JoinColumn(name = "hotel_id", nullable = false)
	private HotelMaster hotel;

	// @ManyToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	// @JoinColumn(name = "city_id", nullable = false)
	// private City city;

	@Column(name = "room_type", nullable = false)
	private String roomType;

	@Column(name = "checkInDate", nullable = false)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date checkInDate;

	@Column(name = "checkOutDate", nullable = false)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date checkOutDate;

	@Column(name = "adult_guests", nullable = false)
	 private Integer adultGuests;
	
	@Column(name = "child_guests", nullable = false)
	private Integer childGuests;
	
	@Column(name = "firstName", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "lastName", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	// @Column(name = "phoneNumber", nullable = false, length = 30)
	// private String phoneNumber;
	//
	@Column(name = "book_from", nullable = false, length = 30)
	private String bookFrom;

	@Column(name = "confirmation_code", nullable = false, length = 50)
	private String confirmationCode;

	@Column(name = "confirmation_code_second", nullable = false, length = 50)
	private String confirmationCodeSecond;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
	@JoinColumn(name = "organization_id", nullable = false)
	private Organization organization;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "sell_by", nullable = false)
	private User sellBy;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "sell_for", nullable = false)
	private User sellFor;

	@Column(name = "is_confirmation_mail_received")
	private Boolean isConfirmationMailReived;

	@Column(name = "confirmed")
	private Boolean isConfirmed;

	@Column(name = "is_removed")
	private Boolean isRemoved;

	@Column(name = "active", nullable = false)
	private Boolean active;
	
	@Column(name = "paymentStatus", nullable = false)
	private String paymentStatus;

	// @Column(name = "premiarPrice")
	// private Integer premiarPrice;

	// public String getHotelName() {
	// return hotelName;
	// }

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
	// public City getCity() {
	// return city;
	// }
	//
	// public void setCity(City city) {
	// this.city = city;
	// }

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

	public Integer getAdultGuests() {
		return adultGuests;
	}
	
	public void setAdultGuests(Integer adultGuests) {
		this.adultGuests = adultGuests;
	}
	
	public Integer getChildGuests() {
		return childGuests;
	}
	
	public void setChildGuests(Integer childGuests) {
		this.childGuests = childGuests;
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
	
	public String getBookFrom() {
		return bookFrom;
	}
	
	public void setBookFrom(String bookFrom) {
		this.bookFrom = bookFrom;
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

	public Organization getOrganization() {
		return organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getSellBy() {
		return sellBy;
	}

	public void setSellBy(User sellBy) {
		this.sellBy = sellBy;
	}

	public User getSellFor() {
		return sellFor;
	}

	public void setSellFor(User sellFor) {
		this.sellFor = sellFor;
	}

	public Boolean getIsConfirmationMailReived() {
		return isConfirmationMailReived;
	}

	public void setIsConfirmationMailReived(Boolean isConfirmationMailReived) {
		this.isConfirmationMailReived = isConfirmationMailReived;
	}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Boolean getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Boolean isRemoved) {
		this.isRemoved = isRemoved;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public HotelMaster getHotel() {
		return hotel;
	}

	public void setHotel(HotelMaster hotel) {
		this.hotel = hotel;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	// public Integer getPremiarPrice() {
	// return premiarPrice;
	// }
	//
	// public void setPremiarPrice(Integer premiarPrice) {
	// this.premiarPrice = premiarPrice;
	// }
	
}
