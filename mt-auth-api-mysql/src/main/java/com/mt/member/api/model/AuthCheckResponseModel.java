package com.mt.member.api.model;

import io.jsonwebtoken.Claims;

public class AuthCheckResponseModel extends ResponseModel {
	
	public AuthCheckResponseModel(AuthCheckRequestModel requestModel) {
		super(requestModel);
	}
	
	private Claims claims;

	public Claims getClaims() {
		return claims;
	}

	public void setClaims(Claims claims) {
		this.claims = claims;
	}
}
