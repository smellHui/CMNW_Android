package com.tepia.cmdbsevice.util;

import android.content.Context;
import android.content.Intent;

import com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsActivity;

/**
 * Author:xch
 * Date:2019/4/28
 * Description:
 */
public class UiHelper {

    /**
     * 事件督办列表页
     *
     * @param ctx
     * @param tabIndex
     */
    public static void goToAlarmStatisticsView(Context ctx, int tabIndex) {
        Intent intent = new Intent(ctx, AlarmStatisticsActivity.class);
        intent.putExtra("tabIndex", tabIndex);
        ctx.startActivity(intent);
    }

}
