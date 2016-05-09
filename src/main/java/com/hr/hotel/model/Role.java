package com.hr.hotel.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "xc_role")
public class Role extends BaseEntity {

	// @Id
	// @Column(name = "roleid")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer roleId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "role_name", nullable = false, unique = true)
	private String roleName;

	// @Column(name = "is_anonymous", nullable = false)
	// private Boolean isAnonymous;

	@Column(name = "is_default", nullable = false)
	private Boolean isDefault;

	@Column(name = "active", nullable = false)
	private Boolean active;

	@OneToMany(mappedBy = "role")
	@Cascade(value = CascadeType.DELETE)
	private Set<UserRole> userRoles;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	// public Boolean getIsAnonymous() {
	// return isAnonymous;
	// }
	//
	// public void setIsAnonymous(Boolean isAnonymous) {
	// this.isAnonymous = isAnonymous;
	// }

}
