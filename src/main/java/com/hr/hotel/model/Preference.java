package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "xc_preference")
@Entity
public class Preference extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "high_floor")
	private Boolean highFloor;

	@Column(name = "early_check_in")
	private Boolean earlyCheckIn;

	@Column(name = "late_check_out")
	private Boolean lateCheckOut;

	@Column(name = "hotel_star")
	private Integer hotelStar;

	@Column(name = "room_type")
	private String roomType;

	@Column(name = "hotel_brand")
	private String hotelBrand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "priority", nullable = false)
	private Integer priority;

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

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getHotelBrand() {
		return hotelBrand;
	}

	public void setHotelBrand(String hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
