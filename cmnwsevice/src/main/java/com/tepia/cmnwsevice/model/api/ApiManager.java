package com.tepia.cmnwsevice.model.api;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.cmnwsevice.model.RiverBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:xch
 * Date:2019/4/3
 * Do:
 */
public class ApiManager {

    private static ApiManager apiManager;
    private ApiService mRetrofitService;

    public static ApiManager getInstance() {
        synchronized (ApiManager.class) {
            if (apiManager == null) {
                apiManager = new ApiManager();
            }
        }
        return apiManager;
    }

    private ApiManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(Urls.ROOT_URL).create(ApiService.class);
    }


    public Observable<BaseCommonResponse<List<RiverBean>>> queryList() {
        return mRetrofitService.getRiverList("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
