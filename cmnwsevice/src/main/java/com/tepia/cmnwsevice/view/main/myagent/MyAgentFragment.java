package com.tepia.cmnwsevice.view.main.myagent;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.event.StartDoCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.model.order.OrderCountBean;
import com.tepia.cmnwsevice.view.main.OrderAdapter;
import com.tepia.cmnwsevice.view.main.OrderPresenter;
import com.tepia.cmnwsevice.view.main.views.DealTextView;
import com.tepia.cmnwsevice.view.setting.MineActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Author:xch
 * Date:2019/4/2
 * Do 我的代办
 */
public class MyAgentFragment extends BaseListFragment<OrderBean> {

    private OrderPresenter orderPresenter;

    private DealTextView pendingTv;
    private DealTextView returnTv;

    public static MyAgentFragment launch() {
        return new MyAgentFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myagent;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setCenterTitle("我的代办");
        setLeftImgEvent(R.mipmap.wddb_icn_weather, v -> toast("天气"));
        setRightImgEvent(R.mipmap.wddb_icn_mine, v -> {
            startActivity(new Intent(getActivity(), MineActivity.class));
        });

        pendingTv = findView(R.id.view_deal_text_first);
        returnTv = findView(R.id.view_deal_text_second);
        pendingTv.setTitle("待处理", R.mipmap.wddb_icn_todo);
        returnTv.setTitle("已退回", R.mipmap.wddb_icn_return);
        orderPresenter = new OrderPresenter(0, this);

    }

    @Subscribe
    public void StartDoCallbackEvent(StartDoCallbackEvent event) {
        refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        orderPresenter.getOrderCount(orderCount -> {
            pendingTv.setCount(orderCount.getToExecute());
            returnTv.setCount(orderCount.getToBack());
        });
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
        if (orderBean == null) return;
        UiHelper.goToActionDetailView(getContext(), orderBean.getId(), orderBean.getDataAndName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
