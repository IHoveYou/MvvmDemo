//package com.lxy.http;
//
//
//import com.guangmo.stockopt.utils.Constant;
//import com.guangmo.stockopt.utils.DateUtil;
//import com.guangmo.stockopt.utils.LogUtil;
//import com.guangmo.stockopt.utils.StringUtil;
//
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Set;
//
//import okhttp3.FormBody;
//import okhttp3.Interceptor;
//import okhttp3.MediaType;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okio.Buffer;
//
///**
// * Created by tyy on 2018/3/26.
// * Desc:
// */
//public class MyInterceptor implements Interceptor {
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Request.Builder requestBuilder = request.newBuilder();
//        Buffer buffer = new Buffer();
//        request.body().writeTo(buffer);
//        HashMap<String, String> hashMap = new HashMap<>();
//        String postBodyString = buffer.readUtf8();
//        LogUtil.i("参数" + postBodyString);
//        String signStr = "";
//        if (request.body() instanceof FormBody) {
//            FormBody oldFormBody = (FormBody) request.body();
//            for (int i = 0; i < oldFormBody.size(); i++) {
//                if (StringUtil.isEmpty(oldFormBody.encodedValue(i))) {
//                    hashMap.put(URLDecoder.decode(oldFormBody.encodedName(i)), "");
//                } else {
//                    hashMap.put(URLDecoder.decode(oldFormBody.encodedName(i)), URLDecoder.decode(oldFormBody.encodedValue(i)).replace("%2C", ",").replace("%7C", "|"));
//                }
//            }
//        }
//        hashMap.put("client_type", "3");
//        postBodyString = URLDecoder.decode(postBodyString);
//        Set<String> strings = hashMap.keySet();
//        String[] str = strings.toArray(new String[]{});
//        Arrays.sort(str);                                                                   //排序
//        for (int i = 0; i < str.length; i++) {
//            if (i == str.length - 1) {
//                signStr = signStr + str[i] + "=" + hashMap.get(str[i]);
//            } else {
//                signStr = signStr + str[i] + "=" + hashMap.get(str[i]) + "&";
//            }
//        }
//        String str2 = signStr + "&apikey=" + Constant.APIKEY + "&timestamp=" + DateUtil.getTime();//排完序拼接参数
//        LogUtil.i(str2);
//        str2 = str2.replace(" ", "");
//        String str3 = toURLEncoded(str2);
//        LogUtil.i(str3);
//        String str4 = MD5(str3);
//        String postBody = postBodyString + "&client_type=3" + "&timestamp=" + DateUtil.getTime() + "&sign=" + str4;
//        request = requestBuilder
//                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
//                        postBody))
//                .build();
//        return chain.proceed(request);
//    }
//
//    /**
//     * 使用URLEncoded转码
//     *
//     * @param paramString
//     * @return
//     */
//    public static String toURLEncoded(String paramString) {
//        if (paramString == null || paramString.equals("")) {
//            return "";
//        }
//        try {
//            String str = new String(paramString.getBytes(), "UTF-8");
//            str = URLEncoder.encode(str, "UTF-8").replace("*", "%2A");
//            return str;
//        } catch (Exception localException) {
//
//        }
//
//        return "";
//    }
//
//    /**
//     * 对字符串进行MD5编码
//     *
//     * @param
//     * @return
//     */
//    public static String MD5(String strs) {
//        String reStr = null;
//        try {
//            // 创建具有指定算法名称的信息
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(strs.getBytes());// 使用指定的字节更新摘要。
//            byte ss[] = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算
//            reStr = bytes2String(ss);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return reStr;
//    }
//
//    /**
//     * 将字节数组转换为字符串
//     *
//     * @param aa
//     * @return
//     */
//    public static String bytes2String(byte[] aa) {
//        String hash = "";
//        for (int i = 0; i < aa.length; i++) {// 循环数组
//            int temp;
//            if (aa[i] < 0) // 如果小于零，将其变为正数
//                temp = 256 + aa[i];
//            else
//                temp = aa[i];
//            if (temp < 16)
//                hash += "0";
//            hash += Integer.toString(temp, 16);// 转换为16进制
//        }
//        return hash;
//    }
//}
