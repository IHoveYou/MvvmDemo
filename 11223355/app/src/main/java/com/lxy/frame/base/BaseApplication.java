package com.lxy.frame.base;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;

import com.lxy.frame.receiver.ConnectionChangeReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXY on 2018/5/10.
 */

public abstract class BaseApplication extends Application {
    private static BaseApplication application;
    public static synchronized BaseApplication getInstance() {
        return application;
    }
    /**
     * 界面集合
     */
    public static List<Activity> list = new ArrayList<Activity>();
    @Override
    public void onCreate() {
        application=this;
        /**
         * 动态注册网络监听广播
         */
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");  //添加要收到的广播
        ConnectionChangeReceiver networkChangeReceiver = new ConnectionChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
        super.onCreate();
    }
    /**
     * 退出程序
     */
    public void exitSystem() {
        /** 退出所有界面 */
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
