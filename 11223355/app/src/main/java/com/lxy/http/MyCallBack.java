package com.lxy.http;


import com.lxy.frame.base.BaseBean;
import com.lxy.frame.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求拦截器
 * Created by Lxy 2017/12/14.
 * Desc:
 */

public abstract class MyCallBack<T extends BaseBean> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200) {
            if (response.body().code.equals("SUCCESS")) {
                LogUtil.i(response.body().data + "");
                onSuccess(response.body().data);
            } else {
                //错误码筛选器
                switch (response.body().code) {
                    case "":
                        break;
                }

                onFailure(response.body().msg, response.body().code);
            }
        } else {
            onFailure(response.message(), response.raw().code() + "");
        }
        onFinish();
    }


    @Override
    public void onFailure(Call<T> call, Throwable t) {
        LogUtil.i("lcc", "失败--------" + t.getMessage());
        if ("timeout".equals(t.getMessage())) {
            onFailure("请求超时,请稍后再试", "408");
        } else {
            onFailure("网络错误", "404");
        }
        onFinish();
    }

    public abstract void onSuccess(Object data);//请求成功

    public abstract void onFailure(String message, String error);//请求失败

    public abstract void onFinish();//请求完成


}
