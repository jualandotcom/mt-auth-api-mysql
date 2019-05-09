package com.mt.member.api.model;

import com.mt.member.api.db.entity.TbAuth;
import com.mt.member.api.model.ResponseModel;

public class AuthAddResponseModel extends ResponseModel {
	
	public AuthAddResponseModel(AuthAddRequestModel requestModel) {
		super(requestModel);
	}
}
