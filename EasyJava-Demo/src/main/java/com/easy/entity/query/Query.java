package com.easy.entity.query;

/**
 * 查询基础类
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
public class Query {
	/**
	 * 分页拓展-偏移
	 */
	private Integer pageOffset;
	/**
	 * 分页拓展-页数
	 */
	private Integer pageSize;
	/**
	 * 排序拓展,需自行填写规则
	 * select * from 表名 order by [规则]
	 */
	private String order;
	public Integer getPageOffset() {
		return this.pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


}