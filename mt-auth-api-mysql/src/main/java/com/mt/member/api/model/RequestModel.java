package com.mt.member.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RequestModel {
	@NotNull(message="Request Id is not null")
    @Size(min=10, max=10, message="Request Id must be 10 characters random alphanumeric")
	private String requestId;

	@NotNull(message="Request Date is not null")
	@Pattern(message="Request Date ", regexp="^(\\d{4})(?:-([0]\\d|[1][0-2]))(?:-([0-2]\\d|[3][01]))(?:T([01]\\d|2[0-3]))(?::([0-5]\\d))(?::([0-5]\\d)(?:\\.(\\d{1,7}?)|)|)$")
	private String requestDate;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
}
