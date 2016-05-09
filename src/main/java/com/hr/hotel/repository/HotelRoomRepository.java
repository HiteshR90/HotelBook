package com.hr.hotel.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.BookRoomForSellDto;
import com.hr.hotel.dto.HotelRoomData;
import com.hr.hotel.dto.RoomBookData;
import com.hr.hotel.model.HotelRoom;

public interface HotelRoomRepository extends BaseRepository<HotelRoom> {

	/**
	 * getHotelRoomForBook
	 * 
	 * @param roomId
	 * @return
	 */
	@Query(value = "SELECT NEW com.hr.onesuite.dto.RoomBookData(hotelRoom.id,hotelRoom.oneNightCost,hotelMaster.hotelName,hotelMaster.id,hotelRoom.isFullSuite,city.name,roomType.roomType,hotelMaster.hotelBrand,hotelRoom.roomDesc,hotelMaster.longitude,hotelMaster.latitude,hotelRoom.startDate,hotelRoom.endDate) FROM HotelRoom AS hotelRoom JOIN hotelRoom.roomType AS roomType JOIN hotelRoom.hotel AS hotelMaster JOIN hotelMaster.city AS city WHERE hotelRoom.id=:roomId AND hotelRoom.isFullBooked=false")
	public RoomBookData getHotelRoomForBook(@Param("roomId") Long roomId);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.BookRoomForSellDto(hotelRoom.id,hotelMaster.id,hotelMaster.hotelName,hotelMaster.hotelBrand,city.name,hotelRoom.oneNightCost,roomType.roomType,hotelRoom.roomDesc,orderDetail.startDate,orderDetail.endDate,DATEDIFF(orderDetail.endDate,orderDetail.startDate),DATEDIFF(orderDetail.endDate,orderDetail.startDate)*hotelRoom.oneNightCost) FROM OrderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.roomType AS roomType JOIN orderDetail.order AS objOrder JOIN objOrder.orderFor AS user JOIN hotelRoom.hotel AS hotelMaster JOIN hotelMaster.city AS city WHERE orderDetail.sellFlag=false AND orderDetail.isCancel=false AND orderDetail.endDate>=NOW() AND user.email=:email AND objOrder.id=:orderId AND hotelRoom.id=:roomId")
	public BookRoomForSellDto getBookedRoom(@Param("orderId") Long orderId,
			@Param("roomId") Long roomId, @Param("email") String email);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.HotelRoomData(hotelMaster.hotelName,hotelMaster.address,roomType.roomType,hotelRoom.id,hotelRoom.startDate,hotelRoom.endDate,hotelRoom.noOfAdults,hotelRoom.oneNightCost,hotelMaster.hotelBrand,city.name,hotelRoom.isFullSuite) FROM HotelRoom AS hotelRoom JOIN hotelRoom.roomType AS roomType JOIN hotelRoom.hotel AS hotelMaster JOIN hotelMaster.city AS city WHERE hotelRoom.id=:roomId")
	public HotelRoomData getHotelRoomData(@Param("roomId") Long roomId);

	/**
	 * isRoomAvailable check that room is posted for given date or not
	 * 
	 * @param roomId
	 *            id of room for which date is checked
	 * @param startDate
	 *            date for room start date
	 * @param endDate
	 *            date for room end date
	 * @return true if room is available else return false
	 */
	@Query(value = "SELECT COUNT(hotelRoom.id) FROM CartDetail AS cartDetail JOIN cartDetail.cart AS cart JOIN cartDetail.hotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId AND cart.isCompleted=false AND cartDetail.releaseAt>NOW()")
	public Long getAvailableRoomFromCartDetail(@Param("roomId") Long roomId);

	@Query(value = "SELECT COUNT(hotelRoom.id) FROM OrderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId AND ((orderDetail.startDate=:startDate AND orderDetail.endDate=:endDate) OR (orderDetail.startDate>:startDate AND orderDetail.startDate<:endDate) OR (orderDetail.endDate>:startDate AND orderDetail.endDate<:endDate))")
	public Long getAvailableFromOrderDetail(@Param("roomId") Long roomId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "SELECT COUNT(hotelRoom.id)  FROM HotelRoom AS hotelRoom  WHERE hotelRoom.startDate<=:startDate  AND hotelRoom.endDate>=:endDate  AND hotelRoom.id=:roomId AND hotelRoom.isFullBooked=false AND :endDate>:startDate AND :startDate>=CURDATE()")
	public Long getAvailableRoomFromHotelRoom(@Param("roomId") Long roomId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "SELECT hotelRoom FROM HotelRoom AS hotelRoom WHERE hotelRoom.isFullBooked=false AND hotelRoom.id=:roomId")
	public HotelRoom getHotelRoom(@Param("roomId") Long roomId);

	/**
	 * roomCost count room cost between given start and end date
	 * 
	 * @param roomId
	 * @param startDate
	 * @param endDate
	 *            must be greater than startDate
	 * @return
	 */
	@Query(value = "SELECT DATEDIFF(:endDate,:startDate)*hotelRoom.oneNightCost FROM HotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId")
	public Double roomCost(@Param("roomId") Long roomId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * isFullyBooked check that room is fully booked or not by roomId
	 * 
	 * @param roomId
	 *            to check is fully booked
	 * @return true if room is fully booked else return false
	 */
	// @Query(value =
	// "SELECT hotelRoom.startDate AS startDate,hotelRoom.endDate AS endDate,hotelRoom.id AS roomid FROM OrderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId GROUP BY hotelRoom.id HAVING DATEDIFF(hotelRoom.endDate,hotelRoom.startDate)=SUM(DATEDIFF(orderDetail.endDate,orderDetail.startDate))")
	@Query(nativeQuery = true, value = "SELECT CASE WHEN EXISTS(SELECT hotelroom1_.start_date, hotelroom1_.end_date, hotelroom1_.id FROM xc_order_detail orderdetai0_ INNER JOIN xc_hotel_room hotelroom1_ ON orderdetai0_.hotel_room_id=hotelroom1_.id WHERE hotelroom1_.id=:roomId GROUP BY hotelroom1_.id HAVING DATEDIFF(hotelroom1_.end_date, hotelroom1_.start_date)=SUM(DATEDIFF(orderdetai0_.end_date, orderdetai0_.start_date))) THEN 'TRUE' ELSE 'FALSE' END")
	public boolean isFullyBooked(@Param("roomId") Long roomId);

	@Modifying
	@Query(value = "UPDATE HotelRoom SET isFullBooked=TRUE WHERE ID=:roomId")
	public void setIsFullBookHotelRoom(@Param("roomId") Long roomId);

	/**
	 * getOrderedRoomBetween check that any confirmed room found between start
	 * date and end date
	 * 
	 * @param roomId
	 *            if of room to check
	 * @param startDate
	 *            date start rang
	 * @param endDate
	 *            date end rang
	 * @return room id if room is booked between start and end date
	 */
	@Query(value = "SELECT DISTINCT hotelRoom.id  FROM OrderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom WHERE hotelRoom.id=:roomId AND (orderDetail.startDate BETWEEN :startDate AND :endDate OR orderDetail.endDate BETWEEN :startDate AND :endDate)")
	public Long getOrderedRoomBetween(@Param("roomId") Long roomId,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query("SELECT hotelMaster.id From HotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotelMaster where hotelRoom.id =:roomId AND hotelRoom.active =TRUE")
	public Long getHotelFromRoomId(@Param("roomId") Long roomId);
}
