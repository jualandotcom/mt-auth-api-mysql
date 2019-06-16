package com.mt.auth.api.model;

import javax.validation.constraints.NotNull;

import com.mt.auth.api.model.RequestModel;

public class AuthInvalidateRequestModel extends RequestModel {
	@NotNull(message = "Email is not null")
	private String tbaEmail;

	public String getTbaEmail() {
		return tbaEmail;
	}

	public void setTbaEmail(String tbaEmail) {
		this.tbaEmail = tbaEmail;
	}
}
