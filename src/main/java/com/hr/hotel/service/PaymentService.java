package com.hr.hotel.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.hr.hotel.dto.CreditcardModel;
import com.hr.hotel.model.User;
import com.paypal.core.rest.PayPalRESTException;

import freemarker.template.TemplateException;

public interface PaymentService {

	// public Response paymentCreditcard(Integer roomId, Integer bookId,
	// PaymentCreditcardModel paymentCreditcardModel, User user)
	// throws PayPalRESTException, Exception;

	/*
	 * public String paywithPaypal(Integer bookId, User user) throws
	 * UnsupportedEncodingException, PayPalRESTException, Exception;
	 */

	// public Response getPaypalLink(User user,
	// PaypalModel paypalModel, Integer roomId, Integer bookId,
	// String access_token, HttpServletRequest request);
	//
	// public String paypalReturn(Integer userRoomBookingId, String status,
	// String payerID, String accessToken, User user,
	// String redirectURL) throws PayPalRESTException, Exception;
	//
	// public Response getPaypalStatus(User user, Integer roomId,
	// Integer bookId) throws Exception;
	//
	// public String paywithPaypal(Integer bookId, User user,
	// String accessToken, String redirectUrl)
	// throws UnsupportedEncodingException, PayPalRESTException, Exception;

	// TODO change
	// public Response paypalLink(User loginUser,
	// PaypalPayment paypalPayment, Integer bookId, String access_token,
	// HttpServletRequest request) throws Exception;
	//
	// public String paypalReturnOld(Integer bookId, String status,
	// String payerID, String accessToken, String redirectURL,
	// User loginUser) throws PayPalRESTException, Exception;

	// TODO new change
	public String paypalLink(User loginUser, String returnURL, String cancelURL)
			throws UnsupportedEncodingException, PayPalRESTException;

	public void paypalReturn(String status, String payerID, String accessToken,
			User loginUser) throws PayPalRESTException, Exception;

	public void creditCardPayment(User loginUser,
			CreditcardModel creditcardModel) throws MessagingException,
			IOException, TemplateException;

	public void paymentToSellerTransfer();

}
