package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvStationTypeItemViewBinding;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/17
 * Time            :       9:36
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterStationTypeList extends BaseQuickAdapter<StationTypeBean,BaseViewHolder> {
    public AdapterStationTypeList(int layoutResId, @Nullable List<StationTypeBean> data) {
        super(layoutResId, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, StationTypeBean item) {
//        lv_station_type_item_view
        LvStationTypeItemViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.ivStation.setBackground(mContext.getDrawable(item.getBackground()));
        mBinding.ivStation.setImageResource(item.getSrc());
    }
}
