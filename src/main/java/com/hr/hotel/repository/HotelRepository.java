package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.HotelDetailDto;
import com.hr.hotel.model.HotelMaster;

public interface HotelRepository extends BaseRepository<HotelMaster> {

	@Query(value = "SELECT new com.hr.onesuite.dto.HotelDetailDto(hotelMaster.id,hotelMaster.hotelName,hotelMaster.address) FROM HotelMaster AS hotelMaster")
	public List<HotelDetailDto> getAllHotels();

	@Query(value = "SELECT new com.hr.onesuite.dto.HotelDetailDto(hotelMaster.id,CONCAT(hotelMaster.hotelName,', ',city.name,', ',state.name,', ',country.name),CONCAT(hotelMaster.address,', ',city.name,', ',state.name,', ',country.name)) FROM HotelMaster AS hotelMaster JOIN hotelMaster.city AS city JOIN city.state AS state JOIN state.country AS country WHERE hotelMaster.hotelName LIKE %:hotelName%")
	public List<HotelDetailDto> getHotels(@Param("hotelName") String hotelName);
}
