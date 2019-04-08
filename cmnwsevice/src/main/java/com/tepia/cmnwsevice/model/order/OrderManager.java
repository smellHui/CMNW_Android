package com.tepia.cmnwsevice.model.order;

import com.tepia.base.http.RetrofitManager;
import com.tepia.cmnwsevice.APPConst;
import com.tepia.cmnwsevice.model.user.UserService;

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
}
