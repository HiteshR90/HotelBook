package com.hr.hotel.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.AssignerUser;
import com.hr.hotel.dto.DeleagetDto;
import com.hr.hotel.exception.AlreadyDeleagateException;
import com.hr.hotel.exception.AuthorizationException;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.NotVerifiedException;
import com.hr.hotel.exception.PathParamMissingException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.DelegateUser;
import com.hr.hotel.model.User;
import com.hr.hotel.repository.DelegateUserRepository;
import com.hr.hotel.repository.UserRepository;
import com.hr.hotel.service.DelegateUserService;
import com.hr.hotel.service.SendMailService;

import freemarker.template.TemplateException;

@Service(value = Constant.SERVICE_DELEGATE_USER)
public class DelegateUserServiceImpl implements DelegateUserService {

	@Resource
	private DelegateUserRepository delegateUserRepository;

	@Resource
	private UserRepository userRepository;

	@Resource(name = Constant.SERVICE_SEND_MAIL)
	private SendMailService sendMailService;

	@Override
	public Boolean isAssigner(String assignerEmailAddress, User loginUser) {
		try {

			if (null != assignerEmailAddress && null != loginUser) {
				Long delegateId = this.delegateUserRepository.getAssignerId(
						assignerEmailAddress, loginUser.getEmail());
				if (delegateId != null) {
					return true;
				} else {
					return false;
				}
			} else {
				throw new PathParamMissingException();
			}
		} finally {

		}
	}

