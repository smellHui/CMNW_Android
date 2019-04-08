package com.tepia.cmnwsevice.view.detail.action;

import android.view.View;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.view.main.views.ActionInfoView;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ActionDetailFragment extends BaseCommonFragment {

    private ActionInfoView actionInfoView;

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
        actionInfoView = findView(R.id.view_action_info);
        actionInfoView.setOnClickListener(v -> UiHelper.goToDoingTipView(getContext()));
    }

    @Override
    protected void initRequestData() {

    }
}
