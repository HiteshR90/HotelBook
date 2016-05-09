package com.hr.hotel.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetails;

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
import com.hr.hotel.dto.UserProfile;
import com.hr.hotel.dto.UserProfileDto;
import com.hr.hotel.model.User;

import freemarker.template.TemplateException;

public interface UserService {
	/**
	 * 
	 * @param loginDto
	 * @return
	 */
	public UserDetails login(UserLoginDto loginDto);

	/**
	 * 
	 * @param token
	 * @return
	 */
	public void logout(String token);

	/**
	 * 
	 * 
	 * @param forgotPasswordModel
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 */
	public void forgotPassword(ForgotPasswordDto forgotPasswordModel)
			throws IOException, TemplateException, MessagingException;

	/**
	 * 
	 * @param resetPasswordDto
	 * @return
	 */
	public void resetPassword(ResetPasswordDTO resetPasswordDto);

	/**
	 * signupUser
	 * 
	 * @param signupUser
	 * @return
	 * @throws MessagingException
	 * @throws TemplateException
	 * @throws IOException
	 * @throws DefaultRoleNotSetException
	 */
	public void signupUser(SignupUser signupUser) throws IOException,
			TemplateException, MessagingException;

	/**
	 * sendEmailVerificationToken send email verification token to user
	 * 
	 * @param emailVerificationRequest
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 */
	public void sendEmailVerificationToken(
			EmailVerificationRequest emailVerificationRequest)
			throws IOException, TemplateException, MessagingException;

	/**
	 * verifyUserAccount verify user send succes or fail message
	 * 
	 * @param accountVerificationToken
	 * @return
	 */
	public void verifyUserAccount(
			AccountVerificationToken accountVerificationToken);

	/**
	 * socialLogin logged in into system using access token
	 * 
	 * @param socialProvider
	 * @param auth2Request
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public UserDetails socialLogin(SocialProvider socialProvider,
			OAuth2Request auth2Request) throws MessagingException, IOException,
			TemplateException;

	/**
	 * 
	 * @param principal
	 * @return
	 */
	public User getUserData(Principal principal);

	public void updateUserProfile(String userNameOrEmail,
			UserProfile userProfile);

	public void saveUpdatePayPalInfo(User user, String paypalId);

	public void saveUpdateBankInfo(User user, ProfileBankAccount bankAccount);

	// TODO changed
	public UserProfileDto getUserProfile(String userNameOrEmail);

	public ProfilePaypal getPaypalInfo(User objUser);

	public ProfileBankAccount getBankInfo(User objUser);

	public AccessTokenContainer getAccessTokenContainer(UserDetails userDetails);

	public List<String> getUserRole(String userNameOrEmail);

	public void changePassword(ChangePasswordDto changePasswordDto,
			String userNameOrEmail);

	// public User getUserByAccessToken(String token);

}
