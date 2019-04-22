package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvStationStatusHisItemViewBinding;
import com.tepia.cmdbsevice.model.station.DeviceMonitorDataListBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/22
 * Time            :       16:24
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterStationStatusHis extends BaseQuickAdapter<DeviceMonitorDataListBean, BaseViewHolder> {
    public AdapterStationStatusHis(int layoutResId, @Nullable List<DeviceMonitorDataListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceMonitorDataListBean item) {
//        lv_station_status_his_item_view

        LvStationStatusHisItemViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvDeviceName.setText(item.getDeviceName());
        mBinding.tvTime.setText(item.getTm());
        if (item.getIsFault() == 0) {
            mBinding.tvStatus.setText("正常");
        } else {
            mBinding.tvStatus.setText("故障");
        }
    }
}
