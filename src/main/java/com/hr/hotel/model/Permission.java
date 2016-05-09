package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "xc_permission", uniqueConstraints = @UniqueConstraint(columnNames = {
		"url", "method" }))
public class Permission extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "permission_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer permissionId;

	@Column(name = "permission", nullable = false, length = 50)
	private String permission;

	@Column(name = "url", nullable = false, length = 50)
	private String url;

	@Column(name = "method", nullable = false, length = 10)
	private String method;

	@Column(name = "active", nullable = false)
	private Boolean active;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
