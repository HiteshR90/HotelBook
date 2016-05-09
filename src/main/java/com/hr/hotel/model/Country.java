package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "xc_country")
public class Country extends BaseEntity {

	// @Id
	// @Column(name = "country_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer countryId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5099745502750547866L;

	@Column(name = "name", length = 60, nullable = false, unique = true)
	private String name;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "country")
	List<State> states = new ArrayList<State>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
