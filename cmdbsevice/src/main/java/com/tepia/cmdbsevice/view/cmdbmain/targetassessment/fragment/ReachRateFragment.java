package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
public class ReachRateFragment extends BaseCommonFragment {

    private static String[] mTitles = {"行政区划", "企业"};
    private static String[] WATER_QUALITY = {"行政区划", "站点总数", "水质达标率"};

    private int pageType;//页面标识，0故障率统计  1人工水质  2在线水质
    private int currectTab;

    private RecyclerView rv;
    private SpssTitleView spssTitleView;
    private TextView tv_choice_data;
    private SelectDataView selectDataView;
    private ReachRateAdapter reachRateAdapter;

    private List<WaterRateBean> vendorTotals, townTotals;
    private String startTime, endTime;

    public static ReachRateFragment launch(int pageType) {
        ReachRateFragment fragment = new ReachRateFragment();
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
        selectDataView = new SelectDataView(getContext());
        selectDataView.setListener(this::onDataSelectPickListener);
        SegmentTabLayout tabLayout = findView(R.id.tl_1);
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        currectTab = 0;
                        listReachRateByStcd();
                        break;
                    case 1:
                        currectTab = 1;
                        listReachRateByVendor();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        setVerModel();
    }

    private void onDataSelectPickListener(String startTime, String endTime) {
        if (startTime == null || endTime == null) {
            ToastUtils.shortToast("请选择日期");
            return;
        }
        this.startTime = startTime;
        this.endTime = endTime;
        tv_choice_data.setText(String.format("%s - %s", startTime.replace("-", "."), endTime.replace("-", ".")));
        if (currectTab == 0) {
            listReachRateByStcd();
        }
        if (currectTab == 1) {
            listReachRateByVendor();
        }
    }

    private void selectDataListner(View view) {
        new XPopup.Builder(getContext())
                .hasStatusBarShadow(true) //启用状态栏阴影
                .dismissOnTouchOutside(false)
                .asCustom(selectDataView)
                .show();
    }

    @Override
    protected void initRequestData() {
        this.startTime = TimeFormatUtils.getFirstDayOfToday();
        this.endTime = TimeFormatUtils.getLastDayOfToday();
        listReachRateByStcd();
    }

    private void setVerModel() {
        rv = findView(R.id.rv);
        reachRateAdapter = new ReachRateAdapter();
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(reachRateAdapter);
    }

    /**
     * 【查询】实时督办-乡镇报警、故障数
     */
    private void listReachRateByStcd() {
        EventManager.getInstance().listReachRateByStcd("startDate", String.format("%s 00:00:00", startTime), "endDate", String.format("%s 23:59:59", endTime))
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WaterRateBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WaterRateBean>> baseCommonResponse) {
                        townTotals = baseCommonResponse.getData();
                        reachRateAdapter.setNewData(townTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    private void listReachRateByVendor() {
        EventManager.getInstance().listReachRateByVendor("startDate", String.format("%s 00:00:00", startTime), "endDate", String.format("%s 23:59:59", endTime))
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<WaterRateBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<WaterRateBean>> baseCommonResponse) {
                        vendorTotals = baseCommonResponse.getData();
                        reachRateAdapter.setNewData(vendorTotals);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

}