package com.easy.entity.po;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户优秀id表
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class NiceUserId implements Serializable {

	/**
	 * 主键id
	 */
	private Integer id;

	/**
	 * 邮箱
	 */
	@JsonIgnore
	private String email;

	/**
	 * 用户信息表对应用户id
	 */
	private Integer userId;

	/**
	 * 状态[0:未使用,1:使用中]
	 */
	private Integer status;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{\"id\":{\"value\":\"" + this.id + "\",\"comment\":\"主键id\"},\"email\":{\"value\":\"" + this.email + "\",\"comment\":\"邮箱\"},\"userId\":{\"value\":\"" + this.userId + "\",\"comment\":\"用户信息表对应用户id\"},\"status\":{\"value\":\"" + this.status + "\",\"comment\":\"状态[0:未使用,1:使用中]\"}}";
	}

}