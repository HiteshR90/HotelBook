package com.hr.hotel.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import freemarker.template.TemplateException;

public interface ApplicationMailerService {
	public void sendMail(String to, String subject, String body);

	// public void sendPreConfiguredMail(String message);

	public void sendTemplateMail(String to, String subject, String templetName,
			Map<String, Object> templateProp, Map<String, String> images)
			throws MessagingException, IOException, TemplateException;
}
