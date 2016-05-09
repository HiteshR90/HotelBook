package com.hr.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:app.properties", "classpath:social.properties",
		"classpath:database.properties", "classpath:hibernate.properties",
		"classpath:email.properties", "classpath:payment.properties" })
public class ApplicationConfig {

	private final static String SECURITY_AUTHORIZATION_REQUIRE_SIGNED_REQUESTS = "security.authorization.requireSignedRequests";
	private final static String SESSION_EXPIRY_DURATION = "session.timeToLive.inMinutes";
	private final static String SESSION_DATE_OFFSET_IN_MINUTES = "session.date.offset.inMinutes";
	private final static String TOKEN_EMAIL_REGISTRATION_DURATION = "token.emailRegistration.timeToLive.inMinutes";
	private final static String TOKEN_EMAIL_VERIFICATION_DURATION = "token.emailVerification.timeToLive.inMinutes";
	private final static String TOKEN_LOST_PASSWORD_DURATION = "token.lostPassword.timeToLive.inMinutes";
	private final static String EMAIL_SERVICES_FROM_ADDRESS = "email.services.fromAddress";
	private final static String EMAIL_SERVICES_REPLYTO_ADDRESS = "email.services.replyTo";
	private final static String EMAIL_SERVICES_VERIFICATION_EMAIL_SUBJECT_TEXT = "email.services.emailVerificationSubjectText";
	private final static String EMAIL_SERVICES_REGISTRATION_EMAIL_SUBJECT_TEXT = "email.services.emailRegistrationSubjectText";
	private final static String EMAIL_SERVICES_LOST_PASSWORD_SUBJECT_TEXT = "email.services.lostPasswordSubjectText";

	@Autowired
	protected Environment environment;

	// TODO social
	public String getFacebookClientId() {
		return environment.getProperty("facebook.clientId");
	}

	public String getFacebookClientSecret() {
		return environment.getProperty("facebook.clientSecret");
	}

	public String getGoogleClientId() {
		return environment.getProperty("google.clientId");
	}

	public String getGoogleClientSecret() {
		return environment.getProperty("google.clientSecret");
	}

	// TODO data base
	public String getJDBCDriver() {
		return environment.getProperty("database.driverClassName");
	}

	public String getJDBCURL() {
		return environment.getProperty("database.url");
	}

	public String getJDBCUser() {
		return environment.getProperty("database.username");
	}

	public String getJDBCPassword() {
		return environment.getProperty("database.password");
	}

	// TODO hibernate
	public String getHDialect() {
		return environment.getProperty("hibernate.dialect");
	}

	public String getHShowSQL() {
		return environment.getProperty("hibernate.showsql");
	}

	public String getHFlushOperation() {
		return environment
				.getProperty("hibernate.transaction.flushbeforecompletion");
	}

	public String getHHBM2DDL() {
		return environment.getProperty("hibernate.hbm2ddl.auto");
	}

	public String getHPackageToScan() {
		return environment.getProperty("hibernate.packagesToScan");
	}

	// TODO email
	public String getMailHost() {
		return environment.getProperty("email.host");
	}

	public Integer getMailPort() {
		return Integer.valueOf(environment.getProperty("email.port"));
	}

	public String getMailUserName() {
		return environment.getProperty("email.username");
	}

	public String getMailPassword() {
		return environment.getProperty("email.password");
	}

	public String getMailTransportProtocol() {
		return environment.getProperty("mail.transport.protocol");
	}

	public String getMailSMTPauth() {
		return environment.getProperty("mail.smtp.auth");
	}

	public String getMailSMTPStartttl() {
		return environment.getProperty("mail.smtp.starttls.enable");
	}

	public String getMailDebug() {
		return environment.getProperty("mail.debug");
	}

	// TODO paypal
	public String getPaypalConnectionTimeOut() {
		return environment.getProperty("http.ConnectionTimeOut");
	}

	public String getPaypalHttpRetry() {
		return environment.getProperty("http.Retry");
	}

