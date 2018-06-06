package com.lxy.frame.bean;

/**
 * 作者: Tany
 * 时间: 2016/8/15 11:46
 * 描述:
 */
public class NetBean {
    public int netState;//网络状态 -1 没网 0 移动网络  1 wifi

    public NetBean(int netState) {
        this.netState = netState;
    }
}
