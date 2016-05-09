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

@Table(name = "xc_state", uniqueConstraints = @UniqueConstraint(columnNames = {
		"name", "country_id" }))
@Entity
public class State extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "state_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer stateId;

	@Column(name = "name", nullable = false, length = 70, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "state")
	private List<City> cities = new ArrayList<City>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
