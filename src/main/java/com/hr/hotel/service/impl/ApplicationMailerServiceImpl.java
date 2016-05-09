package com.hr.hotel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.hr.hotel.common.Constant;
import com.hr.hotel.service.ApplicationMailerService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Service(value = Constant.SERVICE_APPLICATION_MAIL)
public class ApplicationMailerServiceImpl implements ApplicationMailerService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	ServletContext servletContext;

	// @Autowired
	// private SimpleMailMessage preConfiguredMessage;

	@Autowired
	private Configuration freemarkerMailConfiguration;

	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationMailerServiceImpl.class);

	/**
	 * This method will send compose and send the message
	 * */
	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

	/**
	 * sendTemplateMail send template based mail to user
	 * 
	 * @param to
	 *            object of String email id of receiver
	 * @param templetName
	 *            object of string refer which template
	 * @param templateProp
	 *            key value pair for template
	 * @throws TemplateException
	 * @throws IOException
	 * @throws MessagingException
	 * @throws Exception
	 */
	@Async
	@Override
	public void sendTemplateMail(String to, String subject, String templetName,
			Map<String, Object> templateProp, Map<String, String> images)
			throws MessagingException, IOException, TemplateException {
		// create object of MimeMessage
		MimeMessage message = null;
		// create object of MimeMessageHelper
		MimeMessageHelper objHelper = null;
		// create object of String
		String strBody = null;
		try {
			logger.info("sendTemplateMail mail send to start " + to);
			// get body of mail from template
			strBody = FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerMailConfiguration.getTemplate(templetName),
					templateProp);
			// create object of message
			message = this.mailSender.createMimeMessage();
			// create object of MimeMessageHelper
			objHelper = new MimeMessageHelper(message, true);
			// set receiver to message
			objHelper.setTo(to);
			// set html body to mail
			objHelper.setText(strBody, true);
			// set subject for mail
			objHelper.setSubject(subject);
			// TODO add image
			if (images != null && !images.isEmpty()) {
				Set<String> imagesPaths = images.keySet();
				for (String imagePath : imagesPaths) {
					FileSystemResource res = new FileSystemResource(
							new File(this.servletContext
									.getRealPath("\\resources\\img"), images
									.get(imagePath)));
					objHelper.addInline(imagePath, res);
				}
			}
			// send mail
			mailSender.send(message);
			logger.info("sendTemplateMail mail send to end" + to);
		} finally {
			message = null;
			objHelper = null;
		}
	}

	// /**
	// * This method will send a pre-configured message
	// * */
	// public void sendPreConfiguredMail(String message) {
	// SimpleMailMessage mailMessage = new SimpleMailMessage(
	// preConfiguredMessage);
	// mailMessage.setText(message);
	// mailSender.send(mailMessage);
	// }
}
