package com.lxy.frame.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by LXY on 2018/5/17.
 */

public class BannerBean extends BmobObject {
    List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
