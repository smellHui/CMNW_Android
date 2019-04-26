package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.cmnwsevice.model.station.StationBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationListContract {
    interface View extends BaseView {

        void getStationListSuccess(ArrayList<StationBean> list);

        void getStationListMoreSuccess(ArrayList<StationBean> list2, int pageIndex, int pageSize);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
