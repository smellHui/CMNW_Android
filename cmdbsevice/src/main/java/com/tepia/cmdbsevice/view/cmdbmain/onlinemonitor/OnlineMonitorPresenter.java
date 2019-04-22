package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.SPUtils;
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
                        bean.saveOrUpdate("code=?", bean.getCode());
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
        String temp = SPUtils.getInstance().getString("SEARCHHISLIST", "");
        ArrayList<String> list = new ArrayList<>();
        if (!TextUtils.isEmpty(temp)) {
            list = new Gson().fromJson(temp, new TypeToken<ArrayList<String>>() {
            }.getType());
        }
        return list;
    }

    public void clearSearchHisList() {
        SPUtils.getInstance().putString("SEARCHHISLIST", "");
    }

    public ArrayList<StationBean> getSearchTipList(String temp) {
        List<StationBean> list = DataSupport.where("name like ?", "%" + temp + "%").find(StationBean.class);
        ArrayList<StationBean> list2 = new ArrayList<>();
        list2.addAll(list);
        return list2;
    }

    public void putSearchHis(String temp) {
        ArrayList<String> list = getSearchHisList();
        list.add(temp);
        SPUtils.getInstance().putString("SEARCHHISLIST", new Gson().toJson(list));
    }
}
