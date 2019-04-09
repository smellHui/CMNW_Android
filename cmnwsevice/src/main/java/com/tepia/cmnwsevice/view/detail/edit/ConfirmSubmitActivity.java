package com.tepia.cmnwsevice.view.detail.edit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.ConfirmSubmitDataBindView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.order.OrderParamBean;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:确认提交页面
 */
public class ConfirmSubmitActivity extends BaseActivity implements View.OnClickListener {

    private ConfirmSubmitDataBindView mView;
    private OrderParamBean orderParamBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_submit;
    }

    @Override
    public void initView() {
        mView = DataBindingUtil.bind(mRootView);
        mView.setOnClickListener(this);
        mView.setOrderParamBean(orderParamBean);
        setCenterTitle("2019-3-31卫星村1号站");
        showBack();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null)
            orderParamBean = (OrderParamBean)intent.getSerializableExtra("orderParamBean");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    public void submit(View view) {
        UiHelper.goToFeedBackSucView(this);
        //        OrderManager.getInstance().doneOrder("id", orderId, "handleDes", handleDes
//                , "repairType", repairType, "problemType", problemType, "partsUse", partsUse)
//                .safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
//                    @Override
//                    protected void _onNext(BaseCommonResponse baseCommonResponse) {
//                        UiHelper.goToConfirmSubmitView(FillInActivity.this);
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//
//                    }
//                });
    }

    @Override
    public void onClick(View v) {

    }
}
