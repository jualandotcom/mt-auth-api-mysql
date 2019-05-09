package com.mt.member.api.model;

import com.mt.member.api.db.entity.TbAuth;
import com.mt.member.api.model.ResponseModel;

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
