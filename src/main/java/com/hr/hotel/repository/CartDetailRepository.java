package com.hr.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.CartRoomDto;
import com.hr.hotel.model.CartDetail;

public interface CartDetailRepository extends BaseRepository<CartDetail> {
	/**
	 * getCartRoomDetail return list of map contain room data which rooms are in
	 * cart
	 * 
	 * @param email
	 *            login user email
	 * @return list of map
	 */
	@Query(value = "SELECT NEW com.hr.onesuite.dto.CartRoomDto(hotelRoom.id,hotel.hotelName,hotel.address,roomType.roomType,cartDetail.startDate,cartDetail.endDate,DATEDIFF(cartDetail.endDate,cartDetail.startDate)*hotelRoom.oneNightCost,hotelRoom.oneNightCost,TIMESTAMPDIFF(SECOND,NOW(),cartDetail.releaseAt)) FROM CartDetail  AS cartDetail JOIN cartDetail.cart AS cart JOIN cart.addBy AS user JOIN cartDetail.hotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotel JOIN hotelRoom.roomType AS roomType WHERE user.email=:email AND cart.isCompleted=false AND cartDetail.releaseAt>NOW()")
	public List<CartRoomDto> getCartRoomDetail(@Param("email") String email);

	/**
	 * deleteByRoomIdCartId delete from cart detail table based on roomId and
	 * cartId
	 * 
	 * @param roomId
	 *            to delete
	 * @param cartId
	 *            to delete
	 */
	@Modifying
	@Query(value = "DELETE FROM CartDetail AS cartDetail WHERE cartDetail.cart.id=:cartId AND cartDetail.hotelRoom.id=:roomId")
	public void deleteByRoomIdCartId(@Param("roomId") Long roomId,
			@Param("cartId") Long cartId);

	@Modifying
	@Query(value = "DELETE FROM CartDetail AS cartDetail WHERE cartDetail.cart.addBy.email=:email AND cartDetail.hotelRoom.id=:roomId")
	public void deleteByRoomIdEmail(@Param("roomId") Long roomId,
			@Param("email") String email);

	@Query(value = "SELECT cartDetail FROM CartDetail  AS cartDetail JOIN cartDetail.cart AS cart JOIN cart.addBy AS user JOIN cartDetail.hotelRoom AS hotelRoom WHERE user.email=:email AND hotelRoom.id=:roomId AND cart.isCompleted=false AND cartDetail.releaseAt>NOW()")
	public List<CartDetail> getCartRoom(@Param("email") String email,
			@Param("roomId") Long roomId);

	@Modifying
	// @Query(nativeQuery = true, value =
	// "UPDATE `xc_cart_detail` SET release_at=CASE WHEN release_at<(:cartReleaseTime) THEN (:cartReleaseTime) ELSE release_at END WHERE release_at>=NOW() AND id=:cartId")
	@Query(nativeQuery = true, value = "UPDATE `xc_cart_detail` AS cart_detail INNER JOIN `xc_cart` AS cart ON `cart`.`id`=`cart_detail`.`cart_id` SET cart_detail.release_at=CASE WHEN cart_detail.release_at<:cartExtReleaseTime THEN :cartExtReleaseTime ELSE cart_detail.release_at+INTERVAL 1 SECOND END WHERE cart_detail.`release_at`>=NOW() AND `cart`.`id`=:cartId")
	public Integer updateCartDetailTime(@Param("cartId") Long cartId,
			@Param("cartExtReleaseTime") Date cartExtReleaseTime);

	// public Integer unlockCartDetail(Integer cartId, Date releaseAt);
}
