package com.hr.hotel.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.CreditcardModel;
import com.hr.hotel.dto.PaypalRedirect;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.model.User;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.PaymentService;
import com.hr.hotel.service.UserService;
import com.paypal.core.rest.PayPalRESTException;

@Controller
@RequestMapping("/rest/payment")
public class RestPaymentController {

	private static final Logger logger = LoggerFactory
			.getLogger(RestPaymentController.class);

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	@Resource(name = Constant.SERVICE_PAYMENT)
	private PaymentService paymentService;

	private User getUserDetail() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		if (authentication.getPrincipal() instanceof UserDetails) {
			CustomUserDetail userDetails = (CustomUserDetail) authentication
					.getPrincipal();
			if (userDetails != null && userDetails.getUser() != null) {
				return userDetails.getUser();
			}
		}
		return null;
	}

	/*
	 * @RequestMapping(value = "creditCard/{roomId}", method =
	 * RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	 * public
	 * 
	 * @ResponseBody Response paymentCreditcard(@PathVariable Integer roomId,
	 * 
	 * @RequestBody @Valid PaymentCreditcardModel paymentCreditcardModel,
	 * Principal principal) { Response response = null; User objUser = null; try
	 * { logger.info("paymentCreditcard controller start"); objUser =
	 * this.restUserService.getUserData(principal); if (objUser != null) {
	 * response = this.restPaymentService.paymentCreditcard(roomId,
	 * paymentCreditcardModel, objUser); } else { response = new
	 * Response(Constant.ERROR_LOGIN_MESSAGE, "", true,
	 * Constant.ERROR_LOGIN_CODE); }
	 * logger.info("paymentCreditcard controller end"); } catch (IOException
	 * ioException) { ioException.printStackTrace(); response = new Response(
	 * "Some Error Occure Please Contact Administrator", "", true);
	 * ioException.printStackTrace(); } catch (TemplateException
	 * templateException) { templateException.printStackTrace(); response = new
	 * Response( "Some Error Occure Please Contact Administrator", "", true);
	 * templateException.printStackTrace(); } catch (MessagingException
	 * messagingException) { messagingException.printStackTrace(); response =
	 * new Response( "Some Error Occure Please Contact Administrator", "",
	 * true); messagingException.printStackTrace(); } catch (PayPalRESTException
	 * payPalRESTException) { payPalRESTException.printStackTrace(); response =
	 * new Response("Yourcard was decliend", "", true,
	 * Constant.ERROR_PAYMENT_CODE); payPalRESTException.printStackTrace(); }
	 * catch (Exception exception) { exception.printStackTrace(); response = new
	 * Response( "Some Error Occure Please Contact Administrator", "", true);
	 * exception.printStackTrace(); } finally {
	 * 
	 * } return response; }
	 */

	// @RequestMapping(value = "getPaypalLink/{roomId}/{bookId}", method =
	// RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public Response getPaypalLink(
	// @PathVariable Integer roomId,
	// @PathVariable Integer bookId,
	// @Validated(value = { PaypalPaymentCheck.class }) @RequestBody PaypalModel
	// paypalModel,
	// HttpServletRequest request, Principal principal) {
	// Response response = null;
	// User objUser = null;
	// String access_token = null;
	// try {
	// logger.info("sellRoomExtern controller start");
	// objUser = this.restUserService.getUserData(principal);
	//
	// if (StringUtils.hasText(request.getHeader("Authorization"))) {
	// access_token = URLEncoder.encode(
	// request.getHeader("Authorization"), Constant.UTF8);
	// } else {
	// access_token = request.getParameter("access_token");
	// }
	//
	// // access_token = (String) (request.getHeader("Authorization") !=
	// // null ? request
	// // .getHeader("Authorization") : request
	// // .getParameter("access_token"));
	//
	// if (objUser != null) {
	// // TODO CHANGE
	// // response =
	// // this.restPaymentService.getPaypalLink(objUser,
	// // paypalModel, roomId, bookId, access_token, request);
	// } else {
	// response = new Response(Constant.ERROR_LOGIN_MESSAGE, "", true,
	// Constant.ERROR_LOGIN_CODE);
	// }
	// // send mail to user
	// logger.info("sellRoomExtern controller end");
	// } catch (Exception exception) {
	// logger.error("sellRoomExtern", exception);
	// response = new Response(
	// "Some Error Occure Please Contact Administrator", "", true);
	// exception.printStackTrace();
	// } finally {
	//
	// }
	// return response;
	// }

	@RequestMapping(value = "getPaypalStatus/{roomId}/{bookId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, Object> getPaypalStatus(
			@PathVariable Integer roomId, @PathVariable Integer bookId,
			HttpServletResponse response) throws Exception {
		Map<String, Object> objResponse = null;
		User objUser = null;
		try {
			logger.info("getPaypalStatus start");
			objUser = getUserDetail();
			if (objUser != null) {
				// TODO change
				// response = this.restPaymentService.getPaypalStatus(
				// objUser, roomId, bookId);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			logger.info("getPaypalStatus end");
		} finally {

		}
		return objResponse;
	}

	//
	// @RequestMapping(value = "paypal/{bookId}", method = RequestMethod.GET)
	// public String paywithPaypal(@PathVariable Integer bookId,
	// @RequestParam("access_token") String accessToken,
	// @RequestParam("redirect_url") String redirectURL,
	// Principal principal) {
	// String nextView = null;
	// User objUser = null;
	// try {
	// logger.info("paywithPaypal start");
	// nextView = "redirect:" + redirectURL;
	// objUser = this.userService.getUserData(principal);
	// if (objUser != null) {
	// // TODO change
	// // nextView = this.restPaymentService.paywithPaypal(bookId,
	// // objUser, accessToken, redirectURL);
	// }
	// logger.info("paywithPaypal end");
	// } catch (Exception e) {
	// nextView = "redirect:" + redirectURL;
	// e.printStackTrace();
	// logger.error("paywithPaypal", e);
	// } finally {
	//
	// }
	// return nextView;
	// }

	// @RequestMapping(value = "paypalReturn")
	// public String paypalReturn() {
	// return "testPayment";
	// }
	//
	// @RequestMapping(value = "paypalRedirectTest")
	// public String paypalRedirect() {
	// return "testPayment";
	// }
	//
	// @RequestMapping(value = "testPay", method = RequestMethod.POST)
	// public String testPay() {
	// return "testPay";
	// }

	// @RequestMapping(value =
	// "paypalPaymentRedirect/{userRoomBooking}/{status}")
	// public String paypalReturn(
	// @PathVariable(value = Constant.PATH_PARAM_USER_ROOM_BOOKING_ID) Integer
	// userRoomBookingId,
	// @PathVariable(value = Constant.PATH_PARAM_STATUS) String status,
	// @RequestParam(value = Constant.REQUEST_PARAM_PAYPAL_PAYERID, required =
	// false) String payerID,
	// @RequestParam(value = Constant.REQUEST_PARAM_PAYPAL_TOKEN) String
	// accessToken,
	// @RequestParam("access_token") String access_token,
	// @RequestParam("redirect_url") String redirectURL,
	// Principal principal) {
	// // store nextView
	// String nextView = null;
	// User objUser = null;
	// try {
	// nextView = "redirect:" + redirectURL;
	// objUser = this.restUserService.getUserData(principal);
	// if (objUser != null) {
	// // TODO change
	// // nextView = this.restPaymentService.paypalReturn(
	// // userRoomBookingId, status, payerID, accessToken,
	// // objUser, redirectURL);
	// }
	//
	// } catch (PayPalRESTException palRESTException) {
	// logger.error("paypalReturn", palRESTException);
	// nextView = "redirect:" + redirectURL;
	// } catch (Exception exception) {
	// logger.error("paypalReturn", exception);
	// nextView = "redirect:" + redirectURL;
	// }
	// return nextView;
	// }

	// TODO change Design
	// @RequestMapping(value = "paypalLinkOld/{bookId}", method =
	// RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseBody
	// public Response paypalLink(@PathVariable Integer bookId,
	// @Valid @RequestBody PaypalPayment paypalPayment,
	// // @Validated(value = { PaypalPaymentCheck.class }) @RequestBody
	// // PaypalModel paypalModel,
	// HttpServletRequest request, Principal principal) {
	// Response response = null;
	// User objUser = null;
	// String access_token = null;
	// try {
	// logger.info("sellRoomExtern controller start");
	// objUser = this.restUserService.getUserData(principal);
	//
	// if (StringUtils.hasText(request.getHeader("Authorization"))) {
	// access_token = URLEncoder.encode(
	// request.getHeader("Authorization"), Constant.UTF8);
	// } else {
	// access_token = request.getParameter("access_token");
	// }
	//
	// if (objUser != null) {
	// response = this.restPaymentService.paypalLink(objUser,
	// paypalPayment, bookId, access_token, request);
	//
	// } else {
	// response = new Response(Constant.ERROR_LOGIN_MESSAGE, "", true,
	// Constant.ERROR_LOGIN_CODE);
	// }
	// // send mail to user
	// logger.info("sellRoomExtern controller end");
	// } catch (Exception exception) {
	// logger.error("sellRoomExtern", exception);
	// response = new Response(
	// "Some Error Occure Please Contact Administrator", "", true);
	// exception.printStackTrace();
	// } finally {
	// objUser = null;
	// }
	// return response;
	// }
	//
	// @RequestMapping(value = "paypalRedirectOld/{bookId}/{status}")
	// public String paypalReturnOld(
	// @PathVariable(value = "bookId") Integer bookId,
	// @PathVariable(value = Constant.PATH_PARAM_STATUS) String status,
	// @RequestParam(value = Constant.REQUEST_PARAM_PAYPAL_PAYERID, required =
	// false) String payerID,
	// @RequestParam(value = Constant.REQUEST_PARAM_PAYPAL_TOKEN) String
	// accessToken,
	// @RequestParam("redirect_url") String redirectURL,
	// Principal principal) {
	// // store nextView
	// String nextView = null;
	// User objUser = null;
	// try {
	// nextView = "redirect:" + redirectURL;
	// objUser = this.restUserService.getUserData(principal);
	// if (objUser != null) {
	// nextView = this.restPaymentService.paypalReturnOld(bookId,
	// status, payerID, accessToken, redirectURL,
	// objUser);
	// }
	// } catch (PayPalRESTException palRESTException) {
	// logger.error("paypalReturn", palRESTException);
	// nextView = "redirect:" + redirectURL;
	// palRESTException.printStackTrace();
	// } catch (Exception exception) {
	// logger.error("paypalReturn", exception);
	// nextView = "redirect:" + redirectURL;
	// exception.printStackTrace();
	// } finally {
	// objUser = null;
	// }
	// return nextView;
	// }

	// TODO change new design cart
	@RequestMapping(value = "paypalLink", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Map<String, Object> paypalLink(
			@Valid @RequestBody PaypalRedirect paypalRedirect,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, PayPalRESTException {
		Map<String, Object> objResponse = null;
		User objUser = null;
		String access_token = null;
		String paypalUrl = null;
		StringBuilder objSB = null;
		String returnURL = null;
		String cancelURL = null;
		try {
			logger.info("paypalLink controller start");
			objUser = getUserDetail();

			if (StringUtils.hasText(request.getHeader("Authorization"))) {
				access_token = URLEncoder.encode(
						request.getHeader("Authorization"), Constant.UTF8);
			} else {
				access_token = request.getParameter("access_token");
			}

			if (objUser != null) {
				objSB = new StringBuilder();

				objSB.delete(0, objSB.length())
						.append(request.getScheme())
						.append("://")
						.append(request.getServerName())
						.append(":")
						.append(request.getServerPort())
						.append(request.getContextPath())
						.append("/rest/payment/paypalRedirect")
						.append(Constant.STRING_SLASH)
						.append(Constant.STRING_PAYPAL_STATUS_CANCEL)
						.append("?access_token=")
						.append(URLEncoder.encode(access_token, Constant.UTF8))
						.append("&redirect_url=")
						.append(URLEncoder.encode(
								paypalRedirect.getCancelURL(), Constant.UTF8));
				cancelURL = objSB.toString();
				objSB.delete(0, objSB.length())
						.append(request.getScheme())
						.append("://")
						.append(request.getServerName())
						.append(":")
						.append(request.getServerPort())
						.append(request.getContextPath())
						.append("/rest/payment/paypalRedirect")
						.append(Constant.STRING_SLASH)
						.append(Constant.STRING_PAYPAL_STATUS_APPROVE)
						.append("?access_token=")
						.append(URLEncoder.encode(access_token, Constant.UTF8))
						.append("&redirect_url=")
						.append(URLEncoder.encode(
								paypalRedirect.getReturnURL(), Constant.UTF8));
				returnURL = objSB.toString();
				paypalUrl = this.paymentService.paypalLink(objUser, returnURL,
						cancelURL);
				objResponse = new HashMap<String, Object>();
				objResponse.put("paypalUrl", paypalUrl);

			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
			// send mail to user
			logger.info("paypalLink controller end");
		} finally {
			objUser = null;
		}
		return objResponse;
	}

	@RequestMapping(value = "paypalRedirect/{status}")
	public String paypalReturn(@PathVariable(value = "status") String status,
			@RequestParam(value = "PayerID", required = false) String payerID,
			@RequestParam(value = "token") String accessToken,
			@RequestParam("redirect_url") String redirectURL) {
		// store nextView
		String nextView = null;
		User objUser = null;
		try {
			nextView = "redirect:" + redirectURL;
			objUser = getUserDetail();
			if (objUser != null) {
				this.paymentService.paypalReturn(status, payerID, accessToken,
						objUser);
			}
		} catch (PayPalRESTException palRESTException) {
			logger.error("paypalReturn", palRESTException);
			nextView = "redirect:" + redirectURL;
			palRESTException.printStackTrace();
		} catch (Exception exception) {
			logger.error("paypalReturn", exception);
			nextView = "redirect:" + redirectURL;
			exception.printStackTrace();
		} finally {
			objUser = null;
		}
		return nextView;
	}

	@RequestMapping(value = "credit-card", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Map<String, String> paymentCreditcard(
			@RequestBody @Valid CreditcardModel creditcardModel)
			throws Exception {
		Map<String, String> objResponse = null;
		User objUser = null;
		try {
			logger.info("paymentCreditcard controller start");
			objUser = getUserDetail();
			if (objUser != null) {
				this.paymentService.creditCardPayment(objUser, creditcardModel);
				objResponse = new HashMap<String, String>();
				objResponse.put("message", "success");
				return objResponse;
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}
}
