package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.widget.Adapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.ActivityStationBaseInfoDetailBinding;
import com.tepia.cmnwsevice.databinding.ActivityOperationDetailBinding;
import com.tepia.cmnwsevice.model.station.StationBaseInfoBean;
import com.tepia.cmnwsevice.model.station.StationBean;

import java.util.ArrayList;


/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/17 10:57
 * @修改人 ：
 * @修改时间 :       2019/4/17 10:57
 * @功能描述 :       测站 —— 基础信息
 **/
@Route(path = AppRoutePath.app_cmdb_station_base_info)
public class StationBaseInfoDetailActivity extends MVPBaseActivity<StationBaseInfoDetailContract.View, StationBaseInfoDetailPresenter> implements StationBaseInfoDetailContract.View {

    private ActivityStationBaseInfoDetailBinding mBinding;
    private StationBean stationBean;
    private ArrayList<KeyValueBean> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_station_base_info_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("基础信息");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
        initListView();
        mBinding.tvStationName.setText(stationBean.getHandingStation().getName());
    }

    private void initListView() {
        mBinding.rvBaseInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterBaseInfoList adapterBaseInfoList = new AdapterBaseInfoList(R.layout.lv_base_info_view, list);
        mBinding.rvBaseInfo.setAdapter(adapterBaseInfoList);

    }

    @Override
    public void initData() {
        String temp = getIntent().getStringExtra("stationBean");
        if (!TextUtils.isEmpty(temp)) {
            stationBean = new Gson().fromJson(temp, StationBean.class);
        }
        list = new ArrayList<KeyValueBean>();
   /*     站点编码：
        行政区划：
        泵站地址：
        经度：
        纬度：
        设备厂商:
        设备负责人：
        养护单位：
        养护电话：
        设备数量：
        设计排水能力：
        纳污户数：
        运维次数：*/
        StationBaseInfoBean bean = stationBean.getHandingStation();
        if (!TextUtils.isEmpty(bean.getEntryCode())) {
            list.add(new KeyValueBean("站点编号:", bean.getEntryCode()));
        }
        list.add(new KeyValueBean("站点编码:", bean.getCode()));
        list.add(new KeyValueBean("行政区划:", bean.getAdministrativeDivisionName()));
        list.add(new KeyValueBean("泵站地址:", bean.getAddress()));
        list.add(new KeyValueBean("经度:", bean.getLttd() + ""));
        list.add(new KeyValueBean("纬度:", bean.getLgtd() + ""));
        list.add(new KeyValueBean("设备厂商:", bean.getVendorName() + ""));
        if (!CollectionsUtil.isEmpty(bean.getSupportList())) {
            list.add(new KeyValueBean("设备负责人:", bean.getSupportList().get(0).getContactName() + ""));
            list.add(new KeyValueBean("养护单位:", bean.getSupportList().get(0).getEnterpriseName() + ""));
            list.add(new KeyValueBean("养护电话:", bean.getSupportList().get(0).getContactPhone() + ""));
        }
        list.add(new KeyValueBean("设备数量:", bean.getDeviceNum() + ""));
        list.add(new KeyValueBean("设计排水能力:", bean.getDrainageCapacity() + ""));
        if (bean.getServiceHouseholds() != null) {
            list.add(new KeyValueBean("纳污户数:", bean.getServiceHouseholds() + ""));
        }
        if (bean.getOrderCount() != null) {
            list.add(new KeyValueBean("运维次数:", bean.getOrderCount() + ""));
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
