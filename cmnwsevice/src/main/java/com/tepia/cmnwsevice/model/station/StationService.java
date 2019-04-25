package com.tepia.cmnwsevice.model.station;

import com.tepia.base.http.BaseCommonResponse;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/15
 * Time            :       15:45
 * Version         :       1.0
 * 功能描述        :
 **/
public interface StationService {
    /**
     * 【查询】所有站点的列表，用于下拉选择
     *
     * @param token
     * @return
     */

    @GET("station/listStationList")
    Observable<BaseCommonResponse<List<StationBean>>> getStationList(@Header("token") String token);

    /**
     * 【查询】根据code获取站点详情
     *
     * @param token
     * @param code
     * @return
     */
    @GET("station/getStationByCode/{code}")
    Observable<BaseCommonResponse<StationBean>> getStationByCode(@Header("token") String token, @Path("code") String code);

    /**
     * 【查询】历史运行
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("station/getHistoryRunStatus")
    Observable<BaseCommonResponse> getHistoryRunStatus(@Header("token") String token, @Body RequestBody body);

    /**
     * 【查询】历史水质
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("station/historyWaterQuality")
    Observable<BaseCommonResponse> historyWaterQuality(@Header("token") String token, @Body RequestBody body);

    /**
     * 站点详情-历史故障
     *
     * @param token
     * @param startTime
     * @param endTime
     * @param stationCode
     * @param type
     * @return
     */
    @GET("alarmController/getFaultHistory")
    Observable<BaseCommonResponse<HisDataBean>> getFaultHistory(@Header("token") String token,
                                       @Query("startTime") String startTime,
                                       @Query("endTime") String endTime,
                                       @Query("stationCode") String stationCode,
                                       @Query("type") String type);

    /**
     * 站点详情-历史报警
     *
     * @param token
     * @param startTime
     * @param endTime
     * @param stationCode
     * @param type
     * @return
     */
    @GET("alarmController/getWarningHistory")
    Observable<BaseCommonResponse> getWarningHistory(@Header("token") String token,
                                         @Query("startTime") String startTime,
                                         @Query("endTime") String endTime,
                                         @Query("stationCode") String stationCode,
                                         @Query("type") String type);
}
