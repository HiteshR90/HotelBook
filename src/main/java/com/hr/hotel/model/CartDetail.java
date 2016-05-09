package com.hr.hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.hr.hotel.common.Constant;
import com.hr.hotel.util.DateUtil;

@Entity
@Table(name = "xc_cart_detail")
public class CartDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "cart_detail_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Long cartDetailId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Cart.class)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = HotelRoom.class)
	@JoinColumn(name = "hotel_room_id", nullable = false)
	private HotelRoom hotelRoom;

	@Column(name = "start_date", nullable = false)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "release_at", nullable = false)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT_WITH_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	private Date releaseAt;

	// @Column(name = "is_under_process")
	// private Boolean isUnderProcess;

	// public Long getCartDetailId() {
	// return cartDetailId;
	// }
	//
	// public void setCartDetailId(Long cartDetailId) {
	// this.cartDetailId = cartDetailId;
	// }

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public Date getReleaseAt() {
		return releaseAt;
	}

	public void setReleaseAt(Date releaseAt) {
		this.releaseAt = releaseAt;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// public Boolean getIsUnderProcess() {
	// return isUnderProcess;
	// }
	//
	// public void setIsUnderProcess(Boolean isUnderProcess) {
	// this.isUnderProcess = isUnderProcess;
	// }

	public void setCartDetail(Cart cart, User loginUser, Date startDate,
			Date endDate, HotelRoom hotelRoom) {
		this.setCart(cart);
		this.setCreatedBy(loginUser.getId());
		this.setEndDate(endDate);
		this.setHotelRoom(hotelRoom);
		this.setStartDate(startDate);
		this.setReleaseAt(DateUtil.getAddedDate(0, Constant.RELEASE_MINUTE, 0));
	}
}
