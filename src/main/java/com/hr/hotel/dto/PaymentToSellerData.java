package com.hr.hotel.dto;

public class PaymentToSellerData {

	private Long orderDetailId;
	private Long roomId;
	private Double totalAmount;
	private String paypalId;

	public PaymentToSellerData(Long orderDetailId, Long roomId,
			Double totalAmount, String paypalId) {
		this.orderDetailId=orderDetailId;
		this.roomId=roomId;
		this.totalAmount=totalAmount;
		this.paypalId=paypalId;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaypalId() {
		return paypalId;
	}

	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

}
