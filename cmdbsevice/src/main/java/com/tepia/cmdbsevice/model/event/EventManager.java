package com.tepia.cmdbsevice.model.event;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.model.PageBean;
import com.tepia.cmdbsevice.APPConst;
import com.tepia.cmnwsevice.model.user.UserManager;

import java.util.List;

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

    public Observable<BaseCommonResponse<TopTotalBean>> topTotal(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.topTotal(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】实时督办-乡镇报警、故障数
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse<List<TopTotalBean>>> countByTown(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.countByTown(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】实时督办-企业报警、故障数
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse<List<TopTotalBean>>> countByVendor(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.countByVendor(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询乡镇故障率
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse<List<TopTotalBean>>> countFaultRateByTown(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.countFaultRateByTown(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询企业故障率
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse<List<TopTotalBean>>> countFaultRateByVendor(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.countFaultRateByVendor(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 【查询】实时督办报警、故障统计列表
     *
     * @param params
     * @return
     */
    public Observable<PageBean<WarnBean>> listByWarning(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listByWarning(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】获取乡镇列表
     *
     * @return
     */
    public Observable<BaseCommonResponse<List<AreaBean>>> arealist() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.arealist(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】获取企业列表
     *
     * @return
     */
    public Observable<BaseCommonResponse<List<AreaBean>>> vendorlist() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.vendorlist(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
