package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.LvStationTypeItemViewBinding;

import java.util.ArrayList;
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
public class AdapterStationTypeList extends BaseQuickAdapter<StationTypeBean, BaseViewHolder> {
    public AdapterStationTypeList(int layoutResId, @Nullable List<StationTypeBean> data) {
        super(layoutResId, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void convert(BaseViewHolder helper, StationTypeBean item) {
//        lv_station_type_item_view
        LvStationTypeItemViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        if (item.getBackground() != null) {
            mBinding.ivStation.setBackground(mContext.getDrawable(item.getBackground()));
        }
        if (item.getSrc() != null) {
            mBinding.ivStation.setImageResource(item.getSrc());
        }
        if (item.isSelected()) {
            mBinding.ivSelected.setImageResource(R.drawable.ic_check_circle_green_24dp);
        } else {
            mBinding.ivSelected.setImageResource(R.drawable.bg_select_circle);
        }
        mBinding.tvStationName.setText(item.getName());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                getData().get(helper.getLayoutPosition()).setSelected(!item.isSelected());
                if (getSelectData().size() == 0) {
                    ToastUtils.shortToast("至少选择一个");
                    getData().get(helper.getLayoutPosition()).setSelected(!item.isSelected());
                }
                notifyDataSetChanged();
            }
        });
    }

    public List<StationTypeBean> getSelectData() {
        ArrayList<StationTypeBean> list = new ArrayList<>();
        for (StationTypeBean bean : getData()) {
            if (bean.isSelected()) {
                list.add(bean);
            }
        }
        return list;
    }
}
