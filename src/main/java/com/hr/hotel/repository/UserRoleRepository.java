package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.UserRole;

public interface UserRoleRepository extends BaseRepository<UserRole> {

	@Query(value = "SELECT role.roleName FROM UserRole AS userRole JOIN userRole.user AS user JOIN userRole.role AS role WHERE (user.userName=:userNameOrEmail OR user.email=:userNameOrEmail) AND userRole.active=true AND role.active=true")
	public List<String> getUserRolesByUserNameOrEmail(
			@Param("userNameOrEmail") String userNameOrEmail);
}
