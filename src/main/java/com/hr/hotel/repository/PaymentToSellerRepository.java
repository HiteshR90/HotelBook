package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.PaymentToSellerTransterDto;
import com.hr.hotel.dto.PendingTransactionDto;
import com.hr.hotel.model.PaymentToSeller;

public interface PaymentToSellerRepository extends
		BaseRepository<PaymentToSeller> {

	@Query(value = "SELECT NEW com.hr.onesuite.dto.PaymentToSellerTransterDto(userPaymentInfo.paypalId,paymentToSeller,paymentToSeller.amountToTransfer,userSellFor.email,userSellFor.id,orderDetail.id) FROM PaymentToSeller AS paymentToSeller JOIN paymentToSeller.orderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom LEFT JOIN hotelRoom.sells AS sell JOIN sell.sellFor AS userSellFor LEFT JOIN userSellFor.userPaymentInfos AS userPaymentInfo WHERE paymentToSeller.isTransfered=FALSE AND paymentToSeller.transferDate<=NOW()")
	public List<PaymentToSellerTransterDto> getTransterData();

	@Query(value = "SELECT NEW com.hr.onesuite.dto.PendingTransactionDto(hotel.hotelName,hotelRoom.id,orderDetail.createdDate,paymentToSeller.transferDate,paymentToSeller.amountToTransfer) FROM PaymentToSeller AS paymentToSeller JOIN paymentToSeller.orderDetail AS orderDetail JOIN orderDetail.hotelRoom AS hotelRoom JOIN hotelRoom.hotel AS hotel LEFT JOIN hotelRoom.sells AS sell JOIN sell.sellFor AS userSellFor WHERE paymentToSeller.isTransfered=FALSE AND userSellFor.userName=:userName ORDER BY orderDetail.createdDate DESC")
	public List<PendingTransactionDto> getPendingTransactionHistory(
			@Param("userName") String userName);
}
