package com.hr.hotel.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.City;
import com.hr.hotel.model.State;

public interface StateRepository extends BaseRepository<State> {

	@Query(value = "SELECT state FROM State AS state WHERE state.name=:stateName")
	public State getStateByName(@Param("stateName") String stateName);
	
	@Query(value = "SELECT DISTINCT state.name FROM City as city JOIN city.state AS state where city =:city")
	public String getState(@Param("city")City city);
}
