package com.lxy.frame.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/11/30.
 */

public class DoubleUtils {
    public static double getDouble(String str) {
        double dou = 0.0;
        try {
            dou = Double.parseDouble(str.toString().trim());
        } catch (NumberFormatException e) {
            if (getnum(str) >= 2)
                dou = Double.parseDouble(str.substring(0, str.length() - 1));
        }
        BigDecimal b = new BigDecimal(dou);
        return getDoubleXX(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public static int getnum(String str) {
        String des = ".";
        int cnt = 0;
        int offset = 0;
        while ((offset = str.indexOf(des, offset)) != -1) {
            offset = offset + des.length();
            cnt++;
        }
        return cnt;
    }

    /**
     * 取两位小数
     *
     * @return
     */
    public static double getDoubleXX(double l) {
        DecimalFormat df = new DecimalFormat("#.00");
        String bd = df.format(l);
        return Double.valueOf(bd);
    }


}
