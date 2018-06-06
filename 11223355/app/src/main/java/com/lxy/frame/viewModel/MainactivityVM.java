package com.lxy.frame.viewModel;

import android.content.Context;

import com.lxy.frame.base.BaseVMImpl;
import com.lxy.frame.model.MainactivityModel;
import com.lxy.frame.view.activity.MainActivity;
import com.lxy.frame.viewModel.iviewmodel.IMainactivityVM;

/**
 * Created by LXY on 2018/5/11.
 */

public class MainactivityVM extends BaseVMImpl<MainActivity> implements IMainactivityVM{
    MainactivityModel model;
    public MainactivityVM(MainActivity view, Context context) {
        super(view, context);
        model=new MainactivityModel();
    }

    @Override
    public void getName() {
        mView.setNmame(model.getName());
    }
}
