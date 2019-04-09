package com.tepia.cmnwsevice.view.detail.action;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/9
 * Do:
 */
public class OrderDetailActivity extends BaseActivity {

    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ActionDetailFragment.launch(orderId))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
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

    }
}
