package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "xc_premier_list")
public class PremierList extends BaseEntity {
	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "premier_list_id")
	// private Integer premierlistId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "room_id")
	private HotelRoom room;

	@Column(name = "premier_price")
	private Integer premierPrice;

	public HotelRoom getRoom() {
		return room;
	}

	public void setRoom(HotelRoom room) {
		this.room = room;
	}

	public Integer getPremierPrice() {
		return premierPrice;
	}

	public void setPremierPrice(Integer premierPrice) {
		this.premierPrice = premierPrice;
	}

}
