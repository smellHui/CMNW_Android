package com.tepia.cmdbsevice.view.cmdbmain.eventsupervision.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.order.OrderBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:事件adapter
 */
public class CityCountAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    public CityCountAdapter() {
        super(R.layout.item_city_count);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

    }
}