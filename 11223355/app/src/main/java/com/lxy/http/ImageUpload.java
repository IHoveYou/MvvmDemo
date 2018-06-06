//package com.lxy.http;
//
//
//import android.annotation.SuppressLint;
//import android.content.ContentUris;
//import android.content.Context;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Build;
//import android.provider.DocumentsContract;
//import android.provider.MediaStore;
//
//import com.guangmo.stockopt.base.BaseActivity;
//import com.guangmo.stockopt.bean.HeadimgBean;
//import com.guangmo.stockopt.listener.OnRequestListener;
//import com.guangmo.stockopt.model.UserModel;
//import com.guangmo.stockopt.utils.Constant;
//import com.guangmo.stockopt.utils.DateUtil;
//import com.guangmo.stockopt.utils.GsonUtils;
//import com.guangmo.stockopt.utils.LogUtil;
//import com.guangmo.stockopt.utils.ToastUtils;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import me.shaohui.advancedluban.Luban;
//import me.shaohui.advancedluban.OnCompressListener;
//import okhttp3.Callback;
//import okhttp3.Headers;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
///**
// * Created by Administrator on 2017/12/15.
// */
//
//public class ImageUpload {
//
//
//    public interface mCallback {
//        void onUrl(String url);
//    }
//
//    public static void getFile(final Context context, Uri uri, final Map<String, String> map, final OnRequestListener onRequestListener) {
//        int sdkVersion = Build.VERSION.SDK_INT;
//        Uri path;
//        if (sdkVersion >= 19) { // api >= 19
//
//            path = Uri.parse(getRealPathFromUriAboveApi19(context, uri));
//        } else { // api < 19
//            path = Uri.parse(getRealPathFromUriBelowAPI19(context, uri));
//        }
//        File file = new File(path.getPath());//创建文件
//
//
//        Luban.compress(context, file)       // 初始化Luban，并传入要压缩的图片
//                .putGear(Luban.THIRD_GEAR)      // 设定压缩模式，默认 THIRD_GEAR
//                .launch(new OnCompressListener() {
//                    @Override
//                    public void onStart() {
//                    }
//
//                    @Override
//                    public void onSuccess(File file) {
//                        ImageUpload.getFileRequest(new mCallback() {
//                            @Override
//                            public void onUrl(String url) {
//                                UserModel model = new UserModel();
//                                model.setUrl(url, onRequestListener);
//                                LogUtil.e(url);
//                            }
//                        }, file, map);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showMessage("图片上传失败");
//                    }
//                });              // 启动压缩并设置监听
//    }
//
//    public static void getFileRequest(final mCallback initData, File file, Map<String, String> maps) {
//        String url = ApiHost.API_URL + "/p/upload/img";
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        if (maps == null) {
//            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file)
//            ).build();
//        } else {
//            //添加参数
//            builder.addFormDataPart("sign", MD5(Ascll(builder, maps)));
////            for (String key : maps.keySet()) {
////
////                builder.addFormDataPart(key, maps.get(key));
////            }
//
//            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file)
//            );
//
//        }
//        RequestBody body = builder.build();
//
//        OkHttpClient okHttpClient =
//                new OkHttpClient.Builder()
//                        .readTimeout(60, TimeUnit.SECONDS)//设置读取超时时间
//                        .writeTimeout(60, TimeUnit.SECONDS)//设置写的超时时间
//                        .connectTimeout(60, TimeUnit.SECONDS)//设置连接超时时间
//                        .build();
//
//        final Request request = new Request.Builder().url(url).post(body).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                BaseActivity.getmActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ToastUtils.showMessage("图片上传失败");
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, final Response response) throws IOException {
//                final String str = response.body().string();
//                BaseActivity.getmActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (initData != null) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(str);
//                                if (jsonObject.getString("code").equals("SUCCESS")) {
//                                    HeadimgBean bean = GsonUtils.getGson(str, HeadimgBean.class);
//                                    initData.onUrl(bean.getData().get(0).getPath());
//                                } else {
//                                    ToastUtils.showMessage(jsonObject.getString("msg"));
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//
//            }
//        });
//
//    }
//
//    /**
//     * 对字符串进行MD5编码
//     *
//     * @param
//     * @return
//     */
//    public static String MD5(String strs) {
//        String str = toURLEncoded(strs);
//        String reStr = null;
//        try {
//            // 创建具有指定算法名称的信息
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(str.getBytes());// 使用指定的字节更新摘要。
//            byte ss[] = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算
//            reStr = bytes2String(ss);
////						System.out.println(reStr);
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return reStr;
//    }
//
//    // 将字节数组转换为字符串
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
////        hash = hash.toUpperCase();// 全部转换为大写
//        return hash;
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
//            str = URLEncoder.encode(str, "UTF-8");
//            return str;
//        } catch (Exception localException) {
//
//        }
//
//        return "";
//    }
//
//    /**
//     * 对输入的参数进行AscII 字典序排序
//     *
//     * @return
//     */
//    private static String Ascll(MultipartBody.Builder builder, Map<String, String> map) {
//        String time = DateUtil.getTime();
//        String[] key = new String[map.size()];
//        String from = "";
//        int i = 0;
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            key[i] = entry.getKey();
//            i++;
//        }
//        Arrays.sort(key, String.CASE_INSENSITIVE_ORDER);
//
//        for (int j = 0; j < key.length; j++) {
//            from = from + key[j] + "=" + map.get(key[j]) + "&";
//            builder.addFormDataPart(key[j], map.get(key[j]));
//        }
//        builder.addFormDataPart("timestamp", time);
//        from = from + "apikey=" + Constant.APIKEY + "&timestamp=" + time;
//        try {
//            return URLDecoder.decode(from, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return from;
//    }
//
//
//    /**
//     * 根据Uri获取图片的绝对路径
//     *
//     * @param context 上下文对象
//     * @param uri     图片的Uri
//     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
//     */
//    public static void getRealPathFromUri(Context context, Uri uri) {
//
//
//    }
//
//
//    /**
//     * 适配api19以下(不包括api19),根据uri获取图片的绝对路径
//     *
//     * @param context 上下文对象
//     * @param uri     图片的Uri
//     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
//     */
//    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
//        return getDataColumn(context, uri, null, null);
//    }
//
//    /**
//     * 适配api19及以上,根据uri获取图片的绝对路径
//     *
//     * @param context 上下文对象
//     * @param uri     图片的Uri
//     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
//     */
//    @SuppressLint("NewApi")
//    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
//        String filePath = null;
//        if (DocumentsContract.isDocumentUri(context, uri)) {
//            // 如果是document类型的 uri, 则通过document id来进行处理
//            String documentId = DocumentsContract.getDocumentId(uri);
//            if (isMediaDocument(uri)) { // MediaProvider
//                // 使用':'分割
//                String id = documentId.split(":")[1];
//
//                String selection = MediaStore.Images.Media._ID + "=?";
//                String[] selectionArgs = {id};
//                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
//            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
//                filePath = getDataColumn(context, contentUri, null, null);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            // 如果是 content 类型的 Uri
//            filePath = getDataColumn(context, uri, null, null);
//        } else if ("file".equals(uri.getScheme())) {
//            // 如果是 file 类型的 Uri,直接获取图片对应的路径
//            filePath = uri.getPath();
//        }
//        return filePath;
//    }
//
//    /**
//     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
//     *
//     * @return
//     */
//    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
//        String path = null;
//
//        String[] projection = new String[]{MediaStore.Images.Media.DATA};
//        Cursor cursor = null;
//        try {
//            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
//                path = cursor.getString(columnIndex);
//            }
//        } catch (Exception e) {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        return path;
//    }
//
//    /**
//     * @param uri the Uri to check
//     * @return Whether the Uri authority is MediaProvider
//     */
//    private static boolean isMediaDocument(Uri uri) {
//        return "com.android.providers.media.documents".equals(uri.getAuthority());
//    }
//
//    /**
//     * @param uri the Uri to check
//     * @return Whether the Uri authority is DownloadsProvider
//     */
//    private static boolean isDownloadsDocument(Uri uri) {
//        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
//    }
//
//}
