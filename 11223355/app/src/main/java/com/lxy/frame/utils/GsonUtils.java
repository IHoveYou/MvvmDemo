package com.lxy.frame.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

/**
 * Created by Administrator on 2017/11/30.
 */

public class GsonUtils {

    /**
     * json 转对象
     * @param json
     * @param classs
     * @param <T>
     * @return
     */
    public static <T> T getGson(String json, Class<? extends Object> classs) {
        T cla = null;
        if (StringUtil.isEmpty(json))
            return cla;
        try {
            Gson gson = new Gson();
            cla = (T) gson.fromJson(json, classs);
        } catch (JsonIOException e) {

        }
        return cla;
    }
    /**
     * 将实体类转换成json字符串对象            注意此方法需要第三方gson  jar包
     *
     * @param obj 对象
     * @return map
     */
    public static String toJson(Object obj, int method) {
        // TODO Auto-generated method stub
        if (method == 1) { //直接转
            Gson gson = new Gson();
            String obj2 = gson.toJson(obj);
            return obj2;
        } else if (method == 2) {//首字母大写
            Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            String obj2 = gson2.toJson(obj);
            return obj2;
        }
        return "";
    }

}
