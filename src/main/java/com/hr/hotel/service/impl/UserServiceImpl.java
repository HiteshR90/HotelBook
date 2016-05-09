package com.hr.hotel.service.impl;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hr.hotel.common.Constant;
import com.hr.hotel.config.SocialConfig.SocialProvider;
import com.hr.hotel.dto.AccessTokenContainer;
import com.hr.hotel.dto.AccountVerificationToken;
import com.hr.hotel.dto.ChangePasswordDto;
import com.hr.hotel.dto.EmailVerificationRequest;
import com.hr.hotel.dto.ForgotPasswordDto;
import com.hr.hotel.dto.OAuth2Request;
import com.hr.hotel.dto.ProfileBankAccount;
import com.hr.hotel.dto.ProfilePaypal;
import com.hr.hotel.dto.ResetPasswordDTO;
import com.hr.hotel.dto.SignupUser;
import com.hr.hotel.dto.UserLoginDto;
import com.hr.hotel.dto.UserProfileDto;
import com.hr.hotel.dto.ValidationErrorDTO;
import com.hr.hotel.exception.AlreadyVerifiedException;
import com.hr.hotel.exception.AuthenticationException;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.FieldErrorException;
import com.hr.hotel.exception.MessageException;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.TokenHasExpiredException;
import com.hr.hotel.exception.AlreadyVerifiedException.AlreadyVerifiedExceptionType;
import com.hr.hotel.exception.MessageException.MessageExceptionErroCode;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.Role;
import com.hr.hotel.model.SocialUser;
import com.hr.hotel.model.User;
import com.hr.hotel.model.UserPaymentInfo;
import com.hr.hotel.model.UserRole;
import com.hr.hotel.model.VerificationToken;
import com.hr.hotel.model.VerificationToken.VerificationTokenType;
import com.hr.hotel.repository.RoleRepository;
import com.hr.hotel.repository.SocialUserRepository;
import com.hr.hotel.repository.UserPaymentInfoRepository;
import com.hr.hotel.repository.UserRepository;
import com.hr.hotel.repository.UserRoleRepository;
import com.hr.hotel.repository.VerificationTokenRepository;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.security.InMemoryTokenStore;
import com.hr.hotel.service.SendMailService;
import com.hr.hotel.service.UserService;
import com.hr.hotel.util.DateUtil;

import freemarker.template.TemplateException;

