package com.lxy.frame.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by LXY on 2018/5/15.
 */

public class Image extends android.support.v7.widget.AppCompatImageView {
    public Image(Context context) {
        super(context);
    }

    public Image(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Image(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }
}
