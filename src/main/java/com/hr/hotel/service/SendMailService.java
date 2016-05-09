package com.hr.hotel.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.hr.hotel.dto.BookRoomMailData;
import com.hr.hotel.dto.SellRoomMailData;
import com.hr.hotel.model.User;

import freemarker.template.TemplateException;

public interface SendMailService {

	public void sendUserSignupMail(User user, String emailConfirmUrl,
			String token) throws MessagingException, IOException,
			TemplateException;

	public void sendForgotPasswordMail(User user, String redirectUrl,
			String token) throws MessagingException, IOException,
			TemplateException;

	public void sendSocialLoginMail(User user) throws MessagingException,
			IOException, TemplateException;

	public void sendMakeDelegateMail(User loginUser, User delegateUser)
			throws MessagingException, IOException, TemplateException;

	public void sendConfirmRoomMail(User sellFor, Date startDate, Date endDate,
			double price) throws MessagingException, IOException,
			TemplateException;

	public void sendRejectRoomMail(User sellFor, Date startDate, Date endDate,
			double price) throws MessagingException, IOException,
			TemplateException;

	public void sendBookRoomMail(User bookFor,
			List<BookRoomMailData> bookRoomMailDatas)
			throws MessagingException, IOException, TemplateException;

	public void sendSellRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException;

	public void moneyTransferRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException;

	public void moneyTransferSetPaypalEmail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException;

	public void sendSellInternRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException;

	public void sendSellExternRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException;

}
