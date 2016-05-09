package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "xc_amenity")
public class Amenity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7085661506587237382L;

	@Column(name = "amenity_name", nullable = false, length = 50, unique = true)
	private String amenityName;

	@Column(name = "amenity_desc")
	private String amenityDesc;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "is_default")
	private Boolean isDefault;

	@OneToMany(mappedBy = "amenity")
	private List<RoomAmenity> roomAmenities = new ArrayList<RoomAmenity>(0);

	public String getAmenityName() {
		return amenityName;
	}

	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}

	public List<RoomAmenity> getRoomAmenities() {
		return roomAmenities;
	}

	public void setRoomAmenities(List<RoomAmenity> roomAmenities) {
		this.roomAmenities = roomAmenities;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAmenityDesc() {
		return amenityDesc;
	}

	public void setAmenityDesc(String amenityDesc) {
		this.amenityDesc = amenityDesc;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
