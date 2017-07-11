//package com.carrey.mvpjava.http;
//
////import android.text.TextUtils;
////
////import com.carrey.mvpjava.BaseResponse;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Comparator;
//import java.util.Set;
//import java.util.TreeSet;
//
//import okhttp3.Headers;
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * 作者： carrey
// * 时间 2017/7/11
// * desc:
// */
//
//public class RequestInterceptor implements Interceptor {
//
//
//    public static final String API_VERSION = "4.3.0";
//    private static final String SIGN_HEAD = "crackerdontdothisplz!\n";
//    private static final String APP_SECRET = "515e5a339983463b9a727b6d9f20d207";
//    private static final String APP_KEY = "1bc215fa-7a88-4972-a33e-3eb2e37de5ca";
//    private String mRequestId;
//    private String mRequestTime;
//
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//
//        Request originRequest = chain.request();
//        Request.Builder requestBuilder = originRequest.newBuilder();
//        StringBuilder stringBuilder = new StringBuilder();//签名
//
//        stringBuilder.append(SIGN_HEAD);
//        // 拼接请求唯一参数
////        if (TextUtils.isEmpty(BaseResponse.sTimeDiff)) {
////            stringBuilder.append(String.format("request-id=%s@client-request-time=%s\n", mRequestId, mRequestTime));
////        } else {
////            stringBuilder.append(String.format("request-id=%s@client-request-time=%s+time-diff=%s\n", mRequestId, mRequestTime, BaseResponse.sTimeDiff));
////        }
//
//        //拼接url 和params
//        URI uri = originRequest.url().uri();
//
//        String path = uri.getPath();
////        if (!TextUtils.isEmpty(path)) {
////            int index = path.indexOf("/api");
////            if (index > 0) {
////                path = path.substring(index);
////            }
////            stringBuilder.append(path);
////        }
//
//        stringBuilder.append("+");
//
//        //拼接headers
//        Headers headers = originRequest.headers();
//        Headers.Builder headerBuilder = headers.newBuilder();
//
//        headerBuilder.set("version", API_VERSION)
//                .set("app-version", "4.4")
//                .set("device", "123456")
//                .set("screen-width", "480")
//                .set("screen-height", "720")
//                .set("platform", "1")
//                .set("os-version", "1")
//                .set("model", "1")
//                .set("main-area", "1")
//                .set("spec-area", "1")
//                .set("access-token", "1")
//                .set("channel", "1");
//
//        Headers signHeader = headerBuilder.build();
//        Set<String> names = signHeader.names();
//        TreeSet<String> sortSet = new TreeSet<>(new Comparator<String>() {
//            @Override
//            public int compare(String s, String t1) {
//                return s.compareTo(t1);
//            }
//        });
//        sortSet.addAll(names);
//
//        StringBuilder headerSB = new StringBuilder();
//
//        for (String key : sortSet) {
//            String value = TextUtils.isEmpty(signHeader.get(key)) ? "" : signHeader.get(key);
//            headerSB.append(key).append("=").append(value).append("&");
//        }
//        if (headerSB.toString().endsWith("&")) {
//            headerSB.deleteCharAt(headerSB.lastIndexOf("&"));
//        }
//        stringBuilder.append(headerSB);
//        stringBuilder.append("+");
//
//        // 拼接query
//        String uriQuery = uri.getQuery();
//        if (!TextUtils.isEmpty(uriQuery)) {
//            String[] strings = uriQuery.split("&");
//            for (String queryString : strings) {
//                String[] split = queryString.split("=");
//            }
//        }
//        //拼接body
////        if (TYPE_MULTI_PARTheaders.get(""))
//
//        requestBuilder.headers(headerBuilder.build());
//
//        return chain.proceed(requestBuilder.build());
//    }
//}
