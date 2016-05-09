package com.hr.hotel.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.User;
import com.hr.hotel.repository.UserRepository;
import com.hr.hotel.repository.UserRoleRepository;

public class CustomeUserDetailService implements UserDetailsService {

	private UserRepository userRepository;

	private UserRoleRepository userRoleRepository;

	public CustomeUserDetailService(UserRepository userRepository,
			UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		// get user data from database
		User objUser = this.userRepository.getUserByUserNameOrEmail(username);
		if (null != objUser) {
			// get user roles from database
			List<String> userRoles = this.userRoleRepository
					.getUserRolesByUserNameOrEmail(username);
			// put all user role in list
			if (null != userRoles && userRoles.size() > 0) {
				ArrayList<GrantedAuthority> objAuthorities = new ArrayList<GrantedAuthority>();
				for (String role : userRoles) {
					SimpleGrantedAuthority objAuthority = new SimpleGrantedAuthority(
							role);
					objAuthorities.add(objAuthority);
				}
				return new CustomUserDetail(username, objUser.getPassword(),
						true, true, true, true, objAuthorities, objUser);
			} else {
				throw new NotFoundException(NotFound.UserNotFound);
				// throw new UsernameNotFoundException("Not Valid User");
			}
		} else {
			throw new NotFoundException(NotFound.UserNotFound);
			// throw new UsernameNotFoundException("Not Valid User");
		}
	}

}