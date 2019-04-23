package com.tepia.cmdbsevice.view.alarmstatistics;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.AreaBean;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.alarmstatistics.adapter.AlermStatisAdapter;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.SelectEventPopView;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警统计
 */
public class AlarmStatisticsActivity extends BaseListActivity<WarnBean> {

    private static String[] mTitles = {"故障站点", "报警站点"};

    private List<WarnBean> warnBeans;
    private List<AreaBean> areaBeans, vendorBeans;
    private SelectEventPopView selectEventPopView;

    private List<String> vendorCodeArray, areaCodeArray;
    private String status = "1";//1.故障站点   2.报警站点
    private String stationType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_statistics;
    }

    @Override
    public void initView() {
        super.initView();
        setCenterTitle("报警统计");
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
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                status = (position + 1) + "";
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

    private void SelectEventListener(List<String> areaNames, List<String> vendorNames, String stationType) {
        this.stationType = stationType;
        this.areaCodeArray = areaNames;
        this.vendorCodeArray = vendorNames;
        super.refresh();
        selectEventPopView.dismiss();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new AlermStatisAdapter();
    }

    @Override
    protected void initRequestData() {
        listByWarning();
    }

    public void setOnItemChildClickListener(BaseQuickAdapter adapter, View view, int position) {
        int i = view.getId();
        if (i == R.id.btn_look) {
            new XPopup.Builder(getContext())
//                        .enableDrag(false)
                    .asBottomList(null, new String[]{"提升井", "风机"},
                            (position1, text) -> ToastUtils.shortToast("click " + text))
                    .show();
        }
    }

    private void listByWarning() {
        EventManager.getInstance().listByWarning("pageSize", 20, "pageIndex", getPage()
                , "stationType", stationType, "status", status, "vendorCodeArray", vendorCodeArray, "areaCodeArray", areaCodeArray)
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
