package com.hr.hotel.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Autowired
	private ApplicationConfig config;

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(this.config.getMailHost());
		mailSender.setPort(this.config.getMailPort());
		mailSender.setUsername(this.config.getMailUserName());
		mailSender.setPassword(this.config.getMailPassword());
		mailSender.setJavaMailProperties(mailProp());
		return mailSender;
	}

	private Properties mailProp() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol",
				this.config.getMailTransportProtocol());
		properties.setProperty("mail.smtp.auth", this.config.getMailSMTPauth());
		properties.setProperty("mail.smtp.starttls.enable",
				this.config.getMailSMTPStartttl());
		properties.setProperty("mail.debug", this.config.getMailDebug());
		return properties;
	}

	/*
	 * @Bean public SimpleMailMessage preConfiguredMessage() { SimpleMailMessage
	 * mailMessage = new SimpleMailMessage(); mailMessage.setTo("${mail.to}");
	 * mailMessage.setFrom("${mail.from}");
	 * mailMessage.setSubject("${mail.subject}"); return mailMessage; }
	 */

}
