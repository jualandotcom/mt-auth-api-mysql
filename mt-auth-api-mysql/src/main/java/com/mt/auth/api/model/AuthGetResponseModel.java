package com.mt.auth.api.model;

import com.mt.auth.api.db.entity.TbAuth;
import com.mt.auth.api.model.ResponseModel;

public class AuthGetResponseModel extends ResponseModel {
	
	public AuthGetResponseModel(AuthGetRequestModel requestModel) {
		super(requestModel);
	}
	
	private TbAuth tbAuth;

	public TbAuth getTbAuth() {
		return tbAuth;
	}

	public void setTbAuth(TbAuth tbAuth) {
		this.tbAuth = tbAuth;
	}
}
