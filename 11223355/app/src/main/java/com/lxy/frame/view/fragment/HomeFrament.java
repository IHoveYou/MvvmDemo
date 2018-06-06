package com.lxy.frame.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lxy.frame.R;
import com.lxy.frame.adapter.HomeListAdapter;
import com.lxy.frame.base.BaseAdapter;
import com.lxy.frame.base.BaseFragment;
import com.lxy.frame.base.BaseVM;
import com.lxy.frame.bean.BannerBean;
import com.lxy.frame.bean.HomeListBean;
import com.lxy.frame.databinding.FramentHomeBinding;
import com.lxy.frame.view.fragment.ifragment.IFrament;
import com.lxy.frame.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobACL;

/**
 * Created by LXY on 2018/5/11.
 */


public class HomeFrament extends BaseFragment<FramentHomeBinding,BaseVM> implements IFrament {
    Banner banner;
    List<String> images=new ArrayList<>();
    List<HomeListBean> data=new ArrayList<>();
    HomeListAdapter adapter;
    @Override
    protected BaseVM createVM() {
        return null;
    }

    @Override
    public View setTopView(LayoutInflater inflater) {
        return null;
    }

    @Override
    public View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frament_home,null);
    }

    @Override
    protected void initView() {
        BannerBean bean=new BannerBean();
        images.add("http://img.zcool.cn/community/0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png");
        images.add("http://img08.tooopen.com/images/20180316/tooopen_sy_201956178977.jpg");
        images.add("http://img.zcool.cn/community/010f87596f13e6a8012193a363df45.jpg@1280w_1l_2o_100sh.jpg");
        images.add("http://img05.tooopen.com/images/20150820/tooopen_sy_139205349641.jpg");
        bean.setUrls(images);
        bean.setACL(new BmobACL(){
            @Override
            public Map<String, Object> getAcl() {
                return super.getAcl();
            }
        });
        bean.setACL(new BmobACL(){


        });
        banner=mBinding.bannerHome;
        mBinding.btnThree.setOnClickListener(btnthree);
    }

    @Override
    public void initData() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        for (int i=0;i<10;i++){
            data.add(new HomeListBean("我是第"+i+"条数据","我是第"+i+"条吐司"));
        }
        adapter=new HomeListAdapter(getActivity(),data);
        mBinding.rvHomelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvHomelist.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                toast(data.get(position).getTost());
            }
        });
        mBinding.setOnclick(this);
    }
    public void btnone(View view){
        toast("我是第一个按钮点击事件");
    }
    public void btntwo(View view,String tos){
        toast(tos);
    }
    View.OnClickListener btnthree=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toast("我是第三个个按钮点击事件");
        }
    };
}
