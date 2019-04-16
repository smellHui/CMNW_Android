package com.tepia.cmdbsevice.model.station;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.cmdbsevice.APPConst;
import com.tepia.cmnwsevice.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/15
 * Time            :       15:42
 * Version         :       1.0
 * 功能描述        :        测站数据 管理类
 **/
public class StationManager {

    private static final StationManager ourInstance = new StationManager();
    private StationService mRetrofitService;

    public static StationManager getInstance() {
        return ourInstance;
    }

    private StationManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPConst.ROOT_URL).create(StationService.class);
    }

    public Observable<BaseCommonResponse> getStationList() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getStationList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
