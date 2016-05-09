package com.hr.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;

@Configuration
public class SocialConfig {
	@Autowired
	private ApplicationConfig config;

	// @Autowired
	// SocialUserRepository socialUserRepository;

	// @Autowired
	// UserRepository userRepository;

	// @Autowired
	// TextEncryptor textEncryptor;

	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(config
				.getFacebookClientId(), config.getFacebookClientSecret()));
		// add twitter connection factory
		registry.addConnectionFactory(new GoogleConnectionFactory(config
				.getGoogleClientId(), config.getGoogleClientSecret()));
		return registry;
	}

	// @Bean
	// public UsersConnectionRepository usersConnectionRepository() {
	// JpaUsersConnectionRepository usersConnectionRepository = new
	// JpaUsersConnectionRepository(
	// socialUserRepository, userRepository,
	// connectionFactoryLocator(), textEncryptor);
	//
	// return usersConnectionRepository;
	// }

	public enum SocialProvider {
		facebook, google
	}
}
