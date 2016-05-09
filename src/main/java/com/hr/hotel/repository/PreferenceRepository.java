package com.hr.hotel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.model.Preference;

public interface PreferenceRepository extends BaseRepository<Preference> {

	/**
	 * getPreferenceByUserName return user preference based on priority
	 * 
	 * @param userName
	 * @param priority
	 *            0=First Result and 0!=ALL
	 * @return
	 */
	@Query(value = "SELECT preference FROM Preference AS preference JOIN preference.user AS user WHERE user.userName=:userName AND preference.priority=:priority")
	public Preference getPreferenceByUserName(
			@Param("userName") String userName,
			@Param("priority") Integer priority);
}
