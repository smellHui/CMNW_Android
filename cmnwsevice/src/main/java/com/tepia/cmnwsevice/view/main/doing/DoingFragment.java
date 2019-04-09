package com.tepia.cmnwsevice.view.main.doing;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderAdapter;
import com.tepia.cmnwsevice.view.main.OrderPresenter;
import com.tepia.cmnwsevice.view.main.views.DealTextView;

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

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setCenterTitle("处理中");
        setLeftImgEvent(R.mipmap.ic_mine_header_footprint, v -> toast("左边"));
        setRightImgEvent(R.mipmap.ic_mine_header_footprint, v -> toast("右边"));
        doingCountTv = findView(R.id.view_deal_text_first);
        secondTv = findView(R.id.view_deal_text_second);
        secondTv.setVisibility(View.GONE);
        doingCountTv.setTitle("处理中", R.mipmap.wddb_icn_todo);

        orderPresenter = OrderPresenter.getInstance(2, this);
    }

    @Override
    protected void initRequestData() {
        orderPresenter.querylist(getPage(), 20);
    }

    @Override
    public void onResume() {
        super.onResume();
        orderPresenter.getOrderCount(orderCount -> {
            doingCountTv.setCount(orderCount.getOnExecute());
        });
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new OrderAdapter();
    }
}
