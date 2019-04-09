package com.tepia.cmnwsevice.view.detail.operationdetail;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.adapter.PageAdapter;
import com.tepia.cmnwsevice.databinding.ActivityOperationDetailBinding;
import com.tepia.cmnwsevice.view.detail.action.ActionDetailFragment;
import com.tepia.cmnwsevice.view.detail.operationdetail.orderexaminelist.OrderExamineListFragment;
import com.tepia.cmnwsevice.view.detail.operationdetail.operationdetail.OprationDetailFragment;

import java.util.ArrayList;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       17:13
 * Version         :       1.0
 * 功能描述        :        运维工单页
 **/
@Route(path = AppRoutePath.app_cmnw_activity_order_operate)
public class OperationDetailActivty extends BaseActivity {
    private String orderId;
    private ActivityOperationDetailBinding mBinding;
    private final String[] mTitles = {
            "工单详情", "运维详情", "审核记录"
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private PageAdapter pageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_operation_detail;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        setCenterTitle("运维工单详情");
        showBack();
        initTLandViewPager();
    }

    private void initTLandViewPager() {
        ActionDetailFragment actionDetailFragment = new ActionDetailFragment();
        mFragments.add(actionDetailFragment);
        OprationDetailFragment oprationDetailFragment = new OprationDetailFragment();
        oprationDetailFragment.orderId = orderId;
        mFragments.add(oprationDetailFragment);
        OrderExamineListFragment orderExamineListFragment = new OrderExamineListFragment();
        orderExamineListFragment.orderId = orderId;
        mFragments.add(orderExamineListFragment);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mBinding.viewPager.setAdapter(pageAdapter);
        mBinding.lyTab.setViewPager(mBinding.viewPager);
    }

    @Override
    public void initData() {
        orderId = getIntent().getStringExtra("orderId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
