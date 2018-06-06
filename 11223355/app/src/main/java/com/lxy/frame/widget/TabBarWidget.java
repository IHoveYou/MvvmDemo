package com.lxy.frame.widget;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.frame.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LXY on 2018/5/14.
 */

public class TabBarWidget extends LinearLayout{
    private  ArrayList<View> views=new ArrayList<>();
    private FragmentManager fm;
    private FragmentTransaction ff;
    private BaseActivity activity;
    private List<Fragment> fragments=new ArrayList<>();
    private int framentLayoutId;
    public TabBarWidget(Context context) {
        super(context);
    }

    public TabBarWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public TabBarWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public  void initData(BaseActivity activity, List<Fragment> fragments,int framentLayoutId){
        views.addAll(findRecyclerView(this));
        this.activity=activity;
        this.fragments=fragments;
        this.framentLayoutId=framentLayoutId;
        for (final View item:views){
            if (item instanceof TabBarItem){
                TabBarItem tabBarItem= (TabBarItem) item;
                tabBarItem.setTabBarItemOnClick(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPage(views.indexOf(item));
                    }
                });

//                tabBarItem.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            }
        }
        if (views.size()>0)
            showPage(0);
    }

    public  void initData(BaseActivity activity,int framentLayoutId,List<TabBarBean> beans){
        this.activity=activity;
        this.framentLayoutId=framentLayoutId;
        for (TabBarBean bean:beans){
            this.fragments.add(bean.getFragment());
            bean.setPage(beans.indexOf(bean));
            views.add(addItemView(bean));

        }
        if (views.size()>0){
            showItem(views.get(0));
        }
    }

    public View addItemView(final TabBarBean bean){
        TabBarItem tabBarItem=new TabBarItem(getContext());
        final    ImageView imageView=new ImageView(getContext());
        imageView.setImageResource(bean.getLiftImageid());
        final TextView textView=new TextView(getContext());
        textView.setText(bean.getTitle());
        textView.setTextSize(bean.getTextSize());
        LinearLayout.LayoutParams line= new LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        tabBarItem.setLayoutParams(line);

        RelativeLayout.LayoutParams rlp=new RelativeLayout.LayoutParams(bean.getImageWinth(),RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);//addRule参数对应RelativeLayout XML布局的属性
        tabBarItem.addView(imageView,rlp);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tabBarItem.addView(textView,layoutParams);
        bean.setImageView(imageView);
        bean.setTextView(textView);
        tabBarItem.setOnClickListener(onClickListener);
        tabBarItem.setTag(bean);
        this.addView(tabBarItem);
        return tabBarItem;
    }


    OnClickListener onClickListener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            final TabBarBean beans = (TabBarBean) v.getTag();
            showPage(beans.getPage());
            for (View view:views){
                if (view instanceof TabBarItem){
                    final TabBarBean bean = (TabBarBean) view.getTag();
                    TabBarItem tab= (TabBarItem) view;
                    if (tab!=v){
                        bean.getImageView().setImageResource(bean.getLiftImageid());
                        bean.getTextView().setTextColor(getResources().getColor(bean.getLifttextColock()));
                    }else {
                        bean.getImageView().setImageResource(bean.getDownImageid());
                        bean.getTextView().setTextColor(getResources().getColor(bean.getDowntextColock()));
                    }
                }
            }

        }
    };

   public void showItem(View v){
       final TabBarBean beans = (TabBarBean) v.getTag();
       showPage(beans.getPage());
       for (View view:views){
           if (view instanceof TabBarItem){
               final TabBarBean bean = (TabBarBean) view.getTag();
               TabBarItem tab= (TabBarItem) view;
               if (tab!=v){
                   bean.getImageView().setImageResource(bean.getLiftImageid());
                   bean.getTextView().setTextColor(getResources().getColor(bean.getLifttextColock()));
               }else {
                   bean.getImageView().setImageResource(bean.getDownImageid());
                   bean.getTextView().setTextColor(getResources().getColor(bean.getDowntextColock()));
               }
           }
       }

   }
   public void setItemView(final View view, final OnTabChangeListener onTabChangeListener){
       views.add(view);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTabChangeListener.onTabChange(v,views.indexOf(view))){
                    showPage(views.indexOf(v));
                }
            }
        });
   }
    public void setItemView(View view){
        views.add(view);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPage(views.indexOf(v));
            }
        });
    }


    /**
     * 从当前页面中查找所有的RecyclerView控件
     * @param group
     * @return
     */
    private List<TabBarItem> findRecyclerView(ViewGroup group) {
        List<TabBarItem> items=new ArrayList<>();
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof TabBarItem) {
                    items.add ((TabBarItem) child) ;
                } else if (child instanceof ViewGroup) {
                    items.addAll(findRecyclerView((ViewGroup) child));
                }
            }
        }
        return items;
    }


