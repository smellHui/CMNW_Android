package com.tepia.cmdbsevice.view.alarmstatistics.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.alarmstatistics.adapter.WarnAdapter;
import com.tepia.cmdbsevice.view.alarmstatistics.interfe.RefreshStatiseListener;
import com.tepia.cmdbsevice.view.alarmstatistics.model.ReportModel;
import com.tepia.cmdbsevice.view.alarmstatistics.model.SelectParamModel;
import com.tepia.cmnwsevice.model.station.StationBean;

import org.litepal.crud.DataSupport;

import java.util.Arrays;
import java.util.List;

import static com.tepia.cmdbsevice.model.event.WarnBean.ITEM_HISTORY;

/**
 * Author:xch
 * Date:2019/5/21
 * Description:
 */
public class ReportFragment extends BaseListFragment<WarnBean> implements RefreshStatiseListener {

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;
    private int pageType;
    private SelectParamModel selectParamModel;

    public static ReportFragment launch(int pageType) {
        ReportFragment reportFragment = new ReportFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageType", pageType);
        reportFragment.setArguments(bundle);
        return reportFragment;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            pageType = bundle.getInt("pageType");
        }

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ((WarnAdapter) getAdapter()).setReportItemChildClickListener(this::addReportItemChildClick);
    }

    @Override
    protected void initRequestData() {
        todoReportList();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new WarnAdapter(WarnAdapter.PAGE_REPORT);
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        WarnBean warnBean = (WarnBean) adapter.getItem(position);
        if (warnBean == null || warnBean.getItemType() == ITEM_HISTORY) return;
        if (!warnBean.hasSubItem()) {
            ReportModel reportModel = new ReportModel(warnBean.getIntStatus(), warnBean.getEventId());
            warnBean.addSubItem(reportModel);
        }
        if (warnBean.isExpanded()) {
            getAdapter().collapse(position, false);
        } else {
            simpleInfo(warnBean.getEventId(), position);
        }
    }

    @Override
    public void refreshList(SelectParamModel selectParamModel) {
        this.selectParamModel = selectParamModel;
        super.refresh();
    }

    public void addReportItemChildClick(View view, ReportModel reportModel, String content) {
        if (reportModel == null) return;
        int id = view.getId();
        if (id == R.id.btn_back) {
//            refresh();
            examine(reportModel.getEventId(), "1", content);
        }
        if (id == R.id.btn_pass) {
//            refresh();
            examine(reportModel.getEventId(), "0", content);
        }
        if (id == R.id.btn_query) {
            //跳转站点详情
            if (Strings.isNullOrEmpty(reportModel.getStcd())) {
                ToastUtils.shortToast("暂无该站点id");
                return;
            }
            List<StationBean> list = DataSupport.where("code=?", reportModel.getStcd()).find(StationBean.class);
            if (!CollectionsUtil.isEmpty(list)) {
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                        .withString("stationBean", new Gson().toJson(list.get(0))).navigation();
            } else {
                ToastUtils.shortToast("没有该站点");
            }
        }
    }

    private boolean isShowHistory = false;

    @Override
    public void success(PageBean<WarnBean> k) {
        if (k != null) {
            //第一页时把isShowHistory置为false
            if (page == 1) isShowHistory = false;

            List<WarnBean> list = k.getResult();
            if (list != null && !list.isEmpty() && !isShowHistory) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    long count = list.stream().filter(bean -> bean.getIntStatus() != 4).count();
                    if (count != 20) {
                        isShowHistory = true;
                        list.add((int) count, new WarnBean(ITEM_HISTORY));
                    }
                }
            }
        }
        super.success(k);
    }

    /**
     * 【查询】查询群众上报事件列表
     */
    private void todoReportList() {
        if (selectParamModel == null) selectParamModel = new SelectParamModel();
        EventManager.getInstance().todoReportList("pageSize", 20
                , "pageIndex", getPage()
                , "status", selectParamModel.getStatus()
                , "stationType", selectParamModel.getStationType()
                , "vendorCodeArray", selectParamModel.getVendorNames()
                , "areaCodeArray", selectParamModel.getAreaNames()
                , "startTime", selectParamModel.getStartDate()
                , "endTime", selectParamModel.getEndDate())
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
     * 查询详情
     *
     * @param eventId
     * @param position
     */
    private void simpleInfo(String eventId, int position) {
        EventManager.getInstance().simpleInfo(eventId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<ReportModel>>(true, "") {

                    @Override
                    protected void _onNext(BaseCommonResponse<ReportModel> baseCommonResponse) {
                        try {
                            ReportModel bean = baseCommonResponse.getData();
                            WarnBean warnBean = (WarnBean) getAdapter().getItem(position);
                            ReportModel warn = (ReportModel) warnBean.getSubItem(0);
//                            CopyPropertiesUtil.copyProperties(bean, warn);
                            warn.setStnm(bean.getStnm());
                            warn.setStationStatus(bean.getStationStatus());
                            warn.setContent(bean.getContent());
                            warn.setImgUrls(bean.getImgUrls());
                            warn.setFlowList(bean.getFlowList());
                            getAdapter().expand(position, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        error();
                    }
                });
    }

    /**
     * 通过，不通过
     *
     * @param eventId
     * @param resultType
     * @param content
     */
    private void examine(String eventId, String resultType, String content) {
        EventManager.getInstance().examine("eventId", eventId, "resultType", resultType, "content", content)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse>(true, "") {

                    @Override
                    protected void _onNext(BaseCommonResponse baseCommonResponse) {
                        ToastUtils.shortToast("提交成功");
                        //主线程更新
                        new Handler().postDelayed(() -> refresh(), 100);
                    }

                    @Override
                    protected void _onError(String message) {
                        error();
                    }
                });
    }
}
