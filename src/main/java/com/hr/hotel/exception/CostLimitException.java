package com.hr.hotel.exception;

import com.hr.hotel.common.Constant;

public class CostLimitException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CostLimitException() {
		super(400, "40001", "Transaction Limit is $"
				+ Constant.TRANSCATION_LIMIT,
				"Can not made transaction with limit");
	}
}
