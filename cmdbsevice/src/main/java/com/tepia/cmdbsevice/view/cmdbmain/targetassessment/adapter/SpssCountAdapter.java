package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.TopTotalBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:事件adapter
 */
public class SpssCountAdapter extends BaseQuickAdapter<TopTotalBean, BaseViewHolder> {

    public SpssCountAdapter() {
        super(R.layout.item_spss_count);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void convert(BaseViewHolder helper, TopTotalBean item) {
        int pos = helper.getAdapterPosition();
        helper.setBackgroundColor(R.id.ll, Color.parseColor(pos % 2 == 0 ? "#ffffff" : "#F7F9FC"));

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_alarmNum, item.getAlarmNum());
        helper.setText(R.id.tv_faultNum, item.getFaultNum());
        helper.setText(R.id.tv_rate, String.format("%.2f%%", item.getFaultRate()));

    }
}
