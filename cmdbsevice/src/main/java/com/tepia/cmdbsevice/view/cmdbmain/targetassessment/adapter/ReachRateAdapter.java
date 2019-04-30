package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.WaterRateBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:在线水质adapter
 */
public class ReachRateAdapter extends BaseQuickAdapter<WaterRateBean, BaseViewHolder> {

    public ReachRateAdapter() {
        super(R.layout.item_reach_rate_count);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void convert(BaseViewHolder helper, WaterRateBean item) {
        int pos = helper.getAdapterPosition();
        helper.setBackgroundColor(R.id.ll, Color.parseColor(pos % 2 == 0 ? "#ffffff" : "#F7F9FC"));

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_stationCount, item.getCount());
        helper.setText(R.id.tv_rate, String.format("%.2f%%", item.getRate()));
    }
}
