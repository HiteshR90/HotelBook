package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.User;

public interface UserRepository extends BaseRepository<User> {

	@Query(value = "SELECT user.email FROM User AS user WHERE user.email=:email")
	public boolean isEmailPresent(@Param("email") String email);

	@Query(value = "SELECT user FROM User AS user WHERE user.email=:email")
	public User getUserByEmail(@Param("email") String email);

	@Query(value = "SELECT user FROM User AS user WHERE user.email=:userNameOrEmail OR user.userName=:userNameOrEmail")
	public User getUserByUserNameOrEmail(
			@Param("userNameOrEmail") String userNameOrEmail);

	/*
	 * @Query(value =
	 * "SELECT user FROM User user WHERE user.userName=:userName AND user.email =:email"
	 * ) public User getUserByUsernameAndEmail(@Param("userName") String
	 * userName,
	 * 
	 * @Param("email") String email);
	 */

	@Query(value = "SELECT user FROM User AS user WHERE user.email=:email OR user.userName=:userName")
	public User getUserByUserNameOrEmail(@Param("userName") String userName,
			@Param("email") String email);

	@Query(value = "SELECT user FROM User AS user WHERE user.userName=:userName")
	public User getUserByUserName(@Param("userName") String userName);

	@Query(value = "SELECT user.userName FROM User AS user WHERE user.email=:email")
	public String getUserNameByEmail(@Param("email") String email);
}
