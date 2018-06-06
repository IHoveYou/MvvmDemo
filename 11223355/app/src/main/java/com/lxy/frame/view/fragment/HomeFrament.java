package com.lxy.frame.view.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.lxy.frame.R;
import com.lxy.frame.base.BaseFragment;
import com.lxy.frame.base.BaseVM;
import com.lxy.frame.bean.BannerBean;
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
    }

    @Override
    public void initData() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}
