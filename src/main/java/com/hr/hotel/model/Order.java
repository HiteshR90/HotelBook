package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "xc_order")
public class Order extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2374274845232541673L;

	// @Id
	// @Column(name = "order_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer orderId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_by", nullable = false)
	private User orderBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_for", nullable = false)
	private User orderFor;

	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails = new ArrayList<OrderDetail>(0);

	public User getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(User orderBy) {
		this.orderBy = orderBy;
	}

	public User getOrderFor() {
		return orderFor;
	}

	public void setOrderFor(User orderFor) {
		this.orderFor = orderFor;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
