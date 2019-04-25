package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationhisdata;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.cmnwsevice.model.station.HisDataBean;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationHisDataContract {
    interface View extends BaseView {

        void getFaultHistorySuccess(HisDataBean data);

        void getWarningHistorySuccess(HisDataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
