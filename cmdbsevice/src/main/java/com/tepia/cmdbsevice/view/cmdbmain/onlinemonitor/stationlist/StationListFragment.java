package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentStationListBinding;
import com.tepia.cmnwsevice.model.station.StationBean;

import java.util.ArrayList;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/16 16:52
 * @修改人         ：
 * @修改时间       :       2019/4/16 16:52
 * @功能描述       :       处理站 list
 **/

public class StationListFragment extends MVPBaseFragment<StationListContract.View, StationListPresenter> implements StationListContract.View {

    public String shaixuanStr;
    private FragmentStationListBinding mBinding;
    private AdapterStationList adapterStationList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_station_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        initListView();
    }

    private void initListView() {
        mBinding.rvStationList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterStationList = new AdapterStationList(R.layout.lv_station_list_item_view,null);
        mBinding.rvStationList.setAdapter(adapterStationList);
    }

    @Override
    protected void initRequestData() {
        mPresenter.getStationList(shaixuanStr);
    }

    @Override
    public void getStationListSuccess(ArrayList<StationBean> list) {
        adapterStationList.setNewData(list);
    }
}
