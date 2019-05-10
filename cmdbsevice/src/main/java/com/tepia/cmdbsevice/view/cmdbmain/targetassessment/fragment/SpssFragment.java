package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment;

import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.SpssCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SelectDataView;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/18
 * Description: 故障率统计分析
 */
public class SpssFragment extends StatisFragment<TopTotalBean> {

    private static final int TOWN_TYPE = 2;
    private static final int VENDOR_TYPE = TOWN_TYPE >> 1;

    private static String[] FAULT_RATES = {"行政区划", "报警总数", "故障总数", "故障率"};

    private SelectDataView selectDataView;

    private List<TopTotalBean> topTotals;
    private String startTime, endTime;

    public static SpssFragment launch() {
        SpssFragment fragment = new SpssFragment();
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        this.startTime = TimeFormatUtils.getFirstDayOfToday();
        this.endTime = TimeFormatUtils.getLastDayOfToday();
        setChoiceDateTv(startTime);
        selectDataView = new SelectDataView(getContext());
        selectDataView.setListener(this::onDataSelectPickListener);
    }

    private void onDataSelectPickListener(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        setChoiceDateTv(String.format("%s - %s", startTime.replace("-", "."), endTime.replace("-", ".")));
        requestListData();
    }

    @Override
    public void initRequestData() {
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
        faultStatistics(TOWN_TYPE);
    }

    @Override
    public void listByVendor() {
        faultStatistics(VENDOR_TYPE);
    }

    /**
     * 【查询】故障率统计
     * dimension  1 - 按厂家; 2 - 按乡镇;
     */
    private void faultStatistics(int dimension) {
        EventManager.getInstance().faultStatistics(dimension + "", startTime, endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<TopTotalBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<TopTotalBean>> baseCommonResponse) {
                        topTotals = baseCommonResponse.getData();
                        setNewData(topTotals);
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
        return FAULT_RATES;
    }

    @NonNull
    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new SpssCountAdapter();
    }

}
