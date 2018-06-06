//package com.lxy.frame.view.fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import com.lxy.frame.R;
//import com.lxy.frame.base.BaseFragment;
//import com.lxy.frame.base.BaseVM;
//import com.lxy.frame.databinding.FramentDemoBinding;
//import com.lxy.frame.view.fragment.ifragment.IFrament;
//
///**
// * Created by LXY on 2018/5/11.
// */
//
//
//public class DemoFrament2 extends BaseFragment<FramentDemoBinding,BaseVM> implements IFrament {
//    String name="";
//
//    @Override
//    protected BaseVM createVM() {
//        return null;
//    }
//
//    @Override
//    public View setTopView(LayoutInflater inflater) {
//        return null;
//    }
//
//    @Override
//    public View setContentView(LayoutInflater inflater) {
//        return inflater.inflate(R.layout.frament_home,null);
//    }
//
//    @Override
//    protected void initView() {
//        TextView view=findViewById(R.id.text);
//        name=getArguments().getString("name");
//        view.setText(name);
//    }
//
//    @Override
//    public void initData() {
//
//    }
//}
