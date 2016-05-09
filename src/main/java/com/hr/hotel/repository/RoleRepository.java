package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hr.hotel.model.Role;

public interface RoleRepository extends BaseRepository<Role> {

	@Query(value = "SELECT role FROM Role AS role WHERE role.isDefault=true AND role.active=true")
	public List<Role> getDefaultRoles();
}
