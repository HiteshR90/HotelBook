package com.hr.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.Assert;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * All objects will have a unique UUID which allows for the decoupling from
	 * DB generated ids
	 * 
	 */
	@Column(length = 36)
	private String uuid;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Column(name = "modified_date")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;

	public BaseEntity() {
		this(UUID.randomUUID());
		this.createdDate = new Date();
	}

	public BaseEntity(UUID guid) {
		Assert.notNull(guid, "UUID is required");
		setUuid(guid.toString());
		this.createdDate = new Date();
	}

	public UUID getUuid() {
		return UUID.fromString(uuid);
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int hashCode() {
		return getUuid().hashCode();
	}

	/**
	 * In most instances we can rely on the UUID to identify the object.
	 * Subclasses may want a user friendly identifier for constructing easy to
	 * read urls
	 * 
	 * <code>
	 *    /user/1883c578-76be-47fb-a5c1-7bbea3bf7fd0 using uuid as the identifier
	 * 
	 *    /user/jsmith using the username as the identifier
	 * 
	 * </code>
	 * 
	 * @return Object unique identifier for the object
	 */
	public Object getIdentifier() {
		return getUuid().toString();
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
