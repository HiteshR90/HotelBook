package com.hr.hotel.dto;

import java.util.ArrayList;
import java.util.List;

public class PaymentValidaton {

	private Integer index;
	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

	public void addFieldError(String path, String message) {
		FieldErrorDTO error = new FieldErrorDTO(path, message);
		fieldErrors.add(error);
	}

	public PaymentValidaton() {

	}

	public PaymentValidaton(Integer index, List<FieldErrorDTO> fieldErrors) {
		this.index = index;
		this.fieldErrors = fieldErrors;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
