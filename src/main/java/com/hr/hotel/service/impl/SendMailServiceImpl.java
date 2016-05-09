package com.hr.hotel.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.BookRoomMailData;
import com.hr.hotel.dto.SellRoomMailData;
import com.hr.hotel.model.User;
import com.hr.hotel.service.ApplicationMailerService;
import com.hr.hotel.service.SendMailService;

import freemarker.template.TemplateException;

@Service(value = Constant.SERVICE_SEND_MAIL)
public class SendMailServiceImpl implements SendMailService {

	@Resource(name = Constant.SERVICE_APPLICATION_MAIL)
	private ApplicationMailerService applicationMailerService;

	private static final Logger logger = LoggerFactory
			.getLogger(SendMailServiceImpl.class);

	@Override
	public void sendUserSignupMail(User user, String redirectUrl, String token)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		StringBuilder objSB = null;
		try {
			logger.info("sendUserSignupMail start");
			// mail
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", user.getUserName());
			objSB = new StringBuilder();
			objSB.delete(0, objSB.length()).append(redirectUrl).append("?key=")
					.append(token);
			objMapTemplet.put("activeAccountUrl", objSB.toString());
			Map<String, String> images = new HashMap<String, String>();
			images.put("orsEmailLogo", "orsEmailLogo.png");
			images.put("OSR_emailActive", "OSR_emailActive.png");
			// send mail to user
			this.applicationMailerService
					.sendTemplateMail(user.getEmail(),
							Constant.EMAIL_HEADER_CONFIRM_EMAIL,
							Constant.TEMPLATE_EMAIL_CREATE_ACCOUNT,
							objMapTemplet, images);
			logger.info("sendUserSignupMail end");
		} finally {
			objMapTemplet = null;
			objSB = null;
		}
	}

	@Override
	public void sendForgotPasswordMail(User user, String redirectUrl,
			String token) throws MessagingException, IOException,
			TemplateException {
		Map<String, Object> objMapTemplet = null;
		StringBuilder objSB = null;
		try {
			logger.info("sendForgotPasswordMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", user.getUserName());
			objSB = new StringBuilder();
			objSB.delete(0, objSB.length()).append(redirectUrl).append("?key=")
					.append(token);
			objMapTemplet.put("resetPasswordUrl", objSB.toString());
			Map<String, String> images = new HashMap<String, String>();
			images.put("orsEmailLogo", "orsEmailLogo.png");
			images.put("emailResetPW", "emailResetPW.png");
			
			// send mail to user
			this.applicationMailerService
					.sendTemplateMail(user.getEmail(),
							Constant.EMAIL_HEADER_RESET_PASSWORD,
							Constant.TEMPLATE_EMAIL_RESET_PASSWORD,
							objMapTemplet, images);
			logger.info("sendForgotPasswordMail end");
		} finally {
			objMapTemplet = null;
			objSB = null;
		}
	}

	@Override
	public void sendSocialLoginMail(User user) throws MessagingException,
			IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendSocialLoginMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", user.getUserName());
			Map<String, String> images = new HashMap<String, String>();
			images.put("orsEmailLogo", "orsEmailLogo.png");
			// send mail to user
			this.applicationMailerService.sendTemplateMail(user.getEmail(),
					Constant.EMAIL_HEADER_SOCIAL_ACCOUNT,
					Constant.TEMPLATE_SOCIAL_CREATE_ACCOUNT, objMapTemplet,
					images);
			logger.info("sendSocialLoginMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void sendMakeDelegateMail(User loginUser, User delegateUser)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMakeDelegateTemplet = null;
		Map<String, Object> objInviteDelegateTemplet = null;
		try {
			logger.info("sendMakeDelegateMail start");
			// get user data from list
			objMakeDelegateTemplet = new HashMap<String, Object>();
			objMakeDelegateTemplet.put("mainUser", loginUser.getUserName());
			objMakeDelegateTemplet.put("delegateUser",
					delegateUser.getUserName());
			// send mail to user
			this.applicationMailerService.sendTemplateMail(
					loginUser.getEmail(), Constant.EMAIL_HEADER_MAKE_DELEGET,
					Constant.TEMPLATE_MAKE_DELEGATE, objMakeDelegateTemplet,
					null);

			objInviteDelegateTemplet = new HashMap<String, Object>();
			objInviteDelegateTemplet.put("mainUser", loginUser.getUserName());
			objInviteDelegateTemplet.put("delegateUser",
					delegateUser.getUserName());
			// send mail to user
			this.applicationMailerService.sendTemplateMail(
					loginUser.getEmail(), Constant.EMAIL_HEADER_INVITE_DELEGET,
					Constant.TEMPLATE_INVITE_DELEGATE,
					objInviteDelegateTemplet, null);
			logger.info("sendMakeDelegateMail end");
		} finally {
			objMakeDelegateTemplet = null;
			objInviteDelegateTemplet = null;
		}
	}

	@Override
	public void sendConfirmRoomMail(User sellFor, Date startDate, Date endDate,
			double price) throws MessagingException, IOException,
			TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendConfirmRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellFor.getUserName());
			objMapTemplet.put("startdate", startDate);
			objMapTemplet.put("enddate", endDate);
			objMapTemplet.put("cost", price);
			// send mail to user
			this.applicationMailerService.sendTemplateMail(sellFor.getEmail(),
					Constant.EMAIL_HEADER_ROOM_CONFIRM,
					Constant.TEMPLATE_CONFIRM_SELL_ROOM, objMapTemplet, null);
			logger.info("sendConfirmRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void sendRejectRoomMail(User sellFor, Date startDate, Date endDate,
			double price) throws MessagingException, IOException,
			TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendRejectRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellFor.getUserName());
			objMapTemplet.put("startdate", startDate);
			objMapTemplet.put("enddate", endDate);
			objMapTemplet.put("cost", price);
			// send mail to user
			this.applicationMailerService.sendTemplateMail(sellFor.getEmail(),
					Constant.EMAIL_HEADER_REJECT_SELL_ROOM,
					Constant.TEMPLATE_REJECT_SELL_ROOM, objMapTemplet, null);
			logger.info("sendRejectRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void sendBookRoomMail(User bookFor,
			List<BookRoomMailData> bookRoomMailDatas)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendBookRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", bookFor.getUserName());
			objMapTemplet.put("roomData", bookRoomMailDatas);
			// send mail to user
			this.applicationMailerService.sendTemplateMail(bookFor.getEmail(),
					Constant.EMAIL_HEADER_BOOK_ROOMS,
					Constant.TEMPLATE_EMAIL_BOOK_ROOM, objMapTemplet, null);
			logger.info("sendBookRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void sendSellRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendSellRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellRoomMailData.getUserName());
			objMapTemplet.put("startdate", sellRoomMailData.getStartDate());
			objMapTemplet.put("enddate", sellRoomMailData.getEndDate());
			objMapTemplet.put("hotelname", sellRoomMailData.getHotelName());
			objMapTemplet.put("price", sellRoomMailData.getPrice());

			// send mail to user
			this.applicationMailerService.sendTemplateMail(
					sellRoomMailData.getEmail(),
					Constant.EMAIL_HEADER_SELL_ROOM,
					Constant.TEMPLATE_EMAIL_SELL_ROOM, objMapTemplet, null);
			logger.info("sendSellRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void sendSellInternRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendSellRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellRoomMailData.getUserName());
			objMapTemplet.put("startdate", sellRoomMailData.getStartDate());
			objMapTemplet.put("enddate", sellRoomMailData.getEndDate());
			objMapTemplet.put("hotelname", sellRoomMailData.getHotelName());
			objMapTemplet.put("price", sellRoomMailData.getPrice());

			// send mail to user
			this.applicationMailerService.sendTemplateMail(
					sellRoomMailData.getEmail(),
					Constant.EMAIL_HEADER_SELL_ROOM,
					Constant.TEMPLATE_EMAIL_SELL_ROOM_INTERN, objMapTemplet,
					null);
			logger.info("sendSellRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void sendSellExternRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("sendSellRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellRoomMailData.getUserName());
			objMapTemplet.put("startdate", sellRoomMailData.getStartDate());
			objMapTemplet.put("enddate", sellRoomMailData.getEndDate());
			objMapTemplet.put("hotelname", sellRoomMailData.getHotelName());
			objMapTemplet.put("price", sellRoomMailData.getPrice());

			// send mail to user
			this.applicationMailerService.sendTemplateMail(
					sellRoomMailData.getEmail(),
					Constant.EMAIL_HEADER_SELL_ROOM,
					Constant.TEMPLATE_EMAIL_SELL_ROOM_EXTERN, objMapTemplet,
					null);
			logger.info("sendSellRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void moneyTransferRoomMail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("moneyTransferRoomMail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellRoomMailData.getUserName());
			objMapTemplet.put("startdate", sellRoomMailData.getStartDate());
			objMapTemplet.put("enddate", sellRoomMailData.getEndDate());
			objMapTemplet.put("hotelname", sellRoomMailData.getHotelName());
			objMapTemplet.put("price", sellRoomMailData.getPrice());

			// send mail to user
			this.applicationMailerService
					.sendTemplateMail(sellRoomMailData.getEmail(),
							Constant.EMAIL_HEADER_MONEY_TRANSFER,
							Constant.TEMPLATE_EMAIL_MONEY_TRANSFER,
							objMapTemplet, null);
			logger.info("moneyTransferRoomMail end");
		} finally {
			objMapTemplet = null;
		}
	}

	@Override
	public void moneyTransferSetPaypalEmail(SellRoomMailData sellRoomMailData)
			throws MessagingException, IOException, TemplateException {
		Map<String, Object> objMapTemplet = null;
		try {
			logger.info("moneyTransferSetPaypalEmail start");
			// get user data from list
			objMapTemplet = new HashMap<String, Object>();
			objMapTemplet.put("name", sellRoomMailData.getUserName());
			objMapTemplet.put("startdate", sellRoomMailData.getStartDate());
			objMapTemplet.put("enddate", sellRoomMailData.getEndDate());
			objMapTemplet.put("hotelname", sellRoomMailData.getHotelName());
			objMapTemplet.put("price", sellRoomMailData.getPrice());

			// send mail to user
			this.applicationMailerService.sendTemplateMail(
					sellRoomMailData.getEmail(),
					Constant.EMAIL_HEADER_SELL_ROOM,
					Constant.TEMPLATE_EMAIL_MONEY_TRANSFER_PAYPAL_SET,
					objMapTemplet, null);
			logger.info("moneyTransferSetPaypalEmail end");
		} finally {
			objMapTemplet = null;
		}
	}
}
