package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.OrganizationCharges;

public interface OrganizationChargeRepository extends
		BaseRepository<OrganizationCharges> {

	@Query(nativeQuery = true, value = "SELECT SUM(CASE WHEN(oc.charges_percentage>0) THEN ((:amount*oc.`charges_percentage`)/100)+oc.`charge_amount` ELSE oc.`charge_amount` END) AS ee FROM `xc_organization_charges` AS oc INNER JOIN `xc_organization` AS o ON o.`id`=oc.`organization_id` WHERE oc.`is_default_chareges`=TRUE AND oc.`is_seller_charge`=TRUE")
	public double getDefaultAmountForSeller(@Param("amount") double amount);

	@Query(nativeQuery = true, value = "SELECT SUM(CASE WHEN(oc.charges_percentage>0) THEN ((:amount*oc.`charges_percentage`)/100)+oc.`charge_amount` ELSE oc.`charge_amount` END) AS ee FROM `xc_organization_charges` AS oc INNER JOIN `xc_organization` AS o ON o.`id`=oc.`organization_id` WHERE oc.`is_default_chareges`=TRUE AND oc.`is_buyer_charge`=TRUE")
	public double getDefaultAmountForBuyer(@Param("amount") double amount);

	@Query(nativeQuery = true, value = "SELECT (:amount+oc.`charge_amount`)/((100-oc.charges_percentage)/100) AS ee FROM `xc_organization_charges` AS oc INNER JOIN `xc_organization` AS o ON o.`id`=oc.`organization_id` and o.organization_name='paypal'")
	public double getPaypalAmount(@Param("amount") double amount);

	@Query(nativeQuery = true, value = "SELECT (:amount+oc.`charge_amount`) FROM `xc_organization_charges` AS oc INNER JOIN `xc_organization` AS o ON o.`id`=oc.`organization_id` and o.organization_name='Authorize.net'")
	public double getAuthorizeDotNetAmount(@Param("amount") double amount);
}
