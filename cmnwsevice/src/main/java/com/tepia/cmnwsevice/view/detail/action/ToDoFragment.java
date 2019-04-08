package com.tepia.cmnwsevice.view.detail.action;

import android.os.Bundle;
import android.view.View;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public class ToDoFragment extends BaseCommonFragment {

    private String orderId;

    public static ToDoFragment launch(String orderId) {
        ToDoFragment fragment = new ToDoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderId", orderId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_todo;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if(bundle != null)
            orderId = bundle.getString("orderId");
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {

    }
}
