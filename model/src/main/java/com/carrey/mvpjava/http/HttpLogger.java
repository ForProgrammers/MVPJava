package com.carrey.mvpjava.http;


import com.carrey.mvpjava.util.JsonUtil;

import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者： carrey
 * 时间 2017/7/12
 * desc:
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(String message) {

        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}")) || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        //响应结束 打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            Platform.get().log(Platform.INFO, "okhttp:" + mMessage.toString(), null);
        }

    }

}
