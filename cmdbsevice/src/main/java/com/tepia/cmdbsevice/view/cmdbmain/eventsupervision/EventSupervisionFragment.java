package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.CityCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.CountShowView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.RealTimeSuperView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.TownshipStatisticsView;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

import java.io.BufferedReader;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/15 14:43
 * @修改人 ：
 * @修改时间 :       2019/4/15 14:43
 * @功能描述 :        tab-事件督办
 **/

public class EventSupervisionFragment extends BaseListFragment<OrderBean> {

    private String[] mTitles = {"日", "周", "月", "年"};
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
        setRightImgEvent(R.mipmap.img_agrc_service,(v)->{
            ToastUtils.shortToast("右边");
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(selectEventPopView)
                    .show();
        });
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
    public void setEmptyDefauleView() {}
}