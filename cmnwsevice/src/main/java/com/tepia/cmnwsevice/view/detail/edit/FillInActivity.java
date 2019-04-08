package com.tepia.cmnwsevice.view.detail.edit;

import android.view.View;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:工单填报
 */
public class FillInActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_fill_in;
    }

    @Override
    public void initView() {
        setCenterTitle("工单填报");
        showBack();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }

    public void confirmSubmit(View view){
        UiHelper.goToConfirmSubmitView(this);
    }
}