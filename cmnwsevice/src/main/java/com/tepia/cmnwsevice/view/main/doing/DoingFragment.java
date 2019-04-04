package com.tepia.cmnwsevice.view.main.doing;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.RiverBean;
import com.tepia.cmnwsevice.view.main.myagent.MyAgentAdapter;
import com.tepia.cmnwsevice.view.main.myagent.MyAgentPresenter;
import com.tepia.cmnwsevice.view.main.views.DealTextView;

/**
 * Author:xch
 * Date:2019/4/2
 * Do:处理中
 */
public class DoingFragment extends BaseListFragment<RiverBean> {

    private DealTextView doingCountTv;
    private DoingPresenter doingPresenter;

    public static DoingFragment launch() {
        return new DoingFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_doing;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        setCenterTitle("处理中");
        doingCountTv = findView(R.id.view_deal_text_first);
        doingCountTv.setTitle("处理中");
        
        doingPresenter = new DoingPresenter();
        doingPresenter.setmView(this);
    }

    @Override
    protected void initRequestData() {
        doingPresenter.querylist();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new MyAgentAdapter();
    }
}
