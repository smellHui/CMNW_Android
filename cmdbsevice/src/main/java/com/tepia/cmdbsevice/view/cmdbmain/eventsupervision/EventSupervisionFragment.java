package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.view.alarmstatistics.view.DataTopView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.CityCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.CountShowView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.RealTimeSuperView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.TownshipStatisticsView;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SpssTitleView;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:tab-事件督办
 */
public class EventSupervisionFragment extends BaseCommonFragment {
    private static String[] FAULT_RATES = {"行政区划", "运维企业", "站点总数", "报警总数", "故障总数"};

    private RecyclerView rv;
    private TextView currectTimeTv;
    private SpssTitleView spssTitleView;
    private CityCountAdapter cityCountAdapter;
    private CountShowView countShowView;
    private RealTimeSuperView realTimeSuperView;
    private TownshipStatisticsView townshipStatisticsView;

    private TopTotalBean topTotalBean;
    private List<TopTotalBean> townTotals, vendorTotals;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_event_supervision;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(View view) {
        setCenterTitle("事件督办");
        countShowView = findView(R.id.view_count_show);
        realTimeSuperView = findView(R.id.view_real_super);
        townshipStatisticsView = findView(R.id.view_town_statis);
        spssTitleView = findView(R.id.view_title);
        spssTitleView.setData(FAULT_RATES);
        currectTimeTv = findView(R.id.tv_currect_time);
        currectTimeTv.setText(String.format("当前时间：%s", TimeFormatUtils.getStringDateShort()));
        setVerModel();
    }

    private void setVerModel() {
        rv = findView(R.id.rv);
        cityCountAdapter = new CityCountAdapter();
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(cityCountAdapter);
    }

    @Override
    protected void initRequestData() {
        onDataTopPickListener(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
    }

    /**
     * 【查询】报警、故障总数
     */
    private void superviseTopTotal(String startTime, String endTime) {
        EventManager.getInstance().topTotal("startTime", startTime, "endTime", endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<TopTotalBean>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<TopTotalBean> baseCommonResponse) {
                        topTotalBean = baseCommonResponse.getData();
                        if (topTotalBean == null) return;
                        countShowView.setDate(topTotalBean.getFaultNum(), topTotalBean.getAlarmNum());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    /**
     * 【查询】实时督办-乡镇报警、故障数
     *
     * @param startTime
     * @param endTime
     */
    private void superviseCountByTown(String startTime, String endTime) {
        EventManager.getInstance().countByTown("startTime", startTime, "endTime", endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<TopTotalBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<TopTotalBean>> baseCommonResponse) {
                        townTotals = baseCommonResponse.getData();
                        cityCountAdapter.setNewData(townTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    private void superviseCountByVendor(String startTime, String endTime) {
        EventManager.getInstance().countByVendor("startTime", startTime, "endTime", endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<TopTotalBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<TopTotalBean>> baseCommonResponse) {
                        vendorTotals = baseCommonResponse.getData();
                        realTimeSuperView.setData(vendorTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    public void onDataTopPickListener(String startTime, String endTime) {
        superviseTopTotal(startTime, endTime);
        superviseCountByTown(startTime, endTime);
        superviseCountByVendor(startTime, endTime);
    }
}