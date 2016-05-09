package com.hr.hotel.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "xc_sell")
public class Sell extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "sell_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer sellId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_room", nullable = false, unique = true)
	private HotelRoom hotelRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sell_by", nullable = false)
	private User sellBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sell_for", nullable = false)
	private User sellFor;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
	@JoinColumn(name = "organization_id", nullable = false)
	private Organization organization;

	// @OneToMany(mappedBy = "sell")
	// private List<BookedSell> bookedSells = new ArrayList<BookedSell>(0);

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	// public List<BookedSell> getBookedSells() {
	// return bookedSells;
	// }
	//
	// public void setBookedSells(List<BookedSell> bookedSells) {
	// this.bookedSells = bookedSells;
	// }

	public User getSellBy() {
		return sellBy;
	}

	public void setSellBy(User sellBy) {
		this.sellBy = sellBy;
	}

	public User getSellFor() {
		return sellFor;
	}

	public void setSellFor(User sellFor) {
		this.sellFor = sellFor;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}