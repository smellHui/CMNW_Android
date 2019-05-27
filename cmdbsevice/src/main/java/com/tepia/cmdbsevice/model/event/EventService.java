package com.tepia.cmdbsevice.model.event;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.model.PageBean;
import com.tepia.cmdbsevice.view.alarmstatistics.model.ReportModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/15
 * Time            :       16:01
 * Version         :       1.0
 * 功能描述        :
 **/
public interface EventService {
    /**
     * 【查询】报警、故障总数
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/topTotal")
    Observable<BaseCommonResponse<TopTotalBean>> topTotal(@Header("token") String token, @Body RequestBody body);

    /**
     * 【详情】查询事件督办详情
     *
     * @param token
     * @param eventId
     * @return
     */
    @GET("supervise/info")
    Observable<BaseCommonResponse<WarnDetailBean>> superviseInfo(@Header("token") String token, @Query("eventId") String eventId);

    /**
     * 【详情】获取群众上报事件详情
     *
     * @param token
     * @param eventId
     * @return
     */
    @GET("eventReport/simpleInfo")
    Observable<BaseCommonResponse<ReportModel>> simpleInfo(@Header("token") String token, @Query("eventId") String eventId);

    /**
     * 【查询】实时督办-企业报警、故障数
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/countByVendor")
    Observable<BaseCommonResponse<List<TopTotalBean>>> countByVendor(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】查询企业故障率
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/countFaultRateByVendor")
    Observable<BaseCommonResponse<List<TopTotalBean>>> countFaultRateByVendor(@Header("token") String token, @Body RequestBody body);


    /**
     * 【查询】故障率统计
     *
     * @param token
     * @return
     */
    @GET("overallStatistics/faultStatistics")
    Observable<BaseCommonResponse<List<TopTotalBean>>> faultStatistics(@Header("token") String token, @Query("dimension") String dimension, @Query("startDate") String startDate, @Query("endDate") String endDate);

    /**
     * 【查询】按企业分析在线水质达标率
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("overallStatistics/listReachRateByVendor")
    Observable<BaseCommonResponse<List<WaterRateBean>>> listReachRateByVendor(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】实时督办-乡镇报警、故障数
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/countByTown")
    Observable<BaseCommonResponse<List<TopTotalBean>>> countByTown(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】查询乡镇故障率
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/countFaultRateByTown")
    Observable<BaseCommonResponse<List<TopTotalBean>>> countFaultRateByTown(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】按行政区划分析在线水质达标率
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("overallStatistics/listReachRateByStcd")
    Observable<BaseCommonResponse<List<WaterRateBean>>> listReachRateByStcd(@Header("token") String token, @Body RequestBody body);

    /**
     * 按行政区划分析人工水质达标率
     *
     * @param token
     * @param dataTime
     * @return
     */
    @GET("overallStatistics/listWaterQualityRateByStcd")
    Observable<BaseCommonResponse<List<WaterRateBean>>> listWaterQualityRateByStcd(@Header("token") String token, @Query("dataTime") String dataTime);

    /**
     * 【查询】按企业分析人工水质达标率
     *
     * @param token
     * @param dataTime
     * @return
     */
    @GET("overallStatistics/listWaterQualityRateByVendor")
    Observable<BaseCommonResponse<List<WaterRateBean>>> listWaterQualityRateByVendor(@Header("token") String token, @Query("dataTime") String dataTime);

    /**
     * 【查询】实时督办-报警列表
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/listByWarning")
    Observable<PageBean<WarnBean>> listByWarning(@Header("token") String token, @Body RequestBody body);

    /**
     * 【审核】事件审核
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("eventReport/examine")
    Observable<BaseCommonResponse> examine(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】实时督办-故障列表
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/listByFault")
    Observable<PageBean<WarnBean>> listByFault(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】查询群众上报事件列表
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("eventReport/todoReportList")
    Observable<PageBean<WarnBean>> todoReportList(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】获取乡镇列表
     *
     * @param token
     * @return
     */
    @GET("area/list")
    Observable<BaseCommonResponse<List<AreaBean>>> arealist(@Header("token") String token);

    /**
     * 【查询】人工水质可选时间获取
     *
     * @param token
     * @return
     */
    @GET("overallStatistics/listDataTime")
    Observable<BaseCommonResponse<List<String>>> listDataTime(@Header("token") String token);

    /**
     * 【查询】获取企业列表
     *
     * @param token
     * @return
     */
    @GET("vendor/list")
    Observable<BaseCommonResponse<List<AreaBean>>> vendorlist(@Header("token") String token);

    /**
     * 【查询】故障类型分析统计
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/countFaultType")
    Observable<BaseCommonResponse> countFaultType(@Header("token") String token, @Body RequestBody body);
}
