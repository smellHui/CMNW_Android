package com.tepia.cmdbsevice.model.event;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.cmnwsevice.model.order.OrderCountBean;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
     * 【查询】实时督办报警、故障统计列表
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("supervise/listByWarning")
    Observable<BaseCommonResponse<List<WarnBean>>> listByWarning(@Header("token") String token, @Body RequestBody body);

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
