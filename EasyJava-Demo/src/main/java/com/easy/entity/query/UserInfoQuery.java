package com.easy.entity.query;


import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 用户信息表查询类
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class UserInfoQuery extends Query {
	/**
	 * userId的模糊查询字段
	 */
	private String userIdFuzzy;
	/**
	 * userId的批量查询字段
	 */
	private List<String> userIdList;
	/**
	 * email的模糊查询字段
	 */
	private String emailFuzzy;
	/**
	 * email的批量查询字段
	 */
	private List<String> emailList;
	/**
	 * nickName的模糊查询字段
	 */
	private String nickNameFuzzy;
	/**
	 * nickName的批量查询字段
	 */
	private List<String> nickNameList;
	/**
	 * joinType的开始字段
	 */
	private Integer joinTypeStart;
	/**
	 * joinType的结束字段
	 */
	private Integer joinTypeEnd;
	/**
	 * joinType的批量查询字段
	 */
	private List<Integer> joinTypeList;
	/**
	 * sex的开始字段
	 */
	private Integer sexStart;
	/**
	 * sex的结束字段
	 */
	private Integer sexEnd;
	/**
	 * sex的批量查询字段
	 */
	private List<Integer> sexList;
	/**
	 * password的模糊查询字段
	 */
	private String passwordFuzzy;
	/**
	 * password的批量查询字段
	 */
	private List<String> passwordList;
	/**
	 * personalSignature的模糊查询字段
	 */
	private String personalSignatureFuzzy;
	/**
	 * personalSignature的批量查询字段
	 */
	private List<String> personalSignatureList;
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
	/**
	 * createTime的开始字段
	 */
	private Date createTimeStart;
	/**
	 * createTime的结束字段
	 */
	private Date createTimeEnd;
	/**
	 * createTime的批量查询字段
	 */
	private List<Date> createTimeList;
	/**
	 * lastLoginTime的开始字段
	 */
	private Date lastLoginTimeStart;
	/**
	 * lastLoginTime的结束字段
	 */
	private Date lastLoginTimeEnd;
	/**
	 * lastLoginTime的批量查询字段
	 */
	private List<Date> lastLoginTimeList;
	/**
	 * areaName的模糊查询字段
	 */
	private String areaNameFuzzy;
	/**
	 * areaName的批量查询字段
	 */
	private List<String> areaNameList;
	/**
	 * areaCode的模糊查询字段
	 */
	private String areaCodeFuzzy;
	/**
	 * areaCode的批量查询字段
	 */
	private List<String> areaCodeList;
	/**
	 * lastOffTime的开始字段
	 */
	private Long lastOffTimeStart;
	/**
	 * lastOffTime的结束字段
	 */
	private Long lastOffTimeEnd;
	/**
	 * lastOffTime的批量查询字段
	 */
	private List<Long> lastOffTimeList;

	public String getUserIdFuzzy() {
		return this.userIdFuzzy;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public List<String> getUserIdList() {
		return this.userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
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

	public String getNickNameFuzzy() {
		return this.nickNameFuzzy;
	}

	public void setNickNameFuzzy(String nickNameFuzzy) {
		this.nickNameFuzzy = nickNameFuzzy;
	}

	public List<String> getNickNameList() {
		return this.nickNameList;
	}

	public void setNickNameList(List<String> nickNameList) {
		this.nickNameList = nickNameList;
	}

	public Integer getJoinTypeStart() {
		return this.joinTypeStart;
	}

	public void setJoinTypeStart(Integer joinTypeStart) {
		this.joinTypeStart = joinTypeStart;
	}

	public Integer getJoinTypeEnd() {
		return this.joinTypeEnd;
	}

	public void setJoinTypeEnd(Integer joinTypeEnd) {
		this.joinTypeEnd = joinTypeEnd;
	}

	public List<Integer> getJoinTypeList() {
		return this.joinTypeList;
	}

	public void setJoinTypeList(List<Integer> joinTypeList) {
		this.joinTypeList = joinTypeList;
	}

	public Integer getSexStart() {
		return this.sexStart;
	}

	public void setSexStart(Integer sexStart) {
		this.sexStart = sexStart;
	}

	public Integer getSexEnd() {
		return this.sexEnd;
	}

	public void setSexEnd(Integer sexEnd) {
		this.sexEnd = sexEnd;
	}

	public List<Integer> getSexList() {
		return this.sexList;
	}

	public void setSexList(List<Integer> sexList) {
		this.sexList = sexList;
	}

	public String getPasswordFuzzy() {
		return this.passwordFuzzy;
	}

	public void setPasswordFuzzy(String passwordFuzzy) {
		this.passwordFuzzy = passwordFuzzy;
	}

	public List<String> getPasswordList() {
		return this.passwordList;
	}

	public void setPasswordList(List<String> passwordList) {
		this.passwordList = passwordList;
	}

	public String getPersonalSignatureFuzzy() {
		return this.personalSignatureFuzzy;
	}

	public void setPersonalSignatureFuzzy(String personalSignatureFuzzy) {
		this.personalSignatureFuzzy = personalSignatureFuzzy;
	}

	public List<String> getPersonalSignatureList() {
		return this.personalSignatureList;
	}

	public void setPersonalSignatureList(List<String> personalSignatureList) {
		this.personalSignatureList = personalSignatureList;
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

	public Date getCreateTimeStart() {
		return this.createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public List<Date> getCreateTimeList() {
		return this.createTimeList;
	}

	public void setCreateTimeList(List<Date> createTimeList) {
		this.createTimeList = createTimeList;
	}

	public Date getLastLoginTimeStart() {
		return this.lastLoginTimeStart;
	}

	public void setLastLoginTimeStart(Date lastLoginTimeStart) {
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public Date getLastLoginTimeEnd() {
		return this.lastLoginTimeEnd;
	}

	public void setLastLoginTimeEnd(Date lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public List<Date> getLastLoginTimeList() {
		return this.lastLoginTimeList;
	}

	public void setLastLoginTimeList(List<Date> lastLoginTimeList) {
		this.lastLoginTimeList = lastLoginTimeList;
	}

	public String getAreaNameFuzzy() {
		return this.areaNameFuzzy;
	}

	public void setAreaNameFuzzy(String areaNameFuzzy) {
		this.areaNameFuzzy = areaNameFuzzy;
	}

	public List<String> getAreaNameList() {
		return this.areaNameList;
	}

	public void setAreaNameList(List<String> areaNameList) {
		this.areaNameList = areaNameList;
	}

	public String getAreaCodeFuzzy() {
		return this.areaCodeFuzzy;
	}

	public void setAreaCodeFuzzy(String areaCodeFuzzy) {
		this.areaCodeFuzzy = areaCodeFuzzy;
	}

	public List<String> getAreaCodeList() {
		return this.areaCodeList;
	}

	public void setAreaCodeList(List<String> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}

	public Long getLastOffTimeStart() {
		return this.lastOffTimeStart;
	}

	public void setLastOffTimeStart(Long lastOffTimeStart) {
		this.lastOffTimeStart = lastOffTimeStart;
	}

	public Long getLastOffTimeEnd() {
		return this.lastOffTimeEnd;
	}

	public void setLastOffTimeEnd(Long lastOffTimeEnd) {
		this.lastOffTimeEnd = lastOffTimeEnd;
	}

	public List<Long> getLastOffTimeList() {
		return this.lastOffTimeList;
	}

	public void setLastOffTimeList(List<Long> lastOffTimeList) {
		this.lastOffTimeList = lastOffTimeList;
	}


}