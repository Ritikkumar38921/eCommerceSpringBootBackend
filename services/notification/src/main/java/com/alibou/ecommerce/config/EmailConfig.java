package com.alibou.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

	@Value("${notification.smtp.from}")
	private String from;

	@Value("${notification.smtp.password}")
	private String password;

	@Value("${notification.smtp.host}")
	private String host;

	@Value("${notification.smtp.port}")
	private String port;

	@Value("${notification.smtp.ssl.protocols}")
	private String protocols;

	@Value("${notification.smtp.auth}")
	private String auth;

	@Value("${notification.smtp.starttls.enable}")
	private String enable;

	@Value("${notification.smtp.starttls.required}")
	private String required;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocols() {
		return protocols;
	}

	public void setProtocols(String protocols) {
		this.protocols = protocols;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

}
