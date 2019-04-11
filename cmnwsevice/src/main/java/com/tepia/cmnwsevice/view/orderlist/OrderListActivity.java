package com.tepia.cmnwsevice.view.orderlist;

import android.content.Intent;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.ExecuteStatus;
import com.tepia.cmnwsevice.view.detail.action.ActionDetailFragment;

/**
 * Author:xch
 * Date:2019/4/9
 * Do:
 */
public class OrderListActivity extends BaseActivity {

    private ExecuteStatus status;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        setCenterTitle(status.getName());
        showBack();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, OrderListFragment.launch(status))   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null)
            status = (ExecuteStatus) intent.getSerializableExtra("status");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
