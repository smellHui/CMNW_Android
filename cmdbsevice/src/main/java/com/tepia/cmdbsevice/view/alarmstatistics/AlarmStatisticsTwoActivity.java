package com.tepia.cmdbsevice.view.alarmstatistics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import androidx.annotation.RequiresApi;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.SoftHideKeyBoardUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.view.alarmstatistics.fragment.FaultFragment;
import com.tepia.cmdbsevice.view.alarmstatistics.fragment.ReportFragment;
import com.tepia.cmdbsevice.view.alarmstatistics.fragment.PoliceFragment;
import com.tepia.cmdbsevice.view.alarmstatistics.interfe.RefreshStatiseListener;
import com.tepia.cmdbsevice.view.alarmstatistics.model.SelectParamModel;
import com.tepia.cmdbsevice.view.alarmstatistics.view.SelectEventPopView;
import com.tepia.cmnwsevice.adapter.PageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:xch
 * Date:2019/5/21
 * Description:事件督办 - 二期
 */
public class AlarmStatisticsTwoActivity extends BaseActivity {

    public final static int REPORT_SITE = 2;//群众上报
    public final static int FAULT_SITE = REPORT_SITE >> 1;//故障站点
    public final static int ALARM_SITE = FAULT_SITE >> 1;//报警站点

    private int tabIndex;

    private String[] mTitles_2 = {"报警", "故障", "群众上报"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<AreaBean> areaBeans, vendorBeans;

    private SegmentTabLayout tab_layout;
    private ViewPager viewPager;
    private SelectEventPopView selectFaultPopView;
    private SelectEventPopView selectPolicePopView;
    private SelectEventPopView selectReportPopView;
    private List<RefreshStatiseListener> refreshStatiseListeners = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoftHideKeyBoardUtil.assistActivity(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_statistics_two;
    }

    @Override
    public void initView() {
        setCenterTitle("事件督办");
        showBack();

        setRightTextEvent("筛选", R.mipmap.icon_shaixun, v -> {
            BasePopupView basePopupView = null;
            switch (tabIndex) {
                case FAULT_SITE:
                    basePopupView = selectFaultPopView;
                    break;
                case ALARM_SITE:
                    basePopupView = selectPolicePopView;
                    break;
                case REPORT_SITE:
                    basePopupView = selectReportPopView;
                    break;
            }
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(basePopupView)
                    .show();
        });

        selectFaultPopView = new SelectEventPopView(getContext());
        selectFaultPopView.setListener(this::SelectEventListener);
        selectFaultPopView.setStateData(FAULT_SITE);
        selectPolicePopView = new SelectEventPopView(getContext());
        selectPolicePopView.setListener(this::SelectEventListener);
        selectPolicePopView.setStateData(ALARM_SITE);
        selectReportPopView = new SelectEventPopView(getContext());
        selectReportPopView.setListener(this::SelectEventListener);
        selectReportPopView.setStateData(REPORT_SITE);

        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.vp);

        PoliceFragment policeFragment = PoliceFragment.launch(1);
        FaultFragment faultFragment = FaultFragment.launch(1);
        ReportFragment reportFragment = ReportFragment.launch(1);
        refreshStatiseListeners.add(policeFragment);
        refreshStatiseListeners.add(faultFragment);
        refreshStatiseListeners.add(reportFragment);

        mFragments.add(policeFragment);
        mFragments.add(faultFragment);
        mFragments.add(reportFragment);

        tab_layout.setTabData(mTitles_2);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), mFragments, mTitles_2));
        tab_layout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
                tabIndex = position;
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tab_layout.setCurrentTab(i);
                tabIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(tabIndex);

        areaList();
        vendorList();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            tabIndex = intent.getIntExtra("tabIndex", 0);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void SelectEventListener(SelectParamModel selectParamModel) {
        refreshStatiseListeners.forEach(listener -> {
            switch (tabIndex) {
                case ALARM_SITE:
                    if (listener instanceof PoliceFragment) {
                        listener.refreshList(selectParamModel);
                        selectPolicePopView.dismiss();
                    }
                    break;
                case FAULT_SITE:
                    if (listener instanceof FaultFragment) {
                        listener.refreshList(selectParamModel);
                        selectFaultPopView.dismiss();
                    }
                    break;
                case REPORT_SITE:
                    if (listener instanceof ReportFragment) {
                        listener.refreshList(selectParamModel);
                        selectReportPopView.dismiss();
                    }
                    break;
            }
        });
    }

    /**
     * 【查询】获取乡镇列表
     */
    private void areaList() {
        EventManager.getInstance().arealist()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<AreaBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<AreaBean>> baseCommonResponse) {
                        areaBeans = baseCommonResponse.getData();
                        selectPolicePopView.setAreaData(areaBeans);
                        selectFaultPopView.setAreaData(areaBeans);
                        selectReportPopView.setAreaData(areaBeans);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    /**
     * 【查询】获取企业列表
     */
    private void vendorList() {
        EventManager.getInstance().vendorlist()
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<List<AreaBean>>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<List<AreaBean>> baseCommonResponse) {
                        vendorBeans = baseCommonResponse.getData();
                        selectPolicePopView.setVendorData(vendorBeans);
                        selectFaultPopView.setVendorData(vendorBeans);
                        selectReportPopView.setVendorData(vendorBeans);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }
}
