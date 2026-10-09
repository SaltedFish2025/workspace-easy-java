package com.easyjava.bean;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * sql类型 对应Java类型常量表
 */
public class Sql2JavaTypes {

    public static void main(String[] args) {

    }

    public static final String JAVA_LIST_TYPE = "List";
    //Java类型统一模板
    public static final String JAVA_DATE_TYPE = "Date";
    public static final String JAVA_DOUBLE_TYPE = "BigDecimal";
    public static final String JAVA_STRING_TYPE = "String";
    public static final String JAVA_INT_TYPE = "Integer";
    public static final String JAVA_LONG_TYPE = "Long";
    public static final String JAVA_BOOLEAN_TYPE = "Boolean";

    //数据库类型映射Java类型
    private static final Map<String, String> TYPE_MAPPING_MAP = new HashMap<>();
    public static final String[] SQL_DATE_TYPE = {"date"};
    public static final String[] SQL_DATE_TIME_TYPE = {"timestamp", "datetime"};
    public static final String[] SQL_STRING_TYPE = {"char", "varchar", "text", "mediumtext", "longtext"};
    public static final String[] SQL_DOUBLE_TYPE = {"decimal", "double", "float"};
    public static final String[] SQL_INTEGER_TYPE = {"int", "tinyint"};
    public static final String[] SQL_LONG_TYPE = {"bigint"};
    public static final String[] SQL_BOOLEAN_TYPE = {"boolean"};

    static {
        //SQL日期时间类型
        initMap(JAVA_DATE_TYPE, SQL_DATE_TIME_TYPE);
        //SQL日期数据类型
        initMap(JAVA_DATE_TYPE, SQL_DATE_TYPE);
        //SQL 浮点类型
        initMap(JAVA_DOUBLE_TYPE, SQL_DOUBLE_TYPE);
        //SQL 字符类型
        initMap(JAVA_STRING_TYPE, SQL_STRING_TYPE);
        //SQL 整数类型
        initMap(JAVA_INT_TYPE, SQL_INTEGER_TYPE);
        //SQL长类型
        initMap(JAVA_LONG_TYPE, SQL_LONG_TYPE);
        //SQL条件类型
        initMap(JAVA_BOOLEAN_TYPE, SQL_BOOLEAN_TYPE);
    }


    /**
     * 将数组中的元素批量映射到指定的 Java 类型
     */
    private static void initMap(String javaType, String[] sqlTypes) {
        for (String sqlType : sqlTypes) {
            TYPE_MAPPING_MAP.put(sqlType, javaType);
        }
    }

    /**
     * sql类型转换Java类型
     *
     * @param sqlType sql类型
     * @return Java类型
     */
    public static String sqlTypeToJavaType(String sqlType) {
        sqlType = sqlType.toLowerCase();
        if (TYPE_MAPPING_MAP.containsKey(sqlType)) {
            return TYPE_MAPPING_MAP.get(sqlType);
        } else {
            throw new IllegalArgumentException("检测到无法识别的sql类型:" + sqlType);
        }
    }

    /**
     * 获取List<> 字符串
     *
     * @param generic 所需泛型
     * @return List<对应泛型>
     */
    public static String getStrGenericList(String generic) {
        return String.format("%s<%s>", JAVA_LIST_TYPE, generic);
    }



}
