package com.tepia.cmnwsevice.view.orderlist;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.model.ExecuteStatus;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderAdapter;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

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
}
