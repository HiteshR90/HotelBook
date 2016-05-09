package com.hr.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.UserPreferenceDto;
import com.hr.hotel.model.Preference;
import com.hr.hotel.model.User;
import com.hr.hotel.repository.PreferenceRepository;
import com.hr.hotel.service.UserPreferenceService;
import com.hr.hotel.util.DateUtil;
import com.thoughtworks.xstream.XStream;

@Service(value = Constant.SERVICE_USER_PREFERENCE)
public class UserPreferenceServiceImpl implements UserPreferenceService {

	@Autowired
	private PreferenceRepository preferenceRepository;

	@Override
	public UserPreferenceDto getFirstPreference(User loginUser) {
		Preference preference = this.preferenceRepository
				.getPreferenceByUserName(loginUser.getUserName(), 0);
		UserPreferenceDto objUserPreferenceDto = new UserPreferenceDto();
		if (preference != null) {
			XStream objXStream = new XStream();
			objUserPreferenceDto.setEarlyCheckIn(preference.getEarlyCheckIn());
			objUserPreferenceDto.setHighFloor(preference.getHighFloor());
			objUserPreferenceDto.setHotelStar(preference.getHotelStar());
			objUserPreferenceDto.setLateCheckOut(preference.getLateCheckOut());
			if (StringUtils.hasText(preference.getHotelBrand()))
				objUserPreferenceDto.setHotelBrand((String[]) objXStream
						.fromXML(preference.getHotelBrand()));
			if (StringUtils.hasText(preference.getRoomType()))
				objUserPreferenceDto.setRoomType((String[]) objXStream
						.fromXML(preference.getRoomType()));
		}
		return objUserPreferenceDto;
	}

	@Override
	public UserPreferenceDto getAllPreference(User loginUser) {
		Preference preference = this.preferenceRepository
				.getPreferenceByUserName(loginUser.getUserName(), 1);
		UserPreferenceDto objUserPreferenceDto = new UserPreferenceDto();
		if (preference != null) {
			XStream objXStream = new XStream();
			objUserPreferenceDto.setEarlyCheckIn(preference.getEarlyCheckIn());
			objUserPreferenceDto.setHighFloor(preference.getHighFloor());
			objUserPreferenceDto.setHotelStar(preference.getHotelStar());
			objUserPreferenceDto.setLateCheckOut(preference.getLateCheckOut());
			if (StringUtils.hasText(preference.getHotelBrand()))
				objUserPreferenceDto.setHotelBrand((String[]) objXStream
						.fromXML(preference.getHotelBrand()));
			if (StringUtils.hasText(preference.getRoomType()))
				objUserPreferenceDto.setRoomType((String[]) objXStream
						.fromXML(preference.getRoomType()));
		}
		return objUserPreferenceDto;
	}

	@Override
	public void saveFirstPreference(UserPreferenceDto userPreferenceDto,
			User loginUser) {
		Preference preference = this.preferenceRepository
				.getPreferenceByUserName(loginUser.getUserName(), 0);
		if (preference == null) {
			preference = new Preference();
			preference.setPriority(0);
			preference.setUser(loginUser);
			preference.setCreatedBy(loginUser.getId());
		}
		XStream objXStream = new XStream();
		preference.setEarlyCheckIn(userPreferenceDto.getEarlyCheckIn());
		preference.setHighFloor(userPreferenceDto.getHighFloor());
		preference.setHotelBrand(objXStream.toXML(userPreferenceDto
				.getHotelBrand()));
		preference.setHotelStar(userPreferenceDto.getHotelStar());
		preference.setLateCheckOut(userPreferenceDto.getLateCheckOut());
		preference.setModifiedBy(loginUser.getId());
		preference.setModifiedDate(DateUtil.getCurrentDate());
		preference
				.setRoomType(objXStream.toXML(userPreferenceDto.getRoomType()));
		this.preferenceRepository.save(preference);
	}

	@Override
	public void saveAllPreference(UserPreferenceDto userPreferenceDto,
			User loginUser) {
		Preference preference = this.preferenceRepository
				.getPreferenceByUserName(loginUser.getUserName(), 1);
		if (preference == null) {
			preference = new Preference();
			preference.setPriority(1);
			preference.setUser(loginUser);
			preference.setCreatedBy(loginUser.getId());
		}
		XStream objXStream = new XStream();
		preference.setEarlyCheckIn(userPreferenceDto.getEarlyCheckIn());
		preference.setHighFloor(userPreferenceDto.getHighFloor());
		preference.setHotelBrand(objXStream.toXML(userPreferenceDto
				.getHotelBrand()));
		preference.setHotelStar(userPreferenceDto.getHotelStar());
		preference.setLateCheckOut(userPreferenceDto.getLateCheckOut());
		preference.setModifiedBy(loginUser.getId());
		preference.setModifiedDate(DateUtil.getCurrentDate());
		preference
				.setRoomType(objXStream.toXML(userPreferenceDto.getRoomType()));
		this.preferenceRepository.save(preference);
	}
}
