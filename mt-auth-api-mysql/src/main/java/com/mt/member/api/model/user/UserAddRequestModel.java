package com.mt.member.api.model.user;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.mt.member.api.model.RequestModel;

public class UserAddRequestModel extends RequestModel {
	private Integer tthTid;

	private Date tbuCreateDate;

	private Integer tbuCreateId;

	private Date tbuUpdateDate;

	private Integer tbuUpdateId;

	@NotNull(message = "Email is not null")
	private String tbuEmail;

	@NotNull(message = "Password is not null")
	private String tbuPassword;

	@NotNull(message = "First name is not null")
	private String tbuFirstname;

	@NotNull(message = "Last name is not null")
	private String tbuLastname;

	private String tbuMiddlename;

	@NotNull(message = "Mobile phone is not null")
	private String tbuMobilePhone;

	@NotNull(message = "Place of birth is not null")
	private String tbuPlaceOfBirth;

	private String tbuStatus;

	public Integer getTthTid() {
		return tthTid;
	}

	public void setTthTid(Integer tthTid) {
		this.tthTid = tthTid;
	}

	public Date getTbuCreateDate() {
		return tbuCreateDate;
	}

	public void setTbuCreateDate(Date tbuCreateDate) {
		this.tbuCreateDate = tbuCreateDate;
	}

	public Integer getTbuCreateId() {
		return tbuCreateId;
	}

	public void setTbuCreateId(Integer tbuCreateId) {
		this.tbuCreateId = tbuCreateId;
	}

	public Date getTbuUpdateDate() {
		return tbuUpdateDate;
	}

	public void setTbuUpdateDate(Date tbuUpdateDate) {
		this.tbuUpdateDate = tbuUpdateDate;
	}

	public Integer getTbuUpdateId() {
		return tbuUpdateId;
	}

	public void setTbuUpdateId(Integer tbuUpdateId) {
		this.tbuUpdateId = tbuUpdateId;
	}

	public String getTbuEmail() {
		return tbuEmail;
	}

	public void setTbuEmail(String tbuEmail) {
		this.tbuEmail = tbuEmail;
	}

	public String getTbuPassword() {
		return tbuPassword;
	}

	public void setTbuPassword(String tbuPassword) {
		this.tbuPassword = tbuPassword;
	}

	public String getTbuFirstname() {
		return tbuFirstname;
	}

	public void setTbuFirstname(String tbuFirstname) {
		this.tbuFirstname = tbuFirstname;
	}

	public String getTbuLastname() {
		return tbuLastname;
	}

	public void setTbuLastname(String tbuLastname) {
		this.tbuLastname = tbuLastname;
	}

	public String getTbuMiddlename() {
		return tbuMiddlename;
	}

	public void setTbuMiddlename(String tbuMiddlename) {
		this.tbuMiddlename = tbuMiddlename;
	}

	public String getTbuMobilePhone() {
		return tbuMobilePhone;
	}

	public void setTbuMobilePhone(String tbuMobilePhone) {
		this.tbuMobilePhone = tbuMobilePhone;
	}

	public String getTbuPlaceOfBirth() {
		return tbuPlaceOfBirth;
	}

	public void setTbuPlaceOfBirth(String tbuPlaceOfBirth) {
		this.tbuPlaceOfBirth = tbuPlaceOfBirth;
	}

	public String getTbuStatus() {
		return tbuStatus;
	}

	public void setTbuStatus(String tbuStatus) {
		this.tbuStatus = tbuStatus;
	}
}
