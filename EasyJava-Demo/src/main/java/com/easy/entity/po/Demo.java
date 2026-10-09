package com.easy.entity.po;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.easy.utils.*;

/**
 * 构造器测试表
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class Demo implements Serializable {

	/**
	 * 有人很懒什么都没写
	 */
	private Integer id;

	/**
	 * 有人很懒什么都没写
	 */
	private String name;

	/**
	 * 有人很懒什么都没写
	 */
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT-8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	/**
	 * 有人很懒什么都没写
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT-8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dateTime;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 有人很懒什么都没写
	 */
	private String uuid;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "{\"id\":{\"value\":\"" + this.id + "\",\"comment\":\"有人很懒什么都没写\"},\"name\":{\"value\":\"" + this.name + "\",\"comment\":\"有人很懒什么都没写\"},\"date\":{\"value\":\"" + DateUtils.format(this.date,DateUtils.DATE_FORMAT) + "\",\"comment\":\"有人很懒什么都没写\"},\"dateTime\":{\"value\":\"" + DateUtils.format(this.dateTime,DateUtils.DATE_TIME_FORMAT) + "\",\"comment\":\"有人很懒什么都没写\"},\"age\":{\"value\":\"" + this.age + "\",\"comment\":\"年龄\"},\"uuid\":{\"value\":\"" + this.uuid + "\",\"comment\":\"有人很懒什么都没写\"}}";
	}

}