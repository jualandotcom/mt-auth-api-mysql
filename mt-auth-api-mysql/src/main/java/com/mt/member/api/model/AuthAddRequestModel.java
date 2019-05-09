package com.mt.member.api.model;

import javax.validation.constraints.NotNull;

import com.mt.member.api.model.RequestModel;

public class AuthAddRequestModel extends RequestModel {

	@NotNull(message = "Email is not null")
	private String tbaEmail;

	@NotNull(message = "Password is not null")
	private String tbaPassword;

	public String getTbaEmail() {
		return tbaEmail;
	}

	public void setTbaEmail(String tbaEmail) {
		this.tbaEmail = tbaEmail;
	}

	public String getTbaPassword() {
		return tbaPassword;
	}

	public void setTbaPassword(String tbaPassword) {
		this.tbaPassword = tbaPassword;
	}
}
