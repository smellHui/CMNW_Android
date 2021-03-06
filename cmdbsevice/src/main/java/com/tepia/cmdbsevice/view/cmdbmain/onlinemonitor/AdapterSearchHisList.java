package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmdbsevice.databinding.LvSearchHisListBinding;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/16
 * Time            :       15:31
 * Version         :       1.0
 * 功能描述        :
 **/
public class AdapterSearchHisList extends BaseQuickAdapter<String,BaseViewHolder> {
    public AdapterSearchHisList(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        lv_search_his_list
        LvSearchHisListBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvSearchText.setText(item);
    }
}
