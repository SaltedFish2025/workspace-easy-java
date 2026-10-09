package com.easyjava.builder.build_base;

import com.easyjava.bean.Constants;
import com.easyjava.bean.FieldInfo;
import com.easyjava.bean.Sql2JavaTypes;
import com.easyjava.bean.TableInfo;
import com.easyjava.builder.BuilderAnnotation;
import com.easyjava.utils.SimpleDateUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Java bean 对象
 */
public class BuildPo {

    private static Logger logger = LoggerFactory.getLogger(BuildPo.class);//日志对象


    /**
     * 运行
     *
     * @param tableInfo
     */
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_PO);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File opFile = new File(folder, tableInfo.getBeanName() + Constants.JAVA_SUFFIX_NAME);
        writeJavaPoCode(opFile, tableInfo);
    }

    /**
     * 写入Java PO代码
     *
     * @param opFile    Java PO文件
     * @param tableInfo 数据模型
     */
    private static void writeJavaPoCode(File opFile, TableInfo tableInfo) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            bw.write("package " + Constants.PACKAGE_PO + ";");
            bw.newLine();
            bw.newLine();
            writeImport(bw, tableInfo);//写入需要的包路径
            bw.newLine();
            bw.newLine();
            BuilderAnnotation.createClassAnnotation(bw, tableInfo.getComment());//写入类注释
            bw.write("public class " + tableInfo.getBeanName() + " implements Serializable {");
            bw.newLine();
            writeField(bw, tableInfo);//写入属性
            bw.newLine();
            writeMethod(bw, tableInfo);//写入方法
            bw.newLine();
            bw.write("}");
            bw.flush();
        } catch (Exception e) {
            logger.error("PO文件创建失败:", e);
        }
    }


    /**
     * 根据TableInfo属性写入对应import
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("import java.io.Serializable;");
        if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()) {//配置Date所需包
            bw.newLine();
            bw.write("import java.util.Date;");
            bw.newLine();
            bw.write(Constants.BEAN_DATE_FORMAT_IMPORT);
            bw.newLine();
            bw.write(Constants.BEAN_DATE_UN_FORMAT_IMPORT);
            bw.newLine();
            bw.write("import " + Constants.PACKAGE_UTILS + ".*;");
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
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            bw.newLine();
            BuilderAnnotation.createFieldAnnotation(bw, fieldInfo.getComment());
            String[] ignoreField = Constants.IGNORE_BEAN_TOJSON_FIELD;
            if (ignoreField != null && ArrayUtils.contains(ignoreField, fieldInfo.getFieldName())) {
                bw.write("\t" + Constants.IGNORE_BEAN_TOJSON_EXPRESSION);
                bw.newLine();
            }
            //判断是否是JavaDate类型,如果是需要添加固定注解
            if (Sql2JavaTypes.JAVA_DATE_TYPE.equals(fieldInfo.getJavaType())) {
                String dateFormat = SimpleDateUtil.DATE_TIME_FORMAT;
                if (ArrayUtils.contains(Sql2JavaTypes.SQL_DATE_TYPE, fieldInfo.getSqlType())) {
                    dateFormat = SimpleDateUtil.DATE_FORMAT;
                }
                bw.write("\t" + String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, dateFormat));
                bw.newLine();
                bw.write("\t" + String.format(Constants.BEAN_DATE_UN_FORMAT_EXPRESSION, dateFormat));
                bw.newLine();
            }
            bw.write("\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
            bw.newLine();
        }
    }

    /**
     * 根据TableInfo属性写入对应方法信息
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        for (FieldInfo fieldInfo : tableInfo.getFieldInfoList()) {
            BuildBasicPackaging.writeGetMethod(bw, fieldInfo);
            BuildBasicPackaging.writeSetMethod(bw, fieldInfo);
        }
        BuildBasicPackaging.writeToString(bw, tableInfo);
    }


}