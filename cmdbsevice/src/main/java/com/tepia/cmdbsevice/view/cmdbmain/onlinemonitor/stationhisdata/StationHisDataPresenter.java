package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail.KeyValueBean;
import com.tepia.cmnwsevice.model.station.HisDataBean;
import com.tepia.cmnwsevice.model.station.StationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class StationHisDataPresenter extends BasePresenterImpl<StationHisDataContract.View> implements StationHisDataContract.Presenter {

    public void getFaultHistory(String startTime, String endTime, String stationCode, String type) {
        StationManager.getInstance().getFaultHistory(startTime, endTime, stationCode, type).safeSubscribe(new LoadingSubject<BaseCommonResponse<HisDataBean>>() {
            @Override
            protected void _onNext(BaseCommonResponse<HisDataBean> baseCommonResponse) {
                mView.getFaultHistorySuccess(baseCommonResponse.getData());

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getWarningHistory(String startTime, String endTime, String stationCode, String type) {
        StationManager.getInstance().getWarningHistory(startTime, endTime, stationCode, type).safeSubscribe(new LoadingSubject<BaseCommonResponse<HisDataBean>>() {
            @Override
            protected void _onNext(BaseCommonResponse<HisDataBean> baseCommonResponse) {
                mView.getWarningHistorySuccess(baseCommonResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public List<KeyValueBean> getTypeList() {
        ArrayList<KeyValueBean> list = new ArrayList<>();
//        {"10002":"水质","10001":"设备","10003":"通讯"}
        list.add(new KeyValueBean("全部类型", "10001,10002,10003"));
        list.add(new KeyValueBean("设备", "10001"));
        list.add(new KeyValueBean("水质", "10002"));
        list.add(new KeyValueBean("通讯", "10003"));
        return list;
    }
}
