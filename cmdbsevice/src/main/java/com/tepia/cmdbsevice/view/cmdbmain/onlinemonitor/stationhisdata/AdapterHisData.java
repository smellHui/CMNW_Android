package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvWarningViewBinding;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.station.WarningBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/25
 * Time            :       15:20
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterHisData extends BaseQuickAdapter<WarningBean, BaseViewHolder> {
    private String type;

    public AdapterHisData(int layoutResId, @Nullable List<WarningBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarningBean item) {
//lv_warning_view
        LvWarningViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvWarningTypeName.setText(DictManager.getInstance().getDictMap().get("warningType").get(item.getAlarmCode()) + "" + type);
        if (item.getStartTime() != null && item.getEndTime() != null) {
            mBinding.tvTime.setText(item.getStartTime().substring(5, item.getStartTime().length()) + " ~ " + item.getEndTime().substring(5, item.getEndTime().length()));
        } else if (item.getStartTime() != null && item.getEndTime() == null) {
            mBinding.tvTime.setText(item.getStartTime().substring(5, item.getStartTime().length()) + " ~ " + "当前时间");
        }
        if (!TextUtils.isEmpty(item.getDeviceName())) {
            mBinding.tvDeviceName.setVisibility(View.VISIBLE);
            mBinding.tvDeviceName.setText(item.getDeviceName());
        } else {
            mBinding.tvDeviceName.setVisibility(View.GONE);
        }
        mBinding.tvDuration.setText("持续时间 " + item.getDuration() + "小时");
    }

    public void setType(String type) {
        this.type = type;
    }
}
