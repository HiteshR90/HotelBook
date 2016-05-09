package com.hr.hotel.service;

import java.util.List;

import com.hr.hotel.dto.HotelDetailDto;
import com.hr.hotel.dto.HotelDto;

public interface HotelService {

	public Long add(HotelDto hotelDto);

	public List<HotelDetailDto> getAllHotelDetail();

	public List<HotelDetailDto> getHotelsDetail(String hotelName);

}
