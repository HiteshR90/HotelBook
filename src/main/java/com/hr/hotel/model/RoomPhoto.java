package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_room_photos", uniqueConstraints = @UniqueConstraint(columnNames = {
		"hotel_room_id", "photo_id" }))
public class RoomPhoto extends BaseEntity {

	// @Id
	// @Column(name = "room_photo_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer roomPhotoId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "photo_id", nullable = false)
	private Photo photo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_room_id", nullable = false)
	private HotelRoom hotelRoom;

	@Column(name = "is_default", nullable = false)
	private Boolean isDefault;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
