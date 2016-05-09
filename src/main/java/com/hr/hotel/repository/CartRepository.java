package com.hr.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.Cart;

public interface CartRepository extends BaseRepository<Cart> {

	@Query(value = "SELECT cart.id FROM Cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.isCompleted=false")
	public List<Long> getActiveCartIds(@Param("email") String email);

	@Query(value = "UPDATE Cart AS cart SET cart.isCompleted=false WHERE cart.addBy.email=:email")
	public void deActiveAllCart(@Param("email") String email);

	@Query(value = "SELECT cart.isLocked AS isLocked,cart.id AS cartId,cart.unlockAt AS unlockAt FROM Cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.id=:cartId AND cart.isCompleted=false")
	public Cart getCartLockStatus(@Param("cartId") Long cartId,
			@Param("email") String email);

	@Query(value = "SELECT  SUM(DATEDIFF(cartDetail.endDate,cartDetail.startDate)*hotelRoom.oneNightCost) AS totalCost FROM CartDetail AS cartDetail JOIN cartDetail.hotelRoom AS hotelRoom JOIN cartDetail.cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.isCompleted=false AND cartDetail.releaseAt>=NOW()")
	public Double getCartAmount(@Param("email") String email);

	@Modifying
	@Query(value = "UPDATE Cart AS cart SET cart.isLocked=true,cart.unlockAt=:unlockAt WHERE cart.id=:cartId AND cart.isCompleted=false AND cart.isLocked=false")
	public Integer setCartLockAndReleaseTime(@Param("cartId") Long cartId,
			@Param("unlockAt") Date unlockAt);

	@Query(value = "UPDATE Cart AS cart SET cart.isCompleted=true WHERE cart.id=:cartId")
	public Integer confirmCart(@Param("cartId") Long cartId);

	@Query(value = "UPDATE Cart AS cart SET cart.isLocked=false WHERE cart.id=:cartId AND cart.unlockAt<NOW()")
	public Integer unlockCartWithTimeCheck(@Param("cartId") Long cartId);

	@Query(value = "UPDATE Cart AS cart SET cart.isLocked=false WHERE cart.id=:cartId")
	public Integer unlockCartWithoutTimeCheck(@Param("cartId") Long cartId);

	@Query(value = "SELECT cart FROM Cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.isCompleted=false")
	public Cart getCartId(@Param("email") String email);

	@Query(value = "SELECT cart FROM CartDetail AS cartDetail JOIN cartDetail.hotelRoom AS hotelRoom JOIN cartDetail.cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.isCompleted=false AND hotelRoom.id=:roomId")
	public Cart getCartRoomLockRleaseStatus(@Param("email") String email,
			@Param("roomId") Long roomId);
}
