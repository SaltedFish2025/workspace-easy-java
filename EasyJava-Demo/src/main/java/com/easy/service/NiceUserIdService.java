package com.easy.service;

import org.springframework.stereotype.Service;
import com.easy.mapper.NiceUserIdMapper;
import com.easy.service.bean.BeanService;
import com.easy.service.impl.NiceUserIdServiceImpl;
import com.easy.entity.po.NiceUserId;
import com.easy.entity.query.NiceUserIdQuery;


/**
 * 用户优秀id表Service
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Service
public class NiceUserIdService extends BeanService<NiceUserId, NiceUserIdQuery, Integer> implements NiceUserIdServiceImpl {



	public NiceUserIdService(NiceUserIdMapper niceUserIdMapper) {
		super(niceUserIdMapper);
	}








}