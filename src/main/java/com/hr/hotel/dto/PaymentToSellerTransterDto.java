package com.hr.hotel.dto;

import com.hr.hotel.model.PaymentToSeller;

public class PaymentToSellerTransterDto {

	private String sellerPaypalId;
	private PaymentToSeller paymentToSeller;
	private Double amountToTranster;
	private String sellerEmail;
	private Long sellerId;
	private Long orderDetailId;

	public PaymentToSellerTransterDto(String sellerPaypalId,
			PaymentToSeller paymentToSeller, Double amountToTranster,
			String sellerEmail, Long sellerId, Long orderDetailId) {
		super();
		this.sellerPaypalId = sellerPaypalId;
		this.paymentToSeller = paymentToSeller;
		this.amountToTranster = amountToTranster;
		this.sellerEmail = sellerEmail;
		this.sellerId = sellerId;
		this.orderDetailId = orderDetailId;
	}

	public String getSellerPaypalId() {
		return sellerPaypalId;
	}

	public void setSellerPaypalId(String sellerPaypalId) {
		this.sellerPaypalId = sellerPaypalId;
	}

	public PaymentToSeller getPaymentToSeller() {
		return paymentToSeller;
	}

	public void setPaymentToSeller(PaymentToSeller paymentToSeller) {
		this.paymentToSeller = paymentToSeller;
	}

	public Double getAmountToTranster() {
		return amountToTranster;
	}

	public void setAmountToTranster(Double amountToTranster) {
		this.amountToTranster = amountToTranster;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

}
