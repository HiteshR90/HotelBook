package com.hr.hotel.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SellInternDto {

	@NotNull(message = "Please Enter cost for sell")
	@Min(value = 1, message = "Cost must be greate or equal to 1")
	@Max(value = 9999, message = "Cost must be less or equal to 9999")
	private Double oneNightCost;

	// @NotEmpty(message = "Please Enter payment type")
	// private String paymentType;

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
	}

}
