package com.easyjava.builder;

import com.easyjava.bean.Constants;
import com.easyjava.bean.FieldInfo;
import com.easyjava.bean.Sql2JavaTypes;
import com.easyjava.bean.TableInfo;
import com.easyjava.utils.DatasourceUtils;
import com.easyjava.utils.MyStringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * 表信息对象构建类
 */
public class BuilderTable {

    private static Logger logger = LoggerFactory.getLogger(BuilderTable.class);//日志对象

    //读取表信息sql
    private static final String SQL_SHOW_TABLE_STATUS = "SHOW TABLE STATUS";
    //读取表字段信息sql
    private static final String SQL_SHOW_TABLE_FIELDS = "SHOW FULL FIELDS FROM %s";
    //读取表索引信息sql
    private static final String SQL_SHOW_TABLE_INDEX = "SHOW INDEX FROM %s";
    //sql增长标识
    private static final String SQL_AUTO_INCREMENT_IDENTIFIER = "AUTO_INCREMENT";


    /**
     * 数据模型构建
     *
     * @return 数据模型列表
     */
    public static List<TableInfo> buildTableInfo() {
        //构建表实体类
        return constructTableInfo();
    }


    /**
     * 构建TableInfo实体类
     *
     * @return TableInfo 对象
     */
    private static List<TableInfo> constructTableInfo() {
        List<TableInfo> tableInfoList = new ArrayList<>();
        //读取表信息
        List<Map<String, String>> resultMaps = executeQuery(SQL_SHOW_TABLE_STATUS);
        if (resultMaps != null && !resultMaps.isEmpty()) {
            for (Map<String, String> resultMap : resultMaps) {
                String name = resultMap.get("Name");
                String comment = resultMap.get("Comment");
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(name);
                tableInfo.setComment(comment);
                tableInfo.setBeanName(formatTableBeanName(name));
                tableInfo.setBeanParamName(tableInfo.getBeanName() + Constants.BEAN_QUERY_SUFFIX);
                constructTableFieldInfo(tableInfo);//构建字段属性
                constructTableIndexInfo(tableInfo);//构建索引属性

                tableInfo.setMapperName(tableInfo.getBeanName() + Constants.BEAN_MAPPER_SUFFIX);
                tableInfo.setMapperVariableName(MyStringUtils.humpNaming(tableInfo.getMapperName(),false));
                tableInfo.setServiceName(tableInfo.getBeanName() + Constants.BEAN_SERVICE_SUFFIX);
                tableInfo.setServiceVariableName(MyStringUtils.humpNaming(tableInfo.getServiceName(),false));
                tableInfo.setServiceImplName( tableInfo.getBeanName() + Constants.BEAN_SERVICE_IMPL_SUFFIX);
                tableInfo.setControllerName( tableInfo.getBeanName() + Constants.BEAN_CONTROLLER_SUFFIX);


                tableInfoList.add(tableInfo);
            }
        }
        return tableInfoList;
    }


