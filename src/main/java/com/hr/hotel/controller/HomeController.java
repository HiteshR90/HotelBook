package com.hr.hotel.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.AccountVerificationToken;
import com.hr.hotel.dto.ResponseMessageDto;
import com.hr.hotel.exception.BaseWebApplicationException;
import com.hr.hotel.model.FeedBack;
import com.hr.hotel.security.CustomUserDetail;
import com.hr.hotel.service.FeedBackService;
import com.hr.hotel.service.UserService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Resource(name = Constant.SERVICE_USER)
	private UserService userService;

	private SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(
			"ADMIN");

	/**
	 * begin url redirection
	 * 
	 * @param modelMap
	 *            put attribute to pass to view
	 * @return name of view page to redirect
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * showUserForm(ModelMap modelMap, HttpSession session) { String nextView =
	 * null; try { logger.info("/ start"); nextView = "index";
	 * logger.info("/ end"); } finally { } return nextView; }
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(
			@RequestParam(value = "key", required = false, defaultValue = "") String key,
			Locale locale, Model model) {
		AccountVerificationToken accountVerificationToken = null;
		try {
			logger.info("/ start");
			// model.addAttribute("user", new UserLoginDto());
			// model.addAttribute("registerUser", new SignupUser());
			// User user = (User) session.getAttribute(Constant.SESSION_USER);
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			CustomUserDetail userDetail = null;
			if (authentication.getPrincipal() instanceof UserDetails) {
				userDetail = (CustomUserDetail) authentication.getPrincipal();
			}
			if (userDetail != null && userDetail.getUser() != null) {
				if (!userDetail.getAuthorities().isEmpty()
						&& userDetail.getAuthorities().contains(adminAuthority)) {
					return "redirect:/user/admin";
				} else {
					model.addAttribute("isLogin", true);
					return "redirect:/hotel/rooms-map";
				}
			} else {
				model.addAttribute("isLogin", false);
			}
			if (StringUtils.hasText(key)) {
				accountVerificationToken = new AccountVerificationToken();
				accountVerificationToken.setToken(key);
				this.userService.verifyUserAccount(accountVerificationToken);
				model.addAttribute("successMessage",
						"Account Successfully Activated.");
			}
		} catch (BaseWebApplicationException exception) {
			// model.addAttribute("user", new UserLoginDto());
			// model.addAttribute("registerUser", new SignupUser());
			model.addAttribute("errorMessage", exception.getErrorMessage());
		} finally {

		}
		return "index";
	}

	// @RequestMapping(value = "/dash_board", method = RequestMethod.GET)
	// public String dashBoard(HttpSession session, ModelMap modelMap,
	// RedirectAttributes flashAttributes) {
	// User objUser = (User) session.getAttribute(Constant.SESSION_USER);
	// if (objUser != null) {
	// return "dashboard";
	// } else {
	// flashAttributes.addFlashAttribute("redirecturl",
	// "redirect:/dash_board");
	// return "redirect:/dash_board/login";
	// }
	// }
	//
	// @RequestMapping(value = "/dash_board/login", method = RequestMethod.GET)
	// public String dashBoardLogin(HttpSession session, ModelMap modelMap) {
	// User objUser = (User) session.getAttribute(Constant.SESSION_USER);
	// String redirectUrl = (String) modelMap.get("redirecturl");
	// if (objUser != null) {
	// if (StringUtils.hasText(redirectUrl)) {
	// return "redirect:" + redirectUrl;
	// } else {
	// return "redirect:dash_board";
	// }
	// } else {
	// modelMap.clear();
	// // return "dashboard_login";
	// return "redirect:/";
	// }
	// }

	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String aboutUs() {
		return "underConstruction";
	}

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String blog() {
		return "underConstruction";
	}

	@RequestMapping(value = "/press", method = RequestMethod.GET)
	public String press() {
		return "underConstruction";
	}

	@RequestMapping(value = "/legal", method = RequestMethod.GET)
	public String legal() {
		return "underConstruction";
	}

	@RequestMapping(value = "/investors", method = RequestMethod.GET)
	public String investors() {
		return "underConstruction";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		return "underConstruction";
	}

	@RequestMapping(value = "/pageNotFound", method = RequestMethod.GET)
	public String commonPageNotFound() {
		return "underConstruction";
	}

	@Resource(name = "feedbackService")
	private FeedBackService feedBackService;

	@RequestMapping(value = "feedback/{pageName}", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseMessageDto feedback(
			@PathVariable("pageName") String pageName, @Valid FeedBack feedBack) {
		feedBack.setPageName(pageName);
		this.feedBackService.save(feedBack);
		return new ResponseMessageDto("Success");
	}
}
