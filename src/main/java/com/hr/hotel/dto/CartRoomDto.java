package com.hr.hotel.dto;

import java.util.Date;
import java.util.List;

public class CartRoomDto {
	private Long roomId;
	private String hotelName;
	private String address;
	private String roomType;
	private Date startDate;
	private Date endDate;
	private Double totalCost;
	private Double oneNightCost;
	private Integer remainTimeSecond;
	private List<String> images;

	public CartRoomDto() {

	}

	public CartRoomDto(Long roomId, String hotelName, String address, String roomType,
			Date startDate, Date endDate, Double totalCost,
			Double oneNightCost, Integer remainTimeSecond) {
		super();
		this.roomId = roomId;
		this.hotelName = hotelName;
		this.address = address;
		this.roomType = roomType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.oneNightCost = oneNightCost;
		this.remainTimeSecond = remainTimeSecond;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getHotelName() {
		return hotelName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
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

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
	}

	public Integer getRemainTimeSecond() {
		return remainTimeSecond;
	}

	public void setRemainTimeSecond(Integer remainTimeSecond) {
		this.remainTimeSecond = remainTimeSecond;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
