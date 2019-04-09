package com.tepia.cmnwsevice.view.detail.operationdetail.orderexaminelist;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tepia.cmnwsevice.databinding.LvExamineListBinding;
import com.tepia.cmnwsevice.model.order.ExamineBean;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/9
 * Time            :       8:57
 * Version         :       1.0
 * 功能描述        :        审核记录 list 适配器
 **/
public class AdapterExamineList extends BaseQuickAdapter<ExamineBean, BaseViewHolder> {
    public AdapterExamineList(int layoutResId, @Nullable List<ExamineBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamineBean item) {
        helper.setIsRecyclable(false);
        LvExamineListBinding mBinding = DataBindingUtil.bind(helper.itemView);
        mBinding.tvWorkerName.setText(item.getExecuteUserName());
        mBinding.tvWorkerTime.setText(item.getEndTime());
        if (item.getExecuteResult() == 1) {
            mBinding.tvWorkerType.setText("退回");
            mBinding.loDes.setVisibility(View.VISIBLE);
        } else {
            mBinding.tvWorkerType.setText("通过");
            mBinding.loDes.setVisibility(View.GONE);
        }

        mBinding.tvWorkerDes.setText(item.getExecuteResultDes());
    }
}
