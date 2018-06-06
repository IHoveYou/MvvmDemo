package com.lxy.frame.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by LXY on 2018/3/27.
 */

public class DataTimerUtils {
    long time = 0;
   public boolean remove = true;
    static DataTimerUtils mDataTimerUtils;
    TimeCallback timeCallback;

    private DataTimerUtils() {

    }

    public static DataTimerUtils get() {
        if (mDataTimerUtils == null) {
            mDataTimerUtils = new DataTimerUtils();
        }
        return mDataTimerUtils;
    }

    public void start(long time) {
        this.time = time;
        if (remove) {
            remove = false;
            handler.postDelayed(task, 0);
        }
    }

    public void remove() {
        remove = true;
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    Runnable task = new Runnable() {
        @Override
        public void run() {
            // 需要做的事:发送消息
            time = time - 1;
            if (time > 0 && !remove) {
                Message message = new Message();
                message.what = 1;
                if (timeCallback != null) {
                    timeCallback.HaveInHand(time);
                }
                handler.sendMessage(message);
                handler.postDelayed(this, 1000);
            } else {
                if (timeCallback != null) {
                    timeCallback.stop();
                }
                remove();
            }
        }
    };

    public void setTimeCallback(TimeCallback timeCallback) {
        this.timeCallback = timeCallback;
    }

    public interface TimeCallback {
        void HaveInHand(long time);

        void stop();

    }
}