	public String getPaypalHttpReadTimeOut() {
		return environment.getProperty("http.ReadTimeOut");
	}

	public String getPaypalHttpMaxConnection() {
		return environment.getProperty("http.MaxConnection");
	}

	public String getPaypalHttpUserProxy() {
		return environment.getProperty("http.UseProxy");
	}

	public String getPaypalHttpGoogleAppEngine() {
		return environment.getProperty("http.GoogleAppEngine");
	}

	public String getPaypalServiceEndPoint() {
		return environment.getProperty("service.EndPoint");
	}

	public String getPaypalClientId() {
		return environment.getProperty("paypal.clientID");
	}

	public String getPaypalClientSecret() {
		return environment.getProperty("paypal.clientSecret");
	}

	public String getPaypalPaypalPayment() {
		return environment.getProperty("payment.Paypal");
	}

	public String getPaypalCreditCardPayment() {
		return environment.getProperty("paymeny.CreditCard");
	}

	public String getPaypalInitSale() {
		return environment.getProperty("intent.Sale");
	}

	public String getPaypalCurrency() {
		return environment.getProperty("currency");
	}

	public String getPaypalAcct1UserName() {
		return environment.getProperty("paypal.acct1.UserName");
	}

	public String getPaypalAcct1Password() {
		return environment.getProperty("paypal.acct1.Password");
	}

	public String getPaypalAcct1Signature() {
		return environment.getProperty("paypal.acct1.Signature");
	}

	public String getPaypalAcct1AppId() {
		return environment.getProperty("paypal.acct1.AppId");
	}

	public String getPaypalEmailAddress() {
		return environment.getProperty("paypal.emailAddress");
	}

	public String getPaypalMode() {
		return environment.getProperty("paypal.mode");
	}

	// TODO authorize.net
	public String getAuthorizeDotNetApiLoginID() {
		return environment.getProperty("authorize.apiLoginID");
	}

	public String getAuthorizeDotNetTransactionKey() {
		return environment.getProperty("authorize.transactionKey");
	}

	// TODO common
	public int getSessionExpiryTimeInMinutes() {
		return Integer.parseInt(environment
				.getProperty(SESSION_EXPIRY_DURATION));
	}

	public int getSessionDateOffsetInMinutes() {
		return Integer.parseInt(environment
				.getProperty(SESSION_DATE_OFFSET_IN_MINUTES));
	}

	public int getEmailRegistrationTokenExpiryTimeInMinutes() {
		return Integer.parseInt(environment
				.getProperty(TOKEN_EMAIL_REGISTRATION_DURATION));
	}

	public int getEmailVerificationTokenExpiryTimeInMinutes() {
		return Integer.parseInt(environment
				.getProperty(TOKEN_EMAIL_VERIFICATION_DURATION));
	}

	public int getLostPasswordTokenExpiryTimeInMinutes() {
		return Integer.parseInt(environment
				.getProperty(TOKEN_LOST_PASSWORD_DURATION));
	}

	public String getEmailVerificationSubjectText() {
		return environment
				.getProperty(EMAIL_SERVICES_VERIFICATION_EMAIL_SUBJECT_TEXT);
	}

	public String getEmailRegistrationSubjectText() {
		return environment
				.getProperty(EMAIL_SERVICES_REGISTRATION_EMAIL_SUBJECT_TEXT);
	}

	public String getLostPasswordSubjectText() {
		return environment
				.getProperty(EMAIL_SERVICES_LOST_PASSWORD_SUBJECT_TEXT);
	}

	public String getEmailFromAddress() {
		return environment.getProperty(EMAIL_SERVICES_FROM_ADDRESS);
	}

	public String getEmailReplyToAddress() {
		return environment.getProperty(EMAIL_SERVICES_REPLYTO_ADDRESS);
	}

	public Boolean requireSignedRequests() {
		return environment.getProperty(
				SECURITY_AUTHORIZATION_REQUIRE_SIGNED_REQUESTS)
				.equalsIgnoreCase("true");
	}

}
