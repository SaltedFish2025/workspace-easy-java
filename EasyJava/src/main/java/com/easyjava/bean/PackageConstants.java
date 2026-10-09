package com.easyjava.bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 包常量
 */
public class PackageConstants {

    private static final Map<String, String> PACKAGE_MAP;
    public static final String AUTOWIRED_PACKAGE = "org.springframework.beans.factory.annotation.Autowired";
    public static final String LIST_PACKAGE = "java.util.List";
    public static final String DATE_PACKAGE = "java.util.Date";
    public static final String BIG_DECIMAL_PACKAGE = "java.util.Date";




    static {
        Map<String, String> map = new HashMap<>();
        map.put(Sql2JavaTypes.JAVA_LIST_TYPE.toLowerCase(), LIST_PACKAGE);
        map.put(Sql2JavaTypes.JAVA_DATE_TYPE.toLowerCase(), DATE_PACKAGE);
        map.put(Sql2JavaTypes.JAVA_DOUBLE_TYPE.toLowerCase(), BIG_DECIMAL_PACKAGE);
        PACKAGE_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * 根据名字获取对应包路径
     *
     * @param className 类型名
     * @return List<对应泛型>
     */
    public static String getByPackage(String className) {
        return PACKAGE_MAP.get(className.toLowerCase());
    }

    /**
     * 根据名字获取对应包路径
     *
     * @param className 类型名
     * @return List<对应泛型>
     */
    public static boolean containsKey(String className) {
        return PACKAGE_MAP.containsKey(className.toLowerCase());
    }


}
