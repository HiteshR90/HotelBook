package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.PaymentFromBuyerCharge;

public interface PaymentFromBuyerChargeRepository extends
		BaseRepository<PaymentFromBuyerCharge> {

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_payment_from_buyer_charge(created_by,created_date,charges_percentage,charge_amount,organization_charges_id,payment_from_buyer_id) SELECT :createdBy,NOW(),charges_percentage,charge_amount,id,:paymentFromBuyerId FROM xc_organization_charges where is_default_chareges=true")
	public int selectInsertPaymentFromBuyerDefaultCharge(
			@Param("paymentFromBuyerId") Long paymentFromBuyerId,
			@Param("createdBy") Long createdBy);
	
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_payment_from_buyer_charge(created_by,created_date,charges_percentage,charge_amount,organization_charges_id,payment_from_buyer_id) SELECT :createdBy,NOW(),organizationCharge.charges_percentage,organizationCharge.charge_amount,organizationCharge.id,:paymentFromBuyerId FROM xc_organization_charges AS organizationCharge INNER JOIN xc_organization AS organization ON organization.id=organizationCharge.organization_id WHERE organization.organization_name='paypal'")
	public int selectInsertPaymentFromBuyerPaypalCharge(
			@Param("paymentFromBuyerId") Long paymentFromBuyerId,
			@Param("createdBy") Long createdBy);
	
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO xc_payment_from_buyer_charge(created_by,created_date,charges_percentage,charge_amount,organization_charges_id,payment_from_buyer_id) SELECT :createdBy,NOW(),organizationCharge.charges_percentage,organizationCharge.charge_amount,organizationCharge.id,:paymentFromBuyerId FROM xc_organization_charges AS organizationCharge INNER JOIN xc_organization AS organization ON organization.id=organizationCharge.organization_id WHERE organization.organization_name='Authorize.net'")
	public int selectInsertPaymentFromBuyerAuthorizeCharge(
			@Param("paymentFromBuyerId") Long paymentFromBuyerId,
			@Param("createdBy") Long createdBy);
}
