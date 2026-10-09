package com.easy.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.easy.mapper.bean.BeanMapper;
import com.easy.entity.po.NiceUserId;
import com.easy.entity.query.NiceUserIdQuery;


/**
 * 用户优秀id表Mapper
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Mapper
public interface NiceUserIdMapper extends BeanMapper<NiceUserId, NiceUserIdQuery, Integer> {


}