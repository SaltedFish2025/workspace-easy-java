package com.easy.mapper.bean;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper<结果集,查询集,主键类型>接口
 */
public interface BeanMapper<T, P,ID> {
    String QUERY_FIELD_NAME = "query";
    String BEAN_FIELD_NAME = "bean";
    String LIST_FIELD_NAME = "list";
    String ID_FIELD_NAME = "id";

    /**
     * 插入数据
     */
    Integer insert(@Param(BEAN_FIELD_NAME) T t);

    /**
     * 插入或更新数据[需有主键或唯一键]
     */
    Integer insertOrUpdate(@Param(BEAN_FIELD_NAME) T t);

    /**
     * 根据条件更新数据
     * ⚠️ 警告：请确保 query 对象中至少包含一个非空字段作为 WHERE 条件，
     * 否则会导致全表更新！如果仅需根据主键更新，请使用 updateById 方法。
     */
    Integer updateByCondition(@Param(BEAN_FIELD_NAME) T b, @Param(QUERY_FIELD_NAME) P p);

    /**
     * 根据主键更新数据（安全）
     */
    Integer updateById(@Param(BEAN_FIELD_NAME) T b, @Param(ID_FIELD_NAME) ID id);

    /**
     * 根据条件删除数据
     * ⚠️ 警告：请确保 query 对象中至少包含一个非空字段作为 WHERE 条件，
     * 否则会导致全表删除！如果仅需根据主键删除，请使用 deleteById 方法。
     */
    Integer deleteByCondition(@Param(QUERY_FIELD_NAME) P p);

    /**
     * 根据主键删除数据（安全）
     */
    Integer deleteById(@Param(LIST_FIELD_NAME) List<ID> id);

    /**
     * 批量插入数据
     */
    Integer insertBatch(@Param(LIST_FIELD_NAME) List<T> list);

    /**
     * 根据参数查找结果
     */
    List<T> selectList(@Param(QUERY_FIELD_NAME) P p);

    /**
     * 根据参数查找数量
     */
    Integer selectCount(@Param(QUERY_FIELD_NAME) P p);

}
