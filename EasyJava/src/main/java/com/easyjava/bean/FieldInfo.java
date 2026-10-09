package com.easyjava.bean;

/**
 * 字段信息对象
 */

public class FieldInfo {
    //sql唯一键标识
    public static final String SQL_UNIQUE_KEY_IDENTIFIER = "UNI";
    //sql主键标识
    public static final String SQL_PRIMARY_KEY_IDENTIFIER = "PRI";

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * bean属性名
     */
    private String propertyName;

    /**
     * sql字段类型
     */
    private String sqlType;
    /**
     * 对应java字段类型
     */
    private String javaType;

    /**
     * 字段备注
     */
    private String comment;
    /**
     * 字段是否为自增长类型
     */
    private Boolean isAutoIncrement;
    /**
     * 特殊键  PRI = 主键,UNI = 唯一键
     */
    private String specialKey;

    public String getFieldName() {
        return fieldName;
    }

    public String getSpecialKey() {
        return specialKey;
    }

    public void setPrimaryKey(String specialKey) {
        this.specialKey = specialKey;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    @Override
    public String toString() {
        return "FieldInfo{" + "fieldName='" + fieldName + '\'' + ", propertyName='" + propertyName + '\'' + ", sqlType='" + sqlType + '\'' + ", javaType='" + javaType + '\'' + ", comment='" + comment + '\'' + ", isAutoIncrement=" + isAutoIncrement + ", isPrimaryKey=" + specialKey + '}';
    }
}
