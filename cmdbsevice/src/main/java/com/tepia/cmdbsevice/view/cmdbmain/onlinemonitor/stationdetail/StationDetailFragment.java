package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;


import android.databinding.DataBindingUtil;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.databinding.FragmentStationDetailBinding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail.KeyValueBean;
import com.tepia.cmnwsevice.model.station.PictureBean;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmnwsevice.model.station.WaterQualityBean;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

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

    public static StationBean stationBean;
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
        mBinding.btNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                toNav();
            }
        });
        mBinding.btVr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail_vr).navigation();
            }
        });
        mBinding.btGylc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()) {
                    return;
                }
                ARouter.getInstance().build(AppRoutePath.app_cmdb_station_detail_gylc).navigation();
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
                    mBinding.ivCurWaterQTip.setImageResource(R.mipmap.icon_arrow_down_black);
                } else {
                    mBinding.loWaterQ.setVisibility(View.VISIBLE);
                    mBinding.ivCurWaterQTip.setImageResource(R.mipmap.icon_fold);
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
                    mBinding.ivStationStationTip.setImageResource(R.mipmap.icon_arrow_down_black);
                } else {
                    mBinding.loDeviceStatus.setVisibility(View.VISIBLE);
                    mBinding.ivStationStationTip.setImageResource(R.mipmap.icon_fold);
                }
            }
        });
        {
            if (stationBean == null) {
                return;
            }
            String temp = "";
            switch (stationBean.getStationStatus()) {
                case "0":
                    temp = "正常";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail_zc);
                    break;
                case "1":
                    temp = "异常";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail_yc);
                    break;
                case "2":
                    temp = "报警";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail);
                    break;
                case "3":
                    temp = "故障";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail_gz);
                    break;
                default:
                    break;
            }
            mBinding.tvDeviceStatusHeader.setText(temp);
        }
    }

    private void toNav() {
        if (CollectionsUtil.isEmpty(NavUtil.hasMap(getBaseActivity()))) {
            ToastUtils.shortToast("该设备没有安装导航APP");
        } else {
            switch (NavUtil.hasMap(getContext()).get(0)) {
                case "com.baidu.BaiduMap":
                    NavUtil.toBaidu(getBaseActivity(), stationBean.getLttd(), stationBean.getLgtd());
                    break;
                case "com.autonavi.minimap":
                    NavUtil.toGaodeNavi(getBaseActivity(), stationBean.getLttd(), stationBean.getLgtd());
                    break;
                case "com.tencent.map":
                    NavUtil.toTencent(getBaseActivity(), stationBean.getLttd(), stationBean.getLgtd());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void initRequestData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getStationDetail(stationBean.getCode());
    }

    @Override
    public void getStationDetailSuccess(StationBean data) {
        refreshView(data);
    }

    private void refreshView(StationBean data) {
        data.setCode(stationBean.getCode());
        data.setStationType(stationBean.getStationType());
        data.setStationStatus(stationBean.getStationStatus());
        data.setLgtd(stationBean.getLgtd());
        data.setLttd(stationBean.getLttd());
        stationBean = data;
        {
            String temp = "";
            switch (stationBean.getStationStatus()) {
                case "0":
                    temp = "正常";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail_zc);
                    break;
                case "1":
                    temp = "异常";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail_yc);
                    break;
                case "2":
                    temp = "报警";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail);
                    break;
                case "3":
                    temp = "故障";
                    mBinding.tvDeviceStatusHeader.setBackgroundResource(R.drawable.bg_station_status_detail_gz);
                    break;
                default:
                    break;
            }
            mBinding.tvDeviceStatusHeader.setText(temp);
        }
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
            if (data.getCurrentData() == null || data.getCurrentData().getConductivityResult() == null || stationBean.getStationType().equals("2")) {
                mBinding.loWaterQTip.setVisibility(View.GONE);
                mBinding.loWaterQ.setVisibility(View.GONE);
            } else {
                mBinding.loWaterQTip.setVisibility(View.VISIBLE);
                mBinding.loWaterQ.setVisibility(View.VISIBLE);
                {
                    String temp = "";
                    Integer textColor = 0xff888888;
                    switch (data.getCurrentData().getConductivityResult().getConductivityStatus()) {
                        case 0:
                            temp = "正常";
                            textColor = 0xff888888;
                            break;
                        case 1:
                            temp = "异常";
                            textColor = 0xffE2ba00;
                            break;
                        case 2:
                            temp = "报警";
                            textColor = 0xfff1891f;
                            break;
                        case 3:
                            textColor = 0xfff34235;
                            temp = "故障";
                            break;
                        default:
                            break;
                    }
                    mBinding.tvCurWaterQ.setTextColor(textColor);
                    mBinding.tvCurWaterQ.setText(temp);
                    mBinding.tvConductivity.setText("电导率：" + data.getCurrentData().getConductivityResult().getConductivity() + "μs/cm");
                }
            }
        }
        {
            if (data.getCurrentData().getCommunicationResult() != null
                    && !TextUtils.isEmpty(data.getCurrentData().getCommunicationResult().getDuration())

                    && !data.getCurrentData().getCommunicationResult().getDuration().equals("0")
                    && !data.getCurrentData().getCommunicationResult().getDuration().equals("null")) {
                mBinding.tvDurationTimeTip.setVisibility(View.VISIBLE);
                mBinding.tvDurationTimeTip.setText("无通讯维持时间：" + data.getCurrentData().getCommunicationResult().getDuration());
            } else {
                mBinding.tvDurationTimeTip.setVisibility(View.GONE);
            }
            mBinding.rvStationStatusHis.setLayoutManager(new LinearLayoutManager(getContext()));
            AdapterStationStatusHis adapterStationStatusHis = new AdapterStationStatusHis(R.layout.lv_station_status_his_item_view, data.getCurrentData().getDeviceMonitorDataList());
            mBinding.rvStationStatusHis.setAdapter(adapterStationStatusHis);


        }

        {
            if (data.getWaterQuality() == null || stationBean.getStationType().equals("2")) {
                mBinding.loWaterQRen.setVisibility(View.INVISIBLE);
            } else {
                mBinding.loWaterQRen.setVisibility(View.VISIBLE);
                mBinding.rvWaterQData.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
                WaterQualityBean wBean = data.getWaterQuality();
                if (wBean != null) {
                    mBinding.tvLastTime.setText("最近一次监测时间：" + wBean.getUpdatedTime());
                    ArrayList<KeyValueBean> list = new ArrayList<>();
                    list.add(new KeyValueBean("悬浮物", wBean.getSs() + ""));
                    list.add(new KeyValueBean("COD", wBean.getCod() + ""));
                    list.add(new KeyValueBean("动植物油", wBean.getDzwy() + ""));
                    list.add(new KeyValueBean("总磷", wBean.getTp() + ""));
                    list.add(new KeyValueBean("总氮", wBean.getTn() + ""));
                    list.add(new KeyValueBean("氨氮", wBean.getNh3n() + ""));
                    list.add(new KeyValueBean("阴离子表面活性剂", wBean.getLas() + ""));
                    AdapterWaterQData adapterWaterQData = new AdapterWaterQData(R.layout.lv_water_q_item_view, list);
                    mBinding.rvWaterQData.setAdapter(adapterWaterQData);
                }
            }
        }

        if (!CollectionsUtil.isEmpty(data.getHandingStation().getReferenceFileList())) {
            initBanner(data.getHandingStation().getReferenceFileList());
        }
        {
            String temp = "";
            Integer textColor = 0xff888888;
            switch (data.getCurrentData().getCommunicationResult().getCommunicationStatus()) {
                case 0:
                    switch (data.getCurrentData().getDeviceResult().getDeviceStatus()) {
                        case 0:
                            temp = "正常";
                            textColor = 0xff888888;
                            break;
                        case 1:
                            temp = "异常";
                            textColor = 0xffE2ba00;
                            break;
                        case 2:
                            temp = "报警";
                            textColor = 0xfff1891f;
                            break;
                        case 3:
                            textColor = 0xfff34235;
                            temp = "故障";
                            break;
                        case -1:
                            temp = "无数据接入";
                            textColor = 0xfff34235;
                            break;
                        default:
                            break;
                    }
                    break;

                default:
                    temp = "无通讯";
                    break;
            }
            mBinding.tvStationStatus.setTextColor(textColor);
            mBinding.tvStationStatus.setText(temp);
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
