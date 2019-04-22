package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;

import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmdbsevice.model.station.StationBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StationListPresenter extends BasePresenterImpl<StationListContract.View> implements StationListContract.Presenter{

    public void getStationList(String temp) {
        List<StationBean> list = DataSupport.where("name like ?", "%" + temp + "%").find(StationBean.class);
        ArrayList<StationBean> list2 = new ArrayList<>();
        list2.addAll(list);
        mView.getStationListSuccess(list2);
    }
}
