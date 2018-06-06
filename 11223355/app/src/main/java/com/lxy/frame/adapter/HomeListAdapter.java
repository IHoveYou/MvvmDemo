package com.lxy.frame.adapter;



import android.content.Context;

import com.lxy.frame.R;
import com.lxy.frame.base.BaseAdapter;
import com.lxy.frame.base.ViewHolder;
import com.lxy.frame.bean.HomeListBean;
import com.lxy.frame.databinding.ItemHomelistBinding;

import java.util.List;

/**
 * 万能适配器使用示例
 * Created by LXY on 2018/4/2.
 */

public class HomeListAdapter extends BaseAdapter<HomeListBean, ItemHomelistBinding> {


    public HomeListAdapter(Context context, List<HomeListBean> datas) {
        super(context, datas, R.layout.item_homelist);
    }

    @Override
    protected void onBindItem(ViewHolder holder, ItemHomelistBinding binding, HomeListBean homeListBean, int position) {
        binding.setBean(homeListBean);
        binding.executePendingBindings();
    }
}
