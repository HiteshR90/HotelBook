package com.hr.hotel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hr.hotel.common.Constant;
import com.hr.hotel.config.SocialConfig.SocialProvider;
import com.hr.hotel.dto.BookHistoryDto;
import com.hr.hotel.dto.ChangePasswordDto;
import com.hr.hotel.dto.ForgotPasswordDto;
import com.hr.hotel.dto.LoginResponse;
import com.hr.hotel.dto.OAuth2Request;
import com.hr.hotel.dto.PendingTransactionDto;
import com.hr.hotel.dto.ProfileBankAccount;
import com.hr.hotel.dto.ProfilePaypal;
import com.hr.hotel.dto.ResetPasswordDTO;
import com.hr.hotel.dto.ResponseMessageDto;
import com.hr.hotel.dto.SellHistoryDto;
import com.hr.hotel.dto.SignupUser;
import com.hr.hotel.dto.UserLoginDto;
import com.hr.hotel.dto.UserPreferenceDto;
import com.hr.hotel.dto.UserProfile;
import com.hr.hotel.dto.UserProfileDto;
import com.hr.hotel.dto.ValidationErrorDTO;
import com.hr.hotel.dto.ForgotPasswordDto.ForgotPassword;
import com.hr.hotel.dto.ProfilePaypal.CheckProfilePaypal;
import com.hr.hotel.dto.ResetPasswordDTO.RestResetpassword;
import com.hr.hotel.dto.SignupUser.MvcSignUpUser;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.BaseWebApplicationException;
import com.hr.hotel.exception.FieldErrorException;
import com.hr.hotel.model.User;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.HotelRoomService;
import com.hr.hotel.service.PaypalService;
import com.hr.hotel.service.UserPreferenceService;
import com.hr.hotel.service.UserService;

