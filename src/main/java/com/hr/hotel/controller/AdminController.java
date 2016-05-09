package com.hr.hotel.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.UserLoginDto;
import com.hr.hotel.exception.BaseWebApplicationException;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.AdminService;

import freemarker.template.TemplateException;

@Controller
@RequestMapping("/user/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	private SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(
			"ADMIN");

	@RequestMapping(method = RequestMethod.GET)
	public String home(ModelMap modelMap) {
		logger.info("home start");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		CustomUserDetail userDetail = null;
		if (authentication.getPrincipal() instanceof UserDetails) {
			userDetail = (CustomUserDetail) authentication.getPrincipal();
		}
		if (userDetail != null && userDetail.getUser() != null) {
			List<String> objRoles = this.adminService.getUserRole(userDetail
					.getUsername());
			if (!objRoles.isEmpty() && objRoles.contains(adminAuthority)) {
				return "redirect:/user/admin/get-room";
			}
		}
		UserLoginDto objLoginDto = (UserLoginDto) modelMap.get("adminUser");
		if (objLoginDto == null) {
			objLoginDto = new UserLoginDto();
			modelMap.put("adminUser", objLoginDto);
		}
		logger.info("home end");
		return "user/admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@Valid @ModelAttribute("adminUser") UserLoginDto loginDto,
			BindingResult result, HttpSession session,
			RedirectAttributes flashAttribute) {
		try {
			logger.info("login start");
			if (result.hasErrors()) {
				flashAttribute.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
						+ "adminUser", result);
				flashAttribute.addFlashAttribute("adminUser", loginDto);
				return "redirect:/user/admin";
			} else {
				UserDetails objUser = this.adminService.login(loginDto);
				session.setAttribute(Constant.SESSION_USER, objUser);
				return "redirect:/user/admin/get-room";
			}
		} catch (BaseWebApplicationException e) {
			logger.error("login", e);
			flashAttribute.addFlashAttribute("adminUser", loginDto);
			flashAttribute.addFlashAttribute("errorMessage",
					e.getErrorMessage());
			return "redirect:/user/admin";
		}
	}

	@RequestMapping(value = "/get-room", method = RequestMethod.GET)
	public String getRoom(ModelMap modelMap, HttpSession session) {
		logger.info("getRoom start");
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		CustomUserDetail userDetail = null;
		if (authentication.getPrincipal() instanceof UserDetails) {
			userDetail = (CustomUserDetail) authentication.getPrincipal();
		}
		if (userDetail != null && userDetail.getUser() != null) {
			if (userDetail != null && !userDetail.getAuthorities().isEmpty()
					&& userDetail.getAuthorities().contains(adminAuthority)) {
				modelMap.put("roomData", this.adminService.getRooms());
				return "user/admin/confirmroom";
			} else {
				session.invalidate();
				return "redirect:/user/admin";
			}
		} else {
			return "redirect:/user/admin";
		}
	}

	@RequestMapping(value = "/confirm-room/{id}", method = RequestMethod.GET)
	public String confirmRoom(@PathVariable("id") Long id, HttpSession session,
			ModelMap modelMap, RedirectAttributes flashAttribute) {
		try {
			logger.info("confirm room start");
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			CustomUserDetail userDetail = null;
			if (authentication.getPrincipal() instanceof UserDetails) {
				userDetail = (CustomUserDetail) authentication.getPrincipal();
			}
			if (userDetail != null && userDetail.getUser() != null) {
				if (userDetail != null
						&& !userDetail.getAuthorities().isEmpty()
						&& userDetail.getAuthorities().contains(adminAuthority)) {
					this.adminService.confirmRoom(id, userDetail.getUser());
					return "redirect:/user/admin/get-room";
				} else {
					session.invalidate();
					return "redirect:/user/admin";
				}
			} else {
				return "redirect:/user/admin";
			}
		} catch (BaseWebApplicationException e) {
			logger.error("confirm room", e);
			flashAttribute.addFlashAttribute("errorMessage",
					e.getErrorMessage());
			return "redirect:/user/admin/get-room";
		} catch (MessagingException e) {
			logger.error("confirm room", e);
			flashAttribute.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/admin/get-room";
		} catch (IOException e) {
			logger.error("confirm room", e);
			flashAttribute.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/admin/get-room";
		} catch (TemplateException e) {
			logger.error("confirm room", e);
			flashAttribute.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/admin/get-room";
		}
	}

	@RequestMapping(value = "/reject-room/{id}", method = RequestMethod.GET)
	public String rejectRoom(@PathVariable("id") Long id, HttpSession session,
			ModelMap modelMap, RedirectAttributes flashAttribute) {
		try {
			logger.info("rejectRoom");
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			CustomUserDetail userDetail = null;
			if (authentication.getPrincipal() instanceof UserDetails) {
				userDetail = (CustomUserDetail) authentication.getPrincipal();
			}
			if (userDetail != null && userDetail.getUser() != null) {
				if (userDetail != null
						&& !userDetail.getAuthorities().isEmpty()
						&& userDetail.getAuthorities().contains(adminAuthority)) {
					this.adminService.rejectRoom(id, userDetail.getUser());
					return "redirect:/user/admin/get-room";
				} else {
					session.invalidate();
					return "redirect:/user/admin";
				}
			} else {
				return "redirect:/user/admin";
			}
		} catch (BaseWebApplicationException e) {
			logger.error("rejectRoom", e);
			flashAttribute.addFlashAttribute("errorMessage",
					e.getErrorMessage());
			return "redirect:/user/admin/get-room";
		} catch (MessagingException e) {
			logger.error("rejectRoom", e);
			flashAttribute.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/admin/get-room";
		} catch (IOException e) {
			logger.error("rejectRoom", e);
			flashAttribute.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/admin/get-room";
		} catch (TemplateException e) {
			logger.error("rejectRoom", e);
			flashAttribute.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/user/admin/get-room";
		}
	}

	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.SESSION_USER);
		return "redirect:/user/admin";
	}
}
