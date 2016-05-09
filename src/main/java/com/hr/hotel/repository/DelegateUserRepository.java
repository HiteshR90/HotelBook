package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.AssignerUser;
import com.hr.hotel.model.DelegateUser;
import com.hr.hotel.model.User;

public interface DelegateUserRepository extends BaseRepository<DelegateUser> {

	@Query(value = "SELECT delegate FROM DelegateUser AS delegate WHERE delegate.assignerUser=:assignerUser AND delegate.delegateUser=:delegateUser")
	public DelegateUser getDelegateUser(
			@Param("assignerUser") User assignerUser,
			@Param("delegateUser") User delegateUser);

	@Query(value = "SELECT delegate.delegateUser.email FROM DelegateUser AS delegate WHERE delegate.assignerUser.email=:email AND delegate.active=true")
	public List<String> getDelegateUserEmails(@Param("email") String email);

	/**
	 * getMasterUserFromDelegate return master user from delegate
	 * 
	 * @param masterUserId
	 * @param masterUserEmail
	 * @param delegateUserEmail
	 * @return
	 * @throws Exception
	 */
	@Query(value = "SELECT assignerUser FROM DelegateUser AS delegate JOIN delegate.assignerUser AS assignerUser JOIN delegate.delegateUser AS delegateUser WHERE assignerUser.email=:assignerUserEmail AND delegateUser.email=:delegateUserEmail AND delegate.active=true")
	public User getAssignerFromDelegate(
			@Param("delegateUserEmail") String delegateUserEmail,
			@Param("assignerUserEmail") String assignerUserEmail);

	@Query(value = "SELECT delegate.id FROM DelegateUser AS delegate JOIN delegate.assignerUser AS assignerUser JOIN delegate.delegateUser AS delegateUser WHERE assignerUser.email=:assignerEmailAddress AND delegateUser.email=:delegateUserEmail AND delegate.active=true")
	public Long getAssignerId(
			@Param("assignerEmailAddress") String assignerEmailAddress,
			@Param("delegateUserEmail") String delegateUserEmail);

	@Query(value = "SELECT new com.hr.onesuite.dto.AssignerUser(assignerUser.email,assignerUser.fName,assignerUser.lName) FROM DelegateUser AS delegate JOIN delegate.assignerUser AS assignerUser JOIN delegate.delegateUser AS delegateUser WHERE delegateUser.email=:delegateUserEmail AND delegate.active=true")
	public List<AssignerUser> getAssignerUsers(
			@Param("delegateUserEmail") String delegateUserEmail);

}
