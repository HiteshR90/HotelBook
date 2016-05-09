package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "xc_organization")
public class Organization extends BaseEntity {

	// @Id
	// @Column(name = "organization_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer organizationId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "organization_name", nullable = false, length = 50, unique = true)
	private String organizationName;

	// is used for provide list of payment gateway to end user
	@Column(name = "is_payment_receiver", nullable = false)
	private Boolean isPaymentReceiver;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public Boolean getIsPaymentReceiver() {
		return isPaymentReceiver;
	}

	public void setIsPaymentReceiver(Boolean isPaymentReceiver) {
		this.isPaymentReceiver = isPaymentReceiver;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public enum OraganizationType {

	}
}
