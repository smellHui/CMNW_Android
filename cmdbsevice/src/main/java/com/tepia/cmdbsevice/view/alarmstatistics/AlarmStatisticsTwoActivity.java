package com.tepia.cmdbsevice.view.alarmstatistics;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.view.alarmstatistics.fragment.FaultFragment;
import com.tepia.cmdbsevice.view.alarmstatistics.fragment.PoliceFragment;
import com.tepia.cmdbsevice.view.alarmstatistics.fragment.WarnFragment;
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

    private String[] mTitles_2 = {"报警", "故障", "群众上报"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<AreaBean> areaBeans, vendorBeans;

    private SegmentTabLayout tab_layout;
    private ViewPager viewPager;
    private SelectEventPopView selectEventPopView;
    private List<RefreshStatiseListener> refreshStatiseListeners = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_statistics_two;
    }

    @Override
    public void initView() {
        setCenterTitle("事件督办");
        showBack();

        setRightTextEvent("筛选", R.mipmap.icon_shaixun, v -> {
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(selectEventPopView)
                    .show();
        });

        selectEventPopView = new SelectEventPopView(getContext());
        selectEventPopView.setListener(this::SelectEventListener);

        tab_layout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.vp);

        WarnFragment warnFragment = WarnFragment.launch(1);
        FaultFragment faultFragment = FaultFragment.launch(1);
        PoliceFragment policeFragment = PoliceFragment.launch(1);
        refreshStatiseListeners.add(warnFragment);
        refreshStatiseListeners.add(faultFragment);
        refreshStatiseListeners.add(policeFragment);

        mFragments.add(warnFragment);
        mFragments.add(faultFragment);
        mFragments.add(policeFragment);

        tab_layout.setTabData(mTitles_2);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), mFragments, mTitles_2));
        tab_layout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
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
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(0);

        areaList();
        vendorList();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    private void SelectEventListener(SelectParamModel selectParamModel) {
        selectEventPopView.dismiss();
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
                        selectEventPopView.setAreaData(areaBeans);
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
                        selectEventPopView.setVendorData(vendorBeans);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }
}
