package com.easyjava.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * 数据库工具类
 */
public class DatasourceUtils {
    private static  Logger logger = LoggerFactory.getLogger(DatasourceUtils.class);//日志对象
    private static Connection connection = null;

    static {
        String driverClassName = PropertiesUtils.getProperty("db.datasource.driver-class-name");
        String url = PropertiesUtils.getProperty("db.datasource.url");
        String username = PropertiesUtils.getProperty("db.datasource.username");
        String password = PropertiesUtils.getProperty("db.datasource.password");
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            logger.error("数据库连接失败!!!", e);
        }
        autoClose();//
    }

    /**
     * 程序结束自动关闭数据库连接
     */
    private static void autoClose() {
        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // 这里是你希望在程序结束时执行的代码
            logger.info("程序正在关闭，执行数据库清理操作...");
            // 其他清理代码，比如关闭数据库连接、释放资源等
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("自动断开数据连接失败!!", e);
            }
        }));
    }

    /**
     * 获取jdbc连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }


    /**
     * 结果集封装Map
     *
     * @param resultSet 结果集
     * @return 映射列表
     */
    public static List<Map<String, String>> resultSetToMaps(ResultSet resultSet) {
        List<Map<String, String>> resultMapList = new ArrayList<>();
        try {
            // 1. 获取元数据对象
            ResultSetMetaData rsmd = resultSet.getMetaData();
            // 2. 获取列的数量
            int columnCount = rsmd.getColumnCount();
            // 遍历结果集中的每一行
            while (resultSet.next()) {
                Map<String, String> resultMap = new HashMap<>();
                // 遍历当前行的每一列
                for (int i = 1; i <= columnCount; i++) {
                    // 3. 通过列索引获取列名（标签）
                    String columnName = rsmd.getColumnLabel(i);
                    // 4. 通过列名获取对应的值
                    String value = resultSet.getString(columnName);
                    resultMap.put(columnName, value);
                }
                resultMapList.add(resultMap);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultMapList;
    }


}
