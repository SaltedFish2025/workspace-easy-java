package com.easyjava.builder;

import com.easyjava.bean.Constants;
import com.easyjava.utils.SimpleDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 构建注释
 */
public class BuilderAnnotation {
    private static final Logger logger = LoggerFactory.getLogger(BuilderAnnotation.class);//日志对象


    /**
     * 构建类注解s
     */
    public static void createClassAnnotation(BufferedWriter bw, String classAnnotation) throws IOException {
        bw.write("/**");
        bw.newLine();
        if (classAnnotation == null || classAnnotation.isEmpty()) {
            classAnnotation = Constants.REPLACEMENT_COMMENT_IS_EMPTY;
        }
        bw.write(" * " + classAnnotation);
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * @date " + SimpleDateUtil.format(new Date(), SimpleDateUtil.DATE_TIME_FORMAT));
        bw.newLine();
        if (Constants.ANNOTATION_AUTHOR == null || Constants.ANNOTATION_AUTHOR.isEmpty()) {
            bw.write(" * @author 程序自动生成");
        } else {
            bw.write(" * @author " + Constants.ANNOTATION_AUTHOR);
        }
        bw.newLine();
        bw.write(" */");
        bw.newLine();
    }

    /**
     * 构建属性注解
     */
    public static void createFieldAnnotation(BufferedWriter bw, String... fieldAnnotation) throws IOException {
        bw.write("\t/**");
        bw.newLine();
        if (fieldAnnotation == null || fieldAnnotation.length == 0) {
            fieldAnnotation = new String[]{Constants.REPLACEMENT_COMMENT_IS_EMPTY};
        }
        for (String annotation : fieldAnnotation) {
            bw.write("\t * " + annotation);
            bw.newLine();
        }
        bw.write("\t */");
        bw.newLine();
    }


}
