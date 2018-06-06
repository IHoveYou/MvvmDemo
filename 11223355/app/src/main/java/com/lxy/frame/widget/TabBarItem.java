package com.lxy.frame.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by LXY on 2018/5/14.
 */

public class TabBarItem extends RelativeLayout{
    OnClickListener onClickListener;

    public TabBarItem(Context context) {
        super(context);
    }

    public TabBarItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TabBarItem(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

  public void   setTabBarItemOnClick(OnClickListener onClickListener){
     this. onClickListener=onClickListener;
  }

    @Override
    public void setOnClickListener(@Nullable final OnClickListener l) {
      this.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              if (onClickListener!=null){
                  onClickListener.onClick(v);
              }
              l.onClick(v);
          }
      },0);

    }
    public void setOnClickListener(OnClickListener onClickListener,int ps){
        super.setOnClickListener(onClickListener);
    }
}
