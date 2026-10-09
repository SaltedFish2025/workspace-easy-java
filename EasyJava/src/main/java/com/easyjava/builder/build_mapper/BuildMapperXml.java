package com.easyjava.builder.build_mapper;

import com.easyjava.bean.Constants;
import com.easyjava.bean.FieldInfo;
import com.easyjava.bean.Sql2JavaTypes;
import com.easyjava.bean.TableInfo;
import com.easyjava.builder.build_base.BuildQuery;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static com.easyjava.bean.FieldInfo.SQL_PRIMARY_KEY_IDENTIFIER;
import static com.easyjava.bean.FieldInfo.SQL_UNIQUE_KEY_IDENTIFIER;

/**
 * 构建数据库操控器
 */
public class BuildMapperXml {
    private static final Logger logger = LoggerFactory.getLogger(BuildMapperXml.class);//日志对象
    private final static String RESULT_TYPE_NAME = "result_type";
    private final static String EXPAND_CONDITION_QUERY = "expand_condition_query";
    private final static String EXPAND_EXPAND_FUNCTION = "expand_expand_function";
    private final static String TRIM_ALL_FIELD = "trim_all_field";
    private final static String TRIM_INSERT_FIELD_MAPPING = "trim_insert_field_mapping";
    private final static String TRIM_UPDATE_ASSIGNMENT = "trim_update_assignment";
    private final static String BEAN_MAPPER_CONSTANTS = "bean_mapper_constants";

    //大于等于
    private static final String GREATER_EQUAL_SYMBOL = "&gt;=";
    //小于等于
    private static final String LESS_EQUAL_SYMBOL = "&lt;=";
    //等于
    private static final String EQUAL_SYMBOL = "=";
    //模糊
    private static final String FUZZY_SYMBOL = "FUZZY";

    //xml配置常量名
    public static final String BEAN_MAPPER_CONSTANTS_QUERY = "query";
    public static final String BEAN_MAPPER_CONSTANTS_BEAN = "bean";
    public static final String BEAN_MAPPER_CONSTANTS_LIST = "list";
    public static final String BEAN_MAPPER_CONSTANTS_ID = "id";


