package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "xc_ordered_sell")
@Entity
public class OrderedSell extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "ordered_sell_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer orderedSellId;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OrderDetail.class)
	@JoinColumn(name = "order_detail_id", nullable = false)
	private OrderDetail orderDetail;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Sell.class)
	@JoinColumn(name = "sell_id", nullable = false)
	private Sell sell;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
