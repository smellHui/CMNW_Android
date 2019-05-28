package com.tepia.cmdbsevice.util;

import android.content.Context;
import android.content.Intent;

import com.tepia.cmdbsevice.view.alarmstatistics.AlarmStatisticsTwoActivity;

/**
 * Author:xch
 * Date:2019/4/28
 * Description:
 */
public class UiHelper {

    /**
     * 事件督办
     *
     * @param ctx
     * @param tabIndex
     */
    public static void goToAlarmStatisticsTwoView(Context ctx, int tabIndex) {
        Intent intent = new Intent(ctx, AlarmStatisticsTwoActivity.class);
        intent.putExtra("tabIndex", tabIndex);
        ctx.startActivity(intent);
    }

}
