package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.RoomAmenity;

public interface RoomAmenityRepository extends BaseRepository<RoomAmenity> {

	@Query(value = "SELECT amenity.amenityName FROM RoomAmenity AS roomAmenity JOIN roomAmenity.amenity AS amenity JOIN roomAmenity.hotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId")
	public List<String> getRoomAmenities(@Param("roomId") Long roomId);

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_room_amenity(hotel_room_id,amenity_id,created_by,created_date,modified_by,modified_date,active) SELECT :targetRoomId,amenity_id,created_by,created_date,modified_by,modified_date,TRUE FROM xc_room_amenity WHERE hotel_room_id=:sourceRoomId")
	public Integer copyAmenities(@Param("sourceRoomId") Long sourceRoomId,
			@Param("targetRoomId") Long targetRoomId);
}
