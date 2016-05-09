package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.City;

public interface CityRepository extends BaseRepository<City> {

	@Query(value = "SELECT city FROM City AS city WHERE city.name=:cityName")
	public City getCityByName(@Param("cityName") String cityName);
}
