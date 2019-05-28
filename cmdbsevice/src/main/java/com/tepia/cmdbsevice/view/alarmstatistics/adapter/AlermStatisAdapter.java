package com.tepia.cmdbsevice.view.alarmstatistics.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.common.base.Strings;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.WarnBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警adapter
 */
public class AlermStatisAdapter extends BaseQuickAdapter<WarnBean, BaseViewHolder> {

    private volatile boolean isFault;//true故障站点    false报警站点

    public void setTabType(int tabType) {
        isFault = tabType == 0;
    }

    public AlermStatisAdapter() {
        super(R.layout.item_alerm_statis);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarnBean item) {
        helper.setImageResource(R.id.img_tag, isFault ? R.mipmap.bkg_guzhang : R.mipmap.bkg_police);
        helper.setText(R.id.tv_time_title, isFault ? "超出时间" : "剩余时间");
        helper.setTextColor(R.id.tv_alarmType, Color.parseColor(isFault ? "#F46B6D" : "#FF934A"));
        helper.setTextColor(R.id.tv_sttp, Color.parseColor(isFault ? "#F46B6D" : "#FF934A"));
        helper.setBackgroundRes(R.id.tv_sttp, isFault ? R.drawable.red_round_4 : R.drawable.yel_round_4);
        helper.setText(R.id.tv_sttp, item.getSttp());
        helper.setText(R.id.tv_stnm, item.getStnm());
        helper.setText(R.id.tv_vendorName, item.getVendorName());
        helper.setVisible(R.id.tv_vendorName, !Strings.isNullOrEmpty(item.getVendorName()));
        helper.setText(R.id.tv_alarmType, item.getAlarmType());
        helper.setVisible(R.id.tv_status, !Strings.isNullOrEmpty(item.getStatus()));
        helper.setText(R.id.tv_status, item.getStatus());
        helper.setText(R.id.tv_start_time, item.getStartTime());
        helper.setText(R.id.tv_overHours, isFault ? (Strings.isNullOrEmpty(item.getOverHours()) ? "--" : String.format("%s小时",item.getOverHours())) : (Strings.isNullOrEmpty(item.getSurplusHours()) ? "--" : String.format("%s小时",item.getSurplusHours())));
    }

}
