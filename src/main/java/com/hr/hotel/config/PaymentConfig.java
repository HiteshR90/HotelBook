package com.hr.hotel.config;

import java.util.HashMap;
import java.util.Map;

import net.authorize.Environment;
import net.authorize.Merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.svcs.services.AdaptiveAccountsService;
import com.paypal.svcs.services.AdaptivePaymentsService;

@Configuration
public class PaymentConfig {

	@Autowired
	private ApplicationConfig config;

	@Bean
	public AdaptiveAccountsService adaptiveAccountsService() {
		return new AdaptiveAccountsService(paypalConfigMap());
	}

	@Bean
	public AdaptivePaymentsService adaptivePaymentsService() {
		return new AdaptivePaymentsService(paypalConfigMap());
	}

	private Map<String, String> paypalConfigMap() {
		Map<String, String> configMap = new HashMap<String, String>();
		configMap.put("acct1.UserName", this.config.getPaypalAcct1UserName());
		configMap.put("acct1.Password", this.config.getPaypalAcct1Password());
		configMap.put("acct1.Signature", this.config.getPaypalAcct1Signature());
		configMap.put("acct1.AppId", this.config.getPaypalAcct1AppId());
		configMap.put("mode", this.config.getPaypalMode());
		return configMap;
	}

	@Bean
	public HashMap<String, String> paypalSdkConfig() {
		HashMap<String, String> paypalSdkConfig = new HashMap<String, String>();
		paypalSdkConfig.put("mode", this.config.getPaypalMode());
		return paypalSdkConfig;
	}

	@Bean
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential(this.config.getPaypalClientId(),
				this.config.getPaypalClientSecret(), paypalConfigMap());
	}

	@Bean
	public Merchant merchant() {
		Merchant merchant = Merchant.createMerchant(Environment.SANDBOX,
				this.config.getAuthorizeDotNetApiLoginID(),
				this.config.getAuthorizeDotNetTransactionKey());
		return merchant;
	}
}
