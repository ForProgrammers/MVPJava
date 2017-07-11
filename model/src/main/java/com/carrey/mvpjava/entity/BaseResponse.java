package com.carrey.mvpjava.entity;

/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class BaseResponse {
    // 接口中没有带token信息
    public static final int CODE_TOKEN_EMPTY = 10;
    //在新设备登录, 原设备的session失效(不删除), 使用原token访问api抛出13错误
    // (此时服务端删除原session, 再次用原token访问则抛出12错误)
    public static final int CODE_TOKEN_EXPIRED = 12;
    public static final int CODE_TOKEN_NOT_EXIST = 13;
    // 客户端时间错误
    public static final int CODE_WRONG_TIME = 16;
    public static String sTimeDiff;
    public int code;
    /**
     * 是否使用加密
     */
    public boolean encode;
    public String msg;

    public String data;

    public String time_diff;

    public boolean isSuccess() {
        return code == 0;
    }

    public boolean isTokenExpired() {
        return code == CODE_TOKEN_EXPIRED || code == CODE_TOKEN_NOT_EXIST;
    }

    @Override
    public String toString() {
        return "code:" + code + ",msg:" + msg + ",data:" + data;
    }
}
