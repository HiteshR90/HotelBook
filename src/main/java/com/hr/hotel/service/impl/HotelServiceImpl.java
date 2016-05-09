package com.hr.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.HotelDetailDto;
import com.hr.hotel.dto.HotelDto;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.City;
import com.hr.hotel.model.HotelMaster;
import com.hr.hotel.model.State;
import com.hr.hotel.repository.CityRepository;
import com.hr.hotel.repository.HotelRepository;
import com.hr.hotel.repository.StateRepository;
import com.hr.hotel.service.HotelService;

@Service(value = Constant.SERVICE_HOTEL)
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public Long add(HotelDto hotelDto) {
		City objCity = this.cityRepository.getCityByName(hotelDto.getCity());
		State objState = this.stateRepository.getStateByName(hotelDto.getState());
		if (objCity != null) {
			if (objState != null)
			{
				HotelMaster objHotelMaster = new HotelMaster();
				objHotelMaster.setHotelName(hotelDto.getHotelName());
				objHotelMaster.setAddress(hotelDto.getAddress());
				objHotelMaster.setCity(objCity);
				objHotelMaster.setPincode(Integer.parseInt(hotelDto.getPincode()));
				objHotelMaster.setState(objState);
				objHotelMaster.setHotelBrand(3);
				objHotelMaster.setActive(true);
				this.hotelRepository.save(objHotelMaster);
				return objHotelMaster.getId();
			}
			else
			{
				throw new NotFoundException(NotFound.StateNotFound);
			}
		} else {
			throw new NotFoundException(NotFound.CityNotFound);
		}
	}

	@Override
	public List<HotelDetailDto> getAllHotelDetail() {
		return this.hotelRepository.getAllHotels();
	}

	@Override
	public List<HotelDetailDto> getHotelsDetail(String hotelName) {
		return this.hotelRepository.getHotels(hotelName);
	}
}
