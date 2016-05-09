package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.RoomType;

public interface RoomTypeRepository extends BaseRepository<RoomType> {

	@Query(value = "SELECT roomType FROM RoomType AS roomType WHERE roomType.roomType=:roomType")
	public RoomType getRoomTypeByName(@Param("roomType") String roomType);
	
	@Query(value = "SELECT roomType.roomType FROM RoomType AS roomType")
	public List<String> getRoomTypeNames();
}
