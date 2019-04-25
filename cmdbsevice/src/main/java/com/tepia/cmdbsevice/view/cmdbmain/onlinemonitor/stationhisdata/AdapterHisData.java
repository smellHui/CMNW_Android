package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
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
    public AdapterHisData(int layoutResId, @Nullable List<WarningBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarningBean item) {
//lv_warning_view
        LvWarningViewBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvWarningTypeName.setText(DictManager.getInstance().getDictMap().get("warningType").get(item.getAlarmCode()) + "");
        mBinding.tvTime.setText(item.getStartTime() + " ~ " + item.getEndTime());
        mBinding.tvDuration.setText("持续时间 " + item.getDuration() + "分钟");
    }
}