    /**
     * 启动器
     */
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MYBATIS_XML);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File opFile = new File(folder, tableInfo.getBeanName() + Constants.BEAN_MAPPER_SUFFIX + Constants.XML_SUFFIX_NAME);
        writeMyBatisXMLCode(opFile, tableInfo);
    }

    /**
     * 写入MyBatis XML文件
     *
     * @param opFile    Java PO文件
     * @param tableInfo 数据模型
     */
    private static void writeMyBatisXMLCode(File opFile, TableInfo tableInfo) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            bw.newLine();
            bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
            bw.newLine();
            bw.write("<mapper namespace=\"" + Constants.PACKAGE_MAPPER + "." + tableInfo.getBeanName() + Constants.BEAN_MAPPER_SUFFIX + "\">");
            bw.newLine();
            writeTableMapping(bw, tableInfo);
            bw.newLine();
            writeMethod(bw, tableInfo);
            bw.write("</mapper>");

        } catch (Exception e) {
            logger.error("XML文件创建失败:", e);
        }
    }


    /**
     * 写入可复用常量映射
     */
    private static void writeReusableConstantMapping(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--可复用的常量绑定-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + BEAN_MAPPER_CONSTANTS + "\">");
        bw.newLine();
        bw.write("\t\t<if test=\"_parameter != null\">");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"queryKey\" value=\"@" + Constants.PACKAGE_BEAN_MAPPER + ".BeanMapper@QUERY_FIELD_NAME\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"beanKey\" value=\"@" + Constants.PACKAGE_BEAN_MAPPER + ".BeanMapper@BEAN_FIELD_NAME\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"listKey\" value=\"@" + Constants.PACKAGE_BEAN_MAPPER + ".BeanMapper@LIST_FIELD_NAME\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"idKey\" value=\"@" + Constants.PACKAGE_BEAN_MAPPER + ".BeanMapper@ID_FIELD_NAME\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"" + BEAN_MAPPER_CONSTANTS_QUERY + "\" value=\"_parameter.containsKey(queryKey) ? _parameter.get(queryKey) : null\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"" + BEAN_MAPPER_CONSTANTS_BEAN + "\" value=\"_parameter.containsKey(beanKey) ? _parameter.get(beanKey) : null\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"" + BEAN_MAPPER_CONSTANTS_LIST + "\" value=\"_parameter.containsKey(listKey) ? _parameter.get(listKey) : null\"/>");
        bw.newLine();
        bw.write("\t\t\t<bind name=\"" + BEAN_MAPPER_CONSTANTS_ID + "\" value=\"_parameter.containsKey(idKey) ? _parameter.get(idKey) : null\"/>");
        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }


    /**
     * 写入表映射
     */
    private static void writeTableMapping(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--实体类映射-->");
        bw.newLine();
        bw.write("\t<resultMap id=\"" + RESULT_TYPE_NAME + "\" type=\"" + Constants.PACKAGE_PO + "." + tableInfo.getBeanName() + "\">");
        bw.newLine();
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            if (fieldInfo.getFieldName() != null) {
                bw.write("\t\t<!--" + fieldInfo.getComment() + "-->");
                bw.newLine();
                if (SQL_PRIMARY_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey())) {
                    bw.write("\t\t<id column=\"" + fieldInfo.getFieldName() + "\" property=\"" + fieldInfo.getPropertyName() + "\"/>");
                    bw.newLine();
                } else {
                    bw.write("\t\t<result column=\"" + fieldInfo.getFieldName() + "\" property=\"" + fieldInfo.getPropertyName() + "\"/>");
                    bw.newLine();
                }
            }
        }
        bw.write("\t</resultMap>");
        bw.newLine();
    }


    /**
     * 写入拓展条件查询
     */
    private static void writeExpandQueryConditions(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--拓展条件查询-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + EXPAND_CONDITION_QUERY + "\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\t<trim prefixOverrides=\"and\">");
        bw.newLine();
        for (FieldInfo fieldInfo : tableInfo.getComponentFieldInfoList()) { //拓展字段条件添加
            if (fieldInfo.getFieldName() != null) {
                String propertyName = fieldInfo.getPropertyName();
                String javaType = fieldInfo.getJavaType();
                String fieldName = fieldInfo.getFieldName();
                String sqlType = fieldInfo.getSqlType();
                bw.write("\t\t\t<if test=\"" + buildIfCondition(propertyName, javaType) + "\">");
                bw.newLine();
                if (fieldInfo.getJavaType().contains("<") && fieldInfo.getJavaType().equals(Sql2JavaTypes.JAVA_STRING_TYPE)) {
                    bw.write("\t\t\t\t" + buildSqlResult(fieldName, propertyName, javaType, sqlType, FUZZY_SYMBOL));
                } else {
                    if (StringUtils.endsWith(propertyName, Constants.QUERY_BEAN_SCOPE_START)) {
                        bw.write("\t\t\t\t" + buildSqlResult(fieldName, propertyName, javaType, sqlType, GREATER_EQUAL_SYMBOL));
                    } else {
                        bw.write("\t\t\t\t" + buildSqlResult(fieldName, propertyName, javaType, sqlType, LESS_EQUAL_SYMBOL));
                    }
                }
                bw.newLine();
                bw.write("\t\t\t</if>");
                bw.newLine();
            }
        }
        bw.write("\t\t</trim>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }

    /**
     * 写入拓展功能
     */
    private static void writeExpandFunction(BufferedWriter bw) throws IOException {
        bw.write("\t<!--查询拓展功能-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + EXPAND_EXPAND_FUNCTION + "\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\t<if test=\"" + buildIfCondition(BuildQuery.ORDER_NAME, Sql2JavaTypes.JAVA_STRING_TYPE) + "\">");
        bw.newLine();
        bw.write("\t\t\torder by ${" + BEAN_MAPPER_CONSTANTS_QUERY + "." + BuildQuery.ORDER_NAME + "}");
        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write(String.format("\t\t<if test=\"%s.%s != null and %s.%s != null\">", BEAN_MAPPER_CONSTANTS_QUERY, BuildQuery.PAGE_OFFSET_NAME, BEAN_MAPPER_CONSTANTS_QUERY, BuildQuery.PAGE_SIZE_NAME));
        bw.newLine();
        bw.write(String.format("\t\t\tlimit #{%s.%s},#{%s.%s}", BEAN_MAPPER_CONSTANTS_QUERY, BuildQuery.PAGE_OFFSET_NAME, BEAN_MAPPER_CONSTANTS_QUERY, BuildQuery.PAGE_SIZE_NAME));
        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }


    /**
     * 构建 <if test="..."> 表达式
     */
    private static String buildIfCondition(String propertyName, String javaType) {
        //判断是否是List类型
        if (javaType.contains("<")) {
            return String.format("%s.%s != null and %s.%s.size() > 0 ", BEAN_MAPPER_CONSTANTS_QUERY, propertyName, BEAN_MAPPER_CONSTANTS_QUERY, propertyName);
        }
        //判断是否是字符串类型
        if (Sql2JavaTypes.JAVA_STRING_TYPE.equals(javaType)) {
            return String.format("%s.%s != null and %s.%s != ''", BEAN_MAPPER_CONSTANTS_QUERY, propertyName, BEAN_MAPPER_CONSTANTS_QUERY, propertyName);
        }
        // 其余类型（包括 Date、Integer 等）只判断 null
        return String.format("%s.%s != null", BEAN_MAPPER_CONSTANTS_QUERY, propertyName);
    }

    /**
     * 构建 SQL 赋值/比较表达式
     */
    private static String buildSqlResult(String fieldName, String propertyName, String javaType, String sqlType, String operator) {
        if (javaType.contains("<")) {
            return String.format("and %s IN \n\t\t\t\t<foreach collection=\"%s.%s\" item=\"id\" open=\"(\" separator=\",\" close=\")\">\n \t\t\t\t\t#{id}\n\t\t\t\t</foreach>", fieldName, BEAN_MAPPER_CONSTANTS_QUERY, propertyName);
        } else if (Sql2JavaTypes.JAVA_DATE_TYPE.equals(javaType)) {
            // 根据数据库实际类型决定 jdbcType
            String jdbcType = ArrayUtils.contains(Sql2JavaTypes.SQL_DATE_TYPE, sqlType) ? "DATE" : "TIMESTAMP";
            return String.format("and %s %s #{%s.%s, jdbcType=%s}", fieldName, operator, BEAN_MAPPER_CONSTANTS_QUERY, propertyName, jdbcType);
        } else if (FUZZY_SYMBOL.equals(operator) && Sql2JavaTypes.JAVA_STRING_TYPE.equals(javaType)) {
            return String.format("and %s like concat('%%',#{%s.%s},'%%')", fieldName, BEAN_MAPPER_CONSTANTS_QUERY, propertyName);
        }
        // 其他类型
        return String.format("and %s %s #{%s.%s}", fieldName, operator, BEAN_MAPPER_CONSTANTS_QUERY, propertyName);
    }


    /**
     * 根据TableInfo属性写入对应方法信息
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!-- __________________________________________段落sql__________________________________________ -->");
        bw.newLine();
        writeReusableConstantMapping(bw, tableInfo);
        bw.newLine();
        //段落sql
        writeExpandQueryConditions(bw, tableInfo);
        bw.newLine();
        writeExpandFunction(bw);
        bw.newLine();
        writeTrimAssignmentMapping(bw, tableInfo);
        bw.newLine();
        writeTrimInsertField(bw, tableInfo);
        bw.newLine();
        writeTrimInsertFieldMapping(bw, tableInfo);
        bw.newLine();
        bw.newLine();
        bw.write("\t<!-- __________________________________________select__________________________________________ -->");
        bw.newLine();
        //主sql
        writeSelectListMethod(bw, tableInfo);
        bw.newLine();
        writeSelectCountMethod(bw, tableInfo);
        bw.newLine();
        bw.write("\t<!-- __________________________________________insert__________________________________________ -->");
        bw.newLine();
        writeInsertMethod(bw, tableInfo);
        bw.newLine();
        writeInsertBatchMethod(bw, tableInfo);
        bw.newLine();
        writeInsertOrUpdateMethod(bw, tableInfo);
        bw.newLine();
        bw.write("\t<!-- __________________________________________update__________________________________________ -->");
        bw.newLine();
        writeUpdateByConditionMethod(bw, tableInfo);
        bw.newLine();
        writeUpdateByIdMethod(bw, tableInfo);
        bw.newLine();
        bw.write("\t<!-- __________________________________________delete__________________________________________ -->");
        bw.newLine();
        writeDeleteByConditionMethod(bw, tableInfo);
        bw.newLine();
        writeDeleteByIdMethod(bw, tableInfo);
        bw.newLine();
    }

    /**
     * 写入 selectList 方法
     */
    public static void writeSelectListMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--根据条件集查询结果集-->");
        bw.newLine();
        bw.write("\t<select id=\"selectList\" resultMap=\"" + RESULT_TYPE_NAME + "\" parameterType=\"" + Constants.PACKAGE_QUERY + "." + tableInfo.getBeanParamName() + "\">");
        bw.newLine();

        bw.write("\t\tselect ");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_ALL_FIELD + "\"/>");
        bw.newLine();
        bw.write("\t\tfrom " + tableInfo.getTableName());
        bw.newLine();
        bw.write("\t\t<where>");
        bw.newLine();
        bw.write("\t\t\t<include refid=\"" + EXPAND_CONDITION_QUERY + "\"/>");
        bw.newLine();
        bw.write("\t\t</where>");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + EXPAND_EXPAND_FUNCTION + "\"/>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
    }

    /**
     * 写入 selectCount 方法
     */
    public static void writeSelectCountMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--根据条件集查询总数-->");
        bw.newLine();
        bw.write("\t<select id=\"selectCount\" resultType=\"java.lang.Integer\" parameterType=\"" + Constants.PACKAGE_QUERY + "." + tableInfo.getBeanParamName() + "\">");
        bw.newLine();
        bw.write("\t\tselect count(1) from " + tableInfo.getTableName());
        bw.newLine();
        bw.write("\t\t<where>");
        bw.newLine();
        bw.write("\t\t\t<include refid=\"" + EXPAND_CONDITION_QUERY + "\"/>");
        bw.newLine();
        bw.write("\t\t</where>");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
    }


    /**
     * 写入 Insert 方法
     */
    public static void writeInsertMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--根据数据集插入数据-->");
        bw.newLine();
        String keyProperty = "";
        if (tableInfo.getAutoField() != null) {
            FieldInfo autoField = tableInfo.getAutoField();
            keyProperty = "useGeneratedKeys=\"true\" keyProperty=\"" + autoField.getPropertyName() + "\"";
        }
        bw.write(String.format("\t<insert id=\"insert\" parameterType=\"%s\" %s>", Constants.PACKAGE_PO + "." + tableInfo.getBeanName(), keyProperty));
        bw.newLine();
        bw.write("\t\tinsert into " + tableInfo.getTableName() + " ( ");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_ALL_FIELD + "\"/>");
        bw.newLine();
        bw.write("\t\t) values ");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_INSERT_FIELD_MAPPING + "\"/>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
    }

    /**
     * 写入 InsertBatch 方法
     */
    public static void writeInsertBatchMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--根据数据集批量插入数据-->");
        bw.newLine();
        String keyProperty = "";
        if (tableInfo.getAutoField() != null) {
            FieldInfo autoField = tableInfo.getAutoField();
            keyProperty = "useGeneratedKeys=\"true\" keyProperty=\"" + autoField.getPropertyName() + "\"";
        }
        bw.write(String.format("\t<insert id=\"insertBatch\" %s>", keyProperty));
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\tinsert into " + tableInfo.getTableName() + " ( ");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_ALL_FIELD + "\"/>");
        bw.newLine();
        bw.write("\t\t) values ");
        bw.newLine();
        bw.write("\t\t<foreach collection=\"" + BEAN_MAPPER_CONSTANTS_LIST + "\" item=\"" + BEAN_MAPPER_CONSTANTS_BEAN + "\" separator=\",\" >");
        bw.newLine();
        bw.write("\t\t\t<include refid=\"" + TRIM_INSERT_FIELD_MAPPING + "\"/>");
        bw.newLine();
        bw.write("\t\t</foreach>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
    }

    /**
     * 写入 InsertOrUpdate 方法
     */
    public static void writeInsertOrUpdateMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        if (!tableInfo.getHaveUniqueKey() && !tableInfo.getHavePrimaryKey()) {
            return;
        }
        bw.write("\t<!--根据数据集插入数据或修改[需有主键或唯一键]-->");
        bw.newLine();
        String keyProperty = "";
        if (tableInfo.getAutoField() != null) {
            FieldInfo autoField = tableInfo.getAutoField();
            keyProperty = "useGeneratedKeys=\"true\" keyProperty=\"" + autoField.getPropertyName() + "\"";
        }
        bw.write(String.format("\t<insert id=\"insertOrUpdate\" parameterType=\"%s\" %s>", Constants.PACKAGE_PO + "." + tableInfo.getBeanName(), keyProperty));
        bw.newLine();
        bw.write("\t\tinsert into " + tableInfo.getTableName() + " ( ");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_ALL_FIELD + "\"/>");
        bw.newLine();
        bw.write("\t\t) values ");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_INSERT_FIELD_MAPPING + "\"/>");
        bw.newLine();
        bw.write("\t\ton duplicate key update");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_UPDATE_ASSIGNMENT + "\"/>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
    }


    /**
     * 写入 UpdateByCondition 方法
     */
    public static void writeUpdateByConditionMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--根据条件集更新数据集[谨慎使用]-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByCondition\">");
        bw.newLine();
        bw.write("\t\tupdate " + tableInfo.getTableName() + " set");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_UPDATE_ASSIGNMENT + "\"/>");
        bw.newLine();
        bw.write("\t\twhere");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + EXPAND_CONDITION_QUERY + "\"/>");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
    }

    /**
     * 写入 UpdateById 方法
     */
    public static void writeUpdateByIdMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        FieldInfo primaryKey = tableInfo.getPrimaryKey();
        if (primaryKey == null) {
            return;
        }
        bw.write("\t<!--根据id更新数据集-->");
        bw.newLine();
        bw.write("\t<update id=\"updateById\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\tupdate " + tableInfo.getTableName() + " set");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + TRIM_UPDATE_ASSIGNMENT + "\"/>");
        bw.newLine();
        bw.write("\t\twhere " + primaryKey.getFieldName() + " = #{" + BEAN_MAPPER_CONSTANTS_ID + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
    }


    /**
     * 写入 DeleteByCondition 方法
     */
    public static void writeDeleteByConditionMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--根据条件集删除[谨慎使用]-->");
        bw.newLine();
        bw.write("\t<delete id=\"deleteByCondition\">");
        bw.newLine();
        bw.write("\t\tdelete from " + tableInfo.getTableName());
        bw.newLine();
        bw.write("\t\twhere");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + EXPAND_CONDITION_QUERY + "\"/>");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
    }

    /**
     * 写入 DeleteById 方法
     */
    public static void writeDeleteByIdMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        FieldInfo primaryKey = tableInfo.getPrimaryKey();
        if (primaryKey == null) {
            return;
        }
        bw.write("\t<!--根据id删除数据集-->");
        bw.newLine();
        bw.write("\t<delete id=\"deleteById\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\tdelete");
        bw.newLine();
        bw.write("\t\tfrom " + tableInfo.getTableName());
        bw.newLine();
        bw.write("\t\twhere " + primaryKey.getFieldName() + " in ");
        bw.newLine();
        bw.write("\t\t<foreach collection=\"" + BEAN_MAPPER_CONSTANTS_LIST + "\" item=\"id\" open=\"(\" separator=\",\" close=\")\">");
        bw.newLine();
        bw.write("\t\t\t#{id}");
        bw.newLine();
        bw.write("\t\t</foreach>");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
    }


    /**
     * 写入属性或字段映射
     */
    private static void writeTrimInsertField(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--裁剪insert字段-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + TRIM_ALL_FIELD + "\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\t<trim suffixOverrides=\",\">");
        bw.newLine();
        writeAttributeMapping(bw, tableInfo, 1);
        bw.write("\t\t</trim>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }

    /**
     * 写入属性或字段映射
     */
    private static void writeTrimInsertFieldMapping(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--裁剪字段映射-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + TRIM_INSERT_FIELD_MAPPING + "\">");
        bw.newLine();
        bw.write("\t\t<if test=\"!_parameter.containsKey(LIST_KEY)\">");
        bw.newLine();
        bw.write("\t\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write("\t\t<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        bw.newLine();
        writeAttributeMapping(bw, tableInfo, 2);
        bw.write("\t\t</trim>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }

    /**
     * 写入裁剪字段赋值映射
     */
    private static void writeTrimAssignmentMapping(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("\t<!--裁剪字段赋值映射-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + TRIM_UPDATE_ASSIGNMENT + "\">");
        bw.newLine();
        bw.write("\t\t<include refid=\"" + BEAN_MAPPER_CONSTANTS + "\"/>");
        bw.newLine();
        bw.write("\t\t<trim suffixOverrides=\",\">");
        bw.newLine();
        writeAttributeMapping(bw, tableInfo, 3);
        FieldInfo insuranceFieldInfo = null;
        if (tableInfo.getPrimaryKey() != null) {
            insuranceFieldInfo = tableInfo.getPrimaryKey();
        } else {
            for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
                if (FieldInfo.SQL_UNIQUE_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey())) {
                    insuranceFieldInfo = fieldInfo;
                    break;
                }
            }
        }
        if (insuranceFieldInfo != null) {
            bw.write(String.format("\t\t\t%s=%s", insuranceFieldInfo.getFieldName(), insuranceFieldInfo.getFieldName()));
            bw.newLine();
        }
        bw.write("\t\t</trim>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
    }

    /**
     * 写入属性或字段,赋值对应的映射
     *
     * @param type 类型 [1:属性,2:字段,3:赋值]
     */
    private static void writeAttributeMapping2(BufferedWriter bw, TableInfo tableInfo, int type) throws IOException {
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            //赋值的情况下严禁修改主键和唯一键
            if (type == 3 && (SQL_PRIMARY_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey()) || SQL_UNIQUE_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey()))) {
                continue;
            }
            bw.write(String.format("\t\t\t<if test=\"%s.%s != null\">", BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
            if (type == 1) {
                bw.write(String.format("%s,", fieldInfo.getFieldName()));
            } else if (type == 2) {
                bw.write(String.format("#{%s.%s},", BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
            } else {
                bw.write(String.format("%s=#{%s.%s},", fieldInfo.getFieldName(), BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
            }
            bw.write("</if>");
            bw.newLine();
        }
    }

    /**
     * 写入属性或字段,赋值对应的映射
     *
     * @param type 类型 [1:属性,2:字段,3:赋值]
     */
    private static void writeAttributeMapping(BufferedWriter bw, TableInfo tableInfo, int type) throws IOException {
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            //赋值的情况下严禁修改主键和唯一键
            if (type == 3 && (SQL_PRIMARY_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey()) || SQL_UNIQUE_KEY_IDENTIFIER.equals(fieldInfo.getSpecialKey()))) {
                continue;
            }
            if (type == 1) {
                bw.write(String.format("\t\t\t%s,", fieldInfo.getFieldName()));
            } else if (type == 2) {
                bw.write("\t\t\t<choose>");
                bw.newLine();
                bw.write(String.format("\t\t\t\t<when test=\"%s.%s != null\">", BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
                bw.write(String.format("#{%s.%s},", BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
                bw.write("</when>");
                bw.newLine();
                bw.write("\t\t\t\t<otherwise>default,</otherwise>");
                bw.newLine();
                bw.write("\t\t\t</choose>");
            } else {
                bw.write(String.format("\t\t\t<if test=\"%s.%s != null\">", BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
                bw.write(String.format("%s=#{%s.%s},", fieldInfo.getFieldName(), BEAN_MAPPER_CONSTANTS_BEAN, fieldInfo.getPropertyName()));
                bw.write("</if>");
            }
            bw.newLine();
        }
    }
}

