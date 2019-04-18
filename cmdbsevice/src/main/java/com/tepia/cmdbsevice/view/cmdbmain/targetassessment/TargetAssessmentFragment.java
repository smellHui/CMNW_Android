package com.tepia.cmdbsevice.view.cmdbmain.targetassessment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SpssFragment;
import com.tepia.cmnwsevice.adapter.PageAdapter;
import com.tepia.cmnwsevice.view.detail.action.ActionDetailFragment;
import com.tepia.cmnwsevice.view.detail.action.ToDoFragment;

import java.util.ArrayList;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:tab-统计分析
 */
public class TargetAssessmentFragment extends BaseCommonFragment {

    private String[] mTitles = {"故障率统计", "人工水质", "在线水质"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private SlidingTabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_target_assessment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        tabLayout = findView(R.id.ly_tab);
        viewPager = findView(R.id.vp);
        mFragments.add(SpssFragment.launch());
        mFragments.add(SpssFragment.launch());
        mFragments.add(SpssFragment.launch());
        pageAdapter = new PageAdapter(getChildFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    protected void initRequestData() {

    }
}
