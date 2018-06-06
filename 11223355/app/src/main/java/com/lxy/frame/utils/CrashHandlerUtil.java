package com.lxy.frame.utils;

import android.content.Context;

/**
 * 作者: Tany
 * 日期: 2017/9/23
 * 描述: 解决未捕获异常引起的崩溃
 */


public class CrashHandlerUtil implements Thread.UncaughtExceptionHandler {

    private static CrashHandlerUtil instance;  //单例引用，这里我们做成单例的，因为我们一个应用程序里面只需要一个UncaughtExceptionHandler实例

    private CrashHandlerUtil() {
    }

    public synchronized static CrashHandlerUtil getInstance() {  //同步方法，以免单例多线程环境下出现异常
        if (instance == null) {
            instance = new CrashHandlerUtil();
        }
        return instance;
    }

    public void init(Context ctx) {  //初始化，把当前对象设置成UncaughtExceptionHandler处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        LogUtil.d("uncaughtException", "uncaughtException, thread: " + thread
                + " name: " + thread.getName() + " id: " + thread.getId() + "exception: "
                + throwable.getMessage());
    }
}
