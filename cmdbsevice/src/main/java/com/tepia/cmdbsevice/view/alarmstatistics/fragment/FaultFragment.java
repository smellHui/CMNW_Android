package com.tepia.cmdbsevice.view.alarmstatistics.fragment;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.model.event.WarnDetailBean;
import com.tepia.cmdbsevice.util.CopyPropertiesUtil;
import com.tepia.cmdbsevice.view.alarmstatistics.adapter.WarnAdapter;
import com.tepia.cmdbsevice.view.alarmstatistics.interfe.RefreshStatiseListener;
import com.tepia.cmdbsevice.view.alarmstatistics.model.SelectParamModel;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/21
 * Description:实时督办-故障列表
 */
public class FaultFragment extends BaseListFragment<WarnBean> implements RefreshStatiseListener {

    private static final int UNSELECTED = -1;
    private int selectedItem = UNSELECTED;
    private int pageType;
    private SelectParamModel selectParamModel;

    public static FaultFragment launch(int pageType) {
        FaultFragment policeFragment = new FaultFragment();
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
    protected void initRequestData() {
        listByFault();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new WarnAdapter(WarnAdapter.PAGE_FAULT);
    }

    /**
     * 【查询】查询群众上报事件列表
     */
    private void listByFault() {
        if (selectParamModel == null) selectParamModel = new SelectParamModel();
        EventManager.getInstance().listByFault("pageSize", 20, "pageIndex", getPage()
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

    private void superviseInfo(String eventId, int position) {
        EventManager.getInstance().superviseInfo(eventId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<WarnDetailBean>>(true, "") {

                    @Override
                    protected void _onNext(BaseCommonResponse<WarnDetailBean> baseCommonResponse) {
                        try {
                            WarnDetailBean bean = baseCommonResponse.getData();
                            WarnBean warnBean = (WarnBean) getAdapter().getItem(position);
                            WarnDetailBean warn = (WarnDetailBean) warnBean.getSubItem(0);
                            CopyPropertiesUtil.copyProperties(bean, warn);
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
            WarnDetailBean warnDetailBean = new WarnDetailBean(warnBean.getIntStatus());
            warnBean.addSubItem(warnDetailBean);
        }
        if (warnBean.isExpanded()) {
            getAdapter().collapse(position, false);
        } else {
            superviseInfo(warnBean.getEventId(), position);
//            getAdapter().expand(position, false);
        }
    }

    @Override
    public void refreshList(SelectParamModel selectParamModel) {
        this.selectParamModel = selectParamModel;
        super.refresh();
    }
}