//返回值决定是否进行切页
    public interface OnTabChangeListener{
    boolean onTabChange(View view,int pson);
}
    public ArrayList<View> getViews() {
        return views;
    }
    public void showPage(int pson) {
        Fragment fragment=fragments.get(pson);
        fm = activity.getSupportFragmentManager();
        ff = fm.beginTransaction();
        Fragment item;
        item = fm.findFragmentByTag(pson+"");
        if (item == null) {
            item = fragment;
        }
        if (!item.isAdded()) {
            top(fm, ff);
            ff.add(framentLayoutId, item, pson+"");
            ff.show(item);
            if (ff != null)
                ff.commit();
        } else {
            top(fm, ff);
            ff.show(item).commit();
        }
    }
    public void top(FragmentManager fm, FragmentTransaction ff) {
        Fragment fragment;
        for (int i=0;i<fragments.size();i++){
            if (fm.findFragmentByTag(i+"") != null) {
                fragment = fm.findFragmentByTag(i+"");
                ff.hide(fragment);
            }
        }
    }
    public static class TabBarBean{
        int liftImageid;
        int downImageid;
        int lifttextColock;
        int downtextColock;
        String title;
        String lifturl;
        String downurl;
        Fragment fragment;
        int textSize;
        TextView textView;
        ImageView imageView;
        int page=0;
        int imageWinth=50;

        public int getImageWinth() {
            return imageWinth;
        }

        public void setImageWinth(int imageWinth) {
            this.imageWinth = imageWinth;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public int getLiftImageid() {
            return liftImageid;
        }

        public void setLiftImageid(int liftImageid) {
            this.liftImageid = liftImageid;
        }

        public int getDownImageid() {
            return downImageid;
        }

        public void setDownImageid(int downImageid) {
            this.downImageid = downImageid;
        }

        public int getLifttextColock() {
            return lifttextColock;
        }

        public void setLifttextColock(int lifttextColock) {
            this.lifttextColock = lifttextColock;
        }

        public int getDowntextColock() {
            return downtextColock;
        }

        public void setDowntextColock(int downtextColock) {
            this.downtextColock = downtextColock;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLifturl() {
            return lifturl;
        }

        public void setLifturl(String lifturl) {
            this.lifturl = lifturl;
        }

        public String getDownurl() {
            return downurl;
        }

        public void setDownurl(String downurl) {
            this.downurl = downurl;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }

        public int getTextSize() {
            return textSize;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public TabBarBean(int liftImageid, int downImageid,
                          int lifttextColock, int downtextColock,
                          String title,
                          Fragment fragment, int textSize,int imageWinth)
        {
            this.liftImageid = liftImageid;
            this.downImageid = downImageid;
            this.lifttextColock = lifttextColock;
            this.downtextColock = downtextColock;
            this.title = title;
            this.fragment = fragment;
            this.textSize = textSize;
            this.imageWinth=imageWinth;
        }
    }
}
