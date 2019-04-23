package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.LvStationStatusItemViewBinding;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/23
 * Time            :       10:54
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterStationStatusList extends BaseQuickAdapter<StationTypeBean, BaseViewHolder> {
    public AdapterStationStatusList(int layoutResId, @Nullable List<StationTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StationTypeBean item) {
//lv_station_status_item_view
        LvStationStatusItemViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvStationStatus.setText(item.getName());
        mBinding.tvStationStatus.setBackgroundResource(item.getBackground());
        if (item.isSelected()){
            mBinding.ivSelected.setImageResource(R.drawable.ic_check_circle_green_24dp);
        }else {
            mBinding.ivSelected.setImageResource(R.drawable.bg_select_circle);
        }
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                getData().get(helper.getLayoutPosition()).setSelected(!item.isSelected());
                notifyDataSetChanged();
            }
        });
    }
}
