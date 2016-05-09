package com.hr.hotel.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_hotel_master", uniqueConstraints = @UniqueConstraint(columnNames = {
		"hotelname", "city_id", "address" }))
public class HotelMaster extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1319137233651904677L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "hotelid")
	// private Integer hotelId;

	@Column(name = "hotelname", nullable = false, length = 100)
	private String hotelName;

	@Column(name = "hotelbrand", nullable = false, length = 2)
	private Integer hotelBrand;

	@Column(name = "address", nullable = false)
	private String address;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = State.class)
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

	// @Column(name = "city")
	// private String city;
	//
	// @Column(name = "state")
	// private String state;

	@Column(name = "pincode")
	private Integer pincode;

	// @Column(name = "country")
	// private String country;

	@Column(name = "phonenumber", length = 30)
	private String phoneNumber;

	@Column(name = "faxnumber", length = 30)
	private String faxNumber;

	@Column(name = "mobilenumber", length = 30)
	private String mobileNumber;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "website", length = 100)
	private String website;

	@Column(name = "latitude")
	private BigDecimal latitude;

	@Column(name = "longitude")
	private BigDecimal longitude;

	@Column(name = "active")
	private Boolean active;

	@OneToMany(mappedBy = "hotel")
	private Set<HotelRoom> hotelRooms;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Integer getHotelBrand() {
		return hotelBrand;
	}

	public void setHotelBrand(Integer hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<HotelRoom> getHotelRooms() {
		return hotelRooms;
	}

	public void setHotelRooms(Set<HotelRoom> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}

}
