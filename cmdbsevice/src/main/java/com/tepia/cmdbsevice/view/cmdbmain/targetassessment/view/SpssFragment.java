package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter.CityCountAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter.SpssCountAdapter;
import com.tepia.cmnwsevice.model.order.OrderBean;
import com.tepia.cmnwsevice.view.main.OrderPresenter;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/18
 * Description: 统计分析
 */
public class SpssFragment extends BaseCommonFragment {

    private static String[] mTitles = {"行政区划", "企业"};
    private static String[] FAULT_RATES = {"行政区划", "报警总数", "故障总数", "故障率"};
    private static String[] WATER_QUALITY = {"行政区划", "站点总数", "达标站点数", "水质达标率"};

    private int pageType;//页面标识，0故障率统计  1人工水质  2在线水质

    private RecyclerView rv;
    private SpssTitleView spssTitleView;
    private SpssCountAdapter spssCountAdapter;

    private List<TopTotalBean> vendorTotals, townTotals;

    public static SpssFragment launch(int pageType) {
        SpssFragment fragment = new SpssFragment();
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
        spssTitleView = findView(R.id.view_spss_title);
        spssTitleView.setData(pageType == 0 ? FAULT_RATES : WATER_QUALITY);
        SegmentTabLayout tabLayout = findView(R.id.tl_1);
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        countFaultRateByTown(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
                        break;
                    case 1:
                        countFaultRateByVendor(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        setVerModel();
    }

    @Override
    protected void initRequestData() {
        countFaultRateByTown(TimeFormatUtils.getFirstDayOfToday(), TimeFormatUtils.getLastDayOfToday());
    }

    private void setVerModel() {
        rv = findView(R.id.rv);
        spssCountAdapter = new SpssCountAdapter(getContext());
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(spssCountAdapter);
    }

    /**
     * 【查询】实时督办-乡镇报警、故障数
     *
     * @param startTime
     * @param endTime
     */
    private void countFaultRateByTown(String startTime, String endTime) {
        EventManager.getInstance().countFaultRateByTown("startTime", startTime, "endTime", endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<TopTotalBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<TopTotalBean>> baseCommonResponse) {
                        townTotals = baseCommonResponse.getData();
                        spssCountAdapter.setNewData(townTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    private void countFaultRateByVendor(String startTime, String endTime) {
        EventManager.getInstance().countFaultRateByVendor("startTime", startTime, "endTime", endTime)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<TopTotalBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<TopTotalBean>> baseCommonResponse) {
                        vendorTotals = baseCommonResponse.getData();
                        spssCountAdapter.setNewData(vendorTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

}
