package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.CartPaymentDto;
import com.hr.hotel.model.PaymentFromBuyer;

public interface PaymentFromBuyerRepository extends
		BaseRepository<PaymentFromBuyer> {

	//@Query(value = "SELECT NEW com.hr.onesuite.dto.CartPaymentDto(paymentFromBuyer.paymentId,cart.id) FROM PaymentFromBuyer AS paymentFromBuyer JOIN paymentFromBuyer.cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.id=:cartId AND cart.isCompleted=false AND cart.isLocked=true AND cart.unlockAt>=NOW()")
	@Query(value = "SELECT NEW com.hr.onesuite.dto.CartPaymentDto(paymentFromBuyer.transactionId,cart.id) FROM PaymentFromBuyer AS paymentFromBuyer JOIN paymentFromBuyer.cart AS cart JOIN cart.addBy AS user WHERE user.email=:email AND cart.id=:cartId AND cart.isCompleted=false AND cart.isLocked=true AND cart.unlockAt>=NOW() AND paymentFromBuyer.active=TRUE")
	public CartPaymentDto getCartPaymentId(@Param("cartId") Long cartId,
			@Param("email") String email);

	//@Modifying
	//@Query(value = "DELETE FROM PaymentFromBuyer AS paymentFromBuyer WHERE paymentFromBuyer.cart.id=:cartId")
	//public Integer deleteByCartId(@Param("cartId") Long cartId);
	
	@Modifying
	@Query(value = "UPDATE PaymentFromBuyer AS paymentFromBuyer SET paymentFromBuyer.active=FALSE WHERE paymentFromBuyer.cart.id=:cartId")
	public Integer deactiveByCartId(@Param("cartId") Long cartId);

	@Modifying
	@Query(value = "UPDATE PaymentFromBuyer AS paymentFromBuyer set paymentFromBuyer.state=:state WHERE paymentFromBuyer.transactionId=:transactionId")
	public void updatePaymentStateByPaymentId(
			@Param("transactionId") String transactionId, @Param("state") String state);
}
