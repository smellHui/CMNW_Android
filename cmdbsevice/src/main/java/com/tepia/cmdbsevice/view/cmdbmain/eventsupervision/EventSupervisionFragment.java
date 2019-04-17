package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.CityCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.CountShowView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.RealTimeSuperView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.TownshipStatisticsView;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:tab-事件督办
 */
public class EventSupervisionFragment extends BaseListFragment<OrderBean> {

    private String[] mTitles = {"今日", "本周", "本月", "本年"};
    private OrderPresenter orderPresenter;
    private SelectEventPopView selectEventPopView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_event_supervision;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setCenterTitle("事件督办");
        selectEventPopView = new SelectEventPopView(getContext());
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
        return new CityCountAdapter();
    }

    @Override
    public void addHeadView() {
        CountShowView countShowView = new CountShowView(getContext());
        getAdapter().addHeaderView(countShowView);
        RealTimeSuperView realTimeSuperView = new RealTimeSuperView(getContext());
        getAdapter().addHeaderView(realTimeSuperView);
        TownshipStatisticsView townshipStatisticsView = new TownshipStatisticsView(getContext());
        getAdapter().addHeaderView(townshipStatisticsView);
    }

    @Override
    public void setEmptyDefauleView() {
    }
}