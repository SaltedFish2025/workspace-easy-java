package com.easyjava.builder.build_service;

import com.easyjava.bean.*;
import com.easyjava.builder.BuildUtils;
import com.easyjava.builder.BuilderAnnotation;
import com.easyjava.builder.build_mapper.BuildMapperXml;
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

public class BuildService {
    private static Logger logger = LoggerFactory.getLogger(BuildService.class);//日志对象

    static {
        HashMap<String, String> map = new HashMap<>();
        map.put("bean_service_name", Constants.BEAN_SERVICE_NAME);
        map.put("bean_service_impl_name", Constants.BEAN_SERVICE_IMPL_NAME);
        map.put("bean_mapper_name", Constants.BEAN_MAPPER_NAME);
        map.put("package_bean_mapper", Constants.PACKAGE_BEAN_MAPPER + "." + Constants.BEAN_MAPPER_NAME);
        map.put("package_bean_service_impl", Constants.PACKAGE_BEAN_SERVICE_IMPL + "." + Constants.BEAN_SERVICE_IMPL_NAME);
        map.put("package_utils_PageResult", Constants.PACKAGE_UTILS + ".PageResult" );
        //必须使用有序集合让package保存在头部
        ArrayList<String> headDependencyList = new ArrayList<>();
        headDependencyList.add("package " + Constants.PACKAGE_BEAN_SERVICE + ";\n");
        BuildUtils.build("BeanService", Constants.BEAN_SERVICE_NAME, Constants.PATH_BEAN_SERVICE, map, headDependencyList);

        headDependencyList.clear();
        headDependencyList.add("package " + Constants.PACKAGE_UTILS + ";\n");
        BuildUtils.build("PageResult", null, Constants.PATH_UTILS, null, headDependencyList);
    }


    /**
     * 启动器
     */
    public static void execute(TableInfo tableInfo) {
        BuildServiceImpl.execute(tableInfo);
        File folder = new File(Constants.PATH_SERVICE);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File opFile = new File(folder, tableInfo.getBeanName() + Constants.BEAN_SERVICE_SUFFIX + ".java");
        writeJavaServiceCode(opFile, tableInfo);
    }

    /**
     * 写入Java Service代码
     *
     * @param opFile    Java PO文件
     * @param tableInfo 数据模型
     */
    private static void writeJavaServiceCode(File opFile, TableInfo tableInfo) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            bw.write("package " + Constants.PACKAGE_SERVICE + ";");
            bw.newLine();
            bw.newLine();
            writeImport(bw, tableInfo);//写入需要的包路径
            bw.newLine();
            bw.newLine();
            BuilderAnnotation.createClassAnnotation(bw, tableInfo.getComment() + Constants.BEAN_SERVICE_SUFFIX);//写入类注释
            bw.write("@Service");
            bw.newLine();
            String name = tableInfo.getServiceName();
            String serviceImplName = tableInfo.getServiceImplName();
            String beanServiceName = Constants.BEAN_SERVICE_NAME;
            String poName = tableInfo.getBeanName();
            String queryName = tableInfo.getBeanParamName();
            String idType = "Void";
            FieldInfo primaryKey = tableInfo.getPrimaryKey();
            if (primaryKey != null) {
                idType = primaryKey.getJavaType();
            }
            bw.write(String.format("public class %s extends %s<%s, %s, %s> implements %s {", name, beanServiceName, poName, queryName, idType, serviceImplName));
            bw.newLine();
            writeField(bw, tableInfo);//写入属性
            bw.newLine();
            writeMethod(bw, tableInfo);//写入方法
            bw.newLine();
            bw.write("}");
            bw.flush();

        } catch (Exception e) {
            logger.error("Service文件创建失败:", e);
        }
    }

    /**
     * 根据TableInfo属性写入对应import
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("import org.springframework.stereotype.Service;");
        bw.newLine();
        bw.write("import " + Constants.PACKAGE_MAPPER + "." + tableInfo.getMapperName() + ";");
        bw.newLine();
        bw.write(String.format("import %s;", Constants.PACKAGE_BEAN_SERVICE + "." + Constants.BEAN_SERVICE_NAME));
        bw.newLine();
        bw.write(String.format("import %s.%s;", Constants.PACKAGE_SERVICE_IMPL, tableInfo.getServiceImplName()));
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

    }

    /**
     * 写入对应方法信息
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    private static void writeMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.newLine();
        writeConstructorMethod(bw, tableInfo);
        bw.newLine();
        writeInsertBatch(bw, tableInfo);
        bw.newLine();
        writeSelectList(bw, tableInfo);
        bw.newLine();
        writeUpdateByCondition(bw, tableInfo);
        bw.newLine();
        writeDeleteByCondition(bw, tableInfo);
        bw.newLine();
        writeUpdateById(bw, tableInfo);
        bw.newLine();
        writeDeleteById(bw, tableInfo);
        bw.newLine();
    }

    /**
     * writeConstructorMethod
     */
    private static void writeConstructorMethod(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.newLine();
        bw.write(String.format("\tpublic %s(%s %s) {", tableInfo.getServiceName(), tableInfo.getMapperName(), tableInfo.getMapperVariableName()));
        bw.newLine();
        bw.write(String.format("\t\tsuper(%s);", tableInfo.getMapperVariableName(), BuildMapperXml.BEAN_MAPPER_CONSTANTS_LIST));
        bw.newLine();
        bw.write("\t}");
        bw.newLine();


    }


    /**
     * writeInsertBatch
     */
    private static void writeInsertBatch(BufferedWriter bw, TableInfo tableInfo) throws IOException {

    }

    /**
     * writeSelectList
     */
    private static void writeSelectList(BufferedWriter bw, TableInfo tableInfo) {
    }

    /**
     * writeUpdateByCondition
     */
    private static void writeUpdateByCondition(BufferedWriter bw, TableInfo tableInfo) {
    }

    /**
     * writeUpdateById
     */
    private static void writeUpdateById(BufferedWriter bw, TableInfo tableInfo) {
    }

    /**
     * writeDeleteByCondition
     */
    private static void writeDeleteByCondition(BufferedWriter bw, TableInfo tableInfo) {
    }

    /**
     * writeDeleteById
     */
    private static void writeDeleteById(BufferedWriter bw, TableInfo tableInfo) {
    }


}
