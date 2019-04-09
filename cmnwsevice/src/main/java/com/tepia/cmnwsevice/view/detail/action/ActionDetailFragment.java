package com.tepia.cmnwsevice.view.detail.action;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.ActionDetailView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.order.WorkDetailBean;
import com.tepia.cmnwsevice.view.main.views.ActionInfoView;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:工单详情
 */
public class ActionDetailFragment extends BaseCommonFragment {

    private String orderId;

    private ActionDetailView mView;

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
        mView = DataBindingUtil.bind(view);
        mView.viewActionInfo.setOnClickListener(v -> UiHelper.goToDoingTipView(getContext(), orderId));
    }

    @Override
    protected void initRequestData() {
        OrderManager.getInstance().getOrderDetail(orderId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<OrderBean>>() {
                    @Override
                    protected void _onNext(BaseCommonResponse<OrderBean> orderBeanBaseCommonResponse) {
                        OrderBean orderBean = orderBeanBaseCommonResponse.getData();
                        if (orderBean == null) return;
                        mView.setOrderBean(orderBean);
                        mView.viewActionInfo.setData(orderBean.getOrderName(), orderBean.getAreaName());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });

        OrderManager.getInstance().getOrderWorkingDetail(orderId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<WorkDetailBean>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<WorkDetailBean> workDetail) {
                        if (workDetail == null) return;
                        WorkDetailBean workDetailBean = workDetail.getData();
                        if (workDetailBean == null) return;
                        mView.setWorkDetailBean(workDetailBean);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }
}
