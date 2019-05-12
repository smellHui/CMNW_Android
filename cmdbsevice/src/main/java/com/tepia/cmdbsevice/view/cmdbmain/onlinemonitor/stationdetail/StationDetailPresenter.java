package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmnwsevice.model.station.StationManager;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationDetailPresenter extends BasePresenterImpl<StationDetailContract.View> implements StationDetailContract.Presenter{

    public void getStationDetail(String code) {
        StationManager.getInstance().getStationDetail(code).safeSubscribe(new LoadingSubject<BaseCommonResponse<StationBean>>(true,"获取站点详细信息") {
            @Override
            protected void _onNext(BaseCommonResponse<StationBean> stationBeanBaseCommonResponse) {
                mView.getStationDetailSuccess(stationBeanBaseCommonResponse.getData());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
