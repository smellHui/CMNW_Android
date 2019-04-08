package com.tepia.cmnwsevice.model.order;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.cmnwsevice.APPConst;
import com.tepia.cmnwsevice.model.user.LoginBean;
import com.tepia.cmnwsevice.model.user.UserBean;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.model.user.UserService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       11:48
 * Version         :       1.0
 * 功能描述        :        工单数据管理类
 **/
public class OrderManager {
    private static final OrderManager ourInstance = new OrderManager();
    private OrderService mRetrofitService;

    public static OrderManager getInstance() {
        return ourInstance;
    }

    private OrderManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPConst.ROOT_URL).create(OrderService.class);
    }

    /**
     * 分页查询工单列表
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> getOrderList(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderList(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】统计工单状态数
     * @param params
     * @return
     */
    public  Observable<BaseCommonResponse> getOrderCount(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderCount(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
