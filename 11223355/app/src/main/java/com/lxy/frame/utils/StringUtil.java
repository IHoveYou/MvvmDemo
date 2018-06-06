package com.lxy.frame.utils;

import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 作者: GG
 * 时间: 2015/11/16 11:00
 * 简介: 返回跟字符串有关的操作
 */
public final class StringUtil {
    private StringUtil() {
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 格式化文件大小，不保留末尾的0
     */
    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    /**
     * 格式化文件大小，保留末尾的0，达到长度一致
     */
    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)，保留一位小数
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)，个位四舍五入
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)，保留两位小数
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024 / 1024 / (float) 100)) + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100) + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)，保留一位小数
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024 / 1024 / (float) 10)) + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10) + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)，个位四舍五入
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)，保留两位小数
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100) + "GB";
        }
        return size;
    }

    public static String getIntroduceStr(String sex, String job, String location) {
        StringBuilder builder = new StringBuilder();
        switch (sex) {
            case "0":
                builder.append("未知");
                break;
            case "1":
                builder.append("男");
                break;
            case "2":
                builder.append("女");
                break;
            default:
                break;
        }
        if (!isEmpty(job)) {
            builder.append(" ").append(job);
        }
        if (!isEmpty(location)) {
            builder.append(" ").append(location);
        }
        return builder.toString();
    }

    public static boolean getBoolean(String isApproval) {
        if ("yes".equals(isApproval)) {
            return true;
        }
        return false;
    }

    public static String getYesNo(boolean isApproval) {
        if (isApproval) {
            return "yes";
        }
        return "no";
    }

    public static boolean getString2Boolean(String str) {
        if ("1".equals(str)) {
            return true;
        }
        return false;
    }

    public static String getBoolean2State(boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }

    public static String[] getStrArray(String str) {
        String[] split = null;
        if (!isEmpty(str)) {
            split = str.split("\\|");
        }
        return split;
    }

    public static String getStrArr2Str(String[] arr) {
        if (null == arr) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]).append("\t\t");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String getMap2Str(HashMap<String, String> maps) {
        StringBuilder sb = new StringBuilder();
        if (maps.size() > 0) {
            for (String key : maps.keySet()) {
                String value = maps.get(key);
                sb.append(value).append("|");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 保留小数点后三位
     */
    public static double getDoubleFormatPoint3(double d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        double v = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        return v;
    }

    public static String getString(Object s, String s2) {
        if (s == null) {
            return s2;
        } else if (s instanceof String) {
            if (StringUtil.isEmpty(s.toString())) {
                return s2;
            } else {
                return s.toString();
            }
        } else {
            return s2;
        }


    }

    public static boolean isEmpty(Object s, String msg) {


        if (s == null) {
            ToastUtils.showMessage(msg);
            return true;
        }
        if (s instanceof TextView) {
            s = ((TextView) s).getText().toString();
        }
        if (isEmpty(s.toString())) {
            ToastUtils.showMessage(msg);
            return isEmpty(s.toString());
        } else {
            return isEmpty(s.toString());
        }
    }

    public static String unit(String s) {
        double d = Double.parseDouble(s);
        if (d < 10000) {
            return "" + (int) d;
        } else if (d >= 10000 && d < 100000000) {
            Double d1 = d / 10000;
            BigDecimal b = new BigDecimal(d1);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "万";
        } else if (d >= 100000000) {
            Double d1 = d / 100000000;
            BigDecimal b = new BigDecimal(d1);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "亿";
        }
        return "";
    }
}
