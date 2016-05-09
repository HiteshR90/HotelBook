package com.hr.hotel.service;

import com.hr.hotel.dto.UserPreferenceDto;
import com.hr.hotel.model.User;

public interface UserPreferenceService {

	public UserPreferenceDto getFirstPreference(User loginUser);

	public UserPreferenceDto getAllPreference(User loginUser);

	public void saveFirstPreference(UserPreferenceDto userPreferenceDto,
			User loginUser);

	public void saveAllPreference(UserPreferenceDto userPreferenceDto,
			User loginUser);

}
