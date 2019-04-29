package com.tepia.cmdbsevice.view.cmdbmain.targetassessment.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.view.WrapLayoutManager;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.targetassessment.view.SpssTitleView;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/28
 * Description:统计分析base类
 */
public abstract class StatisFragment<T> extends BaseCommonFragment {

    private static String[] mTitles = {"行政区划", "企业"};

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpssTitleView spssTitleView;
    private TextView tv_choice_data;

    private BaseQuickAdapter baseQuickAdapter;
    private int currectTab;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_spss;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        swipeRefreshLayout = findView(R.id.layout_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this::requestListData);
        tv_choice_data = findView(R.id.tv_choice_data);
        tv_choice_data.setOnClickListener(this::showChoiceDateDialog);
        spssTitleView = findView(R.id.view_spss_title);
        spssTitleView.setData(tableTitles());
        SegmentTabLayout tabLayout = findView(R.id.tl_1);
        tabLayout.setTabData(mTitles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                currectTab = position;
                requestListData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        setVerModel();
    }

    public void requestListData() {
        setRefreshing(true);
        if (currectTab == 0) {
            listByStcd();
        } else {
            listByVendor();
        }
    }

    private void setVerModel() {
        rv = findView(R.id.rv);
        baseQuickAdapter = getBaseQuickAdapter();
        rv.setLayoutManager(new WrapLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        int zeroPx = Px2dpUtils.dip2px(getContext(), 0);
        rv.setPadding(zeroPx, zeroPx, zeroPx, zeroPx);
        rv.setAdapter(baseQuickAdapter);
    }

    public void setChoiceDateTv(String str) {
        tv_choice_data.setText(str);
    }

    public void setNewData(@Nullable List<T> data) {
        baseQuickAdapter.setNewData(data);
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(refreshing);
    }

    @NonNull
    public abstract String[] tableTitles();

    @NonNull
    public abstract BaseQuickAdapter getBaseQuickAdapter();

    public abstract void initRequestData();

    public abstract void showChoiceDateDialog(View view);

    //行政区划接口
    public abstract void listByStcd();

    //企业接口
    public abstract void listByVendor();

}
