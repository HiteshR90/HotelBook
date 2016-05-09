package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.RoomPhoto;

public interface RoomPhotoRepository extends BaseRepository<RoomPhoto> {

	@Query(value = "SELECT photo.path AS path FROM RoomPhoto AS roomPhoto JOIN roomPhoto.photo AS photo JOIN roomPhoto.hotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId")
	public List<String> getRoomPhotosPath(@Param("roomId") Long roomId);

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_room_photos(photo_id,hotel_room_id,is_default,created_by,created_date,modified_by,modified_date,active) SELECT photo_id,:targetRoomId,is_default,created_by,created_date,modified_by,modified_date,TRUE FROM xc_room_photos WHERE hotel_room_id=:sourceRoomId")
	public Integer copyPhotos(@Param("sourceRoomId") Long sourceRoomId,
			@Param("targetRoomId") Long targetRoomId);
}