@Service(Constant.SERVICE_USER)
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Resource(name = Constant.SERVICE_SEND_MAIL)
	private SendMailService sendMailService;

	@Resource
	private VerificationTokenRepository verificationTokenRepository;

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserRoleRepository userRoleRepository;

	@Resource
	private UserPaymentInfoRepository userPaymentInfoRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncode;

	@Resource
	private RoleRepository roleRepository;

	@Resource
	private SocialUserRepository socialUserRepository;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private InMemoryTokenStore tokenStore;

	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.READ_COMMITTED)
	public UserDetails login(UserLoginDto loginDto) {
		logger.info("login Start");
		User objUser = this.userRepository.getUserByUserNameOrEmail(loginDto
				.getUserName());
		if (objUser != null) {
			if (objUser.getIsVerified()) {
				if (this.bCryptPasswordEncode.matches(loginDto.getPassword(),
						objUser.getPassword())) {
					List<String> userRoles = this.userRoleRepository
							.getUserRolesByUserNameOrEmail(loginDto
									.getUserName());
					// put all user role in list
					if (null != userRoles && userRoles.size() > 0) {
						ArrayList<GrantedAuthority> objAuthorities = new ArrayList<GrantedAuthority>();
						for (String role : userRoles) {
							SimpleGrantedAuthority objAuthority = new SimpleGrantedAuthority(
									role);
							objAuthorities.add(objAuthority);
						}
						return new CustomUserDetail(objUser.getUserName(),
								objUser.getPassword(), true, true, true, true,
								objAuthorities, objUser);
					} else {
						throw new NotFoundException(NotFound.UserNotFound);
					}
				} else {
					throw new AuthenticationException();
				}
			} else {
				throw new AuthenticationException(
						"Please confirm your account", "User not verified");
			}
		} else {
			throw new NotFoundException(NotFound.UserNotFound);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.READ_COMMITTED)
	public List<String> getUserRole(String userNameOrEmail) {
		logger.info("getUserRole");
		if (StringUtils.hasText(userNameOrEmail))
			return this.userRoleRepository
					.getUserRolesByUserNameOrEmail(userNameOrEmail);
		else
			return Collections.emptyList();
	}

	@Override
	public void logout(String token) {
		UserDetails user = this.tokenStore.readAccessToken(token);
		if (null != user) {
			this.tokenStore.removeToken(token);
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	// @Override
	// public User getUserByAccessToken(String token) {
	// return this.tokenStore.readAccessToken(token);
	// }

	@Override
	public AccessTokenContainer getAccessTokenContainer(UserDetails userDetails) {
		return this.tokenStore.generateAccessToken(userDetails);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	public void forgotPassword(ForgotPasswordDto forgotPasswordModel)
			throws IOException, TemplateException, MessagingException {

		User objUser = this.userRepository
				.getUserByUserNameOrEmail(forgotPasswordModel
						.getUserNameOrEmail());

		// check user available in data base or not
		if (null != objUser) {
			if (objUser.getIsVerified()) {
				String verificationToken = this.verificationTokenRepository
						.getVerifactionTokenByEmail(objUser.getEmail(),
								VerificationTokenType.lostPassword);

				if (!StringUtils.hasText(verificationToken)) {
					// create verification token
					VerificationToken objVerificationToken = new VerificationToken(
							objUser, VerificationTokenType.lostPassword,
							Constant.LOST_PASSWORD_EMAIL_LINK_EXPIRE_TIME);
					objVerificationToken.setRedirectUrl(forgotPasswordModel
							.getRedirectUrl());
					objVerificationToken.setCreatedBy(objUser.getId());
					objVerificationToken.setIsUsed(false);
					this.verificationTokenRepository.save(objVerificationToken);
					verificationToken = objVerificationToken.getToken();
				}

				// send mail
				this.sendMailService
						.sendForgotPasswordMail(objUser,
								forgotPasswordModel.getRedirectUrl(),
								verificationToken);
				// return responseData;
			} else {
				throw new AuthenticationException(
						"Please confirm your account", "User not verified");
			}
		} else {
			// if no data found send error message
			throw new NotFoundException(NotFound.UserNotFound);
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	@Override
	public void resetPassword(ResetPasswordDTO resetPasswordDto) {
		VerificationToken objVerificationToken = this.verificationTokenRepository
				.getVerifactionTokenByToken(resetPasswordDto.getToken(),
						VerificationTokenType.lostPassword);
		if (objVerificationToken != null) {
			if (objVerificationToken.getIsUsed()) {
				throw new AlreadyVerifiedException(
						AlreadyVerifiedExceptionType.Token);
			} else {
				if (objVerificationToken.hasExpired()) {
					throw new TokenHasExpiredException();
				} else {
					User objUser = objVerificationToken.getUser();
					if (objUser != null) {
						objUser.setPassword(this.bCryptPasswordEncode
								.encode(resetPasswordDto.getRetypePassword()));
						objUser.setModifiedBy(objUser.getId());
						objUser.setModifiedDate(new Date());
						// update
						this.userRepository.save(objUser);
						objVerificationToken.setIsUsed(true);
						// update
						this.verificationTokenRepository
								.save(objVerificationToken);
					} else {
						throw new NotFoundException(NotFound.TokenNotFound);
					}
				}
			}
		} else {
			throw new NotFoundException(NotFound.TokenNotFound);
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void changePassword(ChangePasswordDto changePasswordDto,
			String userNameOrEmail) {
		User objUser = this.userRepository
				.getUserByUserNameOrEmail(userNameOrEmail);
		if (objUser != null) {
			if (this.bCryptPasswordEncode.matches(
					changePasswordDto.getCurrentPassword(),
					objUser.getPassword())) {
				if (!changePasswordDto.getCurrentPassword().equalsIgnoreCase(
						changePasswordDto.getRetypeNewPassword())) {
					objUser.setPassword(this.bCryptPasswordEncode
							.encode(changePasswordDto.getRetypeNewPassword()));
					objUser.setModifiedBy(objUser.getId());
					objUser.setModifiedDate(DateUtil.getCurrentDate());
					// update
					this.userRepository.save(objUser);
				} else {
					ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
					validationErrorDTO.addFieldError("newPassword",
							"Password cant be same as current.");
					throw new FieldErrorException(validationErrorDTO);
				}
			} else {
				ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
				validationErrorDTO.addFieldError("currentPassword",
						"Password not match");
				throw new FieldErrorException(validationErrorDTO);
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}

	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.READ_COMMITTED)
	public void signupUser(SignupUser signupUser) throws IOException,
			TemplateException, MessagingException {

		List<Role> objRoles = this.roleRepository.getDefaultRoles();

		if (objRoles != null && objRoles.size() > 0) {

			// save user master in database
			User objUser = new User();
			objUser.setIsVerified(false);
			objUser.setUserName(signupUser.getUserName());
			objUser.setfName(signupUser.getfName());
			objUser.setlName(signupUser.getlName());
			objUser.setEmail(signupUser.getEmail());
			objUser.setPassword(this.bCryptPasswordEncode.encode(signupUser
					.getPassword()));
			this.userRepository.save(objUser);

			// save user role in database
			for (Role role : objRoles) {
				UserRole objUserRole = new UserRole();
				objUserRole.setUser(objUser);
				objUserRole.setRole(role);
				objUserRole.setActive(true);
				objUserRole.setCreatedBy(objUser.getId());
				this.userRoleRepository.save(objUserRole);
			}

			// save data in user payment info
			UserPaymentInfo objPaymentInfo = new UserPaymentInfo();
			// objPaymentInfo.setActive(true);
			objPaymentInfo.setUser(objUser);
			this.userPaymentInfoRepository.save(objPaymentInfo);

			// create token and save in database
			VerificationToken objVerificationToken = new VerificationToken(
					objUser, VerificationTokenType.emailRegistration,
					Constant.REGISTER_EMAIL_LINK_EXPIRE_TIME);
			objVerificationToken.setRedirectUrl(signupUser.getRedirectURL());
			objVerificationToken.setCreatedBy(objUser.getId());
			objVerificationToken.setIsUsed(false);
			this.verificationTokenRepository.save(objVerificationToken);

			// send registration mail
			this.sendMailService.sendUserSignupMail(objUser,
					signupUser.getRedirectURL(),
					objVerificationToken.getToken());
		} else {
			throw new NotFoundException(NotFound.DefaultRoleNotSet);
		}

	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.READ_COMMITTED)
	public void sendEmailVerificationToken(
			EmailVerificationRequest emailVerificationRequest)
			throws IOException, TemplateException, MessagingException {

		User objUser = this.userRepository
				.getUserByUserNameOrEmail(emailVerificationRequest.getEmail());
		if (objUser != null) {
			if (!objUser.getIsVerified()) {
				String objToken = this.verificationTokenRepository
						.getVerifactionTokenByEmail(
								emailVerificationRequest.getEmail(),
								VerificationTokenType.emailRegistration);
				if (!StringUtils.hasText(objToken)) {
					// create verification token
					VerificationToken objVerificationToken = new VerificationToken(
							objUser, VerificationTokenType.emailRegistration,
							Constant.REGISTER_EMAIL_LINK_EXPIRE_TIME);
					objVerificationToken
							.setRedirectUrl(emailVerificationRequest
									.getRedirectUrl());
					objVerificationToken.setCreatedBy(objUser.getId());
					objVerificationToken.setIsUsed(false);
					this.verificationTokenRepository.save(objVerificationToken);
					objToken = objVerificationToken.getToken();
				}
				// send mail
				this.sendMailService.sendUserSignupMail(objUser,
						emailVerificationRequest.getRedirectUrl(), objToken);
			} else {
				throw new AlreadyVerifiedException(
						AlreadyVerifiedExceptionType.User);
			}
		} else {
			throw new NotFoundException(NotFound.UserNotFound);
		}

	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.READ_COMMITTED)
	public void verifyUserAccount(
			AccountVerificationToken accountVerificationToken) {
		VerificationToken objVerificationToken = this.verificationTokenRepository
				.getVerifactionTokenByToken(
						accountVerificationToken.getToken(),
						VerificationTokenType.emailRegistration);
		if (objVerificationToken != null) {
			if (objVerificationToken.getIsUsed()) {
				throw new AlreadyVerifiedException(
						AlreadyVerifiedExceptionType.Token);
			} else if (objVerificationToken.hasExpired()) {
				throw new TokenHasExpiredException();
			} else {
				User objUser = objVerificationToken.getUser();
				objUser.setIsVerified(true);
				objUser.setModifiedDate(new Date());
				objUser.setModifiedBy(objUser.getId());
				// update
				this.userRepository.save(objUser);
				objVerificationToken.setIsUsed(true);
				// update
				this.verificationTokenRepository.save(objVerificationToken);
			}
		} else {
			throw new NotFoundException(NotFound.TokenNotFound);
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = { Exception.class })
	public UserDetails socialLogin(SocialProvider socialProvider,
			OAuth2Request auth2Request) throws MessagingException, IOException,
			TemplateException {
		List<SocialUser> objSocialUsers = null;
		ConnectionKey objConnectionKey = null;
		User objUser = null;
		List<Role> objRoles = null;
		UserRole objUserRole = null;
		UserPaymentInfo objPaymentInfo = null;
		SocialUser objSocialUser = null;
		ConnectionData objConnectionData = null;
		OAuth2ConnectionFactory<?> objConnectionFactory = null;
		Connection<?> objConnection = null;
		Boolean isFirstTime = Boolean.FALSE;
		ArrayList<GrantedAuthority> objAuthorities = new ArrayList<GrantedAuthority>();
		List<String> userRoles = null;
		try {
			objConnectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator
					.getConnectionFactory(socialProvider.toString());
			objConnection = objConnectionFactory
					.createConnection(new AccessGrant(auth2Request
							.getAccessToken()));

			objConnectionKey = objConnection.getKey();
			objSocialUsers = this.socialUserRepository
					.findByProviderIdAndProviderUserId(
							objConnectionKey.getProviderId(),
							objConnectionKey.getProviderUserId());

			if (objSocialUsers != null && !objSocialUsers.isEmpty()) {
				for (SocialUser socialUser : objSocialUsers) {
					objUser = socialUser.getUser();
					userRoles = this.userRoleRepository
							.getUserRolesByUserNameOrEmail(objUser
									.getUserName());
				}
				if (!objUser.getIsVerified()) {
					objUser.setIsVerified(true);
					objUser.setModifiedBy(objUser.getId());
					objUser.setModifiedDate(DateUtil.getCurrentDate());
					// update
					this.userRepository.save(objUser);
				}
			} else {
				UserProfile profile = objConnection.fetchUserProfile();
				if (profile != null && StringUtils.hasText(profile.getEmail())) {
					objUser = this.userRepository.getUserByEmail(profile
							.getEmail());

					if (objUser == null) {
						objRoles = this.roleRepository.getDefaultRoles();
						if (objRoles != null && objRoles.size() > 0) {
							userRoles = new ArrayList<String>();
							// save user master in database
							objUser = new User();
							objUser.setIsVerified(true);
							objUser.setUserName(profile.getEmail());
							objUser.setEmail(profile.getEmail());
							objUser.setfName(profile.getFirstName());
							objUser.setlName(profile.getLastName());
							objUser.setPassword("");
							this.userRepository.save(objUser);

							// save user role in database
							for (Role role : objRoles) {
								userRoles.add(role.getRoleName());
								objUserRole = new UserRole();
								objUserRole.setUser(objUser);
								objUserRole.setRole(role);
								objUserRole.setActive(true);
								objUserRole.setCreatedBy(objUser.getId());
								this.userRoleRepository.save(objUserRole);
							}

							// save data in user payment info
							objPaymentInfo = new UserPaymentInfo();
							// objPaymentInfo.setActive(true);
							objPaymentInfo.setUser(objUser);
							this.userPaymentInfoRepository.save(objPaymentInfo);
							isFirstTime = Boolean.TRUE;
						} else {
							throw new NotFoundException(
									NotFound.DefaultRoleNotSet);
						}
					} else {
						userRoles = this.userRoleRepository
								.getUserRolesByUserNameOrEmail(objUser
										.getUserName());
					}
					// save data in social user table if user logged in first
					// time
					// save social user data
					objConnectionData = objConnection.createData();
					objSocialUser = new SocialUser();
					objSocialUser.setAccessToken(objConnectionData
							.getAccessToken());
					objSocialUser.setCreatedBy(objUser.getId());
					objSocialUser.setDisplayName(objConnectionData
							.getDisplayName());
					objSocialUser.setExpireTime(objConnectionData
							.getExpireTime());
					objSocialUser.setImageUrl(objConnectionData.getImageUrl());
					objSocialUser.setProfileUrl(objConnectionData
							.getProfileUrl());
					objSocialUser.setProviderId(objConnectionData
							.getProviderId());
					objSocialUser.setProviderUserId(objConnectionData
							.getProviderUserId());
					// TODO: currently only support 1 connection per
					// user per provider (rank = 1)
					objSocialUser.setRank(1);
					objSocialUser.setRefreshToken(objConnectionData
							.getRefreshToken());
					objSocialUser.setSecret(objConnectionData.getSecret());
					objSocialUser.setUser(objUser);
					this.socialUserRepository.save(objSocialUser);
				} else {
					throw new MessageException(
							MessageExceptionErroCode.NOTVALIDFBTOKEN);
				}
			}

			if (isFirstTime)
				this.sendMailService.sendSocialLoginMail(objUser);

		} finally {
			objSocialUsers = null;
			objConnectionKey = null;
			objRoles = null;
			objUserRole = null;
			objPaymentInfo = null;
			objSocialUser = null;
			objConnectionData = null;
		}
		return new CustomUserDetail(objUser.getUserName(),
				objUser.getPassword(), true, true, true, true, objAuthorities,
				objUser);
	}

	@Transactional
	@Override
	public User getUserData(Principal principal) {
		User objUser = null;
		if (principal != null && principal.getName() != null) {
			objUser = this.userRepository.getUserByUserNameOrEmail(principal
					.getName());
		}
		return objUser;
	}

	// TODO need change
	@Override
	public UserProfileDto getUserProfile(String userNameOrEmail) {
		logger.info("getUserProfile");
		User objUser = this.userRepository
				.getUserByUserNameOrEmail(userNameOrEmail);
		if (objUser != null) {
			UserProfileDto objUserProfile = new UserProfileDto();
			objUserProfile.setfName(objUser.getfName());
			objUserProfile.setlName(objUser.getlName());
			objUserProfile.setUserName(objUser.getUserName());
			objUserProfile.setEmail(objUser.getEmail());
			objUserProfile.setPhoneNumber(objUser.getPhoneNumber());
			objUserProfile.setAddress(objUser.getAddress());
			objUserProfile.setCity(objUser.getCity());
			objUserProfile.setState(objUser.getState());
			objUserProfile.setZipcode(objUser.getZipcode());
			objUserProfile.setCountry(objUser.getCountry());
			return objUserProfile;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Override
	@Transactional
	public void updateUserProfile(String userNameOrEmail,
			com.hr.hotel.dto.UserProfile userProfile) {
		logger.info("updateUserProfile start");
		// TODO boiler plated code
		// if (!loginUser.getEmail().equals(userProfile.getEmail())) {
		// String userName = this.userRepository
		// .getUserNameByEmail(userProfile.getEmail());
		// if (StringUtils.hasText(userName)
		// && !loginUser.getUserName().equals(userName)) {
		// ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
		// validationErrorDTO
		// .addFieldError("email", "Email Already Exist");
		// throw new FieldErrorException(validationErrorDTO);
		// }
		// }
		// loginUser.setEmail(userProfile.getEmail());
		User objUser = this.userRepository
				.getUserByUserNameOrEmail(userNameOrEmail);
		if (objUser != null) {
			objUser.setfName(userProfile.getFirstName());
			objUser.setlName(userProfile.getLastName());
			objUser.setAddress(userProfile.getAddress());
			objUser.setCity(userProfile.getCity());
			objUser.setState(userProfile.getState());
			objUser.setZipcode(userProfile.getZipcode());
			objUser.setCountry(userProfile.getCountry());
			objUser.setPhoneNumber(userProfile.getPhoneNumber());
			// update
			this.userRepository.save(objUser);
			// TODO send email if email address is changed
			// TODO boiler plated code
			logger.info("updateUserProfile old");
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Override
	@Transactional
	public ProfilePaypal getPaypalInfo(User objUser) {
		logger.info("getPaypalInfo start");
		if (objUser != null) {
			ProfilePaypal objProfilePaypal = this.userPaymentInfoRepository
					.getEncodedPaypalIdByEmail(objUser.getEmail());
			if (objProfilePaypal == null) {
				objProfilePaypal = new ProfilePaypal();
			}
			return objProfilePaypal;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Override
	@Transactional
	public ProfileBankAccount getBankInfo(User objUser) {
		logger.info("getBankInfo start");
		if (objUser != null) {
			ProfileBankAccount objProfileBankAccount = this.userPaymentInfoRepository
					.getEncodedBankInfoByEmail(objUser.getEmail());
			if (objProfileBankAccount == null) {
				objProfileBankAccount = new ProfileBankAccount();
			}
			return objProfileBankAccount;
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Transactional
	@Override
	public void saveUpdatePayPalInfo(User user, String paypalId) {
		if (user != null) {
			UserPaymentInfo objUserPaymentInfo = this.userPaymentInfoRepository
					.getPaymentInfoByEmail(user.getEmail());
			if (objUserPaymentInfo != null) {
				objUserPaymentInfo.setPaypalId(paypalId);
				// update
				this.userPaymentInfoRepository.save(objUserPaymentInfo);
			} else {
				objUserPaymentInfo = new UserPaymentInfo();
				objUserPaymentInfo.setPaypalId(paypalId);
				objUserPaymentInfo.setUser(user);
				this.userPaymentInfoRepository.save(objUserPaymentInfo);
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

	@Transactional
	@Override
	public void saveUpdateBankInfo(User user, ProfileBankAccount bankAccount) {
		if (user != null) {
			UserPaymentInfo userPaymentInfo = this.userPaymentInfoRepository
					.getPaymentInfoByEmail(user.getEmail());
			if (userPaymentInfo != null) {
				userPaymentInfo.setBankAccountNumber(bankAccount
						.getBankAccountNumber());
				userPaymentInfo.setBankRoutingNumber(bankAccount
						.getBankRoutingNumber());
				// update
				this.userPaymentInfoRepository.save(userPaymentInfo);

			} else {
				userPaymentInfo = new UserPaymentInfo();
				userPaymentInfo.setBankAccountNumber(bankAccount
						.getBankAccountNumber());
				userPaymentInfo.setBankRoutingNumber(bankAccount
						.getBankRoutingNumber());
				userPaymentInfo.setUser(user);
				this.userPaymentInfoRepository.save(userPaymentInfo);
			}
		} else {
			throw new AuthorizationException(Constant.ERROR_LOGIN_MESSAGE);
		}
	}

}
