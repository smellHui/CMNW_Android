package com.tepia.cmdbsevice.model.event;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.cmdbsevice.APPConst;
import com.tepia.cmdbsevice.model.station.StationService;
import com.tepia.cmnwsevice.model.user.UserManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/15
 * Time            :       16:00
 * Version         :       1.0
 * 功能描述        :
 **/
public class EventManager {
    private static final EventManager ourInstance = new EventManager();
    private EventService mRetrofitService;

    public static EventManager getInstance() {
        return ourInstance;
    }

    private EventManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPConst.ROOT_URL).create(EventService.class);
    }

    public Observable<BaseCommonResponse> topTotal(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.topTotal(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
