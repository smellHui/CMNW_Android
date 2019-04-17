package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;

import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmdbsevice.model.station.StationBean;

import java.util.ArrayList;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationListPresenter extends BasePresenterImpl<StationListContract.View> implements StationListContract.Presenter{

    public void getStationList() {
        ArrayList<StationBean> list = new ArrayList<>();
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        list.add(new StationBean());
        mView.getStationListSuccess(list);
    }
}
