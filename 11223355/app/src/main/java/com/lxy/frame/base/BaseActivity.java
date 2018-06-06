package com.lxy.frame.base;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxy.frame.R;
import com.lxy.frame.bean.NetBean;
import com.lxy.frame.utils.AppHelper;
import com.lxy.frame.utils.LogUtil;
import com.lxy.frame.utils.NetUtil;
import com.lxy.frame.utils.StatusBarUtil;
import com.lxy.frame.utils.ToastUtils;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by LXY on 2018/5/10.
 */

public abstract class BaseActivity<viewBing extends ViewDataBinding, VM extends BaseVM> extends AppCompatActivity implements View.OnClickListener, BaseView{
    protected viewBing mBindig ;
    protected  VM mVM=getVM();
    protected View contentView;
    protected abstract VM getVM();
    protected abstract @LayoutRes int contentViewId();
    protected RelativeLayout rlError;
    protected RelativeLayout rlLoading;
    protected RelativeLayout rlRoot;
    protected RelativeLayout rlContent;
    private TextView tvErrorTitle;
    private TextView tvErrorContent;
    private TextView tvError;
    public RelativeLayout rl_content;
    private RelativeLayout rl_top;

    private int endX;
    private int startX;
    private int deltaX;
    private int endY;
    private int startY;
    private int deltaY;
    private boolean isClose = true;
    private View decorView;
    private VelocityTracker mVelocityTracker;
    private ImageView ivLeft;
    private TextView tvTitle;
    private boolean leftFinish = false;
    protected TextView tvRight;
    private ViewStub vsError;
    private ViewStub vsLoading;
    public int netModile;
    protected Context mContext;


    private static BaseActivity mActivity;

