package com.easy.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.easy.mapper.bean.BeanMapper;
import com.easy.entity.po.NoIdTable;
import com.easy.entity.query.NoIdTableQuery;


/**
 * 没有主键的表Mapper
 * 
 * @date 2026-10-06 21:56:49
 * @author 程序自动生成
 */
@Mapper
public interface NoIdTableMapper extends BeanMapper<NoIdTable, NoIdTableQuery, Void> {


}