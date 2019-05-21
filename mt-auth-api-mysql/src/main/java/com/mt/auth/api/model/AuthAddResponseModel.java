package com.mt.auth.api.model;

import com.mt.auth.api.db.entity.TbAuth;
import com.mt.auth.api.model.ResponseModel;

public class AuthAddResponseModel extends ResponseModel {
	
	public AuthAddResponseModel(AuthAddRequestModel requestModel) {
		super(requestModel);
	}
}
