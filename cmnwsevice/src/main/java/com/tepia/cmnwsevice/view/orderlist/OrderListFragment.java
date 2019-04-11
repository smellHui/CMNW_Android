package com.tepia.cmnwsevice.view.orderlist;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.ExecuteStatus;
import com.tepia.cmnwsevice.model.event.CompleteCallbackEvent;
import com.tepia.cmnwsevice.model.event.StartDoCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderAdapter;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Author:xch
 * Date:2019/4/11
 * Do:
 */
public class OrderListFragment extends BaseListFragment<OrderBean> {

    private OrderPresenter orderPresenter;
    private ExecuteStatus status;

    public static OrderListFragment launch(ExecuteStatus status) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("status", status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        if (bundle != null)
            status = (ExecuteStatus) bundle.getSerializable("status");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        orderPresenter = new OrderPresenter(status, this);
    }

    @Override
    protected void initRequestData() {
        orderPresenter.querylist(getPage(), 20);
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new OrderAdapter();
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        OrderBean orderBean = (OrderBean) adapter.getItem(position);

        int type = status.getType();
        //已退回  待处理
        if (type == ExecuteStatus.PENDING.getType() || type == ExecuteStatus.RETURNED.getType()) {
            UiHelper.goToActionDetailView(getContext(), orderBean.getId(), orderBean.getDataAndName());
        }

        //处理中
        if (type == ExecuteStatus.EXECUTING.getType()) {
            UiHelper.goToDoingTipView(getContext(), orderBean.getId(), orderBean.getDataAndName());
        }

        //待审核  已完结
        if (type == ExecuteStatus.WAITCONFIRM.getType() || type == ExecuteStatus.COMPLETE.getType()) {
            ARouter.getInstance().build(AppRoutePath.app_cmnw_activity_order_operate)
                    .withString("orderId", orderBean.getId() + "")
                    .navigation();
        }
    }

    @Subscribe
    public void StartDoCallbackEvent(StartDoCallbackEvent event) {
        refresh();
    }

    @Subscribe
    public void CompleteCallbackEvent(CompleteCallbackEvent event) {
        refresh();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
