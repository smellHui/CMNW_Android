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
import com.tepia.cmdbsevice.R;
import com.tepia.cmnwsevice.model.station.AreaBean;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmnwsevice.model.station.StationManager;
import com.tepia.cmnwsevice.model.station.VenderBean;

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
        if (!list.contains(temp)) {
            list.add(temp);
        }
        SPUtils.getInstance().putString("SEARCHHISLIST", new Gson().toJson(list));
    }

    public List<StationTypeBean> getVenderList() {
        ArrayList<StationTypeBean> list = new ArrayList<>();
        List<VenderBean> venderBeanList = DataSupport.findAll(VenderBean.class);
        for (VenderBean bean : venderBeanList) {
            if (bean != null) {
                list.add(new StationTypeBean(bean.getName(), bean.getCode(), true));
            }
        }
        return list;
    }

    public List<StationTypeBean> getAreaList() {
        ArrayList<StationTypeBean> list = new ArrayList<>();
        List<AreaBean> venderBeanList = DataSupport.findAll(AreaBean.class);
        for (AreaBean bean : venderBeanList) {
            if (bean != null) {
                list.add(new StationTypeBean(bean.getName(), bean.getCode(), true));
            }
        }
        return list;
    }

    public List<StationTypeBean> getStationTypeList() {
        ArrayList<StationTypeBean> list = new ArrayList<>();
        list.add(new StationTypeBean("提升井", "2", R.drawable.bg_circle_eee, R.mipmap.icn_tsj, true));
        list.add(new StationTypeBean("污水处理站", "1", R.drawable.bg_circle_eee, R.mipmap.icn_fj, true));
        return list;
    }

    public List<StationTypeBean> getStationStatusList() {
        ArrayList<StationTypeBean> list = new ArrayList<>();
        list.add(new StationTypeBean("正常", "0", R.drawable.bg_circle_4fcffa, true));
        list.add(new StationTypeBean("异常", "1", R.drawable.bg_circle_ffe42d, true));
        list.add(new StationTypeBean("报警", "2", R.drawable.bg_circle_ffaa53, true));
        list.add(new StationTypeBean("故障", "3", R.drawable.bg_circle_f43234, true));
        return list;
    }

    public int getStationNum(String conditions) {
        return DataSupport.where(conditions).count(StationBean.class);
    }

}
