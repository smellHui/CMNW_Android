package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.adapter;

import android.content.Context;
import android.content.res.Resources;

import com.bumptech.glide.load.engine.Resource;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.order.OrderBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:事件adapter
 */
public class SpssCountAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    private Resources resources;

    public SpssCountAdapter(Context ctx) {
        super(R.layout.item_city_count);
        resources = ctx.getResources();
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        int pos = helper.getAdapterPosition();
        helper.setBackgroundColor(R.id.ll, resources.getColor(pos % 2 == 0 ? R.color.white : R.color.color_F7F9FC));
    }
}
