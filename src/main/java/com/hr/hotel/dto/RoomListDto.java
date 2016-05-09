package com.hr.hotel.dto;

import java.util.List;

public class RoomListDto {

	private List<RoomData> roomData = null;
	private Integer noOfRecord = null;
	private Integer pageNo = null;
	private Integer totalPage = null;

	public RoomListDto() {

	}

	public RoomListDto(List<RoomData> roomData, Integer noOfRecord,
			Integer pageNo, Integer totalPage) {
		this.roomData = roomData;
		this.noOfRecord = noOfRecord;
		this.pageNo = pageNo;
		this.totalPage = totalPage;
	}

	public List<RoomData> getRoomData() {
		return roomData;
	}

	public void setRoomData(List<RoomData> roomData) {
		this.roomData = roomData;
	}

	public Integer getNoOfRecord() {
		return noOfRecord;
	}

	public void setNoOfRecord(Integer noOfRecord) {
		this.noOfRecord = noOfRecord;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
