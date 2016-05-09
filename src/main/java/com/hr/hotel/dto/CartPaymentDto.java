package com.hr.hotel.dto;

public class CartPaymentDto {

	private String transactionId;
	private Long cartId;

	public CartPaymentDto(String transactionId, Long cartId) {
		this.transactionId = transactionId;
		this.cartId = cartId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

}
