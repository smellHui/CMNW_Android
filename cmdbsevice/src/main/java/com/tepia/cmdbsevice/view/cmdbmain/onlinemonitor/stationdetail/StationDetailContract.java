package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.cmnwsevice.model.station.StationBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationDetailContract {
    interface View extends BaseView {
        void getStationDetailSuccess(StationBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
