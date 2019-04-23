package com.tepia.cmdbsevice.view.alarmstatistics.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.common.base.Strings;
import com.tepia.base.utils.TimeFormatUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.WarnBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警adapter
 */
public class AlermStatisAdapter extends BaseQuickAdapter<WarnBean, BaseViewHolder> {
    public AlermStatisAdapter() {
        super(R.layout.item_alerm_statis);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarnBean item) {
        helper.setText(R.id.tv_stnm, item.getStnm());
        helper.setText(R.id.tv_vendorName, item.getVendorName());
        helper.setVisible(R.id.tv_vendorName, !Strings.isNullOrEmpty(item.getVendorName()));
        helper.setText(R.id.tv_alarmType, item.getAlarmType());
        helper.setText(R.id.tv_status, item.getStatus());
        helper.setText(R.id.tv_handleMinute, String.format("%dh", Long.parseLong(item.getHandleMinute()) / 60));
//        helper.setText(R.id.tv_last_time, getLastTime(item.getDispatchTime(), item.getHandleMinute()) + "小时");
    }

    /**
     * 获取剩余分钟
     *
     * @param dispatchTime
     * @param handleMinute
     * @return
     */
    private long getLastTime(String dispatchTime, String handleMinute) {
        long handM = Long.parseLong(handleMinute);
        if (handM <= 0l) return 0l;
        return (handM - TimeFormatUtils.coverMinutes(dispatchTime)) / 60;
    }

}
