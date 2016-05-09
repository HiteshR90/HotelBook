package com.hr.hotel.dto;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;

public class UrlResource {

	private Collection<ConfigAttribute> configAttributes;
	private String url;
	private String method;

	public Collection<ConfigAttribute> getConfigAttributes() {
		return configAttributes;
	}

	public void setConfigAttributes(Collection<ConfigAttribute> configAttributes) {
		this.configAttributes = configAttributes;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
