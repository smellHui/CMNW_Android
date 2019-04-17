package com.tepia.cmdbsevice.view.alarmstatistics;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.order.OrderBean;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:报警adapter
 */
public class AlermStatisAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    public AlermStatisAdapter() {
        super(R.layout.item_site_state);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBean item) {
        helper.addOnClickListener(R.id.btn_look);
    }
}
