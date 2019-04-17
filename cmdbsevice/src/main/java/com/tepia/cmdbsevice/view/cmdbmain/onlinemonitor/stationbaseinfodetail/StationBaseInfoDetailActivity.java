package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.ActivityStationBaseInfoDetailBinding;
import com.tepia.cmnwsevice.databinding.ActivityOperationDetailBinding;


/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/17 10:57
 * @修改人         ：
 * @修改时间       :       2019/4/17 10:57
 * @功能描述       :       测站 —— 基础信息
 **/
@Route(path = AppRoutePath.app_cmdb_station_base_info)
public class StationBaseInfoDetailActivity extends MVPBaseActivity<StationBaseInfoDetailContract.View, StationBaseInfoDetailPresenter> implements StationBaseInfoDetailContract.View {

    private ActivityStationBaseInfoDetailBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_station_base_info_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("基础信息");
        showBack();
        mBinding = DataBindingUtil.bind(mRootView);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
}
