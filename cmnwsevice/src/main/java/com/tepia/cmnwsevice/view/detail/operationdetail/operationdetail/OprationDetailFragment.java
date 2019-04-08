package com.tepia.cmnwsevice.view.detail.operationdetail.operationdetail;


import android.databinding.DataBindingUtil;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.FragmentOprationDetailBinding;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.order.OperationBean;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/8 17:34
 * @修改人 ：
 * @修改时间 :       2019/4/8 17:34
 * @功能描述 :      运维详情
 **/
@Route(path = "")
public class OprationDetailFragment extends MVPBaseFragment<OprationDetailContract.View, OptationDetailPresenter> implements OprationDetailContract.View {

    public String orderId;
    private FragmentOprationDetailBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_opration_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
    }

    @Override
    protected void initRequestData() {
        mPresenter.getOrderOprationDetail(orderId);
    }

    @Override
    public void getOrderOprationDetailSuccess(OperationBean data) {
        refreshView(data);
    }

    private void refreshView(OperationBean data) {
        mBinding.tvWorkerName.setText(data.getExecuteUserName());
        mBinding.tvWorkerPhone.setText(data.getExecuteUserPhone());
        mBinding.tvStartTime.setText(data.getExecuteStartTime());
        mBinding.tvEndTime.setText(data.getExecuteEndTime());
        mBinding.tvDelayHours.setText(data.getDelayHours() + "小时");
        mBinding.tvHandlerTime.setText(data.getHandleHours() + "小时");
        if (Float.parseFloat(data.getOverHours()) > 0) {
            mBinding.tvOverTime.setText("是 （超时 " + data.getOverHours() + "小时)");
        } else {
            mBinding.tvOverTime.setText("否");
        }
        mBinding.tvRepairType.setText(DictManager.getInstance().getDictMap().get("repairType").get(data.getRepairType()));
        mBinding.tvProblemType.setText(DictManager.getInstance().getDictMap().get("problemType").get(data.getProblemType()));
        if ("1".equals(data.getPartsUse())) {
            mBinding.tvPartsUse.setText("已使用");
        } else {
            mBinding.tvPartsUse.setText("未使用");
        }
        mBinding.tvHandleDes.setText(data.getHandleDes());
    }
}
