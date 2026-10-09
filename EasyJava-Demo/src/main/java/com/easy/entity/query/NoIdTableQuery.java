package com.easy.entity.query;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 没有主键的表查询类
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class NoIdTableQuery extends Query {
	/**
	 * name的模糊查询字段
	 */
	private String nameFuzzy;
	/**
	 * name的批量查询字段
	 */
	private List<String> nameList;
	/**
	 * age的开始字段
	 */
	private Integer ageStart;
	/**
	 * age的结束字段
	 */
	private Integer ageEnd;
	/**
	 * age的批量查询字段
	 */
	private List<Integer> ageList;
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

	public String getNameFuzzy() {
		return this.nameFuzzy;
	}

	public void setNameFuzzy(String nameFuzzy) {
		this.nameFuzzy = nameFuzzy;
	}

	public List<String> getNameList() {
		return this.nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	public Integer getAgeStart() {
		return this.ageStart;
	}

	public void setAgeStart(Integer ageStart) {
		this.ageStart = ageStart;
	}

	public Integer getAgeEnd() {
		return this.ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
	}

	public List<Integer> getAgeList() {
		return this.ageList;
	}

	public void setAgeList(List<Integer> ageList) {
		this.ageList = ageList;
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


}