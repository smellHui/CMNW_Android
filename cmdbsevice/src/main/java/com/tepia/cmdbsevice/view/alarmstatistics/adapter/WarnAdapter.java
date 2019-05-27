package com.tepia.cmdbsevice.view.alarmstatistics.adapter;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.common.base.Strings;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.model.event.WarnDetailBean;
import com.tepia.cmdbsevice.view.alarmstatistics.interfe.ReportItemChildClickListener;
import com.tepia.cmdbsevice.view.alarmstatistics.model.FlowModel;
import com.tepia.cmdbsevice.view.alarmstatistics.model.ReportModel;

/**
 * Author:xch
 * Date:2019/5/24
 * Description:实时督办-报警列表
 */
public class WarnAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int PAGE_POLICE = 1;//报警
    public static final int PAGE_FAULT = PAGE_POLICE << 1;//故障
    public static final int PAGE_REPORT = PAGE_FAULT << 1;//上报

    private int pageType;

    public WarnAdapter(int pageType) {
        super(null);
        this.pageType = pageType;
        addItemTypes();
    }

    private void addItemTypes() {
        addItemType(-1, R.layout.item_report_to_lead);
        switch (this.pageType) {
            case PAGE_POLICE:
                addItemType(1, R.layout.item_has_sent_view);
                addItemType(5, R.layout.item_close_view);
                break;
            case PAGE_FAULT:
                addItemType(3, R.layout.item_supervised_view);
                addItemType(4, R.layout.view_wait_feedback);
                addItemType(5, R.layout.view_has_complete);
                break;
            case PAGE_REPORT:
                addItemType(1, R.layout.item_report_list);
                addItemType(2, R.layout.item_report_list);
                addItemType(3, R.layout.item_report_list);
                addItemType(4, R.layout.item_report_list);
                break;
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case -1:
                WarnBean warnBean = (WarnBean) item;
                switch (pageType) {
                    case PAGE_POLICE:
                        helper.setImageResource(R.id.img_tag, R.mipmap.bkg_baojing);
                        helper.setText(R.id.tv_time_title, "剩余时间");
                        helper.setTextColor(R.id.tv_alarmType, Color.parseColor("#FF934A"));
                        helper.setTextColor(R.id.tv_sttp, Color.parseColor("#FF934A"));
                        helper.setBackgroundRes(R.id.tv_sttp, R.drawable.yel_round_4);
                        helper.setText(R.id.tv_sttp, warnBean.getSttp());
                        helper.setText(R.id.tv_stnm, warnBean.getStnm());
                        helper.setText(R.id.tv_areaName, warnBean.getAreaName());
                        helper.setText(R.id.tv_vendorName, warnBean.getVendorName());
                        helper.setVisible(R.id.tv_vendorName, !Strings.isNullOrEmpty(warnBean.getVendorName()));
                        helper.setText(R.id.tv_alarmTitle, "报警类型：");
                        helper.setText(R.id.tv_alarmType, warnBean.getAlarmType());
                        helper.setVisible(R.id.tv_status, !Strings.isNullOrEmpty(warnBean.getStatus()));
                        helper.setText(R.id.tv_status, warnBean.getStatus());
                        helper.setText(R.id.tv_time_title, "剩余时间");
                        helper.setText(R.id.tv_sendTitle, "报警时间：");
                        helper.setText(R.id.tv_sendTime, warnBean.getSendTime());
                        helper.setText(R.id.tv_surplusHours, Strings.isNullOrEmpty(warnBean.getSurplusHours()) ? "--" : String.format("%s小时", warnBean.getSurplusHours()));
                        break;
                    case PAGE_FAULT:
                        helper.setImageResource(R.id.img_tag, R.mipmap.bkg_baojing);
                        helper.setText(R.id.tv_time_title, "剩余时间");
                        helper.setTextColor(R.id.tv_alarmType, Color.parseColor("#FF934A"));
                        helper.setTextColor(R.id.tv_sttp, Color.parseColor("#FF934A"));
                        helper.setBackgroundRes(R.id.tv_sttp, R.drawable.yel_round_4);
                        helper.setText(R.id.tv_sttp, warnBean.getSttp());
                        helper.setText(R.id.tv_stnm, warnBean.getStnm());
                        helper.setText(R.id.tv_areaName, warnBean.getAreaName());
                        helper.setText(R.id.tv_vendorName, warnBean.getVendorName());
                        helper.setVisible(R.id.tv_vendorName, !Strings.isNullOrEmpty(warnBean.getVendorName()));
                        helper.setText(R.id.tv_alarmTitle, "报警类型：");
                        helper.setText(R.id.tv_alarmType, warnBean.getAlarmType());
                        helper.setVisible(R.id.tv_status, !Strings.isNullOrEmpty(warnBean.getStatus()));
                        helper.setText(R.id.tv_status, warnBean.getStatus());
                        helper.setText(R.id.tv_time_title, "超出时间");
                        helper.setText(R.id.tv_sendTitle, "报警时间：");
                        helper.setText(R.id.tv_sendTime, warnBean.getSendTime());
                        helper.setText(R.id.tv_surplusHours, Strings.isNullOrEmpty(warnBean.getSurplusHours()) ? "--" : String.format("%s小时", warnBean.getSurplusHours()));
                        break;
                    case PAGE_REPORT:
                        helper.setImageResource(R.id.img_tag, R.mipmap.bkg_baojing);
                        helper.setTextColor(R.id.tv_alarmType, Color.parseColor("#FF934A"));
                        helper.setVisible(R.id.tv_sttp, false);
                        helper.setVisible(R.id.ll_time, false);
                        helper.setText(R.id.tv_stnm, String.format("督办单号：%s", warnBean.getOrderCode()));
                        helper.setText(R.id.tv_areaName, warnBean.getAreaName());
                        helper.setText(R.id.tv_vendorName, warnBean.getVendorName());
                        helper.setVisible(R.id.tv_vendorName, !Strings.isNullOrEmpty(warnBean.getVendorName()));
                        helper.setText(R.id.tv_alarmTitle, "报警地址：");
                        helper.setText(R.id.tv_alarmType, warnBean.getAddr());
                        helper.setVisible(R.id.tv_status, !Strings.isNullOrEmpty(warnBean.getStatus()));
                        helper.setText(R.id.tv_status, warnBean.getStatus());
                        helper.setText(R.id.tv_sendTitle, "督办时间：");
                        helper.setText(R.id.tv_sendTime, warnBean.getSuperviseTime());
                        break;
                }
                break;
            case 1://已派单  待反馈
                switch (pageType) {
                    case PAGE_POLICE:
                        WarnDetailBean warnDetailBean = (WarnDetailBean) item;
                        helper.setText(R.id.tv_stcd, warnDetailBean.getStcd());
                        helper.setText(R.id.tv_orderCode, warnDetailBean.getOrderCode());
                        break;
                    case PAGE_FAULT:
                        WarnDetailBean warnDetail = (WarnDetailBean) item;
                        helper.setText(R.id.tv_stcd, warnDetail.getStcd());
                        helper.setText(R.id.tv_orderCode, warnDetail.getOrderCode());
                        break;
                    case PAGE_REPORT:
                        ReportModel reportModel = (ReportModel) item;
                        RecyclerView rv = helper.getView(R.id.rv_report);
                        setReportRv(reportModel, rv);
                        break;
                }
                break;
            case 2://待审核
                switch (pageType) {
                    case PAGE_POLICE:

                        break;
                    case PAGE_FAULT:

                        break;
                    case PAGE_REPORT:
                        ReportModel reportModel = (ReportModel) item;
                        RecyclerView rv = helper.getView(R.id.rv_report);
                        setReportRv(reportModel, rv);
                        break;
                }
                break;
            case 3://已督办  退回中
                switch (pageType) {
                    case PAGE_POLICE:

                        break;
                    case PAGE_FAULT:
                        WarnDetailBean warnDetail = (WarnDetailBean) item;
                        helper.setText(R.id.tv_stcd, warnDetail.getStcd());
                        helper.setText(R.id.tv_orderCode, warnDetail.getOrderCode());
                        helper.setText(R.id.tv_handleDes, warnDetail.getHandleDes());
                        helper.setText(R.id.tv_faultTime, String.format("时间：%s", warnDetail.getFaultTime()));
                        break;
                    case PAGE_REPORT:
                        ReportModel reportModel = (ReportModel) item;
                        RecyclerView rv = helper.getView(R.id.rv_report);
                        setReportRv(reportModel, rv);
                        break;
                }
                break;
            case 4://未反馈   已完结
                switch (pageType) {
                    case PAGE_POLICE:

                        break;
                    case PAGE_FAULT:
                        WarnDetailBean warnDetail = (WarnDetailBean) item;
                        helper.setText(R.id.tv_stcd, warnDetail.getStcd());
                        helper.setText(R.id.tv_orderCode, warnDetail.getOrderCode());
                        helper.setText(R.id.tv_faultTime, String.format("时间：%s", warnDetail.getFaultTime()));
                        helper.setText(R.id.tv_recoverTime, String.format("时间：%s", warnDetail.getRecoverTime()));
                        break;
                    case PAGE_REPORT:
                        ReportModel reportModel = (ReportModel) item;
                        RecyclerView rv = helper.getView(R.id.rv_report);
                        setReportRv(reportModel, rv);
                        break;
                }
                break;
            case 5://已完结
                switch (pageType) {
                    case PAGE_POLICE:
                        WarnDetailBean warnDetailBean = (WarnDetailBean) item;
                        helper.setText(R.id.tv_stcd, warnDetailBean.getStcd());
                        helper.setText(R.id.tv_orderCode, warnDetailBean.getOrderCode());
                        helper.setText(R.id.tv_recoverTime, String.format("时间：%s", warnDetailBean.getRecoverTime()));
                        break;
                    case PAGE_FAULT:
                        WarnDetailBean warnDetail = (WarnDetailBean) item;
                        helper.setText(R.id.tv_stcd, warnDetail.getStcd());
                        helper.setText(R.id.tv_orderCode, warnDetail.getOrderCode());
                        helper.setText(R.id.tv_handleDes, warnDetail.getHandleDes());
                        helper.setText(R.id.tv_faultTime, String.format("时间：%s", warnDetail.getFaultTime()));
                        helper.setText(R.id.tv_recoverTime, String.format("时间：%s", warnDetail.getRecoverTime()));
                        helper.setText(R.id.tv_backTime, String.format("反馈时间：%s", warnDetail.getBackTime()));
                        break;
                    case PAGE_REPORT:

                        break;
                }
                break;
        }
    }

    private void setReportRv(ReportModel reportModel, RecyclerView rv) {
        ReportAdapter reportAdapter = new ReportAdapter(reportModel.getFlowList(), reportModel.getEventId());
        rv.setLayoutManager(new WrapLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(mContext, 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(reportAdapter);
        reportAdapter.setOnItemChildClickListener((baseQuickAdapter, view, position) -> addReportItemChildClick(rv, baseQuickAdapter, view, position));
    }

    private void addReportItemChildClick(RecyclerView rv, BaseQuickAdapter baseQuickAdapter, View view, int position) {
        if (reportItemChildClickListener != null) {
            FlowModel flowModel = (FlowModel) baseQuickAdapter.getItem(position);
            EditText contentEt = (EditText) baseQuickAdapter.getViewByPosition(rv, position, R.id.et_content);
            String content = "";
            if (contentEt != null && contentEt.getText() != null)
                content = contentEt.getText().toString().trim();
            reportItemChildClickListener.addReportItemChildClick(view, flowModel, content);
        }
    }

    private ReportItemChildClickListener reportItemChildClickListener;

    public void setReportItemChildClickListener(ReportItemChildClickListener reportItemChildClickListener) {
        this.reportItemChildClickListener = reportItemChildClickListener;
    }
}
