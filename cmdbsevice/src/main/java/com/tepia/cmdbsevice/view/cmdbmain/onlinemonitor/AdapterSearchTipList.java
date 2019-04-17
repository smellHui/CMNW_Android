package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.model.station.StationBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/16
 * Time            :       16:22
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterSearchTipList extends BaseQuickAdapter<StationBean,BaseViewHolder> {
    public AdapterSearchTipList(int layoutResId, @Nullable List<StationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StationBean item) {

    }
}
