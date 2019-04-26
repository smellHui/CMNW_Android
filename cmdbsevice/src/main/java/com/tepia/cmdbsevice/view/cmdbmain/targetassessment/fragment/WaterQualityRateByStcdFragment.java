package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
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
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.SpssCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.WaterRateAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SelectDataView;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SpssTitleView;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/18
 * Description: 人工水质达标率统计分析
 */
public class WaterQualityRateByStcdFragment extends BaseCommonFragment {

    private static String[] mTitles = {"行政区划", "企业"};
    private static String[] WATER_QUALITY = {"行政区划", "站点总数", "抽查数", "达标数", "水质达标率"};

    private int pageType;//页面标识，0故障率统计  1人工水质  2在线水质
    private int currectTab;

    private RecyclerView rv;
    private SpssTitleView spssTitleView;
    private TextView tv_choice_data;
    private WaterRateAdapter waterRateAdapter;

    private List<WaterRateBean> vendorTotals, townTotals;
    private List<String> dateList;
    private String choiceDate;

    public static WaterQualityRateByStcdFragment launch(int pageType) {
        WaterQualityRateByStcdFragment fragment = new WaterQualityRateByStcdFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageType", pageType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_spss;
    }

    @Override
    protected void initData() {
        Bundle bundle = this.getArguments();
        if (bundle != null)
            pageType = bundle.getInt("pageType");
    }

    @Override
    protected void initView(View view) {
        tv_choice_data = findView(R.id.tv_choice_data);
        tv_choice_data.setOnClickListener(this::selectDataListner);
        spssTitleView = findView(R.id.view_spss_title);
        spssTitleView.setData(WATER_QUALITY);
        SegmentTabLayout tabLayout = findView(R.id.tl_1);
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        currectTab = 0;
                        listWaterQualityRateByStcd();
                        break;
                    case 1:
                        currectTab = 1;
                        listWaterQualityRateByVendor();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        setVerModel();
    }

    private int checkedPosition;

    private void selectDataListner(View view) {
        if (dateList == null || dateList.isEmpty()) return;
        new XPopup.Builder(getContext())
                .asCenterList("请选择日期", dateList.toArray(new String[dateList.size()]),
                        null, checkedPosition,
                        (position, text) -> {
                            checkedPosition = position;
                            choiceDate = text;
                            if (currectTab == 0) {
                                listWaterQualityRateByStcd();
                            } else {
                                listWaterQualityRateByVendor();
                            }
                        })
                .show();
    }

    @Override
    protected void initRequestData() {
        listDataTime();
    }

    private void setVerModel() {
        rv = findView(R.id.rv);
        waterRateAdapter = new WaterRateAdapter();
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(waterRateAdapter);
    }

    private void listDataTime() {
        EventManager.getInstance().listDataTime()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<String>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<String>> baseCommonResponse) {
                        dateList = baseCommonResponse.getData();
                        if (dateList != null && !dateList.isEmpty()) {
                            choiceDate = dateList.get(0);
                            listWaterQualityRateByStcd();
                        }
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    /**
     * 按行政区划分析人工水质达标率
     */
    private void listWaterQualityRateByStcd() {
        EventManager.getInstance().listWaterQualityRateByStcd(choiceDate)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WaterRateBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WaterRateBean>> baseCommonResponse) {
                        townTotals = baseCommonResponse.getData();
                        waterRateAdapter.setNewData(townTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    /**
     * 【查询】按企业分析人工水质达标率
     */
    private void listWaterQualityRateByVendor() {
        EventManager.getInstance().listWaterQualityRateByVendor(choiceDate)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WaterRateBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WaterRateBean>> baseCommonResponse) {
                        vendorTotals = baseCommonResponse.getData();
                        waterRateAdapter.setNewData(vendorTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

}
