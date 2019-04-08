package com.tepia.cmnwsevice.view.detail.action;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.adapter.PageAdapter;
import com.tepia.cmnwsevice.view.main.myagent.MyAgentFragment;

import java.util.ArrayList;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:工单详情
 */
public class ActionDetailActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "工单详情", "去处理"
    };
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;

    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_action_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("工单详情");
        showBack();
        tabLayout = findViewById(R.id.ly_tab);
        viewPager = findViewById(R.id.view_pager);
        mFragments.add(ActionDetailFragment.launch(orderId));
        mFragments.add(ToDoFragment.launch(orderId));
        pageAdapter = new PageAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null)
            orderId = intent.getStringExtra("orderId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
