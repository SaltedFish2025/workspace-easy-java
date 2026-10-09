package com.easy.service;

import com.easy.entity.po.Demo;
import com.easy.entity.query.DemoQuery;
import com.easy.mapper.DemoMapper;
import com.easy.service.impl.TestServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试服务
 */
@Service
public class TestService implements TestServiceImpl {

    private final DemoMapper demoMapper;

    public TestService(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    public List<Demo> selectList(DemoQuery demoQuery) {
        return demoMapper.selectList(demoQuery);
    }

    public Integer selectCount(DemoQuery demoQuery) {
        return demoMapper.selectCount(demoQuery);
    }

    public Integer inset(Demo demo) {
        return demoMapper.insert(demo);
    }

    public Integer insertBatch(List<Demo> demo) {
        return demoMapper.insertBatch(demo);
    }

    public Integer insertOrUpdate(Demo demo) {
        return demoMapper.insertOrUpdate(demo);
    }

    public Integer updateByCondition(Demo demo, DemoQuery demoQuery) {
        return demoMapper.updateByCondition(demo, demoQuery);
    }

    public Integer updateById(Demo demo, Integer id) {
        return demoMapper.updateById(demo, id);
    }

    @Override
    public Integer deleteByCondition(DemoQuery demoQuery) {
        return demoMapper.deleteByCondition(demoQuery);
    }

    @Override
    public Integer deleteById(List<Integer> id) {
        return demoMapper.deleteById(id);
    }
}
