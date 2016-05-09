package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_room_amenity", uniqueConstraints = @UniqueConstraint(columnNames = {
		"hotel_room_id", "amenity_id" }))
public class RoomAmenity extends BaseEntity {

	// @Id
	// @Column(name = "room_amenity_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer roomAmenityId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_room_id", nullable = false)
	private HotelRoom hotelRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "amenity_id", nullable = false)
	private Amenity amenity;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
