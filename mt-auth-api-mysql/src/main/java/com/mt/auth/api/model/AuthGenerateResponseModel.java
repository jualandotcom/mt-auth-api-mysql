package com.mt.auth.api.model;

import io.jsonwebtoken.Claims;

public class AuthGenerateResponseModel extends ResponseModel {

	public AuthGenerateResponseModel(AuthGenerateRequestModel requestModel) {
		super(requestModel);
	}

	private String token;
	
	private Claims claims;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}
}
