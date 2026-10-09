package com.easyjava.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 表信息对象
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;
    /**
     * bean名称
     */
    private String beanName;
    /**
     * 参数查询名称
     */
    private String beanParamName;
    private String beanParamVariableName;

    /**
     * mapperName
     */
    private String mapperName;
    private String mapperVariableName;

    /**
     * serviceName
     */
    private String serviceName;
    private String serviceVariableName;

    /**
     * serviceImplName
     */
    private String serviceImplName;
    /**
     * controllerName
     */
    private String controllerName;


    /**
     * 表注释
     */
    private String comment;
    /**
     * 字段信息
     */
    private List<FieldInfo> fieldInfoList = new ArrayList<>();

    /**
     * 拓展字段信息
     */
    private List<FieldInfo> componentFieldInfoList = new ArrayList<>();

    /**
     * 唯一索引集合
     */
    private Map<String, List<FieldInfo>> keyIndexMap = new LinkedHashMap<>();

    /**
     * 自增长字段
     */
    private FieldInfo autoField;

    /**
     * 主键字段
     */
    private FieldInfo primaryKey;


    /**
     * 是否有主键
     */
    private Boolean havePrimaryKey = false;
    /**
     * 是否有唯一键
     */
    private Boolean haveUniqueKey = false;

    /**
     * 是否有日期类型
     */
    private Boolean haveDate = false;
    /**
     * 是否有时间类型
     */
    private Boolean haveDateTime = false;

    /**
     * 是否有BigDecimal类型
     */
    private Boolean haveBigDecimal = false;

    /**
     * 是否有需要特定忽略的属性
     */
    private Boolean haveIgnoreField = false;

    public String getBeanParamVariableName() {
        return beanParamVariableName;
    }

    public void setBeanParamVariableName(String beanParamVariableName) {
        this.beanParamVariableName = beanParamVariableName;
    }

    public String getMapperVariableName() {
        return mapperVariableName;
    }

    public void setMapperVariableName(String mapperVariableName) {
        this.mapperVariableName = mapperVariableName;
    }

    public String getServiceVariableName() {
        return serviceVariableName;
    }

    public void setServiceVariableName(String serviceVariableName) {
        this.serviceVariableName = serviceVariableName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public Boolean getHavePrimaryKey() {
        return havePrimaryKey;
    }

    public void setHavePrimaryKey(Boolean havePrimaryKey) {
        this.havePrimaryKey = havePrimaryKey;
    }

    public Boolean getHaveUniqueKey() {
        return haveUniqueKey;
    }

    public void setHaveUniqueKey(Boolean haveUniqueKey) {
        this.haveUniqueKey = haveUniqueKey;
    }

    public FieldInfo getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(FieldInfo primaryKey) {
        this.primaryKey = primaryKey;
    }

    public FieldInfo getAutoField() {
        return autoField;
    }

    public void setAutoField(FieldInfo autoField) {
        this.autoField = autoField;
    }

    public List<FieldInfo> getComponentFieldInfoList() {
        return componentFieldInfoList;
    }

    public void setComponentFieldInfoList(List<FieldInfo> componentFieldInfoList) {
        this.componentFieldInfoList = componentFieldInfoList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanParamName() {
        return beanParamName;
    }

    public void setBeanParamName(String beanParamName) {
        this.beanParamName = beanParamName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }

    public Map<String, List<FieldInfo>> getKeyIndexMap() {
        return keyIndexMap;
    }

    public void setKeyIndexMap(Map<String, List<FieldInfo>> keyIndexMap) {
        this.keyIndexMap = keyIndexMap;
    }

    public Boolean getHaveDate() {
        return haveDate;
    }

    public void setHaveDate(Boolean haveDate) {
        this.haveDate = haveDate;
    }

    public Boolean getHaveDateTime() {
        return haveDateTime;
    }

    public void setHaveDateTime(Boolean haveDateTime) {
        this.haveDateTime = haveDateTime;
    }

    public Boolean getHaveBigDecimal() {
        return haveBigDecimal;
    }

    public void setHaveBigDecimal(Boolean haveBigDecimal) {
        this.haveBigDecimal = haveBigDecimal;
    }

    public Boolean getHaveIgnoreField() {
        return haveIgnoreField;
    }

    public void setHaveIgnoreField(Boolean haveIgnoreField) {
        this.haveIgnoreField = haveIgnoreField;
    }


}
