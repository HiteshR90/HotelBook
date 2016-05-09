package com.hr.hotel.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hr.hotel.common.Constant;
import com.hr.hotel.config.SocialConfig.SocialProvider;
import com.hr.hotel.dto.AccessTokenContainer;
import com.hr.hotel.dto.AccountVerificationToken;
import com.hr.hotel.dto.AssignerUser;
import com.hr.hotel.dto.ChangePasswordDto;
import com.hr.hotel.dto.DeleagetDto;
import com.hr.hotel.dto.EmailVerificationRequest;
import com.hr.hotel.dto.ForgotPasswordDto;
import com.hr.hotel.dto.OAuth2Request;
import com.hr.hotel.dto.ProfileBankAccount;
import com.hr.hotel.dto.ProfilePaypal;
import com.hr.hotel.dto.ResetPasswordDTO;
import com.hr.hotel.dto.ResponseMessageDto;
import com.hr.hotel.dto.SignupUser;
import com.hr.hotel.dto.UserLoginDto;
import com.hr.hotel.dto.UserPreferenceDto;
import com.hr.hotel.dto.UserProfile;
import com.hr.hotel.dto.UserProfileDto;
import com.hr.hotel.dto.ValidationErrorDTO;
import com.hr.hotel.dto.ForgotPasswordDto.RestForgotPassword;
import com.hr.hotel.dto.ProfilePaypal.CheckProfilePaypal;
import com.hr.hotel.dto.SignupUser.RestSignUpUser;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.FieldErrorException;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.User;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.DelegateUserService;
import com.hr.hotel.service.PaypalService;
import com.hr.hotel.service.UserPreferenceService;
import com.hr.hotel.service.UserService;

import freemarker.template.TemplateException;

@Controller
@RequestMapping("/rest/user")
public class RestUserController {

	@Resource(name = Constant.SERVICE_PAYPAL)
	private PaypalService paypalService;

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	private static final Logger logger = LoggerFactory
			.getLogger(RestUserController.class);

	@Resource(name = Constant.SERVICE_DELEGATE_USER)
	private DelegateUserService delegateUserService;

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

