package com.mt.member.api.model;

import com.mt.member.api.db.entity.TbAuth;
import com.mt.member.api.model.ResponseModel;

public class AuthGetResponseModel extends ResponseModel {
	
	public AuthGetResponseModel(AuthGetRequestModel requestModel) {
		super(requestModel);
	}
	
	private TbAuth tbUsers;

	public TbAuth getTbUsers() {
		return tbUsers;
	}

	public void setTbUsers(TbAuth tbUsers) {
		this.tbUsers = tbUsers;
	}
}
