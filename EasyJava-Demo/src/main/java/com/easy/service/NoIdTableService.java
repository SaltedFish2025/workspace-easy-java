package com.easy.service;

import org.springframework.stereotype.Service;
import com.easy.mapper.NoIdTableMapper;
import com.easy.service.bean.BeanService;
import com.easy.service.impl.NoIdTableServiceImpl;
import com.easy.entity.po.NoIdTable;
import com.easy.entity.query.NoIdTableQuery;


/**
 * 没有主键的表Service
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Service
public class NoIdTableService extends BeanService<NoIdTable, NoIdTableQuery, Void> implements NoIdTableServiceImpl {



	public NoIdTableService(NoIdTableMapper noIdTableMapper) {
		super(noIdTableMapper);
	}








}