	@Override
	public List<AssignerUser> getAssignerUsers(User loginUser) {
		// Response objResponse = null;
		List<AssignerUser> objAssignerUsers = null;
		try {
			if (null != loginUser) {
				objAssignerUsers = this.delegateUserRepository
						.getAssignerUsers(loginUser.getEmail());
				if (objAssignerUsers != null && objAssignerUsers.size() > 0) {
					return objAssignerUsers;
					// responseData = BeanUtils.describe(objMasterUsers);
					// objResponse = new Response("success", objAssignerUsers,
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

	// TODO new design
	@Override
	@Transactional(rollbackFor = { Exception.class }, isolation = Isolation.READ_COMMITTED)
	public void makeDeleget(DeleagetDto delegate, User user)
			throws MessagingException, IOException, TemplateException {
		// Response objResponse = null;
		User objUserDelegate = null;
		DelegateUser objDelegateUser = null;
		try {
			// login user user name or email not same as delegate user
			if (!user.getEmail()
					.equalsIgnoreCase(delegate.getUserNameOrEmail())
					|| !user.getUserName().equalsIgnoreCase(
							delegate.getUserNameOrEmail())) {
				objUserDelegate = this.userRepository
						.getUserByUserNameOrEmail(delegate.getUserNameOrEmail());
				if (objUserDelegate != null) {
					// check master delegate user is verified
					if (objUserDelegate.getIsVerified()) {
						objDelegateUser = this.delegateUserRepository
								.getDelegateUser(user, objUserDelegate);
						if (objDelegateUser != null) {
							// if user is active throw exception
							if (objDelegateUser.getActive()) {
								throw new AlreadyDeleagateException();
							} else {
								objDelegateUser.setActive(true);
								// update
								this.delegateUserRepository
										.save(objDelegateUser);
							}
							// add delegate user in database
						} else {
							objDelegateUser = new DelegateUser();
							objDelegateUser.setActive(true);
							objDelegateUser.setCreatedBy(user.getId());
							objDelegateUser.setDelegateUser(objUserDelegate);
							objDelegateUser.setAssignerUser(user);
							this.delegateUserRepository.save(objDelegateUser);
							this.sendMailService.sendMakeDelegateMail(user,
									objUserDelegate);

						}
					} else {
						throw new NotVerifiedException();
					}
				} else {
					throw new NotFoundException(NotFound.UserNotFound);
				}
			} else {
				throw new NotFoundException(NotFound.SelfDelegate);
			}

		} finally {

		}
		// return objResponse;
	}

	// TODO not to delete
	// @Override
	// @Transactional
	// public Response makeDeleget(DeleagetDto delegate, User user,
	// HttpServletRequest request, HttpServletResponse response)
	// throws MessagingException, IOException, TemplateException {
	// Response objResponse = null;
	// User objDelegateUser = null;
	// Map<String, Object> mailParamsSender = null;
	// Map<String, Object> mailParamsReceiver = null;
	// Map<String, String> urlParams = null;
	// String acceptURL = null;
	// String declineURL = null;
	// Calendar objCalendar = null;
	// Long currentTime = null;
	// Long createTime = null;
	// Long expTime = null;
	// StringBuilder objSB = null;
	// DelegateUser delegateUser = null;
	// boolean sendMail = false;
	// Map<String, Object> responseData = null;
	// try {
	// if (!user.getEmail()
	// .equalsIgnoreCase(delegate.getUserNameOrEmail())
	// || !user.getUserName().equalsIgnoreCase(
	// delegate.getUserNameOrEmail())) {
	// objDelegateUser = this.userService
	// .getUserByUserNameOrEmail(delegate.getUserNameOrEmail());
	// // check user present in database and is active
	// if (objDelegateUser != null) {
	// delegateUser = this.delegateUserService.getDelegateUser(
	// user, objDelegateUser);
	// // check user having entry in delegate table
	// if (delegateUser != null) {
	// if (null != delegateUser.getActive()
	// && delegateUser.getActive()) {
	// throw new NotMasterUserException();
	// } else {
	// objCalendar = Calendar.getInstance();
	// currentTime = objCalendar.getTimeInMillis();
	// createTime = delegateUser.getCreatedDate()
	// .getTime() + (24 * 3600000);
	// if (createTime < currentTime) {
	// sendMail = true;
	// responseData = new HashMap<String, Object>();
	// responseData.put("message",
	// "user successfully added");
	// objResponse = new Response("SUCCESS",
	// responseData, false);
	// response.setStatus(HttpServletResponse.SC_OK);
	// } else {
	// responseData = new HashMap<String, Object>();
	// responseData.put("message",
	// "Cant send request within 24 hour");
	// objResponse = new Response("SUCCESS",
	// responseData, true);
	// response.setStatus(HttpServletResponse.SC_OK);
	// }
	// }
	// } else {
	// delegateUser = new DelegateUser();
	// delegateUser.setCreatedBy(user.getId());
	// delegateUser.setDelegateUser(objDelegateUser);
	// delegateUser.setUser(user);
	// delegateUser.setActive(false);
	// delegateUser.setCreatedBy(user.getId());
	// this.commonService.save(delegateUser);
	// sendMail = true;
	// responseData = new HashMap<String, Object>();
	// responseData.put("message", "user successfully added");
	// objResponse = new Response("SUCCESS", responseData,
	// false);
	// response.setStatus(HttpServletResponse.SC_OK);
	// }
	// if (sendMail) {
	// objCalendar = Calendar.getInstance();
	// // add 24 hours
	// expTime = objCalendar.getTimeInMillis()
	// + (24 * 3600000);
	//
	// objSB = new StringBuilder();
	// objSB.delete(0, objSB.length())
	// .append(request.getScheme())
	// .append(Constant.STRING_COLON)
	// .append(Constant.STRING_SLASH)
	// .append(Constant.STRING_SLASH)
	// .append(request.getServerName())
	// .append(Constant.STRING_COLON)
	// .append(request.getServerPort())
	// .append(request.getContextPath())
	// .append(Constant.STRING_SLASH).append("user")
	// .append(Constant.STRING_SLASH)
	// .append("emailConfirm");
	//
	// urlParams = new HashMap<String, String>();
	// urlParams.put("senderID", String.valueOf(user.getId()));
	// urlParams.put("receiverID",
	// String.valueOf(objDelegateUser.getId()));
	// urlParams.put("senderEmail", user.getEmail());
	// urlParams.put("receiverEmail",
	// objDelegateUser.getEmail());
	// urlParams.put(Constant.QUERY_STRING_OPERATION,
	// Constant.QUERY_STRING_OPERATION_MAKE_DELEGATE);
	// urlParams.put("expTime", String.valueOf(expTime));
	// urlParams.put("accept", String.valueOf(true));
	// urlParams.put("redirectURL", delegate.getRedirectUrl());
	// acceptURL = this.commonService.encodeURL(
	// objSB.toString(), urlParams);
	//
	// urlParams.clear();
	// urlParams.put("senderID", String.valueOf(user.getId()));
	// urlParams.put("receiverID",
	// String.valueOf(objDelegateUser.getId()));
	// urlParams.put("senderEmail", user.getEmail());
	// urlParams.put("receiverEmail",
	// objDelegateUser.getEmail());
	// urlParams.put(Constant.QUERY_STRING_OPERATION,
	// Constant.QUERY_STRING_OPERATION_MAKE_DELEGATE);
	// urlParams.put("expTime", String.valueOf(expTime));
	// urlParams.put("accept", String.valueOf(false));
	// urlParams.put("redirectURL", delegate.getRedirectUrl());
	// declineURL = this.commonService.encodeURL(
	// objSB.toString(), urlParams);
	//
	// // send mail to delegate user for confirm delegate
	// mailParamsSender = new HashMap<String, Object>();
	// mailParamsSender.put("delegateUser",
	// objDelegateUser.getEmail());
	// mailParamsSender.put("mainUser",
	// user.getUserName() != null ? user.getUserName()
	// : user.getEmail());
	// mailParamsSender.put("urlAccept",
	// acceptURL != null ? acceptURL : "");
	// mailParamsSender.put("urlDecline",
	// declineURL != null ? declineURL : "");
	// this.applicationMailerService.sendTemplateMail(
	// objDelegateUser.getEmail(),
	// Constant.EMAIL_HEADER_INVITE_DELEGET,
	// Constant.TEMPLATE_INVITE_DELEGATE,
	// mailParamsSender);
	//
	// // send mail to user for information
	// mailParamsReceiver = new HashMap<String, Object>();
	// mailParamsReceiver.put("delegateUser",
	// objDelegateUser.getEmail());
	// mailParamsReceiver.put("mainUser",
	// user.getUserName() != null ? user.getUserName()
	// : user.getEmail());
	// this.applicationMailerService.sendTemplateMail(
	// user.getEmail(),
	// Constant.EMAIL_HEADER_MAKE_DELEGET,
	// Constant.TEMPLATE_MAKE_DELEGATE,
	// mailParamsReceiver);
	// }
	// } else {
	// throw new UserNotFoundException();
	// }
	// } else {
	// throw new SelfDelegateException();
	// }
	//
	// } finally {
	//
	// }
	// return objResponse;
	// }

}
