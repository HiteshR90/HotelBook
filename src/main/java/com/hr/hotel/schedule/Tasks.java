package com.hr.hotel.schedule;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;

import com.hr.hotel.common.Constant;
import com.hr.hotel.service.PaymentService;

public class Tasks {

	@Resource(name = Constant.SERVICE_PAYMENT)
	private PaymentService paymentService;

	@Scheduled(cron="0 0 0 * * ?")
	public void paymentToseller() {
		this.paymentService.paymentToSellerTransfer();
	}
}
