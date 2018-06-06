package com.lxy.frame.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.lxy.frame.base.BaseApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by GangWu on 2016/1/7.
 */
public final class AppHelper {

    private PackageManager pManager;

    private AppHelper() {
    }

    public static Context getContext() {
        return BaseApplication.getInstance().getApplicationContext();
    }

    /**
     * dip转换px
     */
    public static int dip2px(float dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(float px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * sp转换px
     *
     * @param spValue
     * @return
     */
    public static float sp2px(float spValue) {
        final float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    public static float getDisplayMetrics() {
        float density = getContext().getResources().getDisplayMetrics().density;
        return density;
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }


    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    /**
     * 获取textvie里字符串的长度
     *
     * @param textView
     */
    public static float getTextWidth(TextView textView) {
        if (null == textView) {
            return 0f;
        } else {
            String text = textView.getText().toString();
            if (StringUtil.isEmpty(text)) {
                return 0f;
            } else {
                Paint paint = new Paint();
                paint.setTextSize(textView.getTextSize() * textView.getScaleX());
                float width = paint.measureText(text);
                return width;
            }
        }
    }

    /**
     * @param editText
     * @return
     */
    public static String getEditTextStr(EditText editText) {
        String str = editText.getText().toString().trim();
        return str;
    }

    public static View inflateView(int resId) {
        View inflate = LayoutInflater.from(getContext()).inflate(resId, null);
        return inflate;
    }

    /**
     * @return 设备id
     */
    public static String getDeviceId() {
        if (hasDeviceIMEI()) {
            TelephonyManager systemService = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
            @SuppressLint("MissingPermission") String deviceId = systemService.getDeviceId();
            return deviceId;
        } else {
            return "";
        }
    }


    /**
     * @return 设备名
     */
    public static String getDeviceName() {
        boolean permission = isPermission("android.permission.READ_PHONE_STATE");
        if (!permission) {
            return "";
        }
        Build build = new Build();
        return build.MODEL;
    }

    /**
     * @return 获取包名
     */
    public static String getPackageName() {
        try {
            return getContext().getPackageName();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * @param view 强制隐藏软键盘
     */
    public static void hideInput(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * @param view 显示软键盘
     */
    public static void showKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * @return 软键盘是否显示
     */
    public static boolean isShowKeyBoard() {
        boolean isShow = false;
        InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            isShow = true;
        }
        return isShow;
    }

    /**
     * @return 获取屏幕宽度
     */
    public static int getDisplayWidth() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        return width;
    }

    public static int getDisplayHeight() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = manager.getDefaultDisplay().getHeight();
        return height;
    }

    public static boolean hasDeviceIMEI() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param permission 对应的权限
     * @return 判断是否有权限
     */
    public static boolean isPermission(String permission) {
        PackageManager pm = getContext().getPackageManager();
        int i = pm.checkPermission(permission, getPackageName());
        if (i == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBackground() {
        Context context = getContext();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : runningAppProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    LogUtil.i("处于后台" + appProcess.processName);
                    return true;
                } else {
                    LogUtil.i("处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * @param resId id
     * @return 资源文件的uri
     */
    public static Uri getResourceUri(int resId) {
        return Uri.parse("android.resource://" + getPackageName() + "/" + resId);
    }


    /**
     * @return 网络是否链接
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /**
     * 验证手机号
     */
    public static final boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    public static final int getStatusBar() {
        int statusBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取屏幕高度包含虚拟键
     *
     * @param activity
     * @return
     */
    public static int getDpi(Activity activity) {
        int dpi = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取虚拟功能键高度
     */
    public static int getVirtualBarHeigh(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    public static void setWindowAlpha(Activity mActivity, float alpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = alpha; //0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {
        final String CHECK_OP_NO_THROW = "checkOpNoThrow";
        final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
      /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getAlphanumString(int length) {
        Random random = new Random();
        StringBuffer buf = new StringBuffer();
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int charLength = charStr.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            buf.append(charStr.charAt(index));
        }
        return buf.toString();
    }
    public static String MD5(String strs) {
        String reStr = null;
        try {
            // 创建具有指定算法名称的信息
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(strs.getBytes());// 使用指定的字节更新摘要。
            byte ss[] = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算
            reStr = bytes2String(ss);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static String bytes2String(byte[] aa) {
        String hash = "";
        for (int i = 0; i < aa.length; i++) {// 循环数组
            int temp;
            if (aa[i] < 0) // 如果小于零，将其变为正数
                temp = 256 + aa[i];
            else
                temp = aa[i];
            if (temp < 16)
                hash += "0";
            hash += Integer.toString(temp, 16);// 转换为16进制
        }
        return hash;
    }
}