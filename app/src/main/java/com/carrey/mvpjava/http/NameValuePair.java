package com.carrey.mvpjava.http;

/**
 * 作者： carrey
 * 时间 2017/7/12
 * desc:
 */

public class NameValuePair {

    private String name;
    private String value;

    public NameValuePair(String name, String value) {
        this.name = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