    /**
     * 构建表字段信息
     *
     * @param tableInfo 表原始对象
     */
    private static void constructTableFieldInfo(TableInfo tableInfo) {
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        //读取字段信息
        List<Map<String, String>> resultMaps = executeQuery(String.format(SQL_SHOW_TABLE_FIELDS, tableInfo.getTableName()));
        for (Map<String, String> resultMap : resultMaps) {
            FieldInfo fieldInfo = new FieldInfo();
            //配置名
            String field = resultMap.get("Field");
            fieldInfo.setFieldName(field);
            fieldInfo.setPropertyName(MyStringUtils.humpNaming(field, false));
            //配置字段备注
            String comment = resultMap.get("Comment");
            fieldInfo.setComment(comment != null && !comment.isEmpty() ? comment : Constants.REPLACEMENT_COMMENT_IS_EMPTY);
            //配置是否自增长
            fieldInfo.setAutoIncrement(SQL_AUTO_INCREMENT_IDENTIFIER.equals(resultMap.get("Extra").toUpperCase()));
            if (fieldInfo.getAutoIncrement()) {
                tableInfo.setAutoField(fieldInfo);
            }
            //配置主键和唯一键
            fieldInfo.setPrimaryKey(resultMap.get("Key").toUpperCase());
            if (FieldInfo.SQL_PRIMARY_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey())) {
                tableInfo.setPrimaryKey(fieldInfo);
                tableInfo.setHavePrimaryKey(true);
            }
            if (!tableInfo.getHaveUniqueKey() && FieldInfo.SQL_UNIQUE_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey())) {
                tableInfo.setHaveUniqueKey(true);
            }
            //配置类型
            String type = resultMap.get("Type");
            if (type.indexOf("(") > 0) {
                type = type.substring(0, type.indexOf("("));
            }
            fieldInfo.setSqlType(type);
            String javaType = Sql2JavaTypes.sqlTypeToJavaType(type);
            fieldInfo.setJavaType(javaType);
            if (!tableInfo.getHaveIgnoreField() && Constants.IGNORE_BEAN_TOJSON_FIELD != null && ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FIELD, fieldInfo.getPropertyName())) {
                tableInfo.setHaveIgnoreField(true);
            }
            if (!tableInfo.getHaveDate() && Sql2JavaTypes.JAVA_DATE_TYPE.equals(javaType)) {//判断是否有时间类型
                if (ArrayUtils.contains(Sql2JavaTypes.SQL_DATE_TYPE, type)) {
                    tableInfo.setHaveDate(true);
                }
                if (ArrayUtils.contains(Sql2JavaTypes.SQL_DATE_TIME_TYPE, type)) {
                    tableInfo.setHaveDateTime(true);
                }
            } else if (!tableInfo.getHaveBigDecimal() && Sql2JavaTypes.JAVA_DOUBLE_TYPE.equals(javaType)) {//判断是否有大浮点类型
                tableInfo.setHaveBigDecimal(true);
            }
            fieldInfoList.add(fieldInfo);
        }
        tableInfo.setFieldInfoList(fieldInfoList);
    }


    /**
     * 构建表索引信息
     *
     * @param tableInfo 表原始对象
     */
    private static void constructTableIndexInfo(TableInfo tableInfo) {
        Map<String, FieldInfo> fieldInfoMap = new HashMap<>();
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            fieldInfoMap.put(fieldInfo.getFieldName(), fieldInfo);
        }
        List<Map<String, String>> resultMaps = executeQuery(String.format(SQL_SHOW_TABLE_INDEX, tableInfo.getTableName()));
        for (Map<String, String> resultMap : resultMaps) {
            String nonUnique = resultMap.get("Non_unique");
            String keyName = resultMap.get("Key_name");
            String columnName = resultMap.get("Column_name");
            //判断唯一索引
            if ("0".equals(nonUnique)) {
                //Lambda 表达式简化写法,判断map中是否存在key,存在则返回,不存在则创建一个放进去,在返回
                List<FieldInfo> fieldInfos = tableInfo.getKeyIndexMap().computeIfAbsent(keyName, k -> new ArrayList<>());
                if (fieldInfoMap.containsKey(columnName)) {
                    fieldInfos.add(fieldInfoMap.get(columnName));
                }
            }
        }
    }

    /**
     * bean表名格式化
     *
     * @return 格式化
     */
    private static String formatTableBeanName(String name) {
        if (name == null || name.isEmpty()) return null;
        String newName = name;
        if (Constants.IGNORE_TABLE_PREFIX != null && Constants.IGNORE_TABLE_PREFIX.length != 0) {
            String[] prefixArray = Constants.IGNORE_TABLE_PREFIX;
            for (String prefix : prefixArray) {
                if (!prefix.isEmpty() && newName.startsWith(prefix)) {
                    newName = StringUtils.removeStart(newName, prefix);
                    break;
                }
            }
        }
        if (Constants.IGNORE_TABLE_SUFFIX != null && Constants.IGNORE_TABLE_SUFFIX.length != 0) {
            String[] suffixArray = Constants.IGNORE_TABLE_SUFFIX;
            for (String suffix : suffixArray) {
                if (!suffix.isEmpty() && newName.endsWith(suffix)) {
                    newName = StringUtils.removeEnd(newName, suffix);
                    break;
                }
            }
        }
        if (newName.isEmpty()) {
            newName = name;
            logger.error("{}去除前后缀后表名为空,以原表名构建", name);
        }
        newName = MyStringUtils.humpNaming(newName);
        String addTablePrefixSuffix = Constants.BEAN_TABLE_PREFIX_SUFFIX;
        if (addTablePrefixSuffix != null) {
            if (addTablePrefixSuffix.contains("%s")) {
                newName = String.format(addTablePrefixSuffix, newName).trim();
            }
        }
        return newName;
    }


    /**
     * 执行读取sql并返回结果
     *
     * @param sql sql
     * @return 结果map
     */
    private static List<Map<String, String>> executeQuery(String sql) {
        List<Map<String, String>> resultMap = null;
        try {
            PreparedStatement ps = DatasourceUtils.getConnection().prepareStatement(sql);
            resultMap = DatasourceUtils.resultSetToMaps(ps.executeQuery());
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }
}
