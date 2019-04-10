package com.tepia.cmnwsevice.utils;

import com.google.common.base.Strings;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * Author:xch
 * Date:2019/4/10
 * Do:
 */
public class StringUtil {

    public static String nullToDefault(@NullableDecl String string, String defaultstr) {
        return Strings.isNullOrEmpty(string) ? defaultstr : string;
    }

    public static String nullToDefault(@NullableDecl String string) {
        return nullToDefault(string, "暂无");
    }

}
