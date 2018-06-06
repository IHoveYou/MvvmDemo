package com.lxy.frame.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lxy.frame.R;
import com.lxy.frame.bean.NetBean;
import com.lxy.frame.utils.AppHelper;
import com.lxy.frame.utils.LogUtil;
import com.lxy.frame.utils.NetUtil;
import com.lxy.frame.utils.ToastUtils;

import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 作者: Tany
 * 日期: 2017/9/20
 * 描述:
 */


public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseVM> extends Fragment implements BaseFraView, View.OnClickListener {
    protected Context mContext;
    public RelativeLayout rl_content;
    private RelativeLayout rl_top;
    private RelativeLayout rlError;
    private RelativeLayout rlLoading;
    public int netModile;
    private ImageView ivError;
    private TextView tvErrorTitle;
    private TextView tvErrorContent;
    private TextView tvError;
    public B mBinding;
    public VM mVM=createVM();
    public View contentView;
    protected abstract VM createVM();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, null);
        rl_content = view.findViewById(R.id.rl_content_fra);
        rl_top = view.findViewById(R.id.rl_top_fra);
        rlError = view.findViewById(R.id.rl_error_fra);
        rlLoading = view.findViewById(R.id.rl_loading_fra);
        ivError = view.findViewById(R.id.iv_error);
        tvErrorTitle = view.findViewById(R.id.tv_error_title);
        tvErrorContent = view.findViewById(R.id.tv_error_content);
        tvError = view.findViewById(R.id.tv_error);
        rlError.setOnClickListener(this);
        contentView = setContentView(inflater);
        View topView = setTopView(inflater);
        if (contentView != null) {
            setContentLayout(contentView);
        }
        if (topView != null) {
            setTopLayout(topView);
        }
        createVM();
        return view;
    }


    public abstract View setTopView(LayoutInflater inflater);

    public abstract View setContentView(LayoutInflater inflater);

    /**
     * 头部区域
     */
    public void setTopLayout(View topView) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        topView.setLayoutParams(layoutParams);
        if (null != rl_top) {
            rl_top.addView(topView);
        }
    }

    /**
     * 内容区域
     */
    public void setContentLayout(View contentView) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        if (null != rl_content) {
            rl_content.addView(contentView);
            mBinding= DataBindingUtil.bind(contentView);
        }
    }


    /**
     * 当Activity初始化之后可以在这里进行一些数据的初始化操作
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            init();
        } catch (Exception e) {
            LogUtil.e("Error", e.getMessage() + "");
        }
    }

    private void init() {
        netModile = NetUtil.getNetWrokState(mContext);
        initView();
        if (netModile == -1) {
            if (isShowError()) {
//                setError(R.drawable.nonet, R.string.str_error_nonet_title, R.string.str_error_nonet_content, R.string.str_error_nonet_btn);
            } else {
                hideError();
            }
        } else {
            initData();
            if (isShowError()) {
                hideError();
            }
        }
    }

    /**
     * 是否显示无网络界面
     *
     * @return
     */
    public boolean isShowError() {
        return true;
    }

    @Override
    public void toast(String toast, String... location) {
        ToastUtils.showMessage(toast, location);
    }

    /**
     * 子类在此方法中进行界面初始化
     */
    protected abstract void initView();

    /**
     * 子类在此方法中实现数据的初始化
     */
    public abstract void initData();


    @Override
    public void showLoading() {
        rlLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        rlError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        rlError.setVisibility(View.GONE);
    }

    @Override
    public void setError(int... ids) {
        rlError.setVisibility(View.VISIBLE);
        ivError.setImageResource(ids[0]);
        tvErrorTitle.setText(AppHelper.getString(ids[1]));
        if (ids.length == 3) {
            tvErrorContent.setVisibility(View.VISIBLE);
            tvErrorContent.setText(AppHelper.getString(ids[2]));
        } else if (ids.length == 4) {
            tvErrorContent.setVisibility(View.VISIBLE);
            tvErrorContent.setText(AppHelper.getString(ids[2]));
            tvError.setVisibility(View.VISIBLE);
            tvError.setText(AppHelper.getString(ids[3]));
            tvError.setOnClickListener(this);
        }
    }

    @Override
    public void clickError() {
        netModile = NetUtil.getNetWrokState(mContext);
        if (netModile == -1) {
            toast("当前没有网络");
        } else {
            hideError();
        }
    }

    /**
     * 网络变化
     *
     * @param netBean
     */
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void netChangeBus(NetBean netBean) {
        this.netModile = netBean.netState;
        if (netModile == 1) {
            LogUtil.i("连接wifi");
            try {
                hideError();
                initData();
            } catch (Exception e) {
                LogUtil.e("错误信息", e.getMessage());
            }
        } else if (netModile == 0) {
            LogUtil.i("连接移动数据");
            try {
                hideError();
                initData();
            } catch (Exception e) {
                LogUtil.e("错误信息", e.getMessage());
            }
        } else if (netModile == -1) {
            LogUtil.i("当前没有网络");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_error:
                clickError();
                break;
        }
    }

    /**
     * 带值跳转
     *
     * @param activity
     * @param data
     */
    public void start(Class<? extends Activity> activity, Bundle data) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtra("data", data);
        startActivity(intent);
    }

    public void start(Class<? extends Activity> activity, Bundle data, int code) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtra("data", data);
        this.startActivityForResult(intent, code);
    }


    public Bundle getBundle() {
        return getArguments().getBundle("data");
    }

    /**
     * 跳转
     */
    public void start(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }
    public <T extends View> T findViewById(int id){
        return (T) contentView.findViewById(id);
    }
}
