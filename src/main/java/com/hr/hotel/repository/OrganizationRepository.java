package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.Organization;

public interface OrganizationRepository extends BaseRepository<Organization> {
	@Query(value = "SELECT organization.organizationName FROM Organization AS organization WHERE organization.active=true AND organization.isPaymentReceiver=true")
	public List<String> getPaymentTypes();

	@Query(value = "SELECT organization FROM Organization AS organization WHERE organization.active=true AND organization.isPaymentReceiver=true AND organization.organizationName=:organizationName")
	public Organization getPaymentType(
			@Param("organizationName") String typeName);
}
