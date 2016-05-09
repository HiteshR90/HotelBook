package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "xc_rating")
@Entity
@DynamicInsert
@DynamicUpdate
public class Rating extends BaseEntity {

	// @Id
	// @Column(name = "rating_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer ratingId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "comment")
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = HotelRoom.class)
	@JoinColumn(name = "room_id", nullable = false)
	private HotelRoom hotelRoom;

	@Column(name = "is_hotel_rating", nullable = false)
	private Boolean isHotelRating;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public Boolean getIsHotelRating() {
		return isHotelRating;
	}

	public void setIsHotelRating(Boolean isHotelRating) {
		this.isHotelRating = isHotelRating;
	}

}
