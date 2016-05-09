package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "xc_room_type")
public class RoomType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "room_type_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer roomTypeId;

	@Column(name = "room_type", nullable = false, length = 50, unique = true)
	private String roomType;

	@Column(name = "room_desc")
	private String roomDesc;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "roomType")
	private List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>(0);

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public List<HotelRoom> getHotelRooms() {
		return hotelRooms;
	}

	public void setHotelRooms(List<HotelRoom> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
