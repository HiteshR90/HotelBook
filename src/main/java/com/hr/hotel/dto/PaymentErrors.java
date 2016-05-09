package com.hr.hotel.dto;

import java.util.List;

public class PaymentErrors {
	private List<PaymentValidaton> roomDatas;

	public List<PaymentValidaton> getRoomDatas() {
		return roomDatas;
	}

	public void setRoomDatas(List<PaymentValidaton> roomDatas) {
		this.roomDatas = roomDatas;
	}

}
