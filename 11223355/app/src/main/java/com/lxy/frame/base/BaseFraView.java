package com.lxy.frame.base;

/**
 * 作者: Tany
 * 日期: 2017/9/20
 * 描述:
 */


public interface BaseFraView {
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
