package com.tepia.cmnwsevice.view.main.myagent;

import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmnwsevice.R;

/**
 * Author:xch
 * Date:2019/4/2
 * Do 我的代办
 */
public class MyAgentFragment extends MVPBaseFragment<MyAgentContract.View,MyAgentPresenter> implements MyAgentContract.View{

    private MyAgentPresenter myAgentPresenter;

    public static MyAgentFragment launch() {
        return new MyAgentFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myagent;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        myAgentPresenter = new MyAgentPresenter();
    }

    @Override
    protected void initRequestData() {

    }
}
