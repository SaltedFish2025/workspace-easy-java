package com.easyjava.bean;

import com.easyjava.enums.ApplicationParam;
import com.easyjava.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 常量池
 */
public class Constants {
    private static final Logger logger = LoggerFactory.getLogger(Constants.class);//日志对象

    public static String REPLACEMENT_COMMENT_IS_EMPTY = "有人很懒什么都没写";

    public static final String JAVA_SUFFIX_NAME = ".java";
    public static final String XML_SUFFIX_NAME = ".xml";


    public static String BEAN_TABLE_PREFIX_SUFFIX = null;

    public static String[] IGNORE_TABLE_PREFIX = null;
    //格式化表名后缀
    public static String[] IGNORE_TABLE_SUFFIX = null;
    //Java表对象后缀
    public static final String BEAN_QUERY_SUFFIX = PropertiesUtils.getProperty(ApplicationParam.BEAN_QUERY_SUFFIX);//Java表对象后缀
    //作者名称
    public static final String ANNOTATION_AUTHOR = PropertiesUtils.getProperty(ApplicationParam.ANNOTATION_AUTHOR);
    // 文件输出路径
    public static final String PATH_BASE = filePathFormatting(PropertiesUtils.getProperty(ApplicationParam.PATH_BASE));
    public static final String PATH_PO;
    public static final String PATH_UTILS;
    public static final String PATH_QUERY;
    public static final String PATH_MAPPER;
    public static final String PATH_BEAN_MAPPER;
    public static final String PATH_SERVICE;
    public static final String PATH_SERVICE_IMPL;
    public static final String PATH_BEAN_SERVICE;
    public static final String PATH_BEAN_SERVICE_IMPL;
    public static final String PATH_CONTROLLER;
    public static final String PATH_JAVA_NAME = "java";
    public static final String PATH_JAVA; //java全部程序文件路径
    public static final String PATH_JAVA_PACKAGE; //Java 包路径
    public static final String PATH_RESOURCES_NAME = "resources";
    public static final String PATH_RESOURCES;//配置文件路径
    public static final String PATH_MYBATIS_XML;
    //包名
    public static final String PACKAGE_PO;
    public static final String PACKAGE_UTILS;
    public static final String PACKAGE_MAPPER;
    public static final String PACKAGE_BEAN_MAPPER;

    public static final String PACKAGE_SERVICE;
    public static final String PACKAGE_SERVICE_IMPL;
    public static final String PACKAGE_BEAN_SERVICE;
    public static final String PACKAGE_BEAN_SERVICE_IMPL;
    public static final String PACKAGE_CONTROLLER;
    public static final String PACKAGE_QUERY;
    public static final String PACKAGE_BASE;
    //忽略属性所需参数
    public static String[] IGNORE_BEAN_TOJSON_FIELD = null;
    public static final String IGNORE_BEAN_TOJSON_EXPRESSION = PropertiesUtils.getProperty(ApplicationParam.IGNORE_BEAN_TOJSON_EXPRESSION);
    public static final String IGNORE_BEAN_TOJSON_IMPORT = PropertiesUtils.getProperty(ApplicationParam.IGNORE_BEAN_TOJSON_IMPORT);
    //日期序列化返序列化所需参数
    public static final String BEAN_DATE_FORMAT_EXPRESSION = PropertiesUtils.getProperty(ApplicationParam.BEAN_DATE_FORMAT_EXPRESSION);
    public static final String BEAN_DATE_FORMAT_IMPORT = PropertiesUtils.getProperty(ApplicationParam.BEAN_DATE_FORMAT_IMPORT);
    public static final String BEAN_QUERY_FUZZY_SUFFIX = PropertiesUtils.getProperty(ApplicationParam.BEAN_QUERY_FUZZY_SUFFIX);
    public static final String BEAN_MAPPER_SUFFIX = PropertiesUtils.getProperty(ApplicationParam.BEAN_MAPPER_SUFFIX);
    public static final String BEAN_MAPPER_NAME = "Bean" + BEAN_MAPPER_SUFFIX;
    public static final String BEAN_SERVICE_SUFFIX = PropertiesUtils.getProperty(ApplicationParam.BEAN_SERVICE_SUFFIX);
    public static final String BEAN_SERVICE_NAME = "Bean" + BEAN_SERVICE_SUFFIX;
    public static final String BEAN_SERVICE_IMPL_SUFFIX = BEAN_SERVICE_SUFFIX + PropertiesUtils.getProperty(ApplicationParam.BEAN_SERVICE_IMPL_SUFFIX);
    public static final String BEAN_SERVICE_IMPL_NAME = "Bean" + BEAN_SERVICE_IMPL_SUFFIX;
    public static final String BEAN_CONTROLLER_SUFFIX = PropertiesUtils.getProperty(ApplicationParam.BEAN_CONTROLLER_SUFFIX);
    public static final String QUERY_BEAN_SCOPE_START = PropertiesUtils.getProperty(ApplicationParam.QUERY_BEAN_SCOPE_START);
    public static final String QUERY_BEAN_SCOPE_END = PropertiesUtils.getProperty(ApplicationParam.QUERY_BEAN_SCOPE_END);
    public static final String BEAN_DATE_UN_FORMAT_EXPRESSION = PropertiesUtils.getProperty(ApplicationParam.BEAN_DATE_UN_FORMAT_EXPRESSION);
    public static final String BEAN_DATE_UN_FORMAT_IMPORT = PropertiesUtils.getProperty(ApplicationParam.BEAN_DATE_UN_FORMAT_IMPORT);

