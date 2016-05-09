package com.hr.hotel.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hr.hotel.common.Constant;
import com.hr.hotel.config.ApplicationConfig;
import com.hr.hotel.dto.CreditcardModel;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.model.User;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.HotelRoomService;
import com.hr.hotel.service.PaymentService;
import com.hr.hotel.service.UserService;
import com.paypal.core.rest.PayPalRESTException;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	private static final Logger logger = LoggerFactory
			.getLogger(PaymentController.class);

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	@Resource(name = Constant.SERVICE_PAYMENT)
	private PaymentService paymentService;

	@Resource(name = Constant.SERVICE_HOTEL_ROOM)
	private HotelRoomService hotelRoomService;

	@Autowired
	private ApplicationConfig config;

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

	@RequestMapping(method = RequestMethod.GET)
	public String payment(HttpServletRequest request, ModelMap modelMap,
			HttpServletResponse response, RedirectAttributes flashAttributes)
			throws UnsupportedEncodingException, PayPalRESTException {
		Double cartAmount = null;
		logger.info("payment home");
		User objUser = getUserDetail();
		if (objUser != null) {
			modelMap.put("isLogin", true);
			cartAmount = this.hotelRoomService.getCartAmount(objUser);
			if (cartAmount != null && cartAmount > 0) {
				modelMap.put("roomData",
						this.hotelRoomService.getCartData(objUser));
				modelMap.put("cartAmount", cartAmount);
				modelMap.put("userName", objUser.getUserName());
				return "hotel/cart";
			} else {
				return "redirect:/hotel/rooms-map";
			}
		} else {
			// flashAttributes.addFlashAttribute("redirecturl",
			// "redirect:/payment/home");
			return "redirect:/";
		}
	}

	@RequestMapping(value = "paypal", method = RequestMethod.GET)
	public String paypalLink(HttpServletRequest request,
			HttpServletResponse response) {
		StringBuilder objSB = null;
		String cancelURL = null;
		String returnURL = null;
		String paypalUrl = null;
		try {
			logger.info("paypalLink controller start");
			User objUser = getUserDetail();
			if (objUser != null) {
				objSB = new StringBuilder();

				objSB.delete(0, objSB.length()).append(request.getScheme())
						.append("://").append(request.getServerName())
						.append(":").append(request.getServerPort())
						.append(request.getContextPath())
						.append("/payment/paypalRedirect")
						.append(Constant.STRING_SLASH)
						.append(Constant.STRING_PAYPAL_STATUS_CANCEL);
				cancelURL = objSB.toString();

				objSB.delete(0, objSB.length()).append(request.getScheme())
						.append("://").append(request.getServerName())
						.append(":").append(request.getServerPort())
						.append(request.getContextPath())
						.append("/payment/paypalRedirect")
						.append(Constant.STRING_SLASH)
						.append(Constant.STRING_PAYPAL_STATUS_APPROVE);
				returnURL = objSB.toString();

				paypalUrl = this.paymentService.paypalLink(objUser, returnURL,
						cancelURL);
				objSB.delete(0, objSB.length()).append("redirect:")
						.append(paypalUrl);
				return objSB.toString();
			} else {
				return "redirect:/hotel/cart";
			}
			// send mail to user
		} catch (Exception exception) {
			logger.error("paypalLink", exception);
			exception.printStackTrace();
			return "redirect:/hotel/cart";
		}
	}

	@RequestMapping(value = "paypalRedirect/{status}", method = RequestMethod.GET)
	public String paypalReturn(@PathVariable(value = "status") String status,
			@RequestParam(value = "PayerID", required = false) String payerID,
			@RequestParam(value = "token") String accessToken,
			RedirectAttributes flashAttribute) {
		// store nextView
		String nextView = null;
		try {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			CustomUserDetail userDetail = null;
			if (authentication.getPrincipal() instanceof UserDetails) {
				userDetail = (CustomUserDetail) authentication.getPrincipal();
			}
			if (userDetail != null && userDetail.getUser() != null) {
				this.paymentService.paypalReturn(status, payerID, accessToken,
						userDetail.getUser());
				flashAttribute.addFlashAttribute("successMessage",
						"Transaction successful check your mail for detail");
				nextView = "redirect:/hotel/rooms-map";
			}
		} catch (PayPalRESTException palRESTException) {
			logger.error("paypalReturn", palRESTException);
			nextView = "redirect:/hotel/cart";
			flashAttribute.addFlashAttribute("error", "Error While");
			palRESTException.printStackTrace();
		} catch (Exception exception) {
			logger.error("paypalReturn", exception);
			nextView = "redirect:/hotel/cart";
			exception.printStackTrace();
		}
		return nextView;
	}

	@RequestMapping(value = "credit-card", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	Map<String, String> paymentCreditcard(
			@Valid @RequestBody CreditcardModel creditcardModel)
			throws Exception {
		Map<String, String> response = null;
		try {
			logger.info("paymentCreditcard controller start");
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			CustomUserDetail userDetail = null;
			if (authentication.getPrincipal() instanceof UserDetails) {
				userDetail = (CustomUserDetail) authentication.getPrincipal();
			}
			if (userDetail != null && userDetail.getUser() != null) {
				this.paymentService.creditCardPayment(userDetail.getUser(),
						creditcardModel);
				response = new HashMap<String, String>();
				response.put("message", "success");
				return response;
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

}
