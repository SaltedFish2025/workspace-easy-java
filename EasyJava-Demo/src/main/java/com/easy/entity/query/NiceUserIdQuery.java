package com.easy.entity.query;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户优秀id表查询类
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class NiceUserIdQuery extends Query {
	/**
	 * id的开始字段
	 */
	private Integer idStart;
	/**
	 * id的结束字段
	 */
	private Integer idEnd;
	/**
	 * id的批量查询字段
	 */
	private List<Integer> idList;
	/**
	 * email的模糊查询字段
	 */
	private String emailFuzzy;
	/**
	 * email的批量查询字段
	 */
	private List<String> emailList;
	/**
	 * userId的开始字段
	 */
	private Integer userIdStart;
	/**
	 * userId的结束字段
	 */
	private Integer userIdEnd;
	/**
	 * userId的批量查询字段
	 */
	private List<Integer> userIdList;
	/**
	 * status的开始字段
	 */
	private Integer statusStart;
	/**
	 * status的结束字段
	 */
	private Integer statusEnd;
	/**
	 * status的批量查询字段
	 */
	private List<Integer> statusList;

	public Integer getIdStart() {
		return this.idStart;
	}

	public void setIdStart(Integer idStart) {
		this.idStart = idStart;
	}

	public Integer getIdEnd() {
		return this.idEnd;
	}

	public void setIdEnd(Integer idEnd) {
		this.idEnd = idEnd;
	}

	public List<Integer> getIdList() {
		return this.idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public String getEmailFuzzy() {
		return this.emailFuzzy;
	}

	public void setEmailFuzzy(String emailFuzzy) {
		this.emailFuzzy = emailFuzzy;
	}

	public List<String> getEmailList() {
		return this.emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}

	public Integer getUserIdStart() {
		return this.userIdStart;
	}

	public void setUserIdStart(Integer userIdStart) {
		this.userIdStart = userIdStart;
	}

	public Integer getUserIdEnd() {
		return this.userIdEnd;
	}

	public void setUserIdEnd(Integer userIdEnd) {
		this.userIdEnd = userIdEnd;
	}

	public List<Integer> getUserIdList() {
		return this.userIdList;
	}

	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}

	public Integer getStatusStart() {
		return this.statusStart;
	}

	public void setStatusStart(Integer statusStart) {
		this.statusStart = statusStart;
	}

	public Integer getStatusEnd() {
		return this.statusEnd;
	}

	public void setStatusEnd(Integer statusEnd) {
		this.statusEnd = statusEnd;
	}

	public List<Integer> getStatusList() {
		return this.statusList;
	}

	public void setStatusList(List<Integer> statusList) {
		this.statusList = statusList;
	}


}