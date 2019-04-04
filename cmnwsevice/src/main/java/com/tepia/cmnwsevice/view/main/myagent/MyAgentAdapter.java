package com.tepia.cmnwsevice.view.main.myagent;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.RiverBean;

/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public class MyAgentAdapter extends BaseQuickAdapter<RiverBean, BaseViewHolder> {

    public MyAgentAdapter() {
        super(R.layout.item_event);
    }

    @Override
    protected void convert(BaseViewHolder helper, RiverBean item) {

    }
}
