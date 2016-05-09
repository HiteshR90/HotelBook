package com.hr.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.hotel.model.FeedBack;
import com.hr.hotel.repository.FeedBackRepository;
import com.hr.hotel.service.FeedBackService;

@Service(value = "feedbackService")
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	private FeedBackRepository feedBackRepository;

	@Override
	public void save(FeedBack feedBack) {
		this.feedBackRepository.save(feedBack);
	}

}
