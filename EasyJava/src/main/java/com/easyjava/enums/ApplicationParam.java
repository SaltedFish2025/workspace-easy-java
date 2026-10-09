package com.easyjava.enums;

/**
 * 应用配置参数枚举
 */
public enum ApplicationParam {

    //添加表名前后缀,格式:[前缀%s后缀]
    BEAN_TABLE_PREFIX_SUFFIX("bean.table.prefix.suffix"),
    //忽略表名前缀
    IGNORE_TABLE_PREFIX("ignore.table.prefix"),
    //忽略表名后缀
    IGNORE_TABLE_SUFFIX("ignore.table.suffix"),
    //json需要忽略的属性名 逗号[,]分割
    IGNORE_BEAN_TOJSON_FIELD("ignore.bean.tojson.field"),
    //json忽略注解名
    IGNORE_BEAN_TOJSON_EXPRESSION("ignore.bean.tojson.expression"),
    //json忽略注解包路径
    IGNORE_BEAN_TOJSON_IMPORT("ignore.bean.tojson.import"),
    //日期序列化
    BEAN_DATE_FORMAT_EXPRESSION("bean.date.format.expression"),
    //日期序列化包路径
    BEAN_DATE_FORMAT_IMPORT("bean.date.format.import"),
    //日期反序列化
    BEAN_DATE_UN_FORMAT_EXPRESSION("bean.date.un.format.expression"),
    //日期反序列化包路径
    BEAN_DATE_UN_FORMAT_IMPORT("bean.date.un.format.import"),
    //Java表查询对象后缀
    BEAN_QUERY_SUFFIX("bean.query.suffix"),
    //查询对象模糊查询后缀
    BEAN_QUERY_FUZZY_SUFFIX("bean.query.fuzzy.suffix"),
    //Mapper查询对象后缀
    BEAN_MAPPER_SUFFIX("bean.mapper.suffix"),
    //Service查询对象后缀
    BEAN_SERVICE_SUFFIX("bean.service.suffix"),
    //ServiceImpl查询对象后缀
    BEAN_SERVICE_IMPL_SUFFIX("bean.service.impl.suffix"),
    //Controller查询对象后缀
    BEAN_CONTROLLER_SUFFIX("bean.controller.suffix"),

    //查询对象日期开始
    QUERY_BEAN_SCOPE_START("query.bean.scope.start"),

    //查询对象日期结尾
    QUERY_BEAN_SCOPE_END("query.bean.scope.end"),

    //*文件输出路径
    PATH_BASE("path.base"),

    //生成包名
    PACKAGE_BASE("package.base"),

    PACKAGE_PO("package.po"),

    PACKAGE_QUERY("package.query"),

    PACKAGE_UTILS("package.utils"),

    PACKAGE_MAPPER("package.mapper"),

    PACKAGE_SERVICE("package.service"),

    PACKAGE_SERVICE_IMPL("package.service.impl"),

    PACKAGE_CONTROLLER("package.controller"),

    //作者名称
    ANNOTATION_AUTHOR("annotation.author");


    private final String value;

    private ApplicationParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
