package com.lxy.frame.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxy.frame.R;
import com.lxy.frame.base.BaseApplication;


/**
 * 作者: Tany
 * 时间: 2016/8/8 17:19
 * 描述:
 */
public class ToastUtils {
    public static void showMessage(String toastContent, String... location) {//最后个参数 top顶部 bottom底部 默认中间
        LayoutInflater inflater = LayoutInflater.from(BaseApplication.getInstance().getApplicationContext());
        // 根据指定的布局文件创建一个具有层级关系的View对象
        View layout = inflater.inflate(R.layout.layout_toast, null);
        LinearLayout root = (LinearLayout) layout
                .findViewById(R.id.toast_layout_root);
        root.getBackground().setAlpha(200);// 0~255透明度值

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(toastContent);

        Toast toast = new Toast(BaseApplication.getInstance().getApplicationContext());
        // 设置Toast的位置
        if (location.length == 0) {
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        } else {
            if ("top".equals(location[0])) {
                toast.setGravity(Gravity.TOP, 0, 250);
            } else if ("bottom".equals(location[0])) {
                toast.setGravity(Gravity.BOTTOM, 0, 250);
            }
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        // 让Toast显示为我们自定义的样子
        toast.setView(layout);
        toast.show();
    }

}
