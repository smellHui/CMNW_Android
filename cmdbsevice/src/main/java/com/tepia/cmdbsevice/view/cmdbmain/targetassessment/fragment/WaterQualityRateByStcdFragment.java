package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment;

import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WaterRateBean;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.WaterRateAdapter;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/18
 * Description: 人工水质达标率统计分析
 */
public class WaterQualityRateByStcdFragment extends StatisFragment<WaterRateBean> {

    private static String[] PEOPLE_WATER_QUALITY = {"行政区划", "站点总数", "抽查数", "达标数", "水质达标率"};

    private List<WaterRateBean> vendorTotals, townTotals;
    private List<String> dateList;
    private String choiceDate;
    private int checkedPosition;

    public static WaterQualityRateByStcdFragment launch() {
        WaterQualityRateByStcdFragment fragment = new WaterQualityRateByStcdFragment();
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setChoiceDateTv("筛选");
    }

    @Override
    public void initRequestData() {
        setRefreshing(true);
        listDataTime();
    }

    @Override
    public void showChoiceDateDialog(View view) {
        if (dateList == null || dateList.isEmpty()) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            new XPopup.Builder(getContext())
                    .asCenterList("请选择日期", dateList.toArray(new String[0]),
                            null, checkedPosition,
                            (position, text) -> {
                                checkedPosition = position;
                                choiceDate = text;
                                requestListData();
                            })
                    .show();
        }
    }

    @Override
    public void listByStcd() {
        EventManager.getInstance().listWaterQualityRateByStcd(choiceDate)
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
        EventManager.getInstance().listWaterQualityRateByVendor(choiceDate)
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
        return PEOPLE_WATER_QUALITY;
    }

    @NonNull
    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new WaterRateAdapter();
    }

    private void listDataTime() {
        EventManager.getInstance().listDataTime()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<String>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<String>> baseCommonResponse) {
                        setRefreshing(false);
                        dateList = baseCommonResponse.getData();
                        if (dateList != null && !dateList.isEmpty()) {
                            choiceDate = dateList.get(0);
                            setChoiceDateTv(choiceDate);
                            requestListData();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        setRefreshing(false);
                    }
                });
    }

}
