package com.easyjava.builder.build_service;

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

public class BuildServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(BuildServiceImpl.class);//日志对象

    static {
        HashMap<String, String> map = new HashMap<>();
        map.put("bean_service_impl_name", Constants.BEAN_SERVICE_IMPL_NAME);
        map.put("package_utils_PageResult", Constants.PACKAGE_UTILS + ".PageResult");
        //必须使用有序集合让package保存在头部
        ArrayList<String> headDependencyList = new ArrayList<>();
        headDependencyList.add("package " + Constants.PACKAGE_BEAN_SERVICE_IMPL + ";\n");
        BuildUtils.build("BeanServiceImpl", Constants.BEAN_SERVICE_IMPL_NAME, Constants.PATH_BEAN_SERVICE_IMPL, map, headDependencyList);
    }

    /**
     * 启动器
     */
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_SERVICE_IMPL);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File opFile = new File(folder, tableInfo.getBeanName() + Constants.BEAN_SERVICE_IMPL_SUFFIX + ".java");
        writeJavaServiceImplCode(opFile, tableInfo);
    }

    /**
     * 写入Java ServiceImpl代码
     *
     * @param opFile    Java PO文件
     * @param tableInfo 数据模型
     */
    private static void writeJavaServiceImplCode(File opFile, TableInfo tableInfo) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(opFile.toPath()), StandardCharsets.UTF_8))) {
            bw.write("package " + Constants.PACKAGE_SERVICE_IMPL + ";");
            bw.newLine();
            bw.newLine();
            writeImport(bw, tableInfo);//写入需要的包路径
            bw.newLine();
            bw.newLine();
            BuilderAnnotation.createClassAnnotation(bw, tableInfo.getComment() + "接口");//写入类注释
            String interfaceName = tableInfo.getServiceImplName();
            String poName = tableInfo.getBeanName();
            String queryName = tableInfo.getBeanParamName();
            String idType = "Void";
            FieldInfo primaryKey = tableInfo.getPrimaryKey();
            if (primaryKey != null) {
                idType = primaryKey.getJavaType();
            }
            bw.write(String.format("public interface %s extends %s<%s, %s, %s> {", interfaceName, Constants.BEAN_SERVICE_IMPL_NAME, poName, queryName, idType));
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
        bw.newLine();
        bw.write(String.format("import %s;", Constants.PACKAGE_BEAN_SERVICE_IMPL + "." + Constants.BEAN_SERVICE_IMPL_NAME));
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
}