    public static BaseActivity getmActivity() {
        return mActivity;
    }
    private ImageView ivError;
    public TextView tvLeft;
    public ImageView ivRight;
   //初始化之前需要的操作可重写该方法
    protected void initialization(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initialization();
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_base);
        mVelocityTracker = mVelocityTracker.obtain();
        decorView = getWindow().getDecorView();
        StatusBarUtil.setStatusColor(this);
        EventBus.getDefault().register(this);
        BaseApplication.list.add(this);//把activity加入集合，退出的时候全finish掉
        mContext = this;
        rlRoot = findViewById(R.id.rl_root);
        rl_content = findViewById(R.id.rl_content);
        rl_top = findViewById(R.id.rl_top);
        setTopLayout(R.layout.layout_title, 50);
        ivLeft = findViewById(R.id.iv_left);
        tvLeft = findViewById(R.id.tv_left);
        tvTitle = findViewById(R.id.tv_title);
        tvRight = findViewById(R.id.tv_right);
        ivRight = findViewById(R.id.iv_right);
        vsError = findViewById(R.id.vs_error);
        vsLoading = findViewById(R.id.vs_loading);
        rlContent = findViewById(R.id.rl_content);
        ivLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        setContentLayout(contentViewId());
    }
    /**
     * 头部区域
     *
     * @param resId
     * @param height
     */
    public void setTopLayout(int resId, int height) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View topView = inflater.inflate(resId, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, AppHelper.dip2px(height));
        topView.setLayoutParams(layoutParams);
        if (null != rl_top) {
            rl_top.addView(topView);
        }
    }
    /**
     * 内容区域
     *
     * @param resId
     */
    public void setContentLayout(int resId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(resId, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        if (null != rl_content) {
            rl_content.addView(contentView);
            mBindig= DataBindingUtil.bind(contentView);
        }
        try {
            init();
        } catch (Exception e) {
            LogUtil.e("Error", e.getMessage() + "");
        }
    }
    public void init() {
        netModile = NetUtil.getNetWrokState(this);
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

    /**
     * 初始化界面
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                clickIvLeft();
                break;
            case R.id.tv_right:
                clickTvRight();
                break;
            case R.id.tv_left:
                clickTvLeft();
                break;
            case R.id.iv_right:
                clickIvRight();
                break;
            case R.id.rl_error:
                clickError();
                break;
            case R.id.rl_loading:
                break;
        }
    }

    @Override
    public void clickIvLeft() {
        finish();
    }

    @Override
    public void clickIvRight() {
    }

    @Override
    public void clickTvLeft() {
        finish();
    }

    @Override
    public void clickTvRight() {
    }

    /**
     * 设置错误页面
     * 因为 错误页面有可能错误信息只有一行 还有可能没有按钮,所以用可变参数
     * ids[0]为图片id ids[1]为错误标题 ids[2]为错误内容可能没有 ids[3]为按钮的文字 可能没有
     *
     * @param ids
     */
    @Override
    public void setError(int... ids) {
        vsError.setVisibility(View.VISIBLE);
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
    public void showError() {
        try {
            vsError.inflate();     //inflate 方法只能被调用一次，
            ivError = findViewById(R.id.iv_error);
            tvErrorTitle = findViewById(R.id.tv_error_title);
            tvErrorContent = findViewById(R.id.tv_error_content);
            rlError = findViewById(R.id.rl_error);
            tvError = findViewById(R.id.tv_error);
            rlError.setOnClickListener(this);
        } catch (Exception e) {
            vsError.setVisibility(View.VISIBLE);
        } finally {
        }
    }

    @Override
    public void hideError() {
        vsError.setVisibility(View.GONE);
    }


    @Override
    public void hideLeft() {
        ivLeft.setVisibility(View.GONE);
    }

    @Override
    public void hideLeftTv() {
        tvLeft.setVisibility(View.GONE);
    }

    @Override
    public void hideRight() {
        ivRight.setVisibility(View.GONE);
    }

    @Override
    public void hideRightTv() {
        tvRight.setVisibility(View.GONE);
    }

    @Override
    public void setRight(String right) {
        tvRight.setText(right);
        tvRight.setVisibility(View.VISIBLE);
    }

    public void setRight(int right) {
        ivRight.setImageResource(right);
        ivRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLeft(int id) {
        ivLeft.setImageResource(id);
    }

    public void setLeft(String left) {
        tvLeft.setText(left);
        tvLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void showTitle() {
        rl_top.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTitle() {
        rl_top.setVisibility(View.GONE);
    }

    @Override
    public void toast(String toast, String... location) {
        ToastUtils.showMessage(toast, location);
    }

    @Override
    public void showLoading() {
//        try {
        vsLoading.inflate();     //inflate 方法只能被调用一次，
        rlLoading = findViewById(R.id.rl_loading);
        rlLoading.setOnClickListener(this);
//        } catch (Exception e) {
//            vsLoading.setVisibility(View.VISIBLE);
//        } finally {
//        }
    }

    @Override
    public void hideLoading() {
        vsLoading.setVisibility(View.GONE);
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
//            try {
            hideError();
            initData();
//            } catch (Exception e) {
//                LogUtil.e("错误信息", e.getMessage());
//            }
        } else if (netModile == 0) {
            LogUtil.i("连接移动数据");
//            try {
            hideError();
            initData();
//            } catch (Exception e) {
//                LogUtil.e("错误信息", e.getMessage());
//            }
        } else if (netModile == -1) {
            LogUtil.i("当前没有网络");
        }
    }
    /**
     * 隐藏软件盘
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /*****************************************以下是处理侧滑关闭activity***************************************************
     /**
     * 关闭activity时执行这个动画
     *
     * @param deltaX
     */
    public void closeAnimator(int deltaX) {
        if (isClose) {
            ValueAnimator animator = ValueAnimator.ofInt(deltaX, decorView.getWidth());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (Integer) animation.getAnimatedValue();
//                decorView.layout(value, 0, value + decorView.getWidth(), decorView.getHeight());
                    decorView.scrollTo(-value, 0);
                }
            });
            animator.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationRepeat(Animator arg0) {

                }

                @Override
                public void onAnimationEnd(Animator arg0) {
                    if (isClose) {
                        touchFinish();
                    }
                }

                @Override
                public void onAnimationCancel(Animator arg0) {

                }
            });
            animator.setDuration(0);
            animator.start();
        } else {
            ValueAnimator animator = ValueAnimator.ofInt(deltaX, 0);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (Integer) animation.getAnimatedValue();
//                decorView.layout(value, 0, value + decorView.getWidth(), decorView.getHeight());
                    decorView.scrollTo(-value, 10);
                }
            });
            animator.setDuration(10);
            animator.start();
        }
    }

    //下面的用于侧滑关闭Activity
    public void touchFinish() {
        super.finish();
        overridePendingTransition(R.anim.alpha_enter, R.anim.alpha_exit);
    }

    public void closeLeftFinish() {
        leftFinish = false;
    }

    //    需要测滑关闭时在打开这个注释
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!leftFinish) {
            return super.dispatchTouchEvent(ev);
        }
        mVelocityTracker.addMovement(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getRawX();
                startY = (int) ev.getRawY();
                if (startX < getWindow().getDecorView().getWidth() / 32) {
                    return true;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_MOVE:
                endX = (int) ev.getRawX();
                endY = (int) ev.getRawY();
                deltaX = endX - startX;
                deltaY = endY - startY;
                if (deltaX > deltaY && startX < getWindow().getDecorView().getWidth() / 32) {
                    decorView.scrollTo(-deltaX, 0);
                    decorView.getBackground().setColorFilter((Integer) evaluateColor((float) deltaX / (float) decorView.getWidth(), Color.BLACK, Color.TRANSPARENT), PorterDuff.Mode.SRC_OVER);
                    return true;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (-25 < xVelocity && xVelocity <= 50 && deltaX > decorView.getWidth() / 3 && startX < getWindow().getDecorView().getWidth() / 32
                        || xVelocity > 50 && startX < getWindow().getDecorView().getWidth() / 32) {
                    isClose = true;
                    closeAnimator(deltaX);
                    return true;
                } else {
                    if (deltaX > 0 && startX < getWindow().getDecorView().getWidth() / 32) {
                        isClose = false;
                        closeAnimator(deltaX);
                        return true;
                    } else {
                        if (startX < getWindow().getDecorView().getWidth() / 32) {
                            decorView.scrollTo(0, 0);
                        }
                        return super.dispatchTouchEvent(ev);
                    }
                }
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.clear();
                mVelocityTracker.recycle();
                return super.dispatchTouchEvent(ev);
        }
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if (isShouldHideInput(v, ev)) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm != null) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                }
//            }
//            return super.dispatchTouchEvent(ev);
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        if (getWindow().superDispatchTouchEvent(ev)) {
//            return true;
//        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 颜色变化过度
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    public Object evaluateColor(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (startA + (int) (fraction * (endA - startA))) << 24 |
                (startR + (int) (fraction * (endR - startR))) << 16 |
                (startG + (int) (fraction * (endG - startG))) << 8 |
                (startB + (int) (fraction * (endB - startB)));
    }
    @Override
    protected void onResume() {
        super.onResume();
        mActivity = this;
    }
}
