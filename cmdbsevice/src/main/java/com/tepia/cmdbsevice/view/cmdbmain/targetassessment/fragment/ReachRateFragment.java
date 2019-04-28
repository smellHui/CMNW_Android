package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lxj.xpopup.XPopup;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.model.event.WaterRateBean;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.ReachRateAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.SpssCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SelectDataView;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SpssTitleView;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/18
 * Description: 在线水质达标率统计分析
 */
public class ReachRateFragment extends StatisFragment<WaterRateBean> {

    private static String[] ONLINE_WATER_QUALITY = {"行政区划", "站点总数", "水质达标率"};

    private SelectDataView selectDataView;

    private List<WaterRateBean> vendorTotals, townTotals;
    private String startTime, endTime;

    public static ReachRateFragment launch() {
        ReachRateFragment fragment = new ReachRateFragment();
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        selectDataView = new SelectDataView(getContext());
        selectDataView.setListener(this::onDataSelectPickListener);
    }

    private void onDataSelectPickListener(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            ToastUtils.shortToast("请选择日期");
            return;
        }
        this.startTime = startTime;
        this.endTime = endTime;
        setChoiceDateTv(String.format("%s - %s", startTime.replace("-", "."), endTime.replace("-", ".")));
        requestListData();
    }


    @Override
    public void initRequestData() {
        this.startTime = TimeFormatUtils.getFirstDayOfToday();
        this.endTime = TimeFormatUtils.getLastDayOfToday();
        requestListData();
    }

    @Override
    public void showChoiceDateDialog(View view) {
        new XPopup.Builder(getContext())
                .hasStatusBarShadow(true) //启用状态栏阴影
                .dismissOnTouchOutside(false)
                .asCustom(selectDataView)
                .show();
    }

    @Override
    public void listByStcd() {
        EventManager.getInstance().listReachRateByStcd("startDate", String.format("%s 00:00:00", startTime), "endDate", String.format("%s 23:59:59", endTime))
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WaterRateBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WaterRateBean>> baseCommonResponse) {
                        townTotals = baseCommonResponse.getData();
                        setNewData(townTotals);
                        setRefreshing(false);
                    }

                    @Override
                    protected void _onError(String message) {
                        setRefreshing(false);
                    }
                });
    }

    @Override
    public void listByVendor() {
        EventManager.getInstance().listReachRateByVendor("startDate", String.format("%s 00:00:00", startTime), "endDate", String.format("%s 23:59:59", endTime))
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WaterRateBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WaterRateBean>> baseCommonResponse) {
                        vendorTotals = baseCommonResponse.getData();
                        setNewData(vendorTotals);
                        setRefreshing(false);
                    }

                    @Override
                    protected void _onError(String message) {
                        setRefreshing(false);
                    }
                });
    }

    @NonNull
    @Override
    public String[] tableTitles() {
        return ONLINE_WATER_QUALITY;
    }

    @NonNull
    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new ReachRateAdapter();
    }

}
