package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.ProfileBankAccount;
import com.hr.hotel.dto.ProfilePaypal;
import com.hr.hotel.model.UserPaymentInfo;

public interface UserPaymentInfoRepository extends
		BaseRepository<UserPaymentInfo> {

	@Query(value = "SELECT userPaymentInfo FROM UserPaymentInfo AS userPaymentInfo JOIN userPaymentInfo.user AS user WHERE user.email=:email")
	public UserPaymentInfo getPaymentInfoByEmail(@Param("email") String email);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.ProfileBankAccount(userPaymentInfo.bankAccountNumber,userPaymentInfo.bankRoutingNumber) FROM UserPaymentInfo AS userPaymentInfo JOIN userPaymentInfo.user AS user WHERE user.email=:email")
	public ProfileBankAccount getBankInfoByEmail(@Param("email") String email);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.ProfilePaypal(userPaymentInfo.paypalId) FROM UserPaymentInfo AS userPaymentInfo JOIN userPaymentInfo.user AS user WHERE user.email=:email")
	public ProfilePaypal getPaypalIdByEmail(@Param("email") String email);

	@Query(value = "SELECT NEW com.hr.onesuite.dto.ProfilePaypal(CONCAT(SUBSTRING(userPaymentInfo.paypalId,1,1),'...',SUBSTRING(userPaymentInfo.paypalId,LENGTH(SUBSTRING_INDEX(userPaymentInfo.paypalId, '@', 1)),LENGTH(userPaymentInfo.paypalId)))) FROM UserPaymentInfo AS userPaymentInfo JOIN userPaymentInfo.user AS user WHERE user.email=:email")
	public ProfilePaypal getEncodedPaypalIdByEmail(@Param("email") String email);
	
	@Query(value = "SELECT NEW com.hr.onesuite.dto.ProfileBankAccount(CONCAT('XXXXXXXXXXXX',SUBSTRING(userPaymentInfo.bankAccountNumber,LENGTH(userPaymentInfo.bankAccountNumber)-4,LENGTH(userPaymentInfo.bankAccountNumber))),CONCAT('XXXXXX',SUBSTRING(userPaymentInfo.bankRoutingNumber,LENGTH(userPaymentInfo.bankRoutingNumber)-3,LENGTH(userPaymentInfo.bankRoutingNumber)))) FROM UserPaymentInfo AS userPaymentInfo JOIN userPaymentInfo.user AS user WHERE user.email=:email")
	public ProfileBankAccount getEncodedBankInfoByEmail(@Param("email") String email);
}
