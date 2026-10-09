package com.easy.service.impl;

import com.easy.entity.po.Demo;
import com.easy.entity.query.DemoQuery;

import java.util.List;


public interface TestServiceImpl {


    List<Demo> selectList(DemoQuery demoQuery);

    Integer selectCount(DemoQuery demoQuery);

    Integer inset(Demo demo);

    Integer insertOrUpdate(Demo demo);

    Integer updateByCondition(Demo demo, DemoQuery demoQuery);

    Integer updateById(Demo demo, Integer id);

    Integer deleteByCondition(DemoQuery demoQuery);

    Integer deleteById(List<Integer> id);
}
