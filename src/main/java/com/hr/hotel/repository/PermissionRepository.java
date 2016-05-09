package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hr.hotel.model.Permission;

public interface PermissionRepository extends BaseRepository<Permission> {

	@Query(value = "SELECT permission FROM Permission AS permission")
	public List<Permission> getAllPermission();

}