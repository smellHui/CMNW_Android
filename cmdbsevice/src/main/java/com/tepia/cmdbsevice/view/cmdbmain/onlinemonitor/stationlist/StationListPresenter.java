package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationlist;

import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.cmnwsevice.model.station.StationBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class StationListPresenter extends BasePresenterImpl<StationListContract.View> implements StationListContract.Presenter {

    public boolean isCanLoadMore;
    private int pageIndex;
    private int pageSize = 30;


    public void getStationList(String... conditions) {
        List<StationBean> list = DataSupport.where(conditions).limit(pageSize).find(StationBean.class);
        ArrayList<StationBean> list2 = new ArrayList<>();
        list2.addAll(list);
        pageIndex = 1;
        if (list.size() == pageSize) {
            isCanLoadMore = true;
        } else {
            isCanLoadMore = false;
        }
        mView.getStationListSuccess(list2);
    }

    public void getStationListMore(String... conditions) {
        List<StationBean> list = DataSupport.where(conditions).limit(30).offset(pageIndex * pageSize).find(StationBean.class);
        ArrayList<StationBean> list2 = new ArrayList<>();
        pageIndex++;
        list2.addAll(list);
        if (list.size() == pageSize) {
            isCanLoadMore = true;
        } else {
            isCanLoadMore = false;
        }
        mView.getStationListMoreSuccess(list2,pageIndex,pageSize);
    }
}
