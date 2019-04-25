package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvBaseInfoViewBinding;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/25
 * Time            :       10:11
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterBaseInfoList extends BaseQuickAdapter<KeyValueBean, BaseViewHolder> {
    public AdapterBaseInfoList(int layoutResId, @Nullable List<KeyValueBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyValueBean item) {
//        lv_base_info_view
        LvBaseInfoViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvTip.setText(item.getKey());
        mBinding.tvValue.setText(item.getValue());
    }
}
