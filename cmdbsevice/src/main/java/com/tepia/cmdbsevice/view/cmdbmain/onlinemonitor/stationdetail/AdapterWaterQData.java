package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvWaterQItemViewBinding;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail.KeyValueBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/28
 * Time            :       14:05
 * Version         :       1.0
 * 功能描述        :
 **/
class AdapterWaterQData extends BaseQuickAdapter<KeyValueBean, BaseViewHolder> {
    public AdapterWaterQData(int layoutResId, @Nullable List<KeyValueBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyValueBean item) {
//lv_water_q_item_view
        LvWaterQItemViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvName.setText(item.getKey());
        mBinding.tvValue.setText(item.getValue());
    }
}
