package com.hr.hotel.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetail extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private com.hr.hotel.model.User user;

	public CustomUserDetail(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			com.hr.hotel.model.User user) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.user = user;
	}

	public com.hr.hotel.model.User getUser() {
		return user;
	}

	public void setUser(com.hr.hotel.model.User user) {
		this.user = user;
	}

}
