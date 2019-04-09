package com.tepia.cmnwsevice.view.main.operate;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderAdapter;
import com.tepia.cmnwsevice.view.main.OrderPresenter;
import com.tepia.cmnwsevice.view.main.views.DealTextView;

/**
 * Author:xch
 * Date:2019/4/2
 * Do:运维记录
 */
public class OperateFragment extends BaseListFragment<OrderBean> {

    private DealTextView pendTv;
    private DealTextView completeTv;

    private OrderPresenter orderPresenter;
    private OrderAdapter orderAdapter;

    public static OperateFragment launch() {
        return new OperateFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myagent;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setCenterTitle("运维记录");
        setLeftImgEvent(R.mipmap.ic_mine_header_footprint, v -> toast("左边"));
        setRightImgEvent(R.mipmap.ic_mine_header_footprint, v -> toast("右边"));

        pendTv = findView(R.id.view_deal_text_first);
        completeTv = findView(R.id.view_deal_text_second);
        pendTv.setTitle("待审核", R.mipmap.ywjl_icn_dsc);
        completeTv.setTitle("已完结", R.mipmap.ywjl_icn_finish);

        orderPresenter = new OrderPresenter(2, this);
    }

    @Override
    protected void initRequestData() {
        orderPresenter.querylist(getPage(), 20);
    }

    @Override
    public void onResume() {
        super.onResume();
        orderPresenter.getOrderCount(orderCount -> {
            pendTv.setCount(orderCount.getToExamine());
            completeTv.setCount(orderCount.getDone());
        });
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        orderAdapter = new OrderAdapter();
        return orderAdapter;
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        OrderBean orderBean = orderAdapter.getData().get(position);
        ARouter.getInstance().build(AppRoutePath.app_cmnw_activity_order_operate)
                .withString("orderId", orderBean.getId() + "")
                .navigation();
    }
}
