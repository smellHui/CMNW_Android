package com.tepia.cmnwsevice.view.detail.action;

import android.os.Bundle;
import android.view.View;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.view.main.views.ActionInfoView;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ActionDetailFragment extends BaseCommonFragment {

    private ActionInfoView actionInfoView;
    private String orderId;

    public static ActionDetailFragment launch(String orderId) {
        ActionDetailFragment fragment = new ActionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_action_detail;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null)
            orderId = bundle.getString("orderId");
    }

    @Override
    protected void initView(View view) {
        actionInfoView = findView(R.id.view_action_info);
        actionInfoView.setOnClickListener(v -> UiHelper.goToDoingTipView(getContext(), orderId));
    }

    @Override
    protected void initRequestData() {
        OrderManager.getInstance().getOrderDetail(orderId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<OrderBean>>() {
                    @Override
                    protected void _onNext(BaseCommonResponse<OrderBean> orderBeanBaseCommonResponse) {
                        System.out.println(orderBeanBaseCommonResponse.toString());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }
}
