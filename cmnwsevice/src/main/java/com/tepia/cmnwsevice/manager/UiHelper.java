package com.tepia.cmnwsevice.manager;

import android.content.Context;
import android.content.Intent;

import com.tepia.cmnwsevice.view.detail.action.ActionDetailActivity;
import com.tepia.cmnwsevice.view.detail.edit.FillInActivity;
import com.tepia.cmnwsevice.view.detail.tip.DoingTipActivity;

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

    /**
     * 处理中提示页
     *
     * @param ctx
     */
    public static void goToDoingTipView(Context ctx) {
        ctx.startActivity(new Intent(ctx, DoingTipActivity.class));
    }

    /**
     * 工单填报
     *
     * @param ctx
     */
    public static void goToFillInView(Context ctx) {
        ctx.startActivity(new Intent(ctx, FillInActivity.class));
    }
}
