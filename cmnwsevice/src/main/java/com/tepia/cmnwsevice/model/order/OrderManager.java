package com.tepia.cmnwsevice.model.order;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.cmnwsevice.APPConst;
import com.tepia.cmnwsevice.model.user.LoginBean;
import com.tepia.cmnwsevice.model.user.UserBean;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.model.user.UserService;

import java.util.List;
import java.util.Map;

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
    public Observable<BaseCommonResponse<List<OrderBean>>> getOrderList(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderList(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】统计工单状态数
     *
     * @param params
     * @return {"code":0,"msg":"处理成功","data":{"toExamine":1,"toSend":112,"onExecute":0,"toExecute":1,"done":0,"toBack":0}}
     */
    public Observable<BaseCommonResponse<OrderCountBean>> getOrderCount(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderCount(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询工单详情
     *
     * @param orderId
     * @return
     */
    public Observable<BaseCommonResponse<OrderBean>> getOrderDetail(String orderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderDetail(token, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】获取派单操作详情（同处理中提示页面）
     *
     * @param orderId
     * @return
     */
    public Observable<BaseCommonResponse> getOrderWorkingDetail(String orderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderWorkingDetail(token, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询工单运维详情
     *
     * @param orderId
     * @return
     */
    public Observable<BaseCommonResponse> getOrderOperationDetail(String orderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderOperationDetail(token, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询工单审核记录
     *
     * @param orderId
     * @return
     */
    public Observable<BaseCommonResponse> getOrderExamineList(String orderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderExamineList(token, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询工单下发候选用户列表
     *
     * @param orderId
     * @return
     */
    public Observable<BaseCommonResponse> getOrderUserList(String orderId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getOrderUserList(token, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【下发】下发工单
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> getOrderUserList(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.sendOrder(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【执行】点击导航前往执行工单
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> startToExeOrder(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.startToExeOrder(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【执行】开始执行工单
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> startOrder(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.startOrder(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【执行】完成执行工单
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> doneOrder(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.doneOrder(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【执行】完成执行工单
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> examineOrder(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.examineOrder(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
