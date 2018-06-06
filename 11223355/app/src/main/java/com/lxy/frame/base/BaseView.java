package com.lxy.frame.base;

/**
 * 作者: Tany
 * 日期: 2017/9/18
 * 描述:
 */


public interface BaseView {
    /**
     * 点击标题左边图片(返回)
     */
    void clickIvLeft();

    /**
     * 点击右边图片
     */
    void clickIvRight();

    /**
     * 点击左边文字
     */
    void clickTvLeft();

    /**
     * 点击右边文字
     */
    void clickTvRight();

    /**
     * 隐藏返回键
     */
    void hideLeft();

    void hideLeftTv();

    void hideRight();

    void hideRightTv();

    /**
     * 设置左边图片
     *
     * @param id
     */
    void setLeft(int id);

    /**
     * 设置左边文字
     *
     * @param left
     */
    void setLeft(String left);


    /**
     * 设置标题右边的文字
     *
     * @param right
     */
    void setRight(String right);

    /**
     * 设置标题右边的图片
     *
     * @param right
     */
    void setRight(int right);

    /**
     * 设置标题文字
     */
    void setTitle(String title);

    /**
     * 显示标题
     */
    void showTitle();

    /**
     * 隐藏标题
     */
    void hideTitle();

    /**
     * toast提示
     *
     * @param toast
     */
    void toast(String toast, String... location);

    /**
     * 显示加载界面
     */
    void showLoading();

    /**
     * 隐藏加载界面
     */
    void hideLoading();

    /**
     * 显示错误界面
     */
    void showError();

    /**
     * 隐藏错误界面
     */
    void hideError();

    /**
     * 设置错误界面的内容
     *
     * @param ids
     */
    void setError(int... ids);

    /**
     * 错误页面点击按钮
     */
    void clickError();

}
