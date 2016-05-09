package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_city", uniqueConstraints = @UniqueConstraint(columnNames = {
		"name", "state_id" }))
public class City extends BaseEntity {

	// @Id
	// @Column(name = "city_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Long cityId;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1941116522251548712L;

	@Column(name = "name", nullable = false, length = 60, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = State.class)
	@JoinColumn(name = "state_id", nullable = false)
	private State state;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "city")
	private List<HotelMaster> hotelMasters = new ArrayList<HotelMaster>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<HotelMaster> getHotelMasters() {
		return hotelMasters;
	}

	public void setHotelMasters(List<HotelMaster> hotelMasters) {
		this.hotelMasters = hotelMasters;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
