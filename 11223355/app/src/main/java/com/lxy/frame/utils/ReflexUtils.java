package com.lxy.frame.utils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created by LXY on 2018/6/5.
 * 反射工具类
 */

public class ReflexUtils {


    public static  <T> T get (Object mclass,String name){
        Class tab=mclass.getClass();
        try {
            Field field=tab.getDeclaredField(name);
            field.setAccessible(true);
            T object=  (T)field.get(mclass);
            return object;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  <T> T get (String classFile,String name){

        Class tab= null;
        try {
            tab = Class.forName(classFile);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Field field=tab.getDeclaredField(name);
            field.setAccessible(true);
            T object=  (T)field.get(tab);
            return object;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  void set(Object mclass,String name,Object val){
        Class tab=mclass.getClass();
        try {
            Field field=tab.getDeclaredField(name);
            field.setAccessible(true);
            field.set(name,val);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Field[] getFileds(Object mclass){
            Class tab=mclass.getClass();
            Field[] field=tab.getDeclaredFields();
            return field;
    }

    public static void setValue (Object mclass,Field field,Object val){
        try {
            field.setAccessible(true);
            field.set(mclass,val);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
//    public static  Method getMethod (Object mclass,String name){
//        Class tab=mclass.getClass();
//
//        try {
//            Method method=tab.getMethod(name,null);
//            method.setAccessible(true);
//         return   method;
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
