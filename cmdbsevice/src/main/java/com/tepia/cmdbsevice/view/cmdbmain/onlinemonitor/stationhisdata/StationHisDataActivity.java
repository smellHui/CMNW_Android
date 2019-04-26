package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.view.EmptyLayoutUtil;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.ActivityStationHisDataBinding;
import com.tepia.cmdbsevice.databinding.LvHisDataHeaderViewBinding;
import com.tepia.cmnwsevice.model.station.HisDataBean;
import com.tepia.cmnwsevice.model.station.StationBean;

import net.lucode.hackware.magicindicator.buildins.UIUtil;


/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/25 12:01
 * @修改人 ：
 * @修改时间 :       2019/4/25 12:01
 * @功能描述 :        历史数据
 **/
@Route(path = AppRoutePath.app_cmdb_station_his_data)
public class StationHisDataActivity extends MVPBaseActivity<StationHisDataContract.View, StationHisDataPresenter> implements StationHisDataContract.View {
    private static String[] mTitles = {"历史报警", "历史故障"};
    private ActivityStationHisDataBinding mBinding;
    private StationBean stationBean;
    private int currectTab = 0;
    private AdapterHisData adapterHisData;

    private LvHisDataHeaderViewBinding mheaderBinding;


    @Override
    public int getLayoutId() {
        return R.layout.activity_station_his_data;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        setCenterTitle("历史数据");
        showBack();
        getRithtTv().setVisibility(View.VISIBLE);
        getRithtTv().setText("本季");
        getRithtTv().setTextColor(0xff4eb17b);
        getRithtTv().setCompoundDrawablePadding(UIUtil.dip2px(getContext(), 10));
        getRithtTv().setCompoundDrawables(null, null, getDrawable(R.mipmap.icon_rili), null);
        getRithtTv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
            }
        });
        mBinding = DataBindingUtil.bind(mRootView);
        mBinding.tl1.setTabData(mTitles);
        mBinding.tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        currectTab = 0;
                    {
                        String startTime = "2018-04-01";
                        String endTime = "2019-04-30";
                        String stationCode = "HS20190401161319552422";
                        String type = "10001,10002,10003";
                        mPresenter.getWarningHistory(startTime, endTime, stationCode, type);
                    }
                    break;
                    case 1:
                        currectTab = 1;
                    {
                        String startTime = "2018-04-01";
                        String endTime = "2019-04-30";
                        String stationCode = "HS20190401161319552422";
                        String type = "10001,10002,10003";
                        mPresenter.getFaultHistory(startTime, endTime, stationCode, type);
                    }
                    break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initListView();
    }

    private void initListView() {
        mBinding.rvHisData.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterHisData = new AdapterHisData(R.layout.lv_warning_view, null);
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.lv_his_data_header_view, null);
        mheaderBinding = DataBindingUtil.bind(headerView);
        adapterHisData.setHeaderView(headerView);
        mBinding.rvHisData.setAdapter(adapterHisData);
    }

    @Override
    public void initData() {
        String temp = getIntent().getStringExtra("stationBean");
        if (!TextUtils.isEmpty(temp)) {
            stationBean = new Gson().fromJson(temp, StationBean.class);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {
        String startTime = "2018-04-01";
        String endTime = "2019-04-30";
        String stationCode = "HS20190401161319552422";
        String type = "10001,10002,10003";
//        mPresenter.getFaultHistory(startTime, endTime, stationCode, type);
        mPresenter.getWarningHistory(startTime, endTime, stationCode, type);
    }

    @Override
    public void getFaultHistorySuccess(HisDataBean data) {
        adapterHisData.setNewData(data.getWarningList());
        if (CollectionsUtil.isEmpty(data.getWarningList())) {
            adapterHisData.setEmptyView(EmptyLayoutUtil.getView("没有故障记录"));
        }
    }

    @Override
    public void getWarningHistorySuccess(HisDataBean data) {
        adapterHisData.setNewData(data.getWarningList());
        if (CollectionsUtil.isEmpty(data.getWarningList())) {
            adapterHisData.setEmptyView(EmptyLayoutUtil.getView("没有报警记录"));
        }
    }
}
