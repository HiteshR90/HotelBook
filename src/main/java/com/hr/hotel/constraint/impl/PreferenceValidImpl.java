package com.hr.hotel.constraint.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

import com.hr.hotel.constraint.PreferenceValid;
import com.hr.hotel.repository.RoomTypeRepository;

public class PreferenceValidImpl implements
		ConstraintValidator<PreferenceValid, Object> {

	@Resource
	private RoomTypeRepository roomTypeRepository;

	private String roomType;
	private String roomTypeProperty;
	private String roomTypeError;

	private String hotelBrand;
	private String hotelBrandProperty;
	private String hotelBrandError;

	@Override
	public void initialize(PreferenceValid preferenceValid) {
		roomType = preferenceValid.roomType();
		roomTypeProperty = preferenceValid.roomTypeProperty();
		roomTypeError = preferenceValid.roomTypeError();

		hotelBrand = preferenceValid.hotelBrand();
		hotelBrandProperty = preferenceValid.hotelBrandProperty();
		hotelBrandError = preferenceValid.hotelBrandError();
	}

	@Override
	public boolean isValid(Object preference, ConstraintValidatorContext context) {
		Boolean isValid = Boolean.TRUE;
		String[] roomTypes;
		String[] hotelBrands;
		try {
			// get field value
			roomTypes = BeanUtils.getArrayProperty(preference, this.roomType);
			hotelBrands = BeanUtils.getArrayProperty(preference,
					this.hotelBrand);
			

			if (roomTypes != null) {
				List<String> listRoomTypes = this.roomTypeRepository
						.getRoomTypeNames();
				for (String roomType : roomTypes) {
					if (!listRoomTypes.contains(roomType)) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(
								roomTypeError)
								.addPropertyNode(roomTypeProperty)
								.addConstraintViolation();
						isValid = Boolean.FALSE;
					}
				}
			}
			
			if (hotelBrands != null)
				for (String hotelBrand : hotelBrands) {
					try {
						int brand = Integer.valueOf(hotelBrand);
						if (brand < 1 || brand > 5) {
							context.disableDefaultConstraintViolation();
							context.buildConstraintViolationWithTemplate(
									hotelBrandError)
									.addPropertyNode(hotelBrandProperty)
									.addConstraintViolation();
							isValid = Boolean.FALSE;
						}
					} catch (Exception ex) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(
								hotelBrandError)
								.addPropertyNode(hotelBrandProperty)
								.addConstraintViolation();
						isValid = Boolean.FALSE;
					}
				}
		} catch (Exception ex) {
			return isValid;
		}
		return isValid;
	}
}