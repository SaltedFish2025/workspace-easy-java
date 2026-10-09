package com.easy.service;

import org.springframework.stereotype.Service;
import com.easy.mapper.DemoMapper;
import com.easy.service.bean.BeanService;
import com.easy.service.impl.DemoServiceImpl;
import com.easy.entity.po.Demo;
import com.easy.entity.query.DemoQuery;


/**
 * 构造器测试表Service
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Service
public class DemoService extends BeanService<Demo, DemoQuery, Integer> implements DemoServiceImpl {



	public DemoService(DemoMapper demoMapper) {
		super(demoMapper);
	}








}