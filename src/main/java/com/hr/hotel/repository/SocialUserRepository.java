package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.SocialUser;

public interface SocialUserRepository extends BaseRepository<SocialUser> {

	@Query("SELECT socialUser FROM SocialUser AS socialUser WHERE socialUser.providerId=:providerId AND socialUser.providerUserId=:providerUserId")
	public List<SocialUser> findByProviderIdAndProviderUserId(
			@Param("providerId") String providerId,
			@Param("providerUserId") String providerUserId);
}
