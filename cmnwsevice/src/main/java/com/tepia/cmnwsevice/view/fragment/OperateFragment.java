package com.tepia.cmnwsevice.view.fragment;

import android.view.View;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/2
 * Do:运维记录
 */
public class OperateFragment extends BaseCommonFragment {

    public static OperateFragment launch() {
        return new OperateFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_operate;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {

    }
}
