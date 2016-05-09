package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_delegate", uniqueConstraints = @UniqueConstraint(columnNames = {
		"assigner_user_id", "delegate_user_id" }))
public class DelegateUser extends BaseEntity {
	// @Id
	// @Column(name = "delegaet_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer delegateId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6658080294646176904L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigner_user_id", nullable = false)
	private User assignerUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delegate_user_id", nullable = false)
	private User delegateUser;

	@Column(name = "active", nullable = false)
	private Boolean active;

	// @Column(name = "is_verified")
	// private Boolean isVerified;

	public Boolean getActive() {
		return active;
	}

	public User getAssignerUser() {
		return assignerUser;
	}

	public void setAssignerUser(User assignerUser) {
		this.assignerUser = assignerUser;
	}

	public User getDelegateUser() {
		return delegateUser;
	}

	public void setDelegateUser(User delegateUser) {
		this.delegateUser = delegateUser;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
