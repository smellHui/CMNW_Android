package com.tepia.cmdbsevice.view.alarmstatistics;

import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.alarmstatistics.adapter.AlermStatisAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;
import com.tepia.cmnwsevice.model.station.StationBean;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警统计
 */
public class AlarmStatisticsActivity extends BaseListActivity<WarnBean> {

    public final static int FAULT_SITE = 0;//故障站点
    public final static int ALARM_SITE = 1;//报警站点

    private static String[] mTitles = {"故障站点", "报警站点"};
    private List<AreaBean> areaBeans, vendorBeans;
    private SelectEventPopView selectEventPopView;

    private List<String> vendorCodeArray, areaCodeArray;
    private volatile int tabIndex;//0.故障站点   1.报警站点
    private String stationType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_statistics;
    }

    @Override
    public void initView() {
        super.initView();
        setCenterTitle("事件督办");
        showBack();

        findViewById(R.id.tv_repeat).setOnClickListener((v) -> {
            new XPopup.Builder(getContext())
                    .popupPosition(PopupPosition.Right)//右边
                    .hasStatusBarShadow(true) //启用状态栏阴影
                    .asCustom(selectEventPopView)
                    .show();
        });
        SegmentTabLayout tabLayout = findViewById(R.id.tl_1);
        tabLayout.setTabData(mTitles);
        tabLayout.setCurrentTab(tabIndex);
        setAdapterTabIndex();
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabIndex = position;
                setAdapterTabIndex();
                refresh();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        selectEventPopView = new SelectEventPopView(getContext());
        selectEventPopView.setListener(this::SelectEventListener);
        areaList();
        vendorList();
    }

    @Override
    protected void initRequest() {
        if (tabIndex == 0) {
            listByFault();
        } else {
            listByWarning();
        }
    }

    private void setAdapterTabIndex(){
        ((AlermStatisAdapter) getAdapter()).setTabType(tabIndex);
    }

    private void SelectEventListener(List<String> areaNames, List<String> vendorNames, String stationType) {
        this.stationType = stationType;
        this.areaCodeArray = areaNames;
        this.vendorCodeArray = vendorNames;
        super.refresh();
        selectEventPopView.dismiss();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            tabIndex = intent.getIntExtra("tabIndex", -1);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new AlermStatisAdapter();
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        if (DoubleClickUtil.isFastDoubleClick()) {
            return;
        }
        WarnBean warnBean = (WarnBean) adapter.getItem(position);
        if (warnBean == null) return;
        //跳转站点详情
        List<StationBean> list = DataSupport.where("code=?", warnBean.getStcd()).find(StationBean.class);
        if (!CollectionsUtil.isEmpty(list)) {
            ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                    .withString("stationBean", new Gson().toJson(list.get(0))).navigation();
        }else {
            ToastUtils.shortToast("没有该站点");
        }

    }

    /**
     * 【查询】实时督办-报警列表
     */
    private void listByWarning() {
        EventManager.getInstance().listByWarning("pageSize", 20, "pageIndex", getPage()
                , "stationType", stationType, "vendorCodeArray", vendorCodeArray, "areaCodeArray", areaCodeArray)
                .safeSubscribe(new LoadingSubject<PageBean<WarnBean>>() {

                    @Override
                    protected void _onNext(PageBean<WarnBean> baseCommonResponse) {
                        success(baseCommonResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        error();
                    }
                });
    }

    /**
     * 【查询】实时督办-故障列表
     */
    private void listByFault() {
        EventManager.getInstance().listByFault("pageSize", 20, "pageIndex", getPage()
                , "stationType", stationType, "vendorCodeArray", vendorCodeArray, "areaCodeArray", areaCodeArray)
                .safeSubscribe(new LoadingSubject<PageBean<WarnBean>>() {

                    @Override
                    protected void _onNext(PageBean<WarnBean> baseCommonResponse) {
                        success(baseCommonResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        error();
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
