package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;
import com.tepia.cmdbsevice.model.station.StationBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationListContract {
    interface View extends BaseView {

        void getStationListSuccess(ArrayList<StationBean> list);
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}