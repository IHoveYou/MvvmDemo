package com.lxy.frame.view.activity;



import android.os.Bundle;
import android.view.View;

import com.lxy.frame.R;
import com.lxy.frame.base.BaseActivity;
import com.lxy.frame.databinding.ActivityMainBinding;
import com.lxy.frame.utils.ReflexUtils;
import com.lxy.frame.view.activity.iactivity.IMainActivity;
import com.lxy.frame.view.fragment.HomeFrament;
import com.lxy.frame.viewModel.MainactivityVM;
import com.lxy.frame.widget.TabBarWidget;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainactivityVM> implements IMainActivity {

    @Override
    protected MainactivityVM getVM() {
        return new MainactivityVM(this,this);
    }

    @Override
    protected int contentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        List<TabBarWidget.TabBarBean >list=new ArrayList<>();
        for (int i=0;i<4;i++){
            HomeFrament fragment=new HomeFrament();
            Bundle bundle=new Bundle();
            bundle.putString("name","name"+i);
            fragment.setArguments(bundle);
            TabBarWidget.TabBarBean bean=new TabBarWidget.TabBarBean(R.mipmap.myon,R.mipmap.myup,
                    R.color.black,R.color.blue,"我的",fragment,12,60);
            list.add(bean);
        }

        mBindig.tab.initData(this,R.id.fra,list);

        ArrayList<View> views= ReflexUtils.get( "com.lxy.frame.widget.TabBarWidget","views");
        views.get(0).setVisibility(View.GONE);

    }

    @Override
    public void initData() {
        mVM.getName();
    }

    @Override
    public void setNmame(String name) {

    }
}
