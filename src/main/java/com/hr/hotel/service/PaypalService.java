package com.hr.hotel.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.paypal.api.payments.Payment;
import com.paypal.core.rest.PayPalRESTException;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.PayResponse;

public interface PaypalService {
	/*
	 * public String createPaymentLink(HotelRoom hotelRoom, UserRoomBooking
	 * userRoomBooking, PaymentDetail paymentDetail) throws
	 * UnsupportedEncodingException, PayPalRESTException, Exception;
	 */
	public String getApprovalURL(Payment payment)
			throws UnsupportedEncodingException;

	// public void executePaymet(String payerId, PaymentMaster paymentMaster)
	// throws PayPalRESTException, Exception;

	/*
	 * public Response creditCardPayment(PaymentCreditcardModel creditcardModel,
	 * User user, Integer roomId) throws PayPalRESTException, Exception;
	 */

	// public String createPaypalLink(UserRoomBooking userRoomBooking,String
	// accessToken, String redirectUrl)
	// throws UnsupportedEncodingException, PayPalRESTException, Exception;

	/*
	 * public Response creditCardPayment(PaymentCreditcardModel creditcardModel,
	 * User user, Integer roomId, Integer bookId) throws PayPalRESTException,
	 * Exception;
	 */

	public Boolean isPaypalVarifiedAccount(String email);

	// public boolean isPaymentDone(PaymentCreditcardModel creditcardModel,
	// User user, Integer roomId) throws PayPalRESTException,
	// Exception;

	// TODO change design
	public Payment createPayapalPaymentObject(Long cartId, Double cost,
			String returnUrl, String cancelUrl)
			throws UnsupportedEncodingException, PayPalRESTException;

	/**
	 * executePaymet method execute paypal transaction after paypal response
	 * 
	 * @param payerId
	 *            object of String
	 * @param paymentMaster
	 *            object of PaymentMaster to get paymentId
	 * @return Payment object
	 * @throws PayPalRESTException
	 *             ,Exception
	 */
	public Payment executePaymet(String payerId, String transactionId)
			throws PayPalRESTException;

	/**
	 * paymentToSeller create payment object and make payment to seller
	 * 
	 * @param amount
	 *            amount to transfer
	 * @param email
	 *            address of receiver
	 * @return object of PayResponse
	 * @throws SSLConfigurationException
	 * @throws InvalidCredentialException
	 * @throws UnsupportedEncodingException
	 * @throws HttpErrorException
	 * @throws InvalidResponseDataException
	 * @throws ClientActionRequiredException
	 * @throws MissingCredentialException
	 * @throws OAuthException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public PayResponse paymentToSeller(Double amount, String email)
			throws SSLConfigurationException, InvalidCredentialException,
			UnsupportedEncodingException, HttpErrorException,
			InvalidResponseDataException, ClientActionRequiredException,
			MissingCredentialException, OAuthException, IOException,
			InterruptedException;
}
