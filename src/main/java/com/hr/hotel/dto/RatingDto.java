package com.hr.hotel.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RatingDto {

	@NotNull(message = "Please enter rate")
	@Min(value = 1, message = "Minimum value must be greater or equal to 1")
	@Max(value = 5, message = "Maximum value must be less or equal to 5")
	private Integer rate;

	private String comment;

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
