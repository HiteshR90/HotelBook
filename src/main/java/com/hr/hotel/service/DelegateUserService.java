package com.hr.hotel.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import com.hr.hotel.dto.AssignerUser;
import com.hr.hotel.dto.DeleagetDto;
import com.hr.hotel.model.User;

import freemarker.template.TemplateException;

public interface DelegateUserService {
	public Boolean isAssigner(String assignerEmailAddress,
			User loginUser);

	public List<AssignerUser> getAssignerUsers(User delegateUser);

	public void makeDeleget(DeleagetDto delegate, User loginUser)
			throws UnsupportedEncodingException, MessagingException,
			IOException, TemplateException;
}
