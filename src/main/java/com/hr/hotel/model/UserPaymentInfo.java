package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "xc_user_payment_info")
@DynamicUpdate
public class UserPaymentInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "info_id")
	// private Integer infoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "paypal_id")
	private String paypalId;

	@Column(name = "bank_account_number")
	private String bankAccountNumber;

	@Column(name = "bank_routing_number")
	private String bankRoutingNumber;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankRoutingNumber() {
		return bankRoutingNumber;
	}

	public void setBankRoutingNumber(String bankRoutingNumber) {
		this.bankRoutingNumber = bankRoutingNumber;
	}

}
