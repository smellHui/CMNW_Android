package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
import android.widget.TextView;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMonitorBinding;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.OnlineMonitorMapFragment;

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
    }

    private void initRightView() {
        {
            mBinding.loRight.rvStationTypeList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            ArrayList<StationTypeBean> list = new ArrayList<>();
            list.add(new StationTypeBean());
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
            AdapterStationTypeList  adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
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
            AdapterStationTypeList  adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
            mBinding.loRight.rvStationOrganList.setAdapter(adapterStationTypeList);
        }
        {
            mBinding.loRight.rvStationAreaList.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
            ArrayList<StationTypeBean> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add(new StationTypeBean());
            }
            AdapterStationTypeList  adapterStationTypeList = new AdapterStationTypeList(R.layout.lv_station_type_item_view, list);
            mBinding.loRight.rvStationAreaList.setAdapter(adapterStationTypeList);
        }
    }

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

        mBinding.loBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (mBinding.loSearch.loSearch.getVisibility() == View.VISIBLE) {
//                    mBinding.loSearch.loSearch.setVisibility(View.GONE);
                } else {
                    mBinding.loSearch.loSearch.setVisibility(View.VISIBLE);
                }
            }
        });
        mBinding.loSearch.etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        mBinding.loSearch.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String temp = mBinding.loSearch.etSearch.getText().toString();
                if (!TextUtils.isEmpty(temp)) {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchTipList(temp))) {
                        initAndShowSearchTipList();
                    }
                } else {
                    if (!CollectionsUtil.isEmpty(mPresenter.getSearchHisList())) {
                        initAndShowSearchHisList();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initAndShowSearchTipList() {
        mBinding.loSearch.loSearch.setVisibility(View.VISIBLE);
        mBinding.loSearch.loSearchTip.setVisibility(View.VISIBLE);
        mBinding.loSearch.rvSearchHis.setVisibility(View.VISIBLE);
        mBinding.loSearch.rvSearchTip.setVisibility(View.GONE);
        mBinding.loSearch.rvSearchHis.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterSearchTipList adapterSearchHisList = new AdapterSearchTipList(R.layout.lv_search_his_list, mPresenter.getSearchTipList(mBinding.loSearch.etSearch.getText().toString()));
        mBinding.loSearch.rvSearchHis.setAdapter(adapterSearchHisList);
        View footview = LayoutInflater.from(getContext()).inflate(R.layout.lv_search_foot_view, null);
        adapterSearchHisList.setFooterView(footview);
    }

    /**
     * 初始化 搜索历史 list view
     */
    private void initAndShowSearchHisList() {
        mBinding.loSearch.loSearch.setVisibility(View.VISIBLE);
        mBinding.loSearch.loSearchTip.setVisibility(View.VISIBLE);
        mBinding.loSearch.rvSearchHis.setVisibility(View.VISIBLE);
        mBinding.loSearch.rvSearchTip.setVisibility(View.GONE);
        mBinding.loSearch.rvSearchHis.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterSearchHisList adapterSearchHisList = new AdapterSearchHisList(R.layout.lv_search_his_list, mPresenter.getSearchHisList());
        mBinding.loSearch.rvSearchHis.setAdapter(adapterSearchHisList);
        View footview = LayoutInflater.from(getContext()).inflate(R.layout.lv_search_foot_view, null);
        adapterSearchHisList.setFooterView(footview);
    }

    @Override
    protected void initRequestData() {
        mPresenter.getStationList();
    }
}
