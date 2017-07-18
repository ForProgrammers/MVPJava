package com.carrey.mvpjava.http;

import java.util.ArrayList;

import okhttp3.Headers;

/**
 * 作者： carrey
 * 时间 2017/7/13
 * desc:
 */

public interface SignHelper {

    ArrayList<NameValuePair> sortHeaders(Headers.Builder headersBuilder);

    NameValuePair createNameValuePair(String name, String value);
}
