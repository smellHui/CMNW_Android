package com.tepia.cmnwsevice.view.detail.tip;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.order.WorkDetailBean;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:处理中提示页
 */
public class DoingTipActivity extends BaseActivity {

    private String orderId;
    private TextView sendTimeTv;
    private TextView limitTv;
    private TextView currectTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_doing_tip;
    }

    @Override
    public void initView() {
        setCenterTitle("2019-3-31卫星村1号站");
        showBack();

        sendTimeTv = findViewById(R.id.tv_sendTime);
        limitTv = findViewById(R.id.tv_limitTime);
        currectTv = findViewById(R.id.tv_currectTime);
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
                .safeSubscribe(new LoadingSubject<BaseCommonResponse<WorkDetailBean>>() {

                    @Override
                    protected void _onNext(BaseCommonResponse<WorkDetailBean> workDetail) {
                        if (workDetail == null) return;
                        WorkDetailBean workDetailBean = workDetail.getData();
                        if (workDetailBean == null) return;
                        sendTimeTv.setText(workDetailBean.getSendTime());
                        limitTv.setText(workDetailBean.getLimitHours());
                        currectTv.setText(System.currentTimeMillis() + "");
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
