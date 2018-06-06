package com.lxy.frame.bean;

import java.util.List;

/**
 * Created by LXY on 2018/6/6.
 */

public class HomeListBean {
public String title="";
public String tost="";

    public HomeListBean(String title, String tost) {
        this.title = title;
        this.tost = tost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTost() {
        return tost;
    }

    public void setTost(String tost) {
        this.tost = tost;
    }
}
