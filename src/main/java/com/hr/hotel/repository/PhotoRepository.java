package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hr.hotel.model.Photo;

public interface PhotoRepository extends BaseRepository<Photo> {
	@Query(value = "SELECT photo FROM Photo AS photo WHERE photo.isDefault=TRUE")
	public List<Photo> getDefaultAmenities();
}
