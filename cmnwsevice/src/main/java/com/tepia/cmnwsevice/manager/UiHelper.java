package com.tepia.cmnwsevice.manager;

import android.content.Context;
import android.content.Intent;

import com.tepia.cmnwsevice.view.detail.action.ActionDetailActivity;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class UiHelper {

    /**
     * 工单详情
     *
     * @param ctx
     */
    public static void goToActionDetailView(Context ctx) {
        ctx.startActivity(new Intent(ctx, ActionDetailActivity.class));
    }
}
