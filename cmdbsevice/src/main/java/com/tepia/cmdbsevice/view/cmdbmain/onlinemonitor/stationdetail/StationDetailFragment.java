package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;


import android.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.databinding.FragmentStationDetailBinding;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.station.PictureBean;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/16 16:52
 * @修改人 ：
 * @修改时间 :       2019/4/16 16:52
 * @功能描述 :
 **/

public class StationDetailFragment extends MVPBaseFragment<StationDetailContract.View, StationDetailPresenter> implements StationDetailContract.View {

    public StationBean stationBean;
    private FragmentStationDetailBinding mBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_station_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(view);
        mBinding.tvHisData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_his_data)
                        .withString("stationBean", new Gson().toJson(stationBean))
                        .navigation();
            }
        });
        mBinding.loWaterQTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (mBinding.loWaterQ.getVisibility() == View.VISIBLE) {
                    mBinding.loWaterQ.setVisibility(View.GONE);
                }else {
                    mBinding.loWaterQ.setVisibility(View.VISIBLE);
                }
            }
        });
        mBinding.loDeviceStatusTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                if (mBinding.loDeviceStatus.getVisibility() == View.VISIBLE) {
                    mBinding.loDeviceStatus.setVisibility(View.GONE);
                }else {
                    mBinding.loDeviceStatus.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void initRequestData() {
        mPresenter.getStationDetail(stationBean.getCode());
    }

    @Override
    public void getStationDetailSuccess(StationBean data) {
        refreshView(data);
    }

    private void refreshView(StationBean data) {
        data.setCode(stationBean.getCode());
        stationBean = data;
        mBinding.tvStationName.setText(data.getHandingStation().getName());
        mBinding.tvStationOrgan.setText(data.getHandingStation().getVendorName());
        mBinding.tvStationAdress.setText(data.getHandingStation().getAddress());
        mBinding.loStationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_base_info)
                        .withString("stationBean", new Gson().toJson(data))
                        .navigation();
            }
        });
        {
            String temp = "";
            switch (data.getCurrentData().getConductivityStatus()) {
                case 0:
                    temp = "正常";
                    break;
                case 1:
                    temp = "异常";
                    break;
                case 2:
                    temp = "报警";
                    break;
                case 3:
                    temp = "故障";
                    break;
                default:
                    break;
            }
            mBinding.tvCurWaterQ.setText(temp);
        }
        {
            String temp = "";
            switch (data.getCurrentData().getDeviceStatus()) {
                case 0:
                    temp = "正常";
                    break;
                case 1:
                    temp = "异常";
                    break;
                case 2:
                    temp = "报警";
                    break;
                case 3:
                    temp = "故障";
                    break;
                default:
                    break;
            }
            mBinding.tvStationStatus.setText(temp);
        }
        if (!data.getCurrentData().isIsCommunication()) {
            mBinding.tvStationStatus.setText("通信异常");
        }
        mBinding.tvConductivity.setText("电导率：" + data.getCurrentData().getConductivity() + "μs/cm");
        mBinding.tvDurationTimeTip.setText("无通讯维持时间：" + data.getCurrentData().getDurationTime());

        mBinding.rvStationStatusHis.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterStationStatusHis adapterStationStatusHis = new AdapterStationStatusHis(R.layout.lv_station_status_his_item_view, data.getCurrentData().getDeviceMonitorDataList());
        mBinding.rvStationStatusHis.setAdapter(adapterStationStatusHis);
        if (!CollectionsUtil.isEmpty(data.getHandingStation().getReferenceFileList())) {
            initBanner(data.getHandingStation().getReferenceFileList());
        }

    }

    private void initBanner(List<PictureBean> referenceFileList) {
        ArrayList<String> list = new ArrayList<>();
        for (PictureBean bean : referenceFileList) {
            list.add(bean.getFilePath());
        }
        mBinding.bannerStation.setDelayTime(3000);
        //设置banner样式(显示圆形指示器)
        mBinding.bannerStation.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（指示器居右）
        mBinding.bannerStation.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片加载器
        mBinding.bannerStation.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBinding.bannerStation.setImages(list);
        mBinding.bannerStation.start();
    }
}
