package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.SpssCountAdapter;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

/**
 * Author:xch
 * Date:2019/4/18
 * Description: 统计分析
 */
public class SpssFragment extends BaseListFragment<OrderBean> {

    private String[] mTitles = {"行政区划", "企业"};
    private OrderPresenter orderPresenter;

    public static SpssFragment launch() {
        SpssFragment fragment = new SpssFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_spss;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        SegmentTabLayout tabLayout_1 = findView(R.id.tl_1);
        tabLayout_1.setTabData(mTitles);
        orderPresenter = new OrderPresenter(1, this);
    }

    @Override
    protected void initRequestData() {
        orderPresenter.querylist(getPage(), 20);
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new SpssCountAdapter(getContext());
    }
}
