package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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

    @Override
    protected void convert(BaseViewHolder helper, StationTypeBean item) {

    }
}
