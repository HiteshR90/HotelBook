package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_payment_from_buyer", uniqueConstraints = @UniqueConstraint(columnNames = {
		"transaction_id", "cart_id" }))
public class PaymentFromBuyer extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "payment_from_buyer")
	// private Integer paymentFromBuyerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	@Column(name = "transaction_id", nullable = false, length = 50)
	private String transactionId;

	// @Column(name = "intent", nullable = false, length = 10)
	// private String intent;

	@Column(name = "cart_amount", nullable = false)
	private Double cartAmount;

	@Column(name = "total_amount")
	private Double totalAmount;

	// @Column(name = "total_transferable_amount")
	// private Double totalTransferableAmount;

	@Column(name = "state", length = 20, nullable = false)
	private String state;

	@Column(name = "active", nullable = false)
	private Boolean active;

	// @Column(name = "paypel_url", length = 100)
	// private String paypalUrl;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	// public String getPaypalUrl() {
	// return paypalUrl;
	// }
	//
	// public void setPaypalUrl(String paypalUrl) {
	// this.paypalUrl = paypalUrl;
	// }

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Double getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(Double cartAmount) {
		this.cartAmount = cartAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	// public String getIntent() {
	// return intent;
	// }
	//
	// public void setIntent(String intent) {
	// this.intent = intent;
	// }

}
