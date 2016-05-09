package com.hr.hotel.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BookHistoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long orderId;
	private Long roomId;
	private String hotelName;
	private Double oneNightCost;
	private Date orderDate;
	private Date startDate;
	private Date endDate;
	private Boolean sellFlag;
	private List<String> images;

	public BookHistoryDto() {

	}

	public BookHistoryDto(Long orderId, Long roomId, String hotelName,
			Double oneNightCost, Date orderDate, Date startDate, Date endDate,
			Boolean sellFlag) {
		super();
		this.orderId = orderId;
		this.roomId = roomId;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelName = hotelName;
		this.oneNightCost = oneNightCost;
		this.sellFlag = sellFlag;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public Boolean getSellFlag() {
		return sellFlag;
	}

	public void setSellFlag(Boolean sellFlag) {
		this.sellFlag = sellFlag;
	}

}
