package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.VerificationToken;
import com.hr.hotel.model.VerificationToken.VerificationTokenType;

public interface VerificationTokenRepository extends
		BaseRepository<VerificationToken> {

	@Query(value = "SELECT verificationToken.token FROM VerificationToken AS verificationToken JOIN verificationToken.user AS user WHERE verificationToken.tokenType=:tokenType AND user.email=:email AND verificationToken.isUsed=false AND verificationToken.expireAt>NOW()")
	public String getVerifactionTokenByEmail(@Param("email") String email,
			@Param("tokenType") VerificationTokenType tokenType);

	@Query(value = "SELECT verificationToken FROM VerificationToken AS verificationToken WHERE verificationToken.tokenType=:tokenType AND verificationToken.token=:token")
	public VerificationToken getVerifactionTokenByToken(
			@Param("token") String token,
			@Param("tokenType") VerificationTokenType tokenType);
}
