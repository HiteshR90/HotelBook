package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hr.hotel.model.HotelMaster;

public interface HotelMasterRepository extends BaseRepository<HotelMaster> {

	@Query(value = "SELECT DISTINCT city.name FROM HotelMaster as hotelMaster JOIN hotelMaster.city AS city")
	public List<String> getCities();
}
