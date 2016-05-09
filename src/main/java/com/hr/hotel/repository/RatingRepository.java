package com.hr.hotel.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.Rating;

public interface RatingRepository extends BaseRepository<Rating> {

	@Query(value = "SELECT rating.rating AS rate ,rating.comment AS comment FROM Rating AS rating JOIN rating.order AS objOrder JOIN rating.hotelRoom AS hotelRoom WHERE objOrder.id=:orderId AND hotelRoom.id=:roomId AND rating.isHotelRating=:isHotelRating")
	public Map<String, Object> getRating(@Param("orderId") Long orderId,
			@Param("roomId") Long roomId,
			@Param("isHotelRating") Boolean isHotelRating);

	// public List<Map<String, Object>> getUserRating(String email,
	// Integer bookingId);

	@Query(value = "SELECT rating.id AS ratingId FROM Rating AS rating JOIN rating.order AS objOrder JOIN rating.hotelRoom AS hotelRoom WHERE objOrder.id=:orderId AND hotelRoom.id=:roomId AND rating.isHotelRating=:isHotelRating")
	public Long getRatingIdByOrderRoomAndEmail(@Param("orderId") Long orderId,
			@Param("roomId") Long roomId,
			@Param("isHotelRating") Boolean isHotelRating);

	@Modifying
	@Query(value = "UPDATE Rating AS rating SET rating.rating=:rating,rating.comment=:comment WHERE rating.id=:ratingId")
	public Integer update(@Param("ratingId") Long ratingId,
			@Param("rating") Integer rating, @Param("comment") String comment);
}
