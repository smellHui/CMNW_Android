package com.tepia.base.utils;

import android.content.Context;
import android.view.View;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 *     全局
 * </pre>
 */
public final class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        //传递整个app生命周期的上下文，避免内存泄露
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }
    /**
     * 加载布局文件
     */
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }
}