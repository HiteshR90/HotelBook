package com.hr.hotel.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "xc_organization_charges")
public class OrganizationCharges extends BaseEntity {

	// @Id
	// @Column(name = "payment_charges_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer payemntChargesId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// if true then this charge will default apply at transaction
	@Column(name = "is_default_chareges", nullable = false)
	private Boolean isDefaultCharges;

	// @Column(name = "prority", nullable = false)
	// private Integer priority;

	// this charge will count based on percentage
	@Column(name = "charges_percentage", length = 3)
	private BigDecimal chanrgePercentage;

	// this charges will add in total amount
	@Column(name = "charge_amount")
	private BigDecimal chargeAmount;

	// this flag true than percentage will apply to amount first and after that
	// charge amount will added else vice versa
	@Column(name = "is_percentage_first", nullable = false)
	private Boolean isPercentageFirst;

	@Column(name = "is_buyer_charge", nullable = false)
	private Boolean isBuyerCharge;

	@Column(name = "is_seller_charge", nullable = false)
	private Boolean isSellerCharge;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Organization.class)
	@JoinColumn(name = "organization_id", nullable = false, unique = true)
	private Organization organization;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public BigDecimal getChanrgePercentage() {
		return chanrgePercentage;
	}

	public void setChanrgePercentage(BigDecimal chanrgePercentage) {
		this.chanrgePercentage = chanrgePercentage;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Boolean getIsPercentageFirst() {
		return isPercentageFirst;
	}

	public void setIsPercentageFirst(Boolean isPercentageFirst) {
		this.isPercentageFirst = isPercentageFirst;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIsBuyerCharge() {
		return isBuyerCharge;
	}

	public void setIsBuyerCharge(Boolean isBuyerCharge) {
		this.isBuyerCharge = isBuyerCharge;
	}

	public Boolean getIsSellerCharge() {
		return isSellerCharge;
	}

	public void setIsSellerCharge(Boolean isSellerCharge) {
		this.isSellerCharge = isSellerCharge;
	}

	public Boolean getIsDefaultCharges() {
		return isDefaultCharges;
	}

	public void setIsDefaultCharges(Boolean isDefaultCharges) {
		this.isDefaultCharges = isDefaultCharges;
	}

}
