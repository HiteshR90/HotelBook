package com.hr.hotel.dto;

import java.io.Serializable;
import java.util.List;

public class OrderHistoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<BookHistoryDto> bookHistory;
	private List<SellHistoryDto> sellHistory;

	public OrderHistoryDto(List<BookHistoryDto> bookHistory,
			List<SellHistoryDto> sellHistory) {
		super();
		this.bookHistory = bookHistory;
		this.sellHistory = sellHistory;
	}

	public List<BookHistoryDto> getBookHistory() {
		return bookHistory;
	}

	public void setBookHistory(List<BookHistoryDto> bookHistory) {
		this.bookHistory = bookHistory;
	}

	public List<SellHistoryDto> getSellHistory() {
		return sellHistory;
	}

	public void setSellHistory(List<SellHistoryDto> sellHistory) {
		this.sellHistory = sellHistory;
	}

}
