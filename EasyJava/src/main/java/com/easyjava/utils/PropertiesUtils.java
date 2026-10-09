package com.easyjava.utils;

import com.easyjava.enums.ApplicationParam;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置文件工具类
 */
public class PropertiesUtils {

    public static void main(String[] args) {

    }

    private final static Properties PROPERTIES = new Properties();
    private final static Map<String, String> PROPERTIES_MAP = new ConcurrentHashMap<>();

    //初始化配置类
    static {
        defaultProperties();
        InputStream is = null;
        try {
            //读取配置文件
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            PROPERTIES.load(new InputStreamReader(is, StandardCharsets.UTF_8));//加载配置文件
            Iterator<Object> iterator = PROPERTIES.keySet().iterator();//获取key
            while (iterator.hasNext()) {//遍历
                String key = iterator.next().toString();
                String value = PROPERTIES.getProperty(key);
                if (value != null && !value.trim().isEmpty()) {
                    PROPERTIES_MAP.put(key, value);//填充
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 配置文件默认值表
     */
    private static void defaultProperties() {
        PROPERTIES_MAP.put(ApplicationParam.IGNORE_BEAN_TOJSON_EXPRESSION.getValue(), "@JsonIgnore");
        PROPERTIES_MAP.put(ApplicationParam.IGNORE_BEAN_TOJSON_IMPORT.getValue(), "import com.fasterxml.jackson.annotation.JsonIgnore;");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_DATE_FORMAT_EXPRESSION.getValue(), "@JsonFormat(pattern = \"%s\", timezone = \"GMT-8\")");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_DATE_FORMAT_IMPORT.getValue(), "import com.fasterxml.jackson.annotation.JsonFormat;");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_DATE_UN_FORMAT_EXPRESSION.getValue(), "@DateTimeFormat(pattern = \"%s\")");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_DATE_UN_FORMAT_IMPORT.getValue(), "import org.springframework.format.annotation.DateTimeFormat;");

        PROPERTIES_MAP.put(ApplicationParam.BEAN_QUERY_SUFFIX.getValue(), "Query");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_QUERY_FUZZY_SUFFIX.getValue(), "Fuzzy");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_MAPPER_SUFFIX.getValue(), "Mapper");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_SERVICE_SUFFIX.getValue(), "Server");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_SERVICE_IMPL_SUFFIX.getValue(), "Impl");
        PROPERTIES_MAP.put(ApplicationParam.BEAN_CONTROLLER_SUFFIX.getValue(), "Controller");
        PROPERTIES_MAP.put(ApplicationParam.QUERY_BEAN_SCOPE_START.getValue(), "Start");
        PROPERTIES_MAP.put(ApplicationParam.QUERY_BEAN_SCOPE_END.getValue(), "End");
        //#生成包名
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_BASE.getValue(), "com.easyjava");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_PO.getValue(), "entity.po");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_QUERY.getValue(), "entity.query");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_UTILS.getValue(), "utils");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_MAPPER.getValue(), "mapper");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_SERVICE.getValue(), "service");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_SERVICE_IMPL.getValue(), "impl");
        PROPERTIES_MAP.put(ApplicationParam.PACKAGE_CONTROLLER.getValue(), "controller");
    }

    /**
     * 根据属性名获取属性值
     *
     * @param attribute 属性名
     * @return 属性值
     */
    public static String getProperty(String attribute) {
        String property = null;
        if (PROPERTIES_MAP.containsKey(attribute)) {
            property = PROPERTIES_MAP.get(attribute).trim();
        }
        return property;
    }


    /**
     * 根据属性名判断 存在 或 为空[T:存在,F:不存在以及为空]
     *
     * @param attribute 属性名
     * @return 属性值
     */
    public static Boolean containsKey(String attribute) {
        boolean property = false;
        if (PROPERTIES_MAP.containsKey(attribute)) {
            property = !PROPERTIES_MAP.get(attribute).trim().isEmpty();
        }
        return property;
    }


    /**
     * 根据属性名获取属性值
     *
     * @param attribute 属性名
     * @return 属性值
     */
    public static String getProperty(ApplicationParam attribute) {
        return getProperty(attribute.getValue());
    }


    /**
     * 根据属性名判断 存在 或 为空[T:存在,F:不存在以及为空]
     *
     * @param attribute 属性名
     * @return 属性值
     */
    public static Boolean containsKey(ApplicationParam attribute) {
        return containsKey(attribute.getValue());
    }


}
