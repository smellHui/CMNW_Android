package com.tepia.cmnwsevice.view.main.operate;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.BaseListFragment;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.RiverBean;
import com.tepia.cmnwsevice.view.main.doing.DoingPresenter;
import com.tepia.cmnwsevice.view.main.myagent.MyAgentAdapter;
import com.tepia.cmnwsevice.view.main.views.DealTextView;

/**
 * Author:xch
 * Date:2019/4/2
 * Do:运维记录
 */
public class OperateFragment extends BaseListFragment<RiverBean> {

    private DealTextView pendTv;
    private DealTextView completeTv;

    private OperatePresenter operatePresenter;

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
        super.initView(view);
        setCenterTitle("运维记录");
        pendTv = findView(R.id.view_deal_text_first);
        completeTv = findView(R.id.view_deal_text_second);
        pendTv.setTitle("待审核");
        completeTv.setTitle("已完结");

        operatePresenter = new OperatePresenter();
        operatePresenter.setmView(this);
    }

    @Override
    protected void initRequestData() {
        operatePresenter.querylist();
    }

    @Override
    public BaseQuickAdapter getBaseQuickAdapter() {
        return new MyAgentAdapter();
    }
}
