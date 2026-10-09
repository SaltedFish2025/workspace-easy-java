package com.easy.service.bean;

import com.easy.mapper.bean.BeanMapper;
import com.easy.service.impl.bean.BeanServiceImpl;
import com.easy.utils.PageResult;

import java.util.List;
import java.util.Objects;


public class BeanService<T, P, ID> implements BeanServiceImpl<T, P, ID> {


    protected final BeanMapper<T, P, ID> mapper;


    public BeanService(BeanMapper<T, P, ID> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Integer insertBatch(List<T> list) {
        verifyAndRinseList(list);
        return mapper.insertBatch(list);
    }

    @Override
    public PageResult<T> selectList(P p) {
        return new PageResult<>(mapper.selectCount(p), mapper.selectList(p));
    }

    @Override
    public Integer updateByCondition(T b, P p) {
        return mapper.updateByCondition(b, p);
    }

    @Override
    public Integer updateById(T b, ID id) {
        return mapper.updateById(b, id);
    }

    @Override
    public Integer deleteByCondition(P p) {
        return mapper.deleteByCondition(p);
    }

    @Override
    public Integer deleteById(List<ID> id) {
        verifyAndRinseList(id);
        return mapper.deleteById(id);
    }

    /**
     * 验证清洗List
     *
     * @param list 目标list
     */
    protected void verifyAndRinseList(List<?> list) {
        Objects.requireNonNull(list, "传入的列表不能为 null");
        list.removeIf(Objects::isNull);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("空集合...");
        }
    }
}
