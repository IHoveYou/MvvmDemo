package com.lxy.frame.base;

import java.io.Serializable;

/**
 * 作者: Tany
 * 时间: 2016/8/9 10:05
 * 描述:实体类基类
 */
public class BaseBean<T> implements Serializable {
    public String code;
    public String msg;
    public String action;
    public T data;

}
