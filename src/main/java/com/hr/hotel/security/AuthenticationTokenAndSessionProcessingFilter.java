package com.hr.hotel.security;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.hr.hotel.common.Constant;

public class AuthenticationTokenAndSessionProcessingFilter extends
		GenericFilterBean {

	private final InMemoryTokenStore tokenStore;

	private final Object principal;
	private final List<GrantedAuthority> authorities;

	public AuthenticationTokenAndSessionProcessingFilter(
			InMemoryTokenStore tokenStore, String authority, String principal) {
		this.tokenStore = tokenStore;
		this.principal = principal;
		this.authorities = AuthorityUtils.createAuthorityList(authority);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting a HTTP request");
		}

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// String authToken = httpRequest.getHeader("Authorization") != null
		// ?
		// httpRequest
		// .getHeader("Authorization") : httpRequest
		// .getParameter("access_token");

		String authToken = null;
		if (StringUtils.hasText(httpRequest.getHeader("Authorization"))) {
			authToken = httpRequest.getHeader("Authorization");
		} else if (StringUtils
				.hasText(httpRequest.getParameter("access_token"))) {
			authToken = httpRequest.getParameter("access_token");
			authToken = URLDecoder.decode(authToken, Constant.UTF8);
		}

		UserDetails objUserDetails;
		// String userName = "";// =
		// TokenUtils.getUserNameFromToken(authToken);
		if (StringUtils.hasText(authToken)) {
			objUserDetails = this.tokenStore.readAccessToken(authToken);
			setAuthentication(objUserDetails, httpRequest);
		} else {
			HttpSession session = httpRequest.getSession(false);
			if (session != null) {
				objUserDetails = (UserDetails) session
						.getAttribute(Constant.SESSION_USER);
				setAuthentication(objUserDetails, httpRequest);
			} else {
				setAuthentication(null, httpRequest);
			}
		}
		chain.doFilter(request, response);

	}

	private void setAuthentication(UserDetails objUserDetails,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authentication = null;
		if (null != objUserDetails) {
			authentication = new UsernamePasswordAuthenticationToken(
					objUserDetails, null, objUserDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource()
					.buildDetails(request));
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} else {
			authentication = new UsernamePasswordAuthenticationToken(
					this.principal, null, this.authorities);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		}
	}
}
