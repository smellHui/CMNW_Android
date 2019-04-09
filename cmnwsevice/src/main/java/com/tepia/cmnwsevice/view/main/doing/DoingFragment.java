package com.tepia.cmnwsevice.view.main.doing;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.event.CompleteCallbackEvent;
import com.tepia.cmnwsevice.model.event.StartDoCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderAdapter;
import com.tepia.cmnwsevice.view.main.OrderPresenter;
import com.tepia.cmnwsevice.view.main.views.DealTextView;
import com.tepia.cmnwsevice.view.setting.MineActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Author:xch
 * Date:2019/4/2
 * Do:处理中
 */
public class DoingFragment extends BaseListFragment<OrderBean> {

    private DealTextView doingCountTv, secondTv;
    private OrderPresenter orderPresenter;

    public static DoingFragment launch() {
        return new DoingFragment();
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
        setCenterTitle("处理中");
        setLeftImgEvent(R.mipmap.wddb_icn_weather, v -> toast("天气"));
        setRightImgEvent(R.mipmap.wddb_icn_mine, v -> startActivity(new Intent(getActivity(), MineActivity.class)));
        doingCountTv = findView(R.id.view_deal_text_first);
        secondTv = findView(R.id.view_deal_text_second);
        secondTv.setVisibility(View.GONE);
        doingCountTv.setTitle("处理中", R.mipmap.wddb_icn_todo);

        orderPresenter = new OrderPresenter(1, this);
    }

    @Override
    protected void initRequestData() {
        orderPresenter.querylist(getPage(), 20);
    }

    @Override
    public void onResume() {
        super.onResume();
        orderPresenter.getOrderCount(orderCount -> doingCountTv.setCount(orderCount.getOnExecute()));
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new OrderAdapter();
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        OrderBean orderBean = (OrderBean) adapter.getItem(position);
        if (orderBean == null) return;
        UiHelper.goToDoingTipView(getContext(), orderBean.getId());
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
