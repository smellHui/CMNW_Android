package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.model.event.TopTotalBean;
import com.tepia.cmdbsevice.model.event.WaterRateBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:水质adapter
 */
public class WaterRateAdapter extends BaseQuickAdapter<WaterRateBean, BaseViewHolder> {

    public WaterRateAdapter() {
        super(R.layout.item_water_rate_count);
    }

    @Override
    protected void convert(BaseViewHolder helper, WaterRateBean item) {
        int pos = helper.getAdapterPosition();
        helper.setBackgroundColor(R.id.ll, Color.parseColor(pos % 2 == 0 ? "#ffffff" : "#F7F9FC"));

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_stationCount, item.getStationCount());
        helper.setText(R.id.tv_sampleCount, item.getSampleCount());
        helper.setText(R.id.tv_targetCount, item.getTargetCount());
        helper.setText(R.id.tv_rate, item.getRate()+"%");
    }
}
