package com.easy.service;

import org.springframework.stereotype.Service;
import com.easy.mapper.UserInfoMapper;
import com.easy.service.bean.BeanService;
import com.easy.service.impl.UserInfoServiceImpl;
import com.easy.entity.po.UserInfo;
import com.easy.entity.query.UserInfoQuery;


/**
 * 用户信息表Service
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Service
public class UserInfoService extends BeanService<UserInfo, UserInfoQuery, String> implements UserInfoServiceImpl {



	public UserInfoService(UserInfoMapper userInfoMapper) {
		super(userInfoMapper);
	}








}