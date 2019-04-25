package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter;

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
public class CityCountAdapter extends BaseQuickAdapter<TopTotalBean, BaseViewHolder> {
    public CityCountAdapter() {
        super(R.layout.item_city_count);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopTotalBean item) {
        int pos = helper.getAdapterPosition();
        helper.setBackgroundColor(R.id.ll, Color.parseColor(pos % 2 == 0 ? "#ffffff" : "#F7F9FC"));

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_vendorName, item.getVendorName());
        helper.setText(R.id.tv_total, item.getTotal());
        helper.setText(R.id.tv_alarmNum, item.getAlarmNum());
        helper.setText(R.id.tv_faultNum, item.getFaultNum());

    }
}
