package com.mt.auth.api.model;

import io.jsonwebtoken.Claims;

public class AuthInvalidateResponseModel extends ResponseModel {
	
	public AuthInvalidateResponseModel(AuthInvalidateRequestModel requestModel) {
		super(requestModel);
	}
}
