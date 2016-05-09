package com.hr.hotel.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "xc_payment_from_buyer_charge", uniqueConstraints = @UniqueConstraint(columnNames = {
		"payment_from_buyer_id", "organization_charges_id" }))
public class PaymentFromBuyerCharge extends BaseEntity {

	// @Id
	// @Column(name = "paymenyt_from_buyer_charge_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer paymentFromBuyerChargeId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = PaymentFromBuyer.class)
	@JoinColumn(name = "payment_from_buyer_id", nullable = false)
	@Cascade(value = { CascadeType.DELETE })
	private PaymentFromBuyer paymentFromBuyer;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OrganizationCharges.class)
	@JoinColumn(name = "organization_charges_id", nullable = false)
	private OrganizationCharges organizationCharges;

	// this charge will count based on percentage
	@Column(name = "charges_percentage", length = 3)
	private Integer chanrgePercentage;

	// this charges will add in total amount
	@Column(name = "charge_amount")
	private BigDecimal chargeAmount;

	public PaymentFromBuyer getPaymentFromBuyer() {
		return paymentFromBuyer;
	}

	public void setPaymentFromBuyer(PaymentFromBuyer paymentFromBuyer) {
		this.paymentFromBuyer = paymentFromBuyer;
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
