package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.PaymentToSellerCharge;

public interface PaymentToSellerChargeRepository extends
		BaseRepository<PaymentToSellerCharge> {

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_payment_to_seller_charge(created_date,charges_percentage,charge_amount,organization_charges_id,payment_to_seller_id) SELECT NOW(),charges_percentage,charge_amount,id,:paymentToSellerId FROM xc_organization_charges where is_default_chareges=true")
	public int selectInsertPaymentToSellerDefaultCharge(
			@Param("paymentToSellerId") Long paymentToSellerId);

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_payment_to_seller_charge(created_date,charges_percentage,charge_amount,organization_charges_id,payment_to_seller_id) SELECT NOW(),organizationCharge.charges_percentage,organizationCharge.charge_amount,organizationCharge.id,:paymentToSellerId FROM xc_organization_charges AS organizationCharge INNER JOIN xc_organization AS organization ON organization.id=organizationCharge.organization_id WHERE organization.organization_name='paypal'")
	public int selectInsertPaymentToSellerPaypalCharge(
			@Param("paymentToSellerId") Long paymentToSellerId);
}
