package com.easyjava.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    /**
     * 将对象转换为 JSON 格式
     *
     * @param object 目标对象
     * @return JSON 格式字符串
     */
    public static String convertObjToJson(Object object) {
        String json = null;
        if (object != null) {
            json = JSONObject.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
        }
        return json;
    }

}
