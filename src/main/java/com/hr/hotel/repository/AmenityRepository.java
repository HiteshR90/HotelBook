package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hr.hotel.model.Amenity;

public interface AmenityRepository extends BaseRepository<Amenity> {

	@Query(value = "SELECT DISTINCT amenity.amenityName FROM Amenity as amenity")
	public List<String> getAmenity();

	@Query(value="SELECT amenity FROM Amenity AS amenity WHERE amenity.isDefault=TRUE")
	public List<Amenity> getDefaultAmenities();
}
