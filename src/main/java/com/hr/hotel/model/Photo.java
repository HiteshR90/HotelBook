package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "xc_photo")
public class Photo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "photo_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer photoId;

	@Column(name = "photo_name", nullable = false, length = 100)
	private String photoName;

	@Column(name = "photo_desc")
	private String photoDesc;

	@Column(name = "path", nullable = false, unique = true)
	private String path;

	@Column(name = "is_default")
	private Boolean isDefault;

	@OneToMany(mappedBy = "photo")
	private List<RoomPhoto> roomPhotos = new ArrayList<RoomPhoto>(0);

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoDesc() {
		return photoDesc;
	}

	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<RoomPhoto> getRoomPhotos() {
		return roomPhotos;
	}

	public void setRoomPhotos(List<RoomPhoto> roomPhotos) {
		this.roomPhotos = roomPhotos;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
