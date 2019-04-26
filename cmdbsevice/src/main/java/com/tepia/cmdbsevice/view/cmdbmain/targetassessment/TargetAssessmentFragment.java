package com.tepia.cmdbsevice.view.cmdbmain.targetassessment;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment.WaterQualityRateByStcdFragment;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.ScaleTransitionPagerTitleView;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment.SpssFragment;
import com.tepia.cmnwsevice.adapter.PageAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:xch
 * Date:2019/4/17
 * Description:tab-统计分析
 */
public class TargetAssessmentFragment extends BaseCommonFragment {

    private String[] mTitles = {"故障率统计", "人工水质", "在线水质"};
    private List<String> mDataList = Arrays.asList(mTitles);
    private ArrayList<Fragment> mFragments = new ArrayList<>();
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
        viewPager = findView(R.id.vp);
        mFragments.add(SpssFragment.launch(0));
        mFragments.add(WaterQualityRateByStcdFragment.launch(1));
        mFragments.add(WaterQualityRateByStcdFragment.launch(2));
        pageAdapter = new PageAdapter(getChildFragmentManager(), mFragments, mTitles);
        viewPager.setAdapter(pageAdapter);
        initMagicIndicator3();
    }

    @Override
    protected void initRequestData() {

    }

    private void initMagicIndicator3() {
        MagicIndicator magicIndicator = findView(R.id.magic_indicator3);
        magicIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator7 = new CommonNavigator(getContext());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.parseColor("#97A2AE"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#3B493F"));
                simplePagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setLineWidth(UIUtil.dip2px(context, 30));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
}
