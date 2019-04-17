package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.model.station.StationBean;
import com.tepia.cmdbsevice.model.station.StationManager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/15 15:49
 * @修改人 ：
 * @修改时间 :       2019/4/15 15:49
 * @功能描述 :
 **/

public class OnlineMonitorPresenter extends BasePresenterImpl<OnlineMonitorContract.View> implements OnlineMonitorContract.Presenter {

    public void getStationList() {
        StationManager.getInstance().getStationList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<StationBean>>>() {
            @Override
            protected void _onNext(BaseCommonResponse<List<StationBean>> baseCommonResponse) {
                if (!CollectionsUtil.isEmpty(baseCommonResponse.getData())) {
                    for (StationBean bean : baseCommonResponse.getData()) {
                        bean.save();
                    }
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public ArrayList<String> getSearchHisList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("处理站");
        list.add("提升井");
        return list;
    }

    public ArrayList<StationBean> getSearchTipList(String temp) {
        List<StationBean> list = DataSupport.where("name like ?", "%" + temp + "%").find(StationBean.class);
        ArrayList<StationBean> list2 = new ArrayList<>();
        list2.addAll(list);
        return list2;
    }
}
