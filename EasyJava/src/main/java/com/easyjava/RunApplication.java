package com.easyjava;

import com.easyjava.bean.Constants;
import com.easyjava.bean.TableInfo;
import com.easyjava.builder.BuildUtils;
import com.easyjava.builder.build_base.BuildPo;
import com.easyjava.builder.build_base.BuildQuery;
import com.easyjava.builder.BuilderTable;
import com.easyjava.builder.build_mapper.BuildMapper;
import com.easyjava.builder.build_service.BuildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 启动类
 */
public class RunApplication {
    private static Logger logger = LoggerFactory.getLogger(RunApplication.class);//日志对象

    public static void main(String[] args) {
        logger.info("开始生成文件到:{}", Constants.PATH_BASE);
        //1.构建工具类
        BuildUtils.execute();

        List<TableInfo> tableInfoList = BuilderTable.buildTableInfo();
        for (TableInfo tableInfo : tableInfoList) {
            //2.构建表对应Java实体类
            BuildPo.execute(tableInfo);
            //3.构建表对应Java实体查询类
            BuildQuery.execute(tableInfo);
            //4.构建表Mapper数据库控制
            BuildMapper.execute(tableInfo);
            //5.构建表对应Service
            BuildService.execute(tableInfo);
        }


    }
}
