package com.carrey.mvpjava.http;

/**
 * 作者： carrey
 * 时间 2017/7/13
 * desc:
 */

public class BaseNameValuePair implements NameValuePair {
    private String name;
    private String value;

    public BaseNameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
