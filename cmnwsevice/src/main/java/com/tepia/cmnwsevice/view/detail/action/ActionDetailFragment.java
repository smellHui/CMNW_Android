package com.tepia.cmnwsevice.view.detail.action;

import android.view.View;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ActionDetailFragment extends BaseCommonFragment {

    public static ActionDetailFragment launch() {
        return new ActionDetailFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_action_detail;
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