import freemarker.template.TemplateException;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	@Resource(name = Constant.SERVICE_PAYPAL)
	private PaypalService paypalService;

	@Resource(name = Constant.SERVICE_HOTEL_ROOM)
	private HotelRoomService hotelRoomService;

	@Resource(name = Constant.SERVICE_USER_PREFERENCE)
	private UserPreferenceService userPreferenceService;

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

	// @RequestMapping(value = "login", method = RequestMethod.POST)
	// @ResponseBody
	// public ValidationResponse login(
	// @Valid @ModelAttribute("user") UserLoginDto user,
	// BindingResult result, Locale locale, ModelMap model,
	// HttpSession session) {
	// // create object of ValidationResponse
	// ValidationResponse objValidationResponse = null;
	// // create object of List
	// List<FieldError> allErrors = null;
	// List<ErrorMessage> errorMesages = null;
	// // create object of StringBuilder
	// StringBuilder objSB = null;
	// AccessTokenContainer accessTokenContainer = null;
	// ValidationErrorDTO errorDto = null;
	// try {
	// objValidationResponse = new ValidationResponse();
	// errorMesages = new ArrayList<ErrorMessage>();
	// objSB = new StringBuilder();
	// if (null != model.get(Constant.SESSION_ACCESS_TOKEN)) {
	// // forward to next view
	// objValidationResponse.setStatus(Constant.STATUS_SUCCESS);
	// objSB.delete(0, objSB.length()).append("hotel/rooms");
	// objValidationResponse.setRedirect(objSB.toString());
	// // if session is not available
	// } else {
	// // Check that is there any error in filed or not
	// if (result.hasErrors()) { // Check that is there any error in
	// // filed or not
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// allErrors = result.getFieldErrors();
	// for (FieldError objectError : allErrors) {
	// errorMesages.add(new ErrorMessage(objectError
	// .getField(), objectError.getDefaultMessage()));
	// }
	// objValidationResponse.setErrorMessageList(errorMesages);
	// // if there is no error found
	// } else {
	// accessTokenContainer = this.userService.login(user);
	// session.setAttribute(Constant.SESSION_ACCESS_TOKEN,
	// accessTokenContainer.getAccess_token());
	// objSB.delete(0, objSB.length()).append("hotel/rooms");
	// objValidationResponse.setStatus(Constant.STATUS_SUCCESS);
	// objValidationResponse.setRedirect(objSB.toString());
	// }
	// }
	// } catch (BaseWebApplicationException exception) {
	// errorMesages.add(new ErrorMessage(Constant.MAP_ERROR_MESSAGE,
	// exception.getErrorMessage()));
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// objValidationResponse.setErrorMessageList(errorMesages);
	// } catch (FieldErrorException exception) {
	// errorDto = exception.getValidationErrorDTO();
	// for (FieldErrorDTO fieldError : errorDto.getFieldErrors()) {
	// errorMesages.add(new ErrorMessage(fieldError.getField(),
	// fieldError.getMessage()));
	// }
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// objValidationResponse.setErrorMessageList(errorMesages);
	// } finally {
	//
	// }
	// return objValidationResponse;
	// }

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public LoginResponse login(@Valid @RequestBody UserLoginDto loginDto,
			HttpSession session) {
		UserDetails objUser = this.userService.login(loginDto);
		session.setAttribute(Constant.SESSION_USER, objUser);
		return new LoginResponse(objUser.getUsername());
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto signupUser(
			@Validated(MvcSignUpUser.class) @RequestBody SignupUser user,
			HttpServletRequest request) throws IOException, TemplateException,
			MessagingException, Exception {
		StringBuilder objSB = new StringBuilder();
		objSB.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath());
		user.setRedirectURL(objSB.toString());
		this.userService.signupUser(user);
		return new ResponseMessageDto("Please Check Your Mail For Confirmation");
	}

	// @RequestMapping(value = "signup", method = RequestMethod.POST)
	// @ResponseBody
	// public ValidationResponse signup(
	// @Validated(MvcSignUpUser.class) @ModelAttribute("registerUser")
	// SignupUser user,
	// BindingResult result, Locale locale, ModelMap model) {
	// // create object of ValidationResponse
	// ValidationResponse objValidationResponse = null;
	// // create object of List
	// List<FieldError> allErrors = null;
	// List<ErrorMessage> errorMesages = null;
	// // create object of StringBuilder
	// StringBuilder objSB = null;
	// Map<String, Object> responseData = null;
	// ValidationErrorDTO errorDto = null;
	// try {
	// objValidationResponse = new ValidationResponse();
	// errorMesages = new ArrayList<ErrorMessage>();
	// objSB = new StringBuilder();
	// if (null != model.get(Constant.SESSION_ACCESS_TOKEN)) {
	// // forward to next view
	// objValidationResponse.setStatus(Constant.STATUS_SUCCESS);
	// objSB.delete(0, objSB.length()).append("hotel/rooms");
	// objValidationResponse.setRedirect(objSB.toString());
	// // if session is not available
	// } else {
	// // Check that is there any error in filed or not
	// if (result.hasErrors()) { // Check that is there any error in
	// // filed or not
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// allErrors = result.getFieldErrors();
	// for (FieldError objectError : allErrors) {
	// errorMesages.add(new ErrorMessage(objectError
	// .getField(), objectError.getDefaultMessage()));
	// }
	// objValidationResponse.setErrorMessageList(errorMesages);
	// // if there is no error found
	// } else {
	// user.setRedirectURL("http://localhost:9090/onesuite/");
	// responseData = this.userService.signupUser(user);
	// objValidationResponse.setStatus(Constant.STATUS_SUCCESS);
	// objValidationResponse.setRedirect(objSB.toString());
	// objValidationResponse.setSuccessMessage(String
	// .valueOf(responseData.get("message")));
	// }
	// }
	// } catch (BaseWebApplicationException exception) {
	// errorMesages = new ArrayList<ErrorMessage>();
	// errorMesages.add(new ErrorMessage(Constant.MAP_ERROR_MESSAGE,
	// exception.getErrorMessage()));
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// objValidationResponse.setErrorMessageList(errorMesages);
	// } catch (FieldErrorException exception) {
	// errorMesages = new ArrayList<ErrorMessage>();
	// errorDto = exception.getValidationErrorDTO();
	// for (FieldErrorDTO fieldError : errorDto.getFieldErrors()) {
	// errorMesages.add(new ErrorMessage(fieldError.getField(),
	// fieldError.getMessage()));
	// }
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// objValidationResponse.setErrorMessageList(errorMesages);
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (TemplateException e) {
	// e.printStackTrace();
	// } catch (MessagingException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	//
	// }
	// return objValidationResponse;
	// }

	// @RequestMapping(value = "facebookLogin/{access_token}", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public ValidationResponse facebookLogin(
	// @PathVariable("access_token") String accessToken,
	// HttpSession session) {
	// ValidationResponse objValidationResponse = null;
	// OAuth2Request objAuth2Request = null;
	// AccessTokenContainer accessTokenContainer = null;
	// StringBuilder objSB = null;
	// List<ErrorMessage> errorMesages = null;
	// ValidationErrorDTO errorDto = null;
	// try {
	// objValidationResponse = new ValidationResponse();
	// objAuth2Request = new OAuth2Request();
	// objSB = new StringBuilder();
	// objAuth2Request.setAccessToken(accessToken);
	//
	// accessTokenContainer = this.userService.socialLogin(
	// SocialProvider.facebook, objAuth2Request);
	// session.setAttribute(Constant.SESSION_ACCESS_TOKEN,
	// accessTokenContainer.getAccess_token());
	// objSB.delete(0, objSB.length()).append("hotel/rooms");
	// objValidationResponse.setStatus(Constant.STATUS_SUCCESS);
	// objValidationResponse.setRedirect(objSB.toString());
	//
	// } catch (BaseWebApplicationException exception) {
	// errorMesages = new ArrayList<ErrorMessage>();
	// errorMesages.add(new ErrorMessage(Constant.MAP_ERROR_MESSAGE,
	// exception.getErrorMessage()));
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// objValidationResponse.setErrorMessageList(errorMesages);
	// } catch (FieldErrorException exception) {
	// errorMesages = new ArrayList<ErrorMessage>();
	// errorDto = exception.getValidationErrorDTO();
	// for (FieldErrorDTO fieldError : errorDto.getFieldErrors()) {
	// errorMesages.add(new ErrorMessage(fieldError.getField(),
	// fieldError.getMessage()));
	// }
	// objValidationResponse.setStatus(Constant.STATUS_FAIL);
	// objValidationResponse.setErrorMessageList(errorMesages);
	// } catch (MessagingException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (TemplateException e) {
	// e.printStackTrace();
	// } finally {
	//
	// }
	// return objValidationResponse;
	// }

	@RequestMapping(value = "facebookLogin/{access_token}", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse socialLoginFaceBook(
			@PathVariable("access_token") String accessToken,
			HttpSession session) throws MessagingException, IOException,
			TemplateException {

		OAuth2Request objAuth2Request = new OAuth2Request();
		objAuth2Request.setAccessToken(accessToken);

		UserDetails objUser = this.userService.socialLogin(
				SocialProvider.facebook, objAuth2Request);
		session.setAttribute(Constant.SESSION_USER, objUser);
		return new LoginResponse(objUser.getUsername());
	}
	
	@RequestMapping(value = "googleLogin/{access_token}", method = RequestMethod.POST)
	@ResponseBody
	public LoginResponse socialLoginGoogle(
			@PathVariable("access_token") String accessToken,
			HttpSession session) throws MessagingException, IOException,
			TemplateException {

		OAuth2Request objAuth2Request = new OAuth2Request();
		objAuth2Request.setAccessToken(accessToken);

		UserDetails objUser = this.userService.socialLogin(
				SocialProvider.google, objAuth2Request);
		session.setAttribute(Constant.SESSION_USER, objUser);
		return new LoginResponse(objUser.getUsername());
	}


	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.SESSION_USER);
		return "redirect:/";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> forgotPassword(
			@RequestBody @Validated(value = { ForgotPassword.class }) ForgotPasswordDto forgotPasswordModel,
			HttpServletRequest request) throws UnsupportedEncodingException,
			Exception {
		logger.info("userForgotPassword");
		StringBuilder objSB = new StringBuilder();
		objSB.append(request.getScheme()).append("://")
				.append(request.getServerName()).append(":")
				.append(request.getServerPort())
				.append(request.getContextPath()).append("/user/resetPassword");
		forgotPasswordModel.setRedirectUrl(objSB.toString());
		this.userService.forgotPassword(forgotPasswordModel);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("message", "success");
		return responseData;
	}

	/**
	 * resetPassword
	 * 
	 * @param resetPasswordDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPasswordPre(@RequestParam("key") String token,
			ModelMap modelMap) {
		logger.info("resetPassword pre");
		if (StringUtils.hasText(token)) {
			ResetPasswordDTO resetPasswordDTO = (ResetPasswordDTO) modelMap
					.get("resetPassword");
			if (resetPasswordDTO == null)
				resetPasswordDTO = new ResetPasswordDTO();
			resetPasswordDTO.setToken(token);
			modelMap.put("resetPassword", resetPasswordDTO);
			return "user/resetPassword";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPasswordPost(
			@ModelAttribute("resetPassword") @Validated(RestResetpassword.class) ResetPasswordDTO resetPassword,
			BindingResult result, RedirectAttributes redirectAttributes) {
		logger.info("resetPassword post");
		try {
			if (StringUtils.hasText(resetPassword.getToken())) {
				if (result.hasErrors()) {
					redirectAttributes.addFlashAttribute(
							BindingResult.MODEL_KEY_PREFIX + "resetPassword",
							result);
					redirectAttributes.addFlashAttribute("resetPassword",
							resetPassword);
					return "redirect:/user/resetPassword?key="
							+ resetPassword.getToken();
				} else {
					this.userService.resetPassword(resetPassword);
					return "redirect:/";
				}
			}
			return "redirect:/";
		} catch (BaseWebApplicationException baseWebApplicationException) {
			redirectAttributes.addFlashAttribute("errorMessage",
					baseWebApplicationException.getErrorMessage());
			redirectAttributes
					.addFlashAttribute("resetPassword", resetPassword);
			return "redirect:/user/resetPassword?key="
					+ resetPassword.getToken();
		}
	}

	@RequestMapping(value = "account", method = RequestMethod.GET)
	public String account() {
		return "/user/account";
	}

	/**
	 * changePassword
	 * 
	 * @param resetPasswordDto
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto changePassword(
			@RequestBody @Valid ChangePasswordDto changePasswordDto) {
		logger.info("changePassword");
		User objUser = getUserDetail();
		if (objUser != null) {
			this.userService.changePassword(changePasswordDto,
					objUser.getUserName());
			return new ResponseMessageDto("Password change Successfully");
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "profile", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public UserProfileDto getProfile() {
		logger.info("getUserProfile");
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.userService.getUserProfile(objUser.getEmail());
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	// @RequestMapping(value = "profile", method = RequestMethod.GET)
	// public String profile(HttpSession session, ModelMap modelMap,
	// RedirectAttributes flashAttributes) {
	// User objUser = null;
	// try {
	// objUser = (User) session.getAttribute(Constant.SESSION_USER);
	// if (objUser != null) {
	// modelMap.put("profile",
	// this.userService.getUserProfile(objUser));
	// return "user/profile";
	// } else {
	// flashAttributes.addFlashAttribute("redirecturl",
	// "/user/profile");
	// return "redirect:/dash_board/login";
	// // return "user/profile";
	// }
	// } finally {
	//
	// }
	// }

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseMessageDto updateUserProfile(
			@Valid @RequestBody UserProfile userProfile) {
		logger.info("updateUserProfile start");
		User objUser = getUserDetail();
		if (objUser != null) {
			this.userService.updateUserProfile(objUser.getUserName(),
					userProfile);
			return new ResponseMessageDto("Updated successfully");
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "/updatePaypalInfo", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto updatePaypalInfo(
			@Validated(value = { CheckProfilePaypal.class }) @RequestBody ProfilePaypal profilePaypal)
			throws Exception {
		Boolean isSuccess = Boolean.FALSE;
		logger.info("updatePaypalInfo start");
		User objUser = getUserDetail();
		if (objUser != null) {
			isSuccess = this.paypalService
					.isPaypalVarifiedAccount(profilePaypal.getPaypalId());
			if (isSuccess) {
				this.userService.saveUpdatePayPalInfo(objUser,
						profilePaypal.getPaypalId());
				return new ResponseMessageDto("Updated successfully");
			} else {
				ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
				validationErrorDTO.addFieldError("paypalId",
						"Not valid paypalid");
				throw new FieldErrorException(validationErrorDTO);
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "/updateBankInfo", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto updateBankInfo(
			@Valid @RequestBody ProfileBankAccount profileBankAccount)
			throws Exception {
		logger.info("updateBankInfo start");
		User objUser = getUserDetail();
		if (objUser != null) {
			this.userService.saveUpdateBankInfo(objUser, profileBankAccount);
			return new ResponseMessageDto("Data updated successfully");
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "wallet", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Map<String, Object> wallet(ModelMap modelMap,
			RedirectAttributes flashAttributes) {
		User objUser = getUserDetail();
		if (objUser != null) {
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("paypalInfo", this.userService.getPaypalInfo(objUser));
			response.put("bankInfo", this.userService.getBankInfo(objUser));
			return response;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "/getBookHistory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<BookHistoryDto> getBookHistory() {
		logger.info("getBookHistory");
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.hotelRoomService.getBookHistory(objUser, null);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "/getSellHistory", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<SellHistoryDto> getSellHistory() {
		logger.info("getSellHistory");
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.hotelRoomService.getSellHistory(objUser, null);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "/getPendingTransactions", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<PendingTransactionDto> getPendingTransactions() {
		logger.info("getSellHistory");
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.hotelRoomService.getPendingTransaction(objUser);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart(ModelMap modelMap, RedirectAttributes flashAttributes) {
		User objUser = getUserDetail();
		if (objUser != null) {
			modelMap.put("cartData", this.hotelRoomService.getCartData(objUser));
			return "user/cart";
		} else {
			flashAttributes.addFlashAttribute("redirecturl", "/user/cart");
			return "redirect:/dash_board/login";
		}
	}

	@RequestMapping(value = "getUserPreference", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Map<String, UserPreferenceDto> getFirstUserPreference() {
		User objUser = getUserDetail();
		if (objUser != null) {
			Map<String, UserPreferenceDto> preference = new HashMap<String, UserPreferenceDto>();
			preference.put("first",
					this.userPreferenceService.getFirstPreference(objUser));
			preference.put("all",
					this.userPreferenceService.getAllPreference(objUser));
			return preference;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "saveFirstUserPreference", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseMessageDto saveFirstUserPreference(
			@RequestBody @Valid UserPreferenceDto userPreferenceDto) {
		User objUser = getUserDetail();
		if (objUser != null) {
			this.userPreferenceService.saveFirstPreference(userPreferenceDto,
					objUser);
			return new ResponseMessageDto("SUCCESS");
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "saveAllUserPreference", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseMessageDto saveAllUserPreference(
			@RequestBody @Valid UserPreferenceDto userPreferenceDto) {
		User objUser = getUserDetail();
		if (objUser != null) {
			this.userPreferenceService.saveAllPreference(userPreferenceDto,
					objUser);
			return new ResponseMessageDto("SUCCESS");
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}
}
