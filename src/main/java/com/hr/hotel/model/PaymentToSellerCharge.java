package com.hr.hotel.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "xc_payment_to_seller_charge")
public class PaymentToSellerCharge extends BaseEntity {

	// @Id
	// @Column(name = "paymenyt_to_seller_charge_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer paymentToSellerChargeId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PaymentToSeller.class)
	@JoinColumn(name = "payment_to_seller_id", nullable = false)
	@Cascade(value = { CascadeType.DELETE })
	private PaymentToSeller paymentToSeller;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OrganizationCharges.class)
	@JoinColumn(name = "organization_charges_id", nullable = false)
	private OrganizationCharges organizationCharges;

	// this charge will count based on percentage
	@Column(name = "charges_percentage", length = 3)
	private Integer chanrgePercentage;

	// this charges will add in total amount
	@Column(name = "charge_amount")
	private BigDecimal chargeAmount;

	public PaymentToSeller getPaymentToSeller() {
		return paymentToSeller;
	}

	public void setPaymentToSeller(PaymentToSeller paymentToSeller) {
		this.paymentToSeller = paymentToSeller;
	}

	public OrganizationCharges getOrganizationCharges() {
		return organizationCharges;
	}

	public void setOrganizationCharges(OrganizationCharges organizationCharges) {
		this.organizationCharges = organizationCharges;
	}

	public Integer getChanrgePercentage() {
		return chanrgePercentage;
	}

	public void setChanrgePercentage(Integer chanrgePercentage) {
		this.chanrgePercentage = chanrgePercentage;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

}
