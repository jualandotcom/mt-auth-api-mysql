package com.mt.auth.api.model;

import javax.validation.constraints.NotNull;

public class AuthCheckRequestModel extends RequestModel {
	@NotNull(message = "Email is not null")
	private String tbaEmail;

	@NotNull(message = "Token is not null")
	private String tbaToken;

	public String getTbaEmail() {
		return tbaEmail;
	}

	public void setTbaEmail(String tbaEmail) {
		this.tbaEmail = tbaEmail;
	}

	public String getTbaToken() {
		return tbaToken;
	}

	public void setTbaToken(String tbaToken) {
		this.tbaToken = tbaToken;
	}
}
