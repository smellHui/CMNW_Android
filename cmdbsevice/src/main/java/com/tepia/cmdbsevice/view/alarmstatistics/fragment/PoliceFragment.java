package com.tepia.cmdbsevice.view.alarmstatistics.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.view.alarmstatistics.adapter.WarnAdapter;
import com.tepia.cmdbsevice.view.alarmstatistics.interfe.RefreshStatiseListener;
import com.tepia.cmdbsevice.view.alarmstatistics.model.FlowModel;
import com.tepia.cmdbsevice.view.alarmstatistics.model.ReportModel;
import com.tepia.cmdbsevice.view.alarmstatistics.model.SelectParamModel;

/**
 * Author:xch
 * Date:2019/5/21
 * Description:
 */
public class PoliceFragment extends BaseListFragment<WarnBean> implements RefreshStatiseListener {

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;
    private int pageType;
    private SelectParamModel selectParamModel;

    public static PoliceFragment launch(int pageType) {
        PoliceFragment policeFragment = new PoliceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageType", pageType);
        policeFragment.setArguments(bundle);
        return policeFragment;
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

    /**
     * 【查询】查询群众上报事件列表
     */
    private void todoReportList() {
        if (selectParamModel == null) selectParamModel = new SelectParamModel();
        EventManager.getInstance().todoReportList("pageSize", 20, "pageIndex", getPage()
                , "stationType", selectParamModel.getStationType(), "vendorCodeArray", selectParamModel.getVendorNames(), "areaCodeArray", selectParamModel.getAreaNames())
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

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        WarnBean warnBean = (WarnBean) adapter.getItem(position);
        if (warnBean == null) return;
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

    public void addReportItemChildClick(View view, FlowModel flowModel, String content) {
        int id = view.getId();
        if (id == R.id.btn_back) {
            examine(flowModel.getEventId(), "1", content);
        }
        if (id == R.id.btn_query) {
            examine(flowModel.getEventId(), "0", content);
        }
    }


    private void examine(String eventId, String resultType, String content) {
        EventManager.getInstance().examine("eventId", eventId, "resultType", resultType, "content", content)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse>(true, "") {

                    @Override
                    protected void _onNext(BaseCommonResponse baseCommonResponse) {
                        ToastUtils.shortToast("提交成功");
                    }

                    @Override
                    protected void _onError(String message) {
                        error();
                    }
                });
    }
}
