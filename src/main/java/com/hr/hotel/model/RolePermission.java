package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Administrator
 * 
 */
@Entity
@Table(name = "xc_role_permission", uniqueConstraints = @UniqueConstraint(columnNames = {
		"role_id", "permisson_id" }))
public class RolePermission extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "role_permission_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer rolePermissionId;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Permission.class)
	@JoinColumn(name = "permisson_id", nullable = false)
	private Permission permission;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
