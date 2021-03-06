package com.tepia.cmdbsevice.view.alarmstatistics.fragment;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.tepia.cmdbsevice.model.event.WarnDetailBean;
import com.tepia.cmdbsevice.util.CopyPropertiesUtil;
import com.tepia.cmdbsevice.util.UiHelper;
import com.tepia.cmdbsevice.view.alarmstatistics.adapter.WarnAdapter;
import com.tepia.cmdbsevice.view.alarmstatistics.interfe.RefreshStatiseListener;
import com.tepia.cmdbsevice.view.alarmstatistics.model.SelectParamModel;
import com.tepia.cmnwsevice.model.station.StationBean;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.tepia.cmdbsevice.model.event.WarnBean.ITEM_HISTORY;

/**
 * Author:xch
 * Date:2019/5/21
 * Description:实时督办-报警列表
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
    protected void initRequestData() {
        todoReportList();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new WarnAdapter(WarnAdapter.PAGE_POLICE);
    }

    /**
     * 【查询】查询群众上报事件列表
     */
    private void todoReportList() {
        if (selectParamModel == null) selectParamModel = new SelectParamModel();
        EventManager.getInstance().listByWarning("pageSize", 20
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

    private void superviseInfo(String eventId, int position) {
        EventManager.getInstance().superviseInfo(eventId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<WarnDetailBean>>(true, "") {

                    @Override
                    protected void _onNext(BaseCommonResponse<WarnDetailBean> baseCommonResponse) {
                        try {
                            WarnDetailBean bean = baseCommonResponse.getData();
                            if (bean == null) return;
                            WarnBean warnBean = (WarnBean) getAdapter().getItem(position);
                            if (warnBean == null) return;
                            WarnDetailBean warn;
                            if (warnBean.hasSubItem()) {
                                warn = (WarnDetailBean) warnBean.getSubItem(0);
                                warn.setStcd(bean.getStcd());
                                warn.setBackImgUrls(bean.getBackImgUrls());
                                warn.setBackTime(bean.getBackTime());
                                warn.setFaultTime(bean.getFaultTime());
                                warn.setHandleDes(bean.getHandleDes());
                                warn.setId(bean.getId());
                                warn.setOrderCode(bean.getOrderCode());
                                warn.setRecoverTime(bean.getRecoverTime());
                                warn.setSendTime(bean.getSendTime());
                                warn.setStatus(warnBean.getIntStatus());
                            } else {
                                bean.setStatus(warnBean.getIntStatus());
                                warnBean.addSubItem(bean);
                            }
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

    private boolean isShowHistory = false;

    @Override
    public void success(PageBean<WarnBean> k) {
        if (k != null) {
            //第一页时把isShowHistory置为false
            if (page == 1) isShowHistory = false;

            List<WarnBean> list = k.getResult();
            if (list != null && !list.isEmpty() && !isShowHistory) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    //后台数据是已完成排到最后面，所以这里我取未完成的个数做插入索引
                    long count = list.stream().filter(bean -> bean.getIntStatus() < 3).count();
                    //只有一页数据时，防止最后一项加空历史边界
                    if (count != list.size()) {
                        isShowHistory = true;
                        list.add((int) count, new WarnBean(ITEM_HISTORY));
                    }
                }
            }
        }
        super.success(k);
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        //防止点击展开子布局崩溃
        Object bean = adapter.getItem(position);
        if (bean instanceof WarnDetailBean) return;
        WarnBean warnBean = (WarnBean) bean;
        if (warnBean == null || warnBean.getItemType() == ITEM_HISTORY) return;
        if (warnBean.isExpanded()) {
            getAdapter().collapse(position, false);
        } else {
            superviseInfo(warnBean.getEventId(), position);
        }
    }

    @Override
    public void setOnItemChildClickListener(BaseQuickAdapter adapter, View view, int position) {
        WarnDetailBean warnDetailBean = (WarnDetailBean) adapter.getItem(position);
        if (warnDetailBean == null) return;
        int id = view.getId();
        if (id == R.id.btn_query) {
            //跳转站点详情
            List<StationBean> list = DataSupport.where("code=?", warnDetailBean.getStcd()).find(StationBean.class);
            if (!CollectionsUtil.isEmpty(list)) {
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                        .withString("stationBean", new Gson().toJson(list.get(0))).navigation();
            } else {
                ToastUtils.shortToast("没有该站点");
            }
        }
        if (id == R.id.tv_look_detail) {
            UiHelper.goToFaultDetailView(getActivity(), warnDetailBean.getId());
        }
    }

    @Override
    public void refreshList(SelectParamModel selectParamModel) {
        this.selectParamModel = selectParamModel;
        super.refresh();
    }


}
