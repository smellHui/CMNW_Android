package com.tepia.cmnwsevice.view.detail.edit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.ConfirmSubmitDataBindView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.event.CompleteCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.order.OrderParamBean;

import org.greenrobot.eventbus.EventBus;

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
            orderParamBean = (OrderParamBean) intent.getSerializableExtra("orderParamBean");

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    public void submit() {
//        UiHelper.goToFeedBackSucView(this);
        System.out.println("orderParamBean-->" + orderParamBean.toString());
        OrderManager.getInstance().doneOrder("id", orderParamBean.getId(), "handleDes", orderParamBean.getHandleDes()
                , "repairType", orderParamBean.getRepairType(), "problemType", orderParamBean.getProblemType(), "partsUse", orderParamBean.getPartsUse())
                .safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
                    @Override
                    protected void _onNext(BaseCommonResponse baseCommonResponse) {
                        UiHelper.goToFeedBackSucView(ConfirmSubmitActivity.this);
                        ConfirmSubmitActivity.this.finish();
                        EventBus.getDefault().post(new CompleteCallbackEvent());
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_revise) {
            finish();
        }
        if (id == R.id.btn_submit) {
            submit();
        }
    }
}
