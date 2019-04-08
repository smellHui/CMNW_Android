package com.tepia.cmnwsevice.view.detail.edit;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:确认提交页面
 */
public class ConfirmSubmitActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_submit;
    }

    @Override
    public void initView() {
      setCenterTitle("2019-3-31卫星村1号站");
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
}
