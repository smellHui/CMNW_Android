package com.tepia.cmnwsevice.view.feedback;

import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:反馈成功页面
 */
public class FeedBackSucActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back_suc;
    }

    @Override
    public void initView() {
        setCenterTitle("反馈成功");
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
