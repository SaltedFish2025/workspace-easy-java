package com.easyjava.builder.build_mapper;

import com.easyjava.bean.*;
import com.easyjava.builder.BuildUtils;
import com.easyjava.builder.BuilderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 构建数据库操控器
 */
public class BuildMapper {
    private static Logger logger = LoggerFactory.getLogger(BuildMapper.class);//日志对象


    static {
        //必须使用有序集合让package保存在头部
        ArrayList<String> headDependencyList = new ArrayList<>();
        headDependencyList.add("package " + Constants.PACKAGE_BEAN_MAPPER + ";\n");
        BuildUtils.build("ValidationInterceptor", null, Constants.PATH_BEAN_MAPPER, null, headDependencyList);

        HashMap<String,String> map = new HashMap<>();
        map.put("name",Constants.BEAN_MAPPER_NAME);
        BuildUtils.build("BeanMapper", Constants.BEAN_MAPPER_NAME, Constants.PATH_BEAN_MAPPER, map, headDependencyList);
    }


    /**
     * 启动器
     */
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MAPPER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File opFile = new File(folder, tableInfo.getBeanName() + Constants.BEAN_MAPPER_SUFFIX + ".java");
        writeJavaMapperCode(opFile, tableInfo);
        BuildMapperXml.execute(tableInfo);
    }

    /**
     * 写入Java Mapper代码
     *
     * @param opFile    Java PO文件
     * @param tableInfo 数据模型
     */
    private static void writeJavaMapperCode(File opFile, TableInfo tableInfo) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            bw.write("package " + Constants.PACKAGE_MAPPER + ";");
            bw.newLine();
            bw.newLine();
            writeImport(bw, tableInfo);//写入需要的包路径
            bw.newLine();
            bw.newLine();
            BuilderAnnotation.createClassAnnotation(bw, tableInfo.getComment() + Constants.BEAN_MAPPER_SUFFIX);//写入类注释
            bw.write("@Mapper");
            bw.newLine();
            String mapperName = tableInfo.getMapperName();
            String poName = tableInfo.getBeanName();
            String queryName = tableInfo.getBeanParamName();
            String idType = "Void";
            FieldInfo primaryKey = tableInfo.getPrimaryKey();
            if (primaryKey != null) {
                idType = primaryKey.getJavaType();
            }
            bw.write(String.format("public interface %s extends BeanMapper<%s, %s, %s> {", mapperName, poName, queryName, idType));
            bw.newLine();
            writeField(bw, tableInfo);//写入属性
            bw.newLine();
            writeMethod(bw, tableInfo);//写入方法
            bw.newLine();
            bw.write("}");
            bw.flush();

        } catch (Exception e) {
            logger.error("Mapper文件创建失败:", e);
        }
    }

    /**
     * 根据TableInfo属性写入对应import
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("import org.apache.ibatis.annotations.Mapper;");
        bw.newLine();
        bw.write(String.format("import %s;", Constants.PACKAGE_BEAN_MAPPER + "." + Constants.BEAN_MAPPER_NAME));
        bw.newLine();
        bw.write(String.format("import %s;", Constants.PACKAGE_PO + "." + tableInfo.getBeanName()));
        bw.newLine();
        bw.write(String.format("import %s;", Constants.PACKAGE_QUERY + "." + tableInfo.getBeanParamName()));
        bw.newLine();
        FieldInfo primaryKey = tableInfo.getPrimaryKey();
        if (primaryKey != null) {
            if (PackageConstants.containsKey(primaryKey.getJavaType())) {
                bw.write(String.format("import %s;", PackageConstants.getByPackage(primaryKey.getJavaType())));
                bw.newLine();
            }
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

        }
    }

    /**
     * 根据TableInfo属性写入对应方法信息
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        tableInfo.getKeyIndexMap().forEach((k, y) -> {


        });
    }

}
