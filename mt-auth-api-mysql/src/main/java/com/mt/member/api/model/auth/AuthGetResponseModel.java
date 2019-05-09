package com.mt.member.api.model.user;

import com.mt.member.api.db.entity.TbUser;
import com.mt.member.api.model.ResponseModel;

public class UserAddResponseModel extends ResponseModel {
	
	public UserAddResponseModel(UserAddRequestModel requestModel) {
		super(requestModel);
	}
	
	private TbUser tbUsers;

	public TbUser getTbUsers() {
		return tbUsers;
	}

	public void setTbUsers(TbUser tbUsers) {
		this.tbUsers = tbUsers;
	}
}
