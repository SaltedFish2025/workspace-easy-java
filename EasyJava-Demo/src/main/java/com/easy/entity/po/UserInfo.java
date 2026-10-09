package com.easy.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easy.utils.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户信息表
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class UserInfo implements Serializable {

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 邮箱
	 */
	@JsonIgnore
	private String email;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 好友申请方式[0:无验证,1:同意后添加]
	 */
	private Integer joinType;

	/**
	 * 性别[0:男,1:女]
	 */
	@JsonIgnore
	private Integer sex;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 个性签名
	 */
	private String personalSignature;

	/**
	 * 状态[]
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 最后登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
	 * 最后登录地区
	 */
	private String areaName;

	/**
	 * 最后登录地区编号
	 */
	private String areaCode;

	/**
	 * 最后离线时间
	 */
	private Long lastOffTime;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getJoinType() {
		return this.joinType;
	}

	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonalSignature() {
		return this.personalSignature;
	}

	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Long getLastOffTime() {
		return this.lastOffTime;
	}

	public void setLastOffTime(Long lastOffTime) {
		this.lastOffTime = lastOffTime;
	}

	@Override
	public String toString() {
		return "{\"userId\":{\"value\":\"" + this.userId + "\",\"comment\":\"用户id\"},\"email\":{\"value\":\"" + this.email + "\",\"comment\":\"邮箱\"},\"nickName\":{\"value\":\"" + this.nickName + "\",\"comment\":\"昵称\"},\"joinType\":{\"value\":\"" + this.joinType + "\",\"comment\":\"好友申请方式[0:无验证,1:同意后添加]\"},\"sex\":{\"value\":\"" + this.sex + "\",\"comment\":\"性别[0:男,1:女]\"},\"password\":{\"value\":\"" + this.password + "\",\"comment\":\"密码\"},\"personalSignature\":{\"value\":\"" + this.personalSignature + "\",\"comment\":\"个性签名\"},\"status\":{\"value\":\"" + this.status + "\",\"comment\":\"状态[]\"},\"createTime\":{\"value\":\"" + DateUtils.format(this.createTime,DateUtils.DATE_TIME_FORMAT) + "\",\"comment\":\"创建时间\"},\"lastLoginTime\":{\"value\":\"" + DateUtils.format(this.lastLoginTime,DateUtils.DATE_TIME_FORMAT) + "\",\"comment\":\"最后登录时间\"},\"areaName\":{\"value\":\"" + this.areaName + "\",\"comment\":\"最后登录地区\"},\"areaCode\":{\"value\":\"" + this.areaCode + "\",\"comment\":\"最后登录地区编号\"},\"lastOffTime\":{\"value\":\"" + this.lastOffTime + "\",\"comment\":\"最后离线时间\"}}";
	}

}