package com.tepia.cmnwsevice.view.main;

import android.widget.ImageView;

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
        helper.setText(R.id.tv_orderName, item.getOrderName());
        helper.setText(R.id.tv_areaName, "行政区划：" + item.getAreaName());
        helper.setText(R.id.tv_deviceName, "故障类型：" + item.getDeviceName());
        ImageView tagImg = helper.getView(R.id.img_tag);
        int executeStatus = item.getExecuteStatus();
        tagImg.setImageResource(getTagImg(executeStatus));
    }

    private int getTagImg(int status) {
        switch (status) {
            case 1://待处理
                return R.mipmap.label_todo;
            case 4://已退回
                return R.mipmap.label_return;
            case 2://处理中
                return R.mipmap.label_doing;
            case 3://待审核
                return R.mipmap.label_dsh;
            case 5://已完结
                return R.mipmap.label_finish;
        }
        return R.mipmap.label_todo;
    }
}