	/**
	 * login allow anonymous user to logged in in to system
	 * 
	 * @param loginDto
	 *            contain credentials for user
	 * @param request
	 *            contains request data
	 * @param response
	 *            object of HttpServletResponse to send response code etc.
	 * @return object of Response contains token information or error
	 *         information
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public AccessTokenContainer login(@Valid @RequestBody UserLoginDto loginDto) {
		logger.info("login");
		return this.userService.getAccessTokenContainer(this.userService
				.login(loginDto));
	}

	/**
	 * 
	 * 
	 * @param auth2Request
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	@RequestMapping(value = "/login/facebook", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public AccessTokenContainer socialLoginFaceBook(
			@RequestBody @Valid OAuth2Request auth2Request)
			throws MessagingException, IOException, TemplateException {
		logger.info("socialLoginFaceBook");
		return this.userService.getAccessTokenContainer(this.userService
				.socialLogin(SocialProvider.facebook, auth2Request));
	}

	@RequestMapping(value = "/login/google", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public AccessTokenContainer socialLoginGoogle(
			@RequestBody @Valid OAuth2Request auth2Request)
			throws MessagingException, IOException, TemplateException {
		logger.info("socialLoginGoogle");
		return this.userService.getAccessTokenContainer(this.userService
				.socialLogin(SocialProvider.google, auth2Request));
	}

	/**
	 * signupUser used to signup into system
	 * 
	 * @param signupUser
	 *            contains signiup user data
	 * @param response
	 *            object of HttpServletResponse to send response code etc.
	 * @return object of Response contains token information or error
	 *         information
	 * @throws Exception
	 * @throws MessagingException
	 * @throws TemplateException
	 * @throws IOException
	 * @throws DefaultRoleNotSetException
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto signupUser(
			@Validated(RestSignUpUser.class) @RequestBody SignupUser signupUser)
			throws IOException, TemplateException, MessagingException {
		logger.info("registerNewUser start");
		this.userService.signupUser(signupUser);
		// Map<String, String> responseData = new HashMap<String, String>();
		// responseData.put("message",
		// "Please Check Your Mail For Confirmation");
		// return responseData;
		return new ResponseMessageDto("Please Check Your Mail For Confirmation");
	}

	/**
	 * sendEmailVerificationToken send email for verification of email addess
	 * 
	 * @param emailVerificationRequest
	 * @return object of Response
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendEmailVerificationToken", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto sendEmailVerificationToken(
			@Valid @RequestBody EmailVerificationRequest emailVerificationRequest)
			throws IOException, TemplateException, MessagingException {
		logger.info("sendEmailVerificationToken start");
		this.userService.sendEmailVerificationToken(emailVerificationRequest);
		// Map<String, String> responseData = new HashMap<String, String>();
		// responseData
		// .put("message", "Please check your mail for reset password");
		// return responseData;
		return new ResponseMessageDto("Please check your mail.");
	}

	/**
	 * verifyAccount method verify user account based on token
	 * 
	 * @param accountVerificationToken
	 * @return object of Response
	 */
	@RequestMapping(value = "verifyAccount", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto verifyUserAccount(
			@Valid @RequestBody AccountVerificationToken accountVerificationToken) {
		logger.info("verifyUserAccount start");
		this.userService.verifyUserAccount(accountVerificationToken);
		// Map<String, String> objResponseData = new HashMap<String, String>();
		// objResponseData.put("message", "Account Successfully Activated.");
		// return objResponseData;
		return new ResponseMessageDto("Account Successfully Activated.");
	}

	/**
	 * forgotPassword help to send email for change password to user
	 * 
	 * @param forgotPasswordModel
	 * @param response
	 * @return
	 * @throws MessagingException
	 * @throws TemplateException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto forgotPassword(
			@RequestBody @Validated(value = { RestForgotPassword.class }) ForgotPasswordDto forgotPasswordModel)
			throws IOException, TemplateException, MessagingException {
		logger.info("userForgotPassword");
		this.userService.forgotPassword(forgotPasswordModel);
		// Map<String, Object> responseData = new HashMap<String, Object>();
		// responseData
		// .put("message", "Please check your mail for reset password");
		// return responseData;
		return new ResponseMessageDto(
				"Please check your mail for reset password");
	}

	/**
	 * resetPassword
	 * 
	 * @param resetPasswordDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto resetPassword(
			@RequestBody @Valid ResetPasswordDTO resetPasswordDto) {
		logger.info("resetPassword");
		this.userService.resetPassword(resetPasswordDto);
		// Map<String, String> responseData = new HashMap<String, String>();
		// responseData.put("message", "Password Reset Successfully");
		return new ResponseMessageDto("Password Reset Successfully");
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
		this.userService.changePassword(changePasswordDto, getUserDetail()
				.getUserName());
		return new ResponseMessageDto("Password change Successfully");
	}

	// @RequestMapping(value = "/profile", method = RequestMethod.GET, produces
	// = { MediaType.APPLICATION_JSON_VALUE })
	// @ResponseStatus(value = HttpStatus.OK)
	// @ResponseBody
	// public Response getUserProfile(Principal principal) throws Exception {
	// User objUser = null;
	// Response objResponse = null;
	// UserProfileData objUserProfile = null;
	// ProfilePaypal objProfilePaypal = null;
	// ProfileBankAccount objBankAccount = null;
	// // ProfileInfo objProfileInfo = null;
	// Map<String, Object> responseData = null;
	// logger.info("getUserProfile start");
	// objUser = this.restUserService.getUserData(principal);
	// // objUser = this.restUserService.getUserByAccessToken(token);
	// if (objUser != null) {
	// objUserProfile = this.restUserService.getUserProfile(objUser);
	// objProfilePaypal = this.restUserService.getPaypalInfo(objUser);
	// objBankAccount = this.restUserService.getBankInfo(objUser);
	// responseData = new HashMap<String, Object>();
	// responseData.put("profile", objUserProfile);
	// responseData.put("profileBank", objBankAccount);
	// responseData.put("profilePaypal", objProfilePaypal);
	// objResponse = new Response("success", responseData, false);
	// } else {
	// throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
	// }
	// logger.info("getUserProfile end");
	// return objResponse;
	// }

	@RequestMapping(value = "/profile", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public UserProfileDto getProfile() {
		logger.info("getUserProfile");
		return this.userService.getUserProfile(getUserDetail().getUserName());
	}

	@RequestMapping(value = "/getPaypalInfo", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ProfilePaypal getPaypalInfo() {
		User objUser = null;
		try {
			logger.info("getUserProfile start");
			objUser = getUserDetail();
			if (objUser != null) {
				return this.userService.getPaypalInfo(objUser);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
			objUser = null;
		}
	}

	@RequestMapping(value = "/getBankInfo", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ProfileBankAccount getBankInfo() {
		User objUser = null;
		try {
			logger.info("getUserProfile start");
			objUser = getUserDetail();
			if (objUser != null) {
				return this.userService.getBankInfo(objUser);
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
			objUser = null;
		}
	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseMessageDto updateUserProfile(
			@Valid @RequestBody UserProfile userProfile) {
		User objUserSession = null;
		try {
			logger.info("updateUserProfile start");
			objUserSession = getUserDetail();
			if (objUserSession != null) {
				this.userService.updateUserProfile(
						objUserSession.getUserName(), userProfile);
				return new ResponseMessageDto("Updated successfully");
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@RequestMapping(value = "/updatePaypalInfo", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto updatePaypalInfo(
			@Validated(value = { CheckProfilePaypal.class }) @RequestBody ProfilePaypal profilePaypal) {
		User objUser = null;
		boolean isSuccess = false;
		ValidationErrorDTO validationErrorDTO = null;
		try {
			logger.info("updatePaypalInfo start");
			objUser = getUserDetail();
			if (objUser != null) {
				isSuccess = this.paypalService
						.isPaypalVarifiedAccount(profilePaypal.getPaypalId());
				if (isSuccess) {
					this.userService.saveUpdatePayPalInfo(objUser,
							profilePaypal.getPaypalId());
					return new ResponseMessageDto("Updated successfully");
				} else {
					validationErrorDTO = new ValidationErrorDTO();
					validationErrorDTO.addFieldError("paypalId",
							"Not valid paypalid");
					throw new FieldErrorException(validationErrorDTO);
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
			objUser = null;
			validationErrorDTO = null;
		}
	}

	@RequestMapping(value = "/updateBankInfo", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto updateBankInfo(
			@Valid @RequestBody ProfileBankAccount profileBankAccount) {
		User objUser = null;
		try {
			logger.info("updateBankInfo start");
			objUser = getUserDetail();
			if (objUser != null) {
				this.userService
						.saveUpdateBankInfo(objUser, profileBankAccount);
				return new ResponseMessageDto("Data updated successfully");
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@RequestMapping(value = "logout", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseMessageDto logout(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String access_token = null;
		if (StringUtils.hasText(request.getHeader("Authorization"))) {
			access_token = request.getHeader("Authorization");
		} else {
			access_token = URLEncoder.encode(
					request.getParameter("access_token"), Constant.UTF8);
		}
		if (StringUtils.hasText(access_token)) {
			this.userService.logout(access_token);
			return new ResponseMessageDto("Successfully logged out");
		}
		return null;
	}

	// user preferences

	/**
	 * makeDelegate add delegate user to user list
	 * 
	 * @param deleagetDto
	 *            contains delegate user username or email address
	 * @param principal
	 *            login user detail
	 * @return object of Response
	 * @throws TemplateException
	 * @throws IOException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@RequestMapping(value = "makeDelegate", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseMessageDto makeDelegate(
			@RequestBody @Valid DeleagetDto deleagetDto)
			throws UnsupportedEncodingException, MessagingException,
			IOException, TemplateException {
		User objUser = null;
		try {
			objUser = getUserDetail();
			if (objUser != null) {
				this.delegateUserService.makeDeleget(deleagetDto, objUser);
				return new ResponseMessageDto("user successfully activated");
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {
			objUser = null;
		}
	}

	@RequestMapping(value = "isAssigner/{email}/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody ResponseMessageDto isAssigner(
			@PathVariable(value = "email") String assignerEmailAddress) {
		User objUser = null;
		try {
			objUser = getUserDetail();
			if (objUser != null) {
				if (this.delegateUserService.isAssigner(assignerEmailAddress,
						objUser)) {
					return new ResponseMessageDto("user is assigner");
					// objResponse = new Response("success", responseData,
					// false);
				} else {
					throw new NotFoundException(NotFound.NotAssigner);
				}
			} else {
				throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
			}
		} finally {

		}
	}

	@RequestMapping(value = "getAssignerUsers", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<AssignerUser> getAssignerUsers() throws Exception {
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.delegateUserService.getAssignerUsers(objUser);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "getFirstUserPreference", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody UserPreferenceDto getFirstUserPreference() {
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.userPreferenceService.getFirstPreference(objUser);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@RequestMapping(value = "getAllUserPreference", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody UserPreferenceDto getAllUserPreference() {
		User objUser = getUserDetail();
		if (objUser != null) {
			return this.userPreferenceService.getAllPreference(objUser);
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
