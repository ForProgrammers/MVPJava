package com.carrey.mvpjava.http;

import com.carrey.mvpjava.util.StringUtil;

import okhttp3.Interceptor;

/**
 * 作者： carrey
 * 时间 2017/7/12
 * desc:
 */

public class RetrofitConfig {

    final String mBaseUrl;
    final boolean mLogDebugger;
    final Interceptor mSignInterceptor;

    private RetrofitConfig(Builder builder) {
        this.mBaseUrl = builder.mBaseUrl;
        this.mLogDebugger = builder.mLogDebugger;
        this.mSignInterceptor = builder.mSignInterceptor;

    }


    public static class Builder {
        String mBaseUrl;
        boolean mLogDebugger;
        Interceptor mSignInterceptor;

        public Builder setBaseUrl(String baseUrl) {
            mBaseUrl = baseUrl;
            return this;
        }

        public Builder setLogDebugger(boolean logDebugger) {
            this.mLogDebugger = logDebugger;
            return this;
        }

        public Builder setSignInterceptor(Interceptor signInterceptor) {
            mSignInterceptor = signInterceptor;
            return this;
        }

        public RetrofitConfig build() {

            if (StringUtil.isEmpty(mBaseUrl)){
                throw new RuntimeException("mBaseUrl must not null");
            }

            return new RetrofitConfig(this);
        }

    }

}
