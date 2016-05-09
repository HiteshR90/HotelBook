package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hr.hotel.dto.TempSellRoomDto;
import com.hr.hotel.model.TempSellConfirm;

public interface TempSellRepository extends BaseRepository<TempSellConfirm> {

	@Query(value = "SELECT new com.hr.onesuite.dto.TempSellRoomDto(tempSell.id,hotel.hotelName,hotel.address,tempSell.roomType,tempSell.checkInDate,tempSell.checkOutDate,tempSell.confirmationCode,tempSell.confirmationCodeSecond,tempSell.price,tempSell.isConfirmed,tempSell.active) FROM TempSellConfirm AS tempSell JOIN tempSell.hotel AS hotel ORDER BY tempSell.createdDate DESC")
	public List<TempSellRoomDto> getRooms();
}
