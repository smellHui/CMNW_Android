package com.tepia.cmnwsevice.manager;

import android.content.Context;
import android.content.Intent;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.model.order.OrderParamBean;
import com.tepia.cmnwsevice.view.detail.action.ActionDetailActivity;
import com.tepia.cmnwsevice.view.detail.action.OrderDetailActivity;
import com.tepia.cmnwsevice.view.detail.edit.ConfirmSubmitActivity;
import com.tepia.cmnwsevice.view.detail.edit.FillInActivity;
import com.tepia.cmnwsevice.view.detail.tip.DoingTipActivity;
import com.tepia.cmnwsevice.view.feedback.FeedBackSucActivity;

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
    public static void goToActionDetailView(Context ctx, String orderId, String topTitle) {
        Intent intent = new Intent(ctx, ActionDetailActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("topTitle", topTitle);
        ctx.startActivity(intent);
    }

    /**
     * 处理中提示页
     *
     * @param ctx
     */
    public static void goToDoingTipView(Context ctx, String orderId, String topTitle) {
        Intent intent = new Intent(ctx, DoingTipActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("topTitle", topTitle);
        ctx.startActivity(intent);
    }

    /**
     * 工单填报
     *
     * @param ctx
     */
    public static void goToFillInView(Context ctx, String orderId) {
        Intent intent = new Intent(ctx, FillInActivity.class);
        intent.putExtra("orderId", orderId);
        ctx.startActivity(intent);
    }

    /**
     * 工单详情
     *
     * @param ctx
     * @param orderId
     */
    public static void goToOrderDetailView(Context ctx, String orderId) {
        Intent intent = new Intent(ctx, OrderDetailActivity.class);
        intent.putExtra("orderId", orderId);
        ctx.startActivity(intent);
    }

    /**
     * 确认提交页面
     *
     * @param ctx
     */
    public static void goToConfirmSubmitView(Context ctx, OrderParamBean orderParamBean) {
        Intent intent = new Intent(ctx, ConfirmSubmitActivity.class);
        intent.putExtra("orderParamBean", orderParamBean);
        ctx.startActivity(intent);
    }

    /**
     * 反馈成功页面
     *
     * @param ctx
     */
    public static void goToFeedBackSucView(Context ctx) {
        ctx.startActivity(new Intent(ctx, FeedBackSucActivity.class));
    }
}
