package com.lxy.frame.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.lxy.frame.bean.NetBean;
import com.lxy.frame.utils.LogUtil;
import com.lxy.frame.utils.NetUtil;

import de.greenrobot.event.EventBus;


/**
 * 作者:
 * 时间: 2016/8/15 10:17
 * 描述:
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWrokState =  NetUtil.getNetWrokState(context);
            // 接口回调传过去状态的类型
            LogUtil.i(netWrokState +"");
            EventBus.getDefault().post(new NetBean(netWrokState));
        }
    }
}
