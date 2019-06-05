package com.tepia.cmdbsevice.model.event;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.model.PageBean;

import com.tepia.cmdbsevice.view.alarmstatistics.model.FaultInfoModel;
import com.tepia.cmdbsevice.view.alarmstatistics.model.ReportModel;
import com.tepia.cmnwsevice.APPConst;
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
     * 【详情】查询事件督办详情
     *
     * @param eventId
     * @return
     */
    public Observable<BaseCommonResponse<WarnDetailBean>> superviseInfo(String eventId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.superviseInfo(token, eventId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【详情】查询故障详情
     *
     * @param eventId
     * @return
     */
    public Observable<BaseCommonResponse<FaultInfoModel>> faultInfo(String eventId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.faultInfo(token, eventId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 详情】获取群众上报事件详情
     *
     * @param eventId
     * @return
     */
    public Observable<BaseCommonResponse<ReportModel>> simpleInfo(String eventId) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.simpleInfo(token, eventId)
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
     * 【查询】按行政区划分析在线水质达标率
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse<List<WaterRateBean>>> listReachRateByStcd(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listReachRateByStcd(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 按行政区划分析人工水质达标率
     *
     * @param dataTime
     * @return
     */
    public Observable<BaseCommonResponse<List<WaterRateBean>>> listWaterQualityRateByStcd(String dataTime) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listWaterQualityRateByStcd(token, dataTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】按企业分析人工水质达标率
     *
     * @param dataTime
     * @return
     */
    public Observable<BaseCommonResponse<List<WaterRateBean>>> listWaterQualityRateByVendor(String dataTime) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listWaterQualityRateByVendor(token, dataTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】人工水质可选时间获取
     *
     * @return
     */
    public Observable<BaseCommonResponse<List<String>>> listDataTime() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listDataTime(token)
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
     * 【查询】故障率统计
     *
     * @return
     */
    public Observable<BaseCommonResponse<List<TopTotalBean>>> faultStatistics(String dimension, String startDate, String endDate) {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.faultStatistics(token, dimension, startDate, endDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】按企业分析在线水质达标率
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse<List<WaterRateBean>>> listReachRateByVendor(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listReachRateByVendor(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【审核】事件审核
     *
     * @param params
     * @return
     */
    public Observable<BaseCommonResponse> examine(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.examine(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 【查询】实时督办-报警列表
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
     * 【查询】实时督办-故障列表
     *
     * @param params
     * @return
     */
    public Observable<PageBean<WarnBean>> listByFault(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.listByFault(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 【查询】查询群众上报事件列表
     *
     * @param params
     * @return
     */
    public Observable<PageBean<WarnBean>> todoReportList(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.todoReportList(token, body)
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
