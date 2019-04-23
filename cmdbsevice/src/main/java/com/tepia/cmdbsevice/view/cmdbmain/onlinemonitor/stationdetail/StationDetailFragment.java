package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;


import android.databinding.DataBindingUtil;

import com.tepia.cmdbsevice.databinding.FragmentStationDetailBinding;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.station.StationBean;

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
        mBinding.tvStationName.setText(data.getStationMessage().getStationName());
        mBinding.tvStationOrgan.setText(data.getStationMessage().getVendorName());
        mBinding.tvStationAdress.setText(data.getStationMessage().getAddress());
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
        AdapterStationStatusHis adapterStationStatusHis = new AdapterStationStatusHis(R.layout.lv_station_status_his_item_view,data.getCurrentData().getDeviceMonitorDataList());
        mBinding.rvStationStatusHis.setAdapter(adapterStationStatusHis);
    }
}
