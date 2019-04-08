package com.tepia.cmnwsevice.view.main;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.RiverBean;
import com.tepia.cmnwsevice.model.order.OrderBean;

/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {

    public OrderAdapter() {
        super(R.layout.item_event);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {

    }
}
