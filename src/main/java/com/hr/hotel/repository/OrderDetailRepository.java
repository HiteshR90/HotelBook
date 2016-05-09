package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.BookHistoryDto;
import com.hr.hotel.dto.BookRoomMailData;
import com.hr.hotel.dto.ConfirmedRoomDto;
import com.hr.hotel.dto.PaymentToSellerData;
import com.hr.hotel.dto.SellRoomMailData;
import com.hr.hotel.model.OrderDetail;

public interface OrderDetailRepository extends BaseRepository<OrderDetail> {

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_order_detail(order_id,hotel_room_id,is_cancel,start_date,end_date,created_by,created_date,sell_flag,version) SELECT :orderId,hotel_room_id,false,start_date,end_date,:createdBy,NOW(),false,1 FROM xc_cart_detail WHERE cart_id=:cartId AND `release_at`>NOW()")
	public Integer insertSelect(@Param("orderId") Long orderId,
			@Param("cartId") Long cartId, @Param("createdBy") Long createdBy);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.BookRoomMailData(hotel.hotelName,orderDetail.startDate,orderDetail.endDate,hotelRoom.oneNightCost) FROM OrderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotel JOIN orderDetail.order AS objOrder WHERE objOrder.id=:orderId")
	public List<BookRoomMailData> getBookRoomMailData(
			@Param("orderId") Long orderId);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.SellRoomMailData(hotel.hotelName,orderDetail.startDate,orderDetail.endDate,userSellFor.userName,userSellFor.email,hotelRoom.oneNightCost) FROM OrderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotel LEFT JOIN hotelRoom.sells AS sell JOIN sell.sellFor AS userSellFor WHERE orderDetail.id=:orderDetailId")
	public SellRoomMailData getSellRoomMailData(
			@Param("orderDetailId") Long orderDetailId);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.PaymentToSellerData(orderDetail.id,hotelRoom.id,DATEDIFF(orderDetail.endDate,orderDetail.startDate)*hotelRoom.oneNightCost,userPaymentInfo.paypalId) FROM OrderDetail AS orderDetail JOIN orderDetail.order AS objOrder JOIN objOrder.orderBy AS orderByUser JOIN orderDetail.hotelRoom AS hotelRoom LEFT JOIN hotelRoom.sells AS sell JOIN sell.sellFor AS userSellFor LEFT JOIN userSellFor.userPaymentInfos AS userPaymentInfo WHERE objOrder.id=:orderId AND orderByUser.email=:email")
	public List<PaymentToSellerData> getPaymentToSellerData(
			@Param("orderId") Long orderId, @Param("email") String email);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.ConfirmedRoomDto(objOrder.id,hotelRoom.id,hotelMaster.id,hotelMaster.hotelName,hotelMaster.hotelBrand,city.name,hotelRoom.oneNightCost,roomType.roomType,hotelRoom.roomDesc,orderDetail.startDate,orderDetail.endDate,photo.path) FROM OrderDetail AS orderDetail JOIN orderDetail.order AS objOrder JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.roomType AS roomType JOIN hotelRoom.hotel AS hotelMaster JOIN hotelMaster.city AS city JOIN objOrder.orderFor AS userOrderFor LEFT JOIN hotelRoom.roomPhotos AS roomPhotos  LEFT JOIN roomPhotos.photo AS photo WHERE userOrderFor.email=:email AND roomPhotos.isDefault=true AND orderDetail.endDate>NOW() AND orderDetail.isCancel=false AND orderDetail.sellFlag=false")
	public List<ConfirmedRoomDto> getConfirmBookedRooms(
			@Param("email") String email);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.BookHistoryDto(objOrder.id,hotelRoom.id,hotelMaster.hotelName,hotelRoom.oneNightCost,orderDetail.createdDate,orderDetail.startDate,orderDetail.endDate,orderDetail.sellFlag) FROM OrderDetail AS orderDetail JOIN orderDetail.order AS objOrder JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotelMaster JOIN objOrder.orderFor AS userOrderFor WHERE userOrderFor.email=:email ORDER BY orderDetail.createdDate DESC")
	public List<BookHistoryDto> getBookHistory(@Param("email") String email);

	@Query(value = "SELECT objOrder.id AS orderId FROM OrderDetail AS orderDetail JOIN orderDetail.order AS objOrder JOIN orderDetail.hotelRoom AS hotelRoom JOIN objOrder.orderFor AS userOrderFor WHERE userOrderFor.email=:email AND objOrder.id=:orderId AND hotelRoom.id=:roomId")
	public Boolean isRoomOrderExist(@Param("email") String email,
			@Param("roomId") Long roomId, @Param("orderId") Long orderId);

	@Query(value = "SELECT orderDetail FROM OrderDetail AS orderDetail JOIN orderDetail.order AS objOrder JOIN orderDetail.hotelRoom AS hotelRoom JOIN objOrder.orderFor AS userOrderFor WHERE userOrderFor.email=:email AND objOrder.id=:orderId AND hotelRoom.id=:roomId AND orderDetail.sellFlag=false AND orderDetail.startDate>=NOW()")
	public OrderDetail getOrderDetailForSell(@Param("email") String email,
			@Param("roomId") Long roomId, @Param("orderId") Long orderId);

	@Modifying
	@Query(value = "UPDATE OrderDetail AS orderDetail SET orderDetail.sellFlag=true WHERE orderDetail.id=:orderDetailId")
	public Integer setSellFlagTrue(@Param("orderDetailId") Long orderDetailId);
	
	@Query(value = "SELECT NEW com.hr.onesuite.dto.BookHistoryDto(objOrder.id,hotelRoom.id,hotelMaster.hotelName,hotelRoom.oneNightCost,orderDetail.createdDate,orderDetail.startDate,orderDetail.endDate,orderDetail.sellFlag) FROM OrderDetail AS orderDetail JOIN orderDetail.order AS objOrder JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotelMaster JOIN objOrder.orderFor AS userOrderFor WHERE hotelMaster.id=:hotelId AND orderDetail.sellFlag =FALSE ORDER BY orderDetail.startDate")
	public List<BookHistoryDto> getBookHistoryId(@Param("hotelId") Long hotelId);
}