    static {
        if (PropertiesUtils.containsKey(ApplicationParam.BEAN_TABLE_PREFIX_SUFFIX)) {
            String property = PropertiesUtils.getProperty(ApplicationParam.BEAN_TABLE_PREFIX_SUFFIX);
            if (property.contains("%s")) {
                BEAN_TABLE_PREFIX_SUFFIX = property;
            } else {
                logger.error("定义表名添加前后缀格式有误,需要使用[%s]定义表名占位符");
                System.exit(0);
            }

        }
        if (PropertiesUtils.containsKey(ApplicationParam.IGNORE_TABLE_PREFIX)) {
            IGNORE_TABLE_PREFIX = PropertiesUtils.getProperty(ApplicationParam.IGNORE_TABLE_PREFIX).split("\\s*,\\s*");
        }
        if (PropertiesUtils.containsKey(ApplicationParam.IGNORE_TABLE_SUFFIX)) {
            IGNORE_TABLE_SUFFIX = PropertiesUtils.getProperty(ApplicationParam.IGNORE_TABLE_SUFFIX).split("\\s*,\\s*");
        }

        if (PropertiesUtils.containsKey(ApplicationParam.IGNORE_BEAN_TOJSON_FIELD)) {
            IGNORE_BEAN_TOJSON_FIELD = PropertiesUtils.getProperty(ApplicationParam.IGNORE_BEAN_TOJSON_FIELD).split(",");
        }

        PACKAGE_BASE = PropertiesUtils.getProperty(ApplicationParam.PACKAGE_BASE);
        PACKAGE_PO = PACKAGE_BASE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_PO);
        PACKAGE_UTILS = PACKAGE_BASE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_UTILS);
        PACKAGE_QUERY = PACKAGE_BASE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_QUERY);
        PACKAGE_MAPPER = PACKAGE_BASE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_MAPPER);
        PACKAGE_BEAN_MAPPER = PACKAGE_MAPPER + "." + "bean";
        PACKAGE_SERVICE = PACKAGE_BASE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_SERVICE);
        PACKAGE_BEAN_SERVICE = PACKAGE_SERVICE + "." + "bean";
        PACKAGE_SERVICE_IMPL = PACKAGE_SERVICE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_SERVICE_IMPL);
        PACKAGE_BEAN_SERVICE_IMPL = PACKAGE_SERVICE_IMPL + "." + "bean";
        PACKAGE_CONTROLLER = PACKAGE_BASE + "." + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_CONTROLLER);

        PATH_JAVA = filePathFormatting(PropertiesUtils.getProperty(ApplicationParam.PATH_BASE) + "/" + PATH_JAVA_NAME);
        PATH_RESOURCES = filePathFormatting(PropertiesUtils.getProperty(ApplicationParam.PATH_BASE) + "/" + PATH_RESOURCES_NAME);

        PATH_JAVA_PACKAGE = filePathFormatting(PATH_JAVA + "/" + PACKAGE_BASE);
        PATH_PO = filePathFormatting(PATH_JAVA_PACKAGE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_PO));
        PATH_UTILS = filePathFormatting(PATH_JAVA_PACKAGE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_UTILS));
        PATH_QUERY = filePathFormatting(PATH_JAVA_PACKAGE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_QUERY));
        PATH_MAPPER = filePathFormatting(PATH_JAVA_PACKAGE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_MAPPER));
        PATH_BEAN_MAPPER = filePathFormatting(PATH_MAPPER + "/" + "bean");
        PATH_SERVICE = filePathFormatting(PATH_JAVA_PACKAGE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_SERVICE));
        PATH_BEAN_SERVICE = filePathFormatting(PATH_SERVICE + "/" + "bean");
        PATH_SERVICE_IMPL = filePathFormatting(PATH_SERVICE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_SERVICE_IMPL));
        PATH_BEAN_SERVICE_IMPL = filePathFormatting(PATH_SERVICE_IMPL + "/" + "bean");
        PATH_CONTROLLER = filePathFormatting(PATH_JAVA_PACKAGE + "/" + PropertiesUtils.getProperty(ApplicationParam.PACKAGE_CONTROLLER));

        PATH_MYBATIS_XML = filePathFormatting(PATH_RESOURCES + "/" + PACKAGE_BASE + "/" + BEAN_MAPPER_SUFFIX).toLowerCase();


    }


    public static void main(String[] args) {
        String[] array = {""};
        Arrays.stream(array).forEach(s -> System.out.println(s.replaceAll("\\.", "_").toUpperCase()));
    }


    /**
     * File路径格式化
     * 将[.\ = / ][/+ = /]
     *
     * @param path 为格式化路径
     * @return 格式化路径
     */
    public static String filePathFormatting(String path) {
        return Paths.get(path.replace(".", "/")).toString().replace("\\", "/");
    }


}
