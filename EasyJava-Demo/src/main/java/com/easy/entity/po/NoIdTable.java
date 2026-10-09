package com.easy.entity.po;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 没有主键的表
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class NoIdTable implements Serializable {

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别
	 */
	@JsonIgnore
	private Integer sex;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "{\"name\":{\"value\":\"" + this.name + "\",\"comment\":\"名字\"},\"age\":{\"value\":\"" + this.age + "\",\"comment\":\"年龄\"},\"sex\":{\"value\":\"" + this.sex + "\",\"comment\":\"性别\"}}";
	}

}