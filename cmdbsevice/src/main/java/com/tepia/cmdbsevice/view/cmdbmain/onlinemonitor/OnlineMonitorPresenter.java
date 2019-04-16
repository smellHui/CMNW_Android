package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmdbsevice.model.station.StationManager;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/15 15:49
 * @修改人         ：      
 * @修改时间       :       2019/4/15 15:49
 * @功能描述       :
 **/

public class OnlineMonitorPresenter extends BasePresenterImpl<OnlineMonitorContract.View> implements OnlineMonitorContract.Presenter{

    public void getStationList() {
        StationManager.getInstance().getStationList().safeSubscribe(new LoadingSubject<BaseCommonResponse>() {
            @Override
            protected void _onNext(BaseCommonResponse baseCommonResponse) {

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
