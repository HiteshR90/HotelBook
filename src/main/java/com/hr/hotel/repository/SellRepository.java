package com.hr.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hr.hotel.dto.SellHistoryDto;
import com.hr.hotel.model.Sell;

public interface SellRepository extends BaseRepository<Sell> {
	/**
	 * getSellHistory return selling history for user
	 * 
	 * @param email
	 *            address of user for whom selling data is require
	 * @return list of sell data
	 */
	@Query(value = "SELECT NEW com.hr.onesuite.dto.SellHistoryDto(hotelRoom.id,hotelMaster.hotelName,sell.createdDate,hotelRoom.oneNightCost,hotelRoom.startDate,hotelRoom.endDate,hotelRoom.isFullBooked) FROM Sell as sell JOIN sell.hotelRoom AS hotelRoom JOIN sell.sellFor AS userSellFor JOIN hotelRoom.hotel AS hotelMaster WHERE userSellFor.email=:email ORDER BY sell.createdDate DESC")
	public List<SellHistoryDto> getSellHistory(@Param("email") String email);
}
