package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.BaseActivity;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.ActivityStationDetailBinding;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.OnlineMonitorMapFragment;
import com.tepia.cmnwsevice.model.station.StationBean;

import org.litepal.annotation.Column;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/23
 * Time            :       11:40
 * Version         :       1.0
 * 功能描述        :
 **/
@Route(path = AppRoutePath.app_cmdb_station_detail)
public class StationDetailActivity extends BaseActivity {
    private ActivityStationDetailBinding mBinding;
    private StationBean stationBean;
    private HeaderMapFragment headerMapFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_station_detail;
    }

    @Override
    public void initView() {
        mBinding = DataBindingUtil.bind(mRootView);
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        StationDetailFragment stationDetailFragment = new StationDetailFragment();
        stationDetailFragment.stationBean = stationBean;
        ft2.replace(R.id.fl_container, stationDetailFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();
        initMapFragment();
    }

    /**
     * 初始化 地图 fragment
     */
    private void initMapFragment() {
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        headerMapFragment = new HeaderMapFragment();
        headerMapFragment.stationBean = stationBean;
        ft2.replace(R.id.fl_map_container, headerMapFragment);
        ft2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft2.addToBackStack(null);
        ft2.commit();

    }

    @Override
    public void initData() {
        String temp = getIntent().getStringExtra("stationBean");
        if (!TextUtils.isEmpty(temp)) {
            stationBean = new Gson().fromJson(temp, StationBean.class);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void initListener() {
        mBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initRequestData() {

    }
}
