package com.easyjava.builder.build_base;

import com.easyjava.bean.Constants;
import com.easyjava.bean.FieldInfo;
import com.easyjava.bean.Sql2JavaTypes;
import com.easyjava.bean.TableInfo;
import com.easyjava.builder.BuilderAnnotation;
import com.easyjava.utils.MyStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Java 查询bean对象
 */
public class BuildQuery {

    private static Logger logger = LoggerFactory.getLogger(BuildQuery.class);//日志对象
    public static final String PAGE_OFFSET_NAME = "pageOffset";
    public static final String PAGE_SIZE_NAME = "pageSize";
    public static final String ORDER_NAME = "order";


    static {
        File folder = new File(Constants.PATH_QUERY);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String queryName = MyStringUtils.humpNaming(Constants.BEAN_QUERY_SUFFIX);
        File queryExpansionFile = new File(folder, queryName + Constants.JAVA_SUFFIX_NAME);
        writeQueryExpansion(queryExpansionFile);
    }


    /**
     * 运行
     *
     * @param tableInfo dd
     */
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_QUERY);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File opFile = new File(folder, tableInfo.getBeanParamName() + Constants.JAVA_SUFFIX_NAME);
        writeJavaPoCode(opFile, tableInfo);
    }

    /**
     * 写入Java QUERY代码
     *
     * @param opFile    Java QUERY文件
     * @param tableInfo 数据模型
     */
    private static void writeJavaPoCode(File opFile, TableInfo tableInfo) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            bw.write("package " + Constants.PACKAGE_QUERY + ";");
            bw.newLine();
            bw.newLine();
            writeImport(bw, tableInfo);//写入需要的包路径
            bw.newLine();
            bw.newLine();
            BuilderAnnotation.createClassAnnotation(bw, tableInfo.getComment() + "查询类");//写入类注释
            bw.write("public class " + tableInfo.getBeanParamName() + " extends " + MyStringUtils.humpNaming(Constants.BEAN_QUERY_SUFFIX) + " {");
            bw.newLine();
            writeField(bw, tableInfo);//写入属性
            bw.newLine();
            writeMethod(bw, tableInfo);//写入方法
            bw.newLine();
            bw.write("}");
            bw.flush();
        } catch (Exception e) {
            logger.error("Query文件创建失败:", e);
        }
    }


    /**
     * 根据TableInfo属性写入对应import
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.newLine();
        bw.write("import java.util.List;");
        if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {//配置Date所需包
            bw.newLine();
            bw.write("import java.util.Date;");
        }
        if (tableInfo.getHaveBigDecimal()) {//配置BigDecimal所需包
            bw.newLine();
            bw.write("import java.math.BigDecimal;");
        }
        if (tableInfo.getHaveIgnoreField()) {//配置忽略属性所需包
            bw.newLine();
            bw.write(Constants.IGNORE_BEAN_TOJSON_IMPORT);
        }
    }

    /**
     * 根据TableInfo属性写入对应属性信息
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeField(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        List<FieldInfo> componentFieldInfoList = new ArrayList<>();
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            //添加 模糊以及范围查询拓展
            if (Sql2JavaTypes.JAVA_STRING_TYPE.equals(fieldInfo.getJavaType())) {
                String newName = fieldInfo.getPropertyName() + Constants.BEAN_QUERY_FUZZY_SUFFIX;
                BuilderAnnotation.createFieldAnnotation(bw, fieldInfo.getPropertyName() + "的模糊查询字段");
                bw.write("\tprivate " + fieldInfo.getJavaType() + " " + newName + ";");
                bw.newLine();
                componentFieldInfoList.add(newSimpleFieldInfo(Sql2JavaTypes.JAVA_STRING_TYPE, newName, fieldInfo.getSqlType(), fieldInfo.getFieldName()));
            } else {
                String javaType = fieldInfo.getJavaType();
                String newStartName = fieldInfo.getPropertyName() + Constants.QUERY_BEAN_SCOPE_START;
                String newEndName = fieldInfo.getPropertyName() + Constants.QUERY_BEAN_SCOPE_END;
                BuilderAnnotation.createFieldAnnotation(bw, fieldInfo.getPropertyName() + "的开始字段");
                bw.write("\tprivate " + javaType + " " + newStartName + ";");
                bw.newLine();
                BuilderAnnotation.createFieldAnnotation(bw, fieldInfo.getPropertyName() + "的结束字段");
                bw.write("\tprivate " + javaType + " " + newEndName + ";");
                bw.newLine();
                componentFieldInfoList.add(newSimpleFieldInfo(javaType, newStartName, fieldInfo.getSqlType(), fieldInfo.getFieldName()));
                componentFieldInfoList.add(newSimpleFieldInfo(javaType, newEndName, fieldInfo.getSqlType(), fieldInfo.getFieldName()));
            }
            String newName = fieldInfo.getPropertyName() + "List";
            BuilderAnnotation.createFieldAnnotation(bw, fieldInfo.getPropertyName() + "的批量查询字段");
            bw.write("\tprivate " + Sql2JavaTypes.getStrGenericList(fieldInfo.getJavaType()) + " " + newName + ";");
            bw.newLine();
            componentFieldInfoList.add(newSimpleFieldInfo(Sql2JavaTypes.getStrGenericList(fieldInfo.getJavaType()), newName, fieldInfo.getSqlType(), fieldInfo.getFieldName()));
        }
        tableInfo.getComponentFieldInfoList().addAll(componentFieldInfoList);
    }

    private static void writeQueryExpansion(File opFile) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            TableInfo tableInfo = new TableInfo();
            List<FieldInfo> componentFieldInfoList = new ArrayList<>();
            bw.write("package " + Constants.PACKAGE_QUERY + ";");
            bw.newLine();
            bw.newLine();
            BuilderAnnotation.createClassAnnotation(bw, "查询基础类");//写入类注释
            bw.write("public class " + Constants.BEAN_QUERY_SUFFIX + " {");
            bw.newLine();
            /*写入分页排序等额外拓展*/
            BuilderAnnotation.createFieldAnnotation(bw, "分页拓展-偏移");
            bw.write("\tprivate " + Sql2JavaTypes.JAVA_INT_TYPE + " " + PAGE_OFFSET_NAME + ";");
            componentFieldInfoList.add(newSimpleFieldInfo(Sql2JavaTypes.JAVA_INT_TYPE, PAGE_OFFSET_NAME, null, null));
            bw.newLine();
            BuilderAnnotation.createFieldAnnotation(bw, "分页拓展-页数");
            bw.write("\tprivate " + Sql2JavaTypes.JAVA_INT_TYPE + " " + PAGE_SIZE_NAME + ";");
            componentFieldInfoList.add(newSimpleFieldInfo(Sql2JavaTypes.JAVA_INT_TYPE, PAGE_SIZE_NAME, null, null));
            bw.newLine();
            BuilderAnnotation.createFieldAnnotation(bw, "排序拓展,需自行填写规则", "select * from 表名 order by [规则]");
            bw.write("\tprivate " + Sql2JavaTypes.JAVA_STRING_TYPE + " " + ORDER_NAME + ";");
            componentFieldInfoList.add(newSimpleFieldInfo(Sql2JavaTypes.JAVA_STRING_TYPE, ORDER_NAME, null, null));
            bw.newLine();
            tableInfo.getComponentFieldInfoList().addAll(componentFieldInfoList);
            writeMethod(bw, tableInfo);
            bw.newLine();
            bw.write("}");
            bw.flush();
        } catch (Exception e) {
            logger.error("Query文件创建失败:", e);
        }
    }


    /**
     * 根据TableInfo属性写入对应方法信息
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        for (FieldInfo fieldInfo : tableInfo.getComponentFieldInfoList()) {
            BuildBasicPackaging.writeGetMethod(bw, fieldInfo);
            BuildBasicPackaging.writeSetMethod(bw, fieldInfo);
        }
    }


    /**
     * 简单构建FieldInfo
     *
     * @param javaType     Java类型
     * @param propertyName 字段名
     * @param fieldName    数据库字段名
     * @return FieldInfo
     */
    private static FieldInfo newSimpleFieldInfo(String javaType, String propertyName, String sqlType, String fieldName) {
        FieldInfo newFieldInfo = new FieldInfo();
        newFieldInfo.setJavaType(javaType);
        newFieldInfo.setPropertyName(propertyName);
        newFieldInfo.setFieldName(fieldName);
        newFieldInfo.setSqlType(sqlType);
        return newFieldInfo;
    }


}