package com.tepia.cmdbsevice.view.alarmstatistics;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.EventManager;
import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.model.event.WarnDetailBean;
import com.tepia.cmdbsevice.view.alarmstatistics.model.FaultInfoModel;
import com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.view.ImageListView;
import com.tepia.cmnwsevice.model.station.StationBean;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Author:xch
 * Date:2019/6/3
 * Description:故障详情页
 */
public class FaultDetailActivity extends BaseActivity {

    private ImageView img_tag;
    private ImageView img_status;
    private TextView tv_time_title;
    private TextView tv_stationStatus;
    private TextView tv_sttp;
    private TextView tv_stnm;
    private TextView tv_areaName;
    private TextView tv_vendorName;
    private TextView tv_alarmTitle;
    private TextView tv_alarmType;
    private TextView tv_status;
    private TextView tv_sendTitle;
    private TextView tv_top_sendTime;
    private TextView tv_surplusHours;
    private LinearLayout ll_time;
    private ViewStub stub_supervise, stub_feedback, stub_complete;

    private String eventId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fault_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("督办详情");
        showBack();

        img_tag = findViewById(R.id.img_tag);
        img_status = findViewById(R.id.img_status);
        tv_time_title = findViewById(R.id.tv_time_title);
        tv_stationStatus = findViewById(R.id.tv_stationStatus);
        tv_sttp = findViewById(R.id.tv_sttp);
        tv_stnm = findViewById(R.id.tv_stnm);
        tv_areaName = findViewById(R.id.tv_areaName);
        tv_vendorName = findViewById(R.id.tv_vendorName);
        tv_alarmTitle = findViewById(R.id.tv_alarmTitle);
        tv_alarmType = findViewById(R.id.tv_alarmType);
        tv_status = findViewById(R.id.tv_status);
        tv_sendTitle = findViewById(R.id.tv_sendTitle);
        tv_top_sendTime = findViewById(R.id.tv_top_sendTime);
        tv_surplusHours = findViewById(R.id.tv_surplusHours);
        ll_time = findViewById(R.id.ll_time);

        stub_supervise = findViewById(R.id.stub_supervise);
        stub_feedback = findViewById(R.id.stub_feedback);
        stub_complete = findViewById(R.id.stub_complete);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            eventId = intent.getStringExtra("eventId");
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        faultInfo();
    }

    private void faultInfo() {
        EventManager.getInstance().faultInfo(eventId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<FaultInfoModel>>(true, "") {

                    @Override
                    protected void _onNext(BaseCommonResponse<FaultInfoModel> baseCommonResponse) {
                        FaultInfoModel faultInfoModel = baseCommonResponse.getData();
                        if (faultInfoModel == null) return;
                        WarnBean warnBean = faultInfoModel.getTop();
                        WarnDetailBean warnDetailBean = faultInfoModel.getInfo();
                        int status = warnBean.getIntStatus();
                        showTop(warnBean);
                        showInfo(status, warnDetailBean);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    private void showTop(WarnBean warnBean) {
        if (warnBean == null) return;
        img_tag.setImageResource(R.mipmap.bkg_fault);
        img_status.setImageResource(R.mipmap.icon_guzhang);
        tv_stationStatus.setText(warnBean.getStationInfo());
        tv_stationStatus.setTextColor(Color.parseColor(warnBean.getStationStatus() == 0 ? "#62C08D" : "#F46B6D"));
        tv_sttp.setTextColor(Color.parseColor("#FF934A"));
        tv_sttp.setBackgroundResource(R.drawable.yel_round_4);
        tv_sttp.setText(warnBean.getSttp());
        tv_stnm.setText(warnBean.getStnm());
        tv_areaName.setText(warnBean.getAreaName());
        tv_vendorName.setText(warnBean.getVendorName());
        tv_vendorName.setVisibility(Strings.isNullOrEmpty(warnBean.getVendorName()) ? View.GONE : View.VISIBLE);
        tv_alarmTitle.setText("故障类型：");
        tv_alarmType.setText(warnBean.getAlarmType());
        tv_status.setText(warnBean.getFaultStatus());
        ll_time.setVisibility(warnBean.getIntStatus() == 5 ? View.GONE : View.VISIBLE);
        tv_status.setBackgroundResource(warnBean.getIntStatus() == 5 ? R.drawable.bg_semi_circle_left_top_green : R.drawable.bg_semi_circle_left_top);
        tv_time_title.setText("超出时间");
        tv_sendTitle.setText("故障时间：");
        tv_top_sendTime.setText(warnBean.getFaultTime());
        tv_surplusHours.setText(Strings.isNullOrEmpty(warnBean.getOverHours()) ? "--" : String.format("%s小时", warnBean.getOverHours()));
    }

    private void showInfo(int status, WarnDetailBean warnDetail) {
        if (warnDetail == null) return;
        View.OnClickListener onClickListener = v -> goStationDetail(warnDetail.getStcd());
        switch (status) {
            case 3:
                stub_supervise.inflate();
                ((TextView) findViewById(R.id.tv_stcd)).setText(warnDetail.getStcd());
                ((TextView) findViewById(R.id.tv_orderCode)).setText(warnDetail.getOrderCode());
                ((TextView) findViewById(R.id.tv_sendTime)).setText(warnDetail.getSendTime());
                ((TextView) findViewById(R.id.tv_faultTime)).setText(String.format("时间：%s", warnDetail.getFaultTime()));
                findViewById(R.id.btn_query).setOnClickListener(onClickListener);
                break;
            case 4:
                stub_feedback.inflate();
                ((TextView) findViewById(R.id.tv_stcd)).setText(warnDetail.getStcd());
                ((TextView) findViewById(R.id.tv_orderCode)).setText(warnDetail.getOrderCode());
                ((TextView) findViewById(R.id.tv_sendTime)).setText(warnDetail.getSendTime());
                ((TextView) findViewById(R.id.tv_faultTime)).setText(String.format("时间：%s", warnDetail.getFaultTime()));
                ((TextView) findViewById(R.id.tv_recoverTime)).setText(String.format("时间：%s", warnDetail.getRecoverTime()));
                findViewById(R.id.btn_query).setOnClickListener(onClickListener);
                break;
            case 5:
                stub_complete.inflate();
                ((TextView) findViewById(R.id.tv_stcd)).setText(warnDetail.getStcd());
                ((TextView) findViewById(R.id.tv_orderCode)).setText(warnDetail.getOrderCode());
                ((TextView) findViewById(R.id.tv_handleDes)).setText(warnDetail.getHandleDes());
                ((TextView) findViewById(R.id.tv_sendTime)).setText(warnDetail.getSendTime());
                ((TextView) findViewById(R.id.tv_faultTime)).setText(String.format("时间：%s", warnDetail.getFaultTime()));
                ((TextView) findViewById(R.id.tv_recoverTime)).setText(String.format("时间：%s", warnDetail.getRecoverTime()));
                ((TextView) findViewById(R.id.tv_backTime)).setText(String.format("反馈时间：%s", warnDetail.getBackTime()));
                ImageListView imageLv = findViewById(R.id.view_imgs);
                List<String> imgUrls = warnDetail.getBackImgUrls();
                if (imgUrls == null || imgUrls.isEmpty()) {
                    imageLv.setVisibility(View.GONE);
                } else {
                    imageLv.addImages(imgUrls);
                    imageLv.setVisibility(View.VISIBLE);
                }
                findViewById(R.id.btn_query).setOnClickListener(onClickListener);
                break;
        }
    }

    private void goStationDetail(String stcd) {
        //跳转站点详情
        List<StationBean> list = DataSupport.where("code=?", stcd).find(StationBean.class);
        if (!CollectionsUtil.isEmpty(list)) {
            ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail)
                    .withString("stationBean", new Gson().toJson(list.get(0))).navigation();
        } else {
            ToastUtils.shortToast("没有该站点");
        }
    }
}
