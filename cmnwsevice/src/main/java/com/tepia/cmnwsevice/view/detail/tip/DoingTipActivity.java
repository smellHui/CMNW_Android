package com.tepia.cmnwsevice.view.detail.tip;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.TextView;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.DoingTipView;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.event.CompleteCallbackEvent;
import com.tepia.cmnwsevice.model.order.OrderManager;
import com.tepia.cmnwsevice.model.order.WorkDetailBean;
import com.tepia.common_view.ColorArcProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Date;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:处理中提示页
 */
public class DoingTipActivity extends BaseActivity implements View.OnClickListener {

    private String orderId;
    private TextView sendTimeTv;
    private TextView limitTv;
    private TextView currectTv;
    private ColorArcProgressBar cbTipBar;

    private DoingTipView mView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_doing_tip;
    }

    @Override
    public void initView() {
        mView = DataBindingUtil.bind(mRootView);
        mView.setOnClickListener(this);
        mView.setDate(new Date());
        setCenterTitle("2019-3-31卫星村1号站");
        showBack();
        cbTipBar = findViewById(R.id.cb_tip_bar);
        sendTimeTv = findViewById(R.id.tv_sendTime);
        limitTv = findViewById(R.id.tv_limitTime);
        currectTv = findViewById(R.id.tv_currectTime);
        cbTipBar.setMaxValues(100);
        cbTipBar.setCurrentValues(40);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
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
                        mView.setWorkDetail(workDetailBean);
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }

    @Subscribe
    public void CompleteCallbackEvent(CompleteCallbackEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_fill){
            UiHelper.goToFillInView(this,orderId);
        }
    }
}
