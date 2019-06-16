package com.mt.auth.api.model;

import com.mt.auth.api.db.entity.TbAuth;
import com.mt.auth.api.model.ResponseModel;

public class AuthGetResponseModel extends ResponseModel {

	public AuthGetResponseModel(AuthGetRequestModel requestModel) {
		super(requestModel);
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
