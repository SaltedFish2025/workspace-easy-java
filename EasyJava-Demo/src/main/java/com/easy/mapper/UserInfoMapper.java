package com.easy.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.easy.mapper.bean.BeanMapper;
import com.easy.entity.po.UserInfo;
import com.easy.entity.query.UserInfoQuery;


/**
 * 用户信息表Mapper
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Mapper
public interface UserInfoMapper extends BeanMapper<UserInfo, UserInfoQuery, String> {


}