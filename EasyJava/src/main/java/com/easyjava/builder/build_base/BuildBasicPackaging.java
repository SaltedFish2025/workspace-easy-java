package com.easyjava.builder.build_base;

import com.easyjava.bean.FieldInfo;
import com.easyjava.bean.Sql2JavaTypes;
import com.easyjava.bean.TableInfo;
import com.easyjava.utils.MyStringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public abstract class BuildBasicPackaging {

    /**
     * 根据属性写入属性的set方法
     *
     * @param bw        io写入流
     * @param fieldInfo 参数对象
     */
    public static void writeSetMethod(BufferedWriter bw, FieldInfo fieldInfo) throws IOException {
        bw.write("\tpublic void set" + MyStringUtils.humpNaming(fieldInfo.getPropertyName()) + "(" + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ") {");
        bw.newLine();
        bw.write("\t\tthis." + fieldInfo.getPropertyName() + " = " + fieldInfo.getPropertyName() + ";");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();
    }

    /**
     * 根据属性写入属性的get方法
     *
     * @param bw        io写入流
     * @param fieldInfo 参数对象
     */
    public static void writeGetMethod(BufferedWriter bw, FieldInfo fieldInfo) throws IOException {
        bw.write("\tpublic " + fieldInfo.getJavaType() + " get" + MyStringUtils.humpNaming(fieldInfo.getPropertyName()) + "() {");
        bw.newLine();
        bw.write("\t\treturn this." + fieldInfo.getPropertyName() + ";");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();
    }

    /**
     * 根据属性写入toString方法
     *
     * @param bw        io写入流
     * @param tableInfo 参数对象
     */
    public static void writeToString(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        StringBuilder sb = new StringBuilder("{");
        List<FieldInfo> fieldInfoList = tableInfo.getFieldInfoList();
        FieldInfo fieldInfo = fieldInfoList.get(0);
        sb.append(getToStringJsonFormat(fieldInfoList.get(0)));
        for (int i = 1; i < fieldInfoList.size(); i++) {
            sb.append(",").append(getToStringJsonFormat(fieldInfoList.get(i)));
        }
        sb.append("}");

        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic String toString() {");
        bw.newLine();
        bw.write("\t\treturn \"" + sb + "\";");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();
    }

    /**
     * 计算对象ToString()字符串格式
     *
     * @param fieldInfo 对象
     * @return json String格式
     */
    private static String getToStringJsonFormat(FieldInfo fieldInfo) {
        String jsonTemplate1 = "\\\"%s\\\":{\\\"value\\\":\\\"\" + %s + \"\\\",\\\"comment\\\":\\\"%s\\\"}";
        String jsonTemplate2 = "\\\"%s\\\":{\\\"value\\\":\\\"\" + %s + \"\\\"}";
        String jsonStr = "";
        String data = "this." + fieldInfo.getPropertyName();
        if (Sql2JavaTypes.JAVA_DATE_TYPE.equals(fieldInfo.getJavaType())) {
            String dateFormat = "DateUtils.DATE_TIME_FORMAT";
            if (ArrayUtils.contains(Sql2JavaTypes.SQL_DATE_TYPE, fieldInfo.getSqlType())) {
                dateFormat = "DateUtils.DATE_FORMAT";
            }
            data = "DateUtils.format(this." + fieldInfo.getPropertyName() + "," + dateFormat + ")";
        }
        if (fieldInfo.getComment().isEmpty()) {
            jsonStr = String.format(jsonTemplate2, fieldInfo.getPropertyName(), data);
        } else {
            jsonStr = String.format(jsonTemplate1, fieldInfo.getPropertyName(), data, fieldInfo.getComment());
        }
        return jsonStr;
    }

}
