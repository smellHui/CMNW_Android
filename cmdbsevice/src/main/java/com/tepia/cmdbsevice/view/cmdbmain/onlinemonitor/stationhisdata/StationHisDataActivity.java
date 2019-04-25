package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.cmdbsevice.R;


/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/25 12:01
 * @修改人         ：
 * @修改时间       :       2019/4/25 12:01
 * @功能描述       :
 **/
@Route(path = AppRoutePath.app_cmdb_station_his_data)
public class StationHisDataActivity extends MVPBaseActivity<StationHisDataContract.View, StationHisDataPresenter> implements StationHisDataContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_station_his_data;
    }

    @Override
    public void initView() {

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
