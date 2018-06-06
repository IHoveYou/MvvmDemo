package com.lxy.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Desc:网络请求
 */

public class HttpClient {
    final static String API_URL="";//请求头

    /**
     * 网络任务对象
     */
    private static NetWorkTask netWorkTask;

    /**
     * 单例模式获取网络任务实例
     *
     * @return
     */
    public static NetWorkTask getInstance() {
        if (netWorkTask == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
//                    .addInterceptor(new MyInterceptor())//拦截器
                    .retryOnConnectionFailure(true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateTypeAdapter())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            netWorkTask = retrofit.create(NetWorkTask.class);
        }
        return netWorkTask;
    }

    public interface NetWorkTask {


//        /**
//         * 初始化数据字典
//         *
//         * @return
//         */
//        @POST("p/init/index")
//        Call<BaseBean<DictionaryBean>> index();
//
//        @FormUrlEncoded
//        @POST("user/home/index")
//        Call<BaseBean<HomeBean>> getHome(@Field("page") String page, @Field("pagesize") String pagesize);


    }

}
