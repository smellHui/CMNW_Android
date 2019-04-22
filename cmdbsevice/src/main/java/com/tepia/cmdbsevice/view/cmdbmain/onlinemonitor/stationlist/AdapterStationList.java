package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvStationListItemViewBinding;
import com.tepia.cmdbsevice.model.station.StationBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/17
 * Time            :       11:26
 * Version         :       1.0
 * 功能描述        :        测站list 适配器
 **/
public class AdapterStationList extends BaseQuickAdapter<StationBean,BaseViewHolder> {
    public AdapterStationList(int layoutResId, @Nullable List<StationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StationBean item) {
//lv_station_list_item_view
        LvStationListItemViewBinding  mBinding  = DataBindingUtil.bind(helper.itemView);
        mBinding.tvStationName.setText(item.getName());
        mBinding.tvStationAdress.setText(item.getAddress());
    }
}
