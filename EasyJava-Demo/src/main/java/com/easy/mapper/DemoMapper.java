package com.easy.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.easy.mapper.bean.BeanMapper;
import com.easy.entity.po.Demo;
import com.easy.entity.query.DemoQuery;


/**
 * 构造器测试表Mapper
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Mapper
public interface DemoMapper extends BeanMapper<Demo, DemoQuery, Integer> {


}