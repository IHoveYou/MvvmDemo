package com.lxy.frame.utils;

/**
 * Created by Administrator on 2017/11/30.
 */

public class IntUtils {
    public static int getInt(String str) {
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (ClassCastException e) {
            i = 0;
        }
        return i;
    }

}
