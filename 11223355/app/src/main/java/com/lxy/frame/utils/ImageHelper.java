package com.lxy.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

/**
 * 图片加载工具类
 */

public class ImageHelper {
    private static ImageHelper helper;
    private static Glide glide;
    private static Context mContext;

    private ImageHelper() {
    }


    public static void initHelper(Context context) {

        mContext = context;
        if (helper == null || glide == null) {
            synchronized (ImageHelper.class) {
                if (helper == null)
                    helper = new ImageHelper();
                if (glide == null)
                    glide = Glide.get(context.getApplicationContext());
            }
        }
    }

    public static ImageHelper getImageHelper() {
        return helper;
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param filePath   图片的路径
     * @param iv
     * @param defaultRes 默认展示的图片
     */
    public void displayLocalImage(Context context, String filePath, ImageView iv, int defaultRes) {

        loadImage(glide.with(mContext), false, filePath, iv, defaultRes);
    }

    public void scaleImage(Context context, String imageUrl, ImageView iv) {
//        if (context != null)
        loadScaleImage(glide.with(mContext), true, imageUrl, iv, 0);
    }

    /**
     * 加载网路图片
     *
     * @param context
     * @param imageUrl   图片路径
     * @param iv
     * @param defaultRes 默认展示的图片
     */
    public void displayNetImage(Context context, String imageUrl, final ImageView iv, int defaultRes) {

        loadImage(glide.with(mContext), true, getImageUrl(imageUrl), iv, defaultRes);

    }

    public void displayLocalImage(Fragment context, String filePath, ImageView iv, int defaultRes) {
//        if (context != null)
        loadImage(glide.with(mContext), false, filePath, iv, defaultRes);
    }

    public void displayNetImage(Fragment context, String imageUrl, ImageView iv, int defaultRes) {

//        if (context != null)
        loadImage(glide.with(mContext), true, getImageUrl(imageUrl), iv, defaultRes);
    }

    public void displayLocalImage(Context context, String fiePath, ImageView iv) {
//        if (context != null)
        LogUtil.e("ImageHelp", fiePath);
        loadImage(glide.with(mContext), false, fiePath, iv, 0);
    }

    public void displayNetImage(Context context, String imageUrl, ImageView iv) {
//        if (context != null)
        loadImage(glide.with(mContext), true, getImageUrl(imageUrl), iv, 0);
    }

    public void displayLocalImage(Fragment context, String fiePath, ImageView iv) {
        if (context != null)
            loadImage(glide.with(mContext), false, fiePath, iv, 0);
    }

    public void displayNetImage(Fragment context, String imageUrl, ImageView iv) {
//        if (context != null)
        loadImage(glide.with(mContext), true, getImageUrl(imageUrl), iv, 0);

    }


    /**
     * 加载图片
     *
     * @param requestManager
     * @param isNet
     * @param path
     * @param iv
     * @param resImage
     */
    public void loadImage(RequestManager requestManager, boolean isNet, String path,
                          ImageView iv, int resImage) {
        Uri uri = null;
        if (!StringUtil.isEmpty(path)) {
            uri = isNet ? Uri.parse(path) : Uri.fromFile(new File(path));
        }

        RequestOptions options = new RequestOptions()
                .placeholder(resImage)
                .error(resImage)
                .dontAnimate()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);


        requestManager.load(uri == null ? "" : uri)
                .apply(options)
                .into(iv);
    }

    public void loadScaleImage(RequestManager requestManager, boolean isNet, String path,
                               final ImageView iv, int resImage) {
        Uri uri = null;
        if (!StringUtil.isEmpty(path)) {
            uri = isNet ? Uri.parse(path) : Uri.fromFile(new File(path));
        }

        RequestOptions options = new RequestOptions()
                .placeholder(resImage)
                .error(resImage)
                .dontAnimate()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);


        requestManager.load(uri == null ? "" : uri)
                .apply(options)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        int imageWidth = resource.getIntrinsicWidth();
                        int imageHeight = resource.getIntrinsicHeight();
                        int width = AppHelper.getDisplayWidth();
                        int height = width * imageHeight / imageWidth;
                        ViewGroup.LayoutParams para = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        para.height = height;
                        para.width = width;
                        iv.setImageDrawable(resource);
                    }
                });
    }

    /**
     * 加载图片
     *
     * @param requestManager
     * @param isNet          是否是网络图片
     * @param path           图片的路径
     * @param iv
     * @param resImage       默认展示的图片
     */
    public void loadImagFitCenter(RequestManager requestManager, boolean isNet, String path,
                                  ImageView iv, int resImage) {
        Uri uri = null;
        if (!StringUtil.isEmpty(path)) {
            uri = isNet ? Uri.parse(path) : Uri.fromFile(new File(path));
        }

        RequestOptions options = new RequestOptions()
                .placeholder(resImage)
                .fitCenter()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        requestManager.load(uri == null ? "" : uri)
                .apply(options)
                .into(iv);
    }


    public static String[] getBanb(String item) {
        if (StringUtil.isEmpty(item))
            return new String[0];
        String[] temp = item.split("[.]");
        return temp;
    }

    /**
     * 根据图片的Url返回图片的bitmap数据
     *
     * @param activity
     * @param Url      图片的路径
     * @param callBack 返回图片bitmap
     */
    public void loadingImageBitMap(Activity activity, final String Url, final getBitMap callBack) {

        glide.with(mContext)
                .asBitmap()
                .load(Url);
    }


    public interface getBitMap {
        void getBitMap(Bitmap bitmap);
    }

    public static String getImageUrl(String url) {
        if (StringUtil.isEmpty(StringUtil.getString(url, ""))) {
            return "";
        }
//        if (Patterns.WEB_URL.matcher(url).matches()|| URLUtil.isValidUrl(url)) {
//            //符合标准
//            return url;
//        } else {
//            return ApiHost.RES_URL + url;
//            //不符合标准
//        }

        if (url.startsWith("http")){
            return url;
        }else {
            return url;
        }


    }

}
