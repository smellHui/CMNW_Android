package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ScreenUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.ScrollLayout.ContentScrollView;
import com.tepia.base.view.ScrollLayout.ScrollLayout;
import com.tepia.base.view.dialog.permissiondialog.Px2dpUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMonitorBinding;
import com.tepia.cmdbsevice.model.station.StationBean;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.OnlineMonitorMapFragment;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail.StationDetailFragment;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist.StationListFragment;

import java.util.ArrayList;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/15 14:44
 * @修改人 ：
 * @修改时间 :       2019/4/15 14:44
 * @功能描述 :        tab-在线监测
 **/
public class OnlineMonitorFragment extends MVPBaseFragment<OnlineMonitorContract.View, OnlineMonitorPresenter> implements OnlineMonitorContract.View {

    private FragmentOnlineMonitorBinding mBinding;
    private AdapterStationTypeList adapterStationTypeList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_monitor;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        initMapFragment();
        initRightView();
        initLisitener();

        initScrollLayout(mRootView);
        showLayer(0);
    }

    /**
     * 初始化滑动布局
     *
     * @param view
     */
    private void initScrollLayout(View view) {
        ScrollLayout scrollownLayout = view.findViewWithTag("scroll_down_layout");
        ContentScrollView itemScrollView = view.findViewWithTag("item_scroll_view_layout");
        FrameLayout flContainer = findView(R.id.fl_container);

        //设置列表滑动布局
        //关闭状态时最上方预留高度
        scrollownLayout.setMinOffset(Px2dpUtils.dip2px(getContext(), 100));
        //打开状态时内容显示区域的高度
        scrollownLayout.setMaxOffset(ScreenUtil.getScreenHeightPix(getContext()) / 2);
        //最低部退出状态时可看到的高度，0为不可见
        scrollownLayout.setExitOffset(Px2dpUtils.dip2px(getContext(), 100));
        //解决recycleView底部显示不全的问题
//        listRecylcler.setPadding(0, 0, 0, ScreenUtil.getScreenHeightPix(getContext()) - imgLocationTop - Px2dpUtils.dip2px(mContext, 36 + 55));
        //是否支持下滑退出，支持会有下滑到最底部时的回调
        scrollownLayout.setIsSupportExit(true);
        //是否支持横向滚动
        scrollownLayout.setAllowHorizontalScroll(true);
        scrollownLayout.setOnScrollChangedListener(new ScrollLayout.OnScrollChangedListener() {
            @Override
            public void onScrollProgressChanged(float currentProgress) {

            }

            @Override
            public void onScrollFinished(ScrollLayout.Status currentStatus) {

            }

            @Override
            public void onChildScroll(int top) {

            }
        });
        scrollownLayout.setToExit();
        scrollownLayout.getBackground().setAlpha(0);


    }


    private void initRightView() {
        {
            mBinding.loRight.rvStationTypeList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            ArrayList<StationTypeBean> list = new ArrayList<>();
            list.add(new StationTypeBean("提升井",R.drawable.bg_circle_eee,R.mipmap.icn_tsj));
            list.add(new StationTypeBean("提升井",R.drawable.bg_circle_eee,R.mipmap.icn_tsj));
            list.add(new StationTypeBean());
            adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
            mBinding.loRight.rvStationTypeList.setAdapter(adapterStationTypeList);
        }
        {
            mBinding.loRight.rvStationStatusList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            ArrayList<StationTypeBean> list = new ArrayList<>();
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            AdapterStationTypeList adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
            mBinding.loRight.rvStationStatusList.setAdapter(adapterStationTypeList);
        }
        {
            mBinding.loRight.rvStationOrganList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            ArrayList<StationTypeBean> list = new ArrayList<>();
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            list.add(new StationTypeBean());
            AdapterStationTypeList adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
            mBinding.loRight.rvStationOrganList.setAdapter(adapterStationTypeList);
        }
        {
            mBinding.loRight.rvStationAreaList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            ArrayList<StationTypeBean> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add(new StationTypeBean());
            }
            AdapterStationTypeList adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
            mBinding.loRight.rvStationAreaList.setAdapter(adapterStationTypeList);
        }
    }

    /**
     * 初始化 地图 fragment
     */
    private void initMapFragment() {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        ft2.replace(R.id.fl_map_container, new OnlineMonitorMapFragment());
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();

    }

    private void initLisitener() {
        mBinding.loMapOptLayer.loMapLayersSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                /** 打开右侧 图层选择 视图*/
                mBinding.drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });


        mBinding.loSearchHeader.loBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (mBinding.loSearchHeader.loSearchEt.getVisibility() == View.VISIBLE) {
                    if (mBinding.loSearchHeader.ivSearch.getVisibility() == View.VISIBLE) {
                        String temp = mBinding.loSearchHeader.etSearch.getText().toString();
                        if (TextUtils.isEmpty(temp)) {
                            ToastUtils.shortToast("请输入要搜索的名称");
                            initAndShowSearchHisList();
                        } else {
                            mPresenter.putSearchHis(temp);
                            showLayer(3);
                            initListFragment(temp);
                        }
                    } else {
                        showLayer(1);
                        mBinding.loSearchHeader.etSearch.setText("");
                    }
                } else {
                    showLayer(1);
                }
            }
        });
        mBinding.loSearchHeader.etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchHisList())) {
                        initAndShowSearchHisList();
                    }

                } else {

                }
            }
        });
        mBinding.loSearchHeader.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = mBinding.loSearchHeader.etSearch.getText().toString();
                if (!TextUtils.isEmpty(temp)) {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchTipList(temp))) {
                        showLayer(2);
                        initAndShowSearchTipList();
                    }
                } else {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchHisList())) {
                        showLayer(1);
                        initAndShowSearchHisList();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBinding.loSearchHeader.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                showLayer(0);
            }
        });
    }

    private void initListFragment(String temp) {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        StationListFragment stationListFragment = new StationListFragment();
        stationListFragment.shaixuanStr = temp;
        ft2.replace(R.id.fl_container, stationListFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();
    }

    /**
     * 初始化 并显示 提示list
     */
    private void initAndShowSearchTipList() {

        mBinding.loSearchTip.rvSearchTip.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterSearchTipList adapterSearchTipList = new AdapterSearchTipList(R.layout.lv_search_tip_list, mPresenter.getSearchTipList(mBinding.loSearchHeader.etSearch.getText().toString()));
        mBinding.loSearchTip.rvSearchTip.setAdapter(adapterSearchTipList);
        View footview = LayoutInflater.from(getContext()).inflate(R.layout.lv_search_foot_view, null);
        adapterSearchTipList.setFooterView(footview);

        adapterSearchTipList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                mBinding.loSearchHeader.etSearch.setText(adapterSearchTipList.getData().get(position).getName());
                showLayer(3);
                initDetailFragment(adapterSearchTipList.getData().get(position));
            }
        });

    }

    /**
     * 根据不同状态 显示不同 图层
     *
     * @param status
     */
    private void showLayer(int status) {
        switch (status) {
            case 0: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.GONE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearch.setVisibility(View.GONE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.GONE);
            }
            break;
            case 1: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.VISIBLE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearchTip.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.rvSearchHis.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.rvSearchTip.setVisibility(View.GONE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.GONE);
            }
            break;
            case 2: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.VISIBLE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.GONE);
                mBinding.loSearchTip.loSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearchTip.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.rvSearchHis.setVisibility(View.GONE);
                mBinding.loSearchTip.rvSearchTip.setVisibility(View.VISIBLE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.GONE);
            }
            break;
            case 3: {
                mBinding.loSearchHeader.loBtSearch.setVisibility(View.VISIBLE);
                mBinding.loSearchHeader.loSearchEt.setVisibility(View.VISIBLE);
                mBinding.loMapOptLayer.loMapOptLayer.setVisibility(View.VISIBLE);
                mBinding.loSearchTip.loSearch.setVisibility(View.GONE);
                mBinding.flSearchContent.flSearchContent.setVisibility(View.VISIBLE);
            }
            break;
            default:
                break;
        }
    }

    private void initDetailFragment(StationBean stationBean) {
        FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
        StationDetailFragment stationDetailFragment = new StationDetailFragment();
        stationDetailFragment.stationBean = stationBean;
        ft2.replace(R.id.fl_container, stationDetailFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();
    }

    /**
     * 初始化 搜索历史 list view
     */
    private void initAndShowSearchHisList() {
        mBinding.loSearchTip.rvSearchHis.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterSearchHisList adapterSearchHisList = new AdapterSearchHisList(R.layout.lv_search_his_list, mPresenter.getSearchHisList());
        mBinding.loSearchTip.rvSearchHis.setAdapter(adapterSearchHisList);
        View footview = LayoutInflater.from(getContext()).inflate(R.layout.lv_search_foot_view, null);

        footview.findViewById(R.id.tv_foot_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                mPresenter.clearSearchHisList();
            }
        });
        adapterSearchHisList.setFooterView(footview);

        adapterSearchHisList.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                mBinding.loSearchHeader.etSearch.setText(adapterSearchHisList.getData().get(position));
                showLayer(3);
                initListFragment(adapterSearchHisList.getData().get(position));
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getStationList();
    }
}
