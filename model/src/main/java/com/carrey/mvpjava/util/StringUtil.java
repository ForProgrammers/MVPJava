package com.carrey.mvpjava.util;


/**
 * 作者： carrey
 * 时间 2017/7/11
 * desc:
 */

public class StringUtil {

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

}
