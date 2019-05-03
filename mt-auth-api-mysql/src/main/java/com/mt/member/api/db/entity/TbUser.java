package com.mt.member.api.db.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_users database table.
 * 
 */
@Entity
@Table(name="tb_user")
@NamedQuery(name="TbUser.findAll", query="SELECT t FROM TbUser t")
public class TbUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tbu_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tthTid;

	@Column(name="tbu_create_date")
	private Date tbuCreateDate;

	@Column(name="tbu_create_id")
	private Integer tbuCreateId;

	@Column(name="tbu_update_date")
	private Date tbuUpdateDate;

	@Column(name="tbu_update_id")
	private Integer tbuUpdateId;

	@Column(name="tbu_email")
	private String tbuEmail;

	@Column(name="tbu_password")
	private String tbuPassword;

	@Column(name="tbu_firstname")
	private String tbuFirstname;

	@Column(name="tbu_lastname")
	private String tbuLastname;

	@Column(name="tbu_middlename")
	private String tbuMiddlename;

	@Column(name="tbu_mobile_phone")
	private String tbuMobilePhone;

	@Column(name="tbu_place_of_birth")
	private String tbuPlaceOfBirth;

	@Column(name="tbu_status")
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