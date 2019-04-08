package com.tepia.cmnwsevice.view.main.myagent;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.model.PageBean;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.mvp.NetListListener;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.manager.UiHelper;
import com.tepia.cmnwsevice.model.RiverBean;
import com.tepia.cmnwsevice.view.setting.MineActivity;

/**
 * Author:xch
 * Date:2019/4/2
 * Do 我的代办
 */
public class MyAgentFragment extends BaseListFragment<RiverBean> {

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
        super.initView(view);
        setCenterTitle("我的代办");
        setLeftImgEvent(R.mipmap.ic_mine_header_footprint, v -> toast("左边"));
        setRightImgEvent(R.mipmap.ic_mine_header_footprint, v -> startActivity(new Intent(getActivity(), MineActivity.class)));

        myAgentPresenter = new MyAgentPresenter();
        myAgentPresenter.setmView(this);
    }

    @Override
    protected void initRequestData() {
        myAgentPresenter.querylist();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new MyAgentAdapter();
    }

    @Override
    public void setOnItemClickListener(BaseQuickAdapter adapter, View view, int position) {
        UiHelper.goToActionDetailView(getContext());
    }
}
