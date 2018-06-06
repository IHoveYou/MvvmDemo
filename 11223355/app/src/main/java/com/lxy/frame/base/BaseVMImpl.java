package com.lxy.frame.base;

import android.content.Context;

/**
 * Created by Tany on 2018/3/23.
 * Desc:
 */


public class BaseVMImpl<V extends BaseView> implements BaseVM {
    protected V mView;
    protected Context mContext;

    public BaseVMImpl(V view, Context context) {
        mView = view;
        mContext = context;
    }
}
