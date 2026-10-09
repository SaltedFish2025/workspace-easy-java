package com.easyjava.builder;


import com.easyjava.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 构建生成后基础工具
 */
public class BuildUtils {
    private static Logger logger = LoggerFactory.getLogger(BuildUtils.class);//日志对象
    private final static Pattern pattern = Pattern.compile("\\#\\{([^}]+)\\}");

    public static void execute() {
        //必须使用有序集合让package保存在头部
        ArrayList<String> headDependencyList = new ArrayList<>();
        headDependencyList.add("package " + Constants.PACKAGE_UTILS + ";\n");
        build("DateUtils", null, Constants.PATH_UTILS, null, headDependencyList);
    }

    public static void build(String fileName, String newFileName, String outPutPath, Map<String, String> replaceMap, String... headDependencyList) {
        build(fileName, newFileName, outPutPath, replaceMap, Arrays.asList(headDependencyList));
    }

    /**
     * 读取文件并构建为Java文件
     *
     * @param fileName           文件名 和 构建后的Java 名
     * @param newJavaName        java 名
     * @param outPutPath         文件路径
     * @param headDependencyList Java 头部代码[按顺序填充]
     *                           m@param replaceMap         替换对应占位符的MAP [占位符:${name}  = replaceMap.get(name) ]
     */
    public static void build(String fileName, String newJavaName, String outPutPath, Map<String, String> replaceMap, List<String> headDependencyList) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (newJavaName == null || newJavaName.isEmpty()) {
            newJavaName = fileName;
        }

        File javaFile = new File(outPutPath, newJavaName + ".java");
        String classPath = "template/" + fileName + ".txt";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(javaFile.toPath()), StandardCharsets.UTF_8)); InputStream is = BuildUtils.class.getClassLoader().getResourceAsStream(classPath); BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            for (String headDependency : headDependencyList) {
                bw.write(headDependency);
                bw.newLine();
            }
            String line = "";
            while ((line = br.readLine()) != null) {
                line = replace(line, replaceMap);
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            logger.error("类生成失败:{},失败:", fileName, e);
        }
    }


    /**
     * 文本替换
     *
     * @param line
     * @param replaceMap
     * @return
     */
    private static String replace(String line, Map<String, String> replaceMap) {
        if (line == null || replaceMap == null || replaceMap.isEmpty()) {
            return line;
        }
        StringBuffer sb = new StringBuffer("");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = replaceMap.getOrDefault(key, matcher.group(0));
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "import java.text.ParseException;";
        replace(s, null);
    }

}
