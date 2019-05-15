package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.LvStationSaixunItemViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/23
 * Time            :       9:34
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterStationSaixuanList extends BaseQuickAdapter<StationTypeBean, BaseViewHolder> {
    private boolean isSelectAll;

    public AdapterStationSaixuanList(int layoutResId, @Nullable List<StationTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StationTypeBean item) {
//        lv_station_saixun_item_view
        LvStationSaixunItemViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvName.setText(item.getName());
        if (isSelectAll) {
            mBinding.tvName.setBackgroundResource(R.drawable.bg_select_round_e3faee);
        } else {
            if (item.isSelected()) {
                mBinding.tvName.setBackgroundResource(R.drawable.bg_select_round_e3faee);
            } else {
                mBinding.tvName.setBackgroundResource(R.drawable.bg_select_round);
            }
        }
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
        if (isSelectAll) {
            list.addAll(getData());
        } else {
            for (StationTypeBean bean : getData()) {
                if (bean.isSelected()) {
                    list.add(bean);
                }
            }
        }
        return list;
    }

    public void toggleSelectAll() {
        isSelectAll = !isSelectAll;
        notifyDataSetChanged();
    }

    public boolean isSelectAll() {
        return isSelectAll;
    }
}
