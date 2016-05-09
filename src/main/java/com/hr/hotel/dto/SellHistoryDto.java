package com.hr.hotel.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SellHistoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roomId;
	private String hotelName;
	private Date sellDate;
	private Double oneNightCost;
	private Date startDate;
	private Date endDate;
	private Boolean isSold;
	private List<String> images;

	public SellHistoryDto(Long roomId, String hotelName, Date sellDate,
			Double oneNightCost, Date startDate, Date endDate, Boolean isSold) {
		super();
		this.roomId = roomId;
		this.sellDate = sellDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelName = hotelName;
		this.oneNightCost = oneNightCost;
		this.isSold = isSold;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public Boolean getIsSold() {
		return isSold;
	}

	public void setIsSold(Boolean isSold) {
		this.isSold = isSold;
	}

}
