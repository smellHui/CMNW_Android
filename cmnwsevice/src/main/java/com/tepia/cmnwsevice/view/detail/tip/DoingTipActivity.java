package com.tepia.cmnwsevice.view.detail.tip;

import android.content.Intent;
import android.view.View;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.order.OrderManager;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:处理中提示页
 */
public class DoingTipActivity extends BaseActivity {

    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_doing_tip;
    }

    @Override
    public void initView() {
        setCenterTitle("2019-3-31卫星村1号站");
        showBack();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null)
            orderId = intent.getStringExtra("orderId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        OrderManager.getInstance().getOrderWorkingDetail(orderId)
                .safeSubscribe(new LoadingSubject<BaseCommonResponse>(){

                    @Override
                    protected void _onNext(BaseCommonResponse baseCommonResponse) {

                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    public void fillIn(View view) {
        UiHelper.goToFillInView(this);
    }
}
