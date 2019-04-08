package com.tepia.cmnwsevice.model.order;

import com.alibaba.android.arouter.facade.Postcard;
import com.tepia.base.http.BaseCommonResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       11:49
 * Version         :       1.0
 * 功能描述        :        工单相关接口
 **/
public interface OrderService {
    /**
     * 分页查询工单列表
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/page")
    Observable<BaseCommonResponse> getOrderList(@Header("token") String token, @Body RequestBody body);

    /**
     * 统计工单状态数
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/count")
    Observable<BaseCommonResponse> getOrderCount(@Header("token") String token, @Body RequestBody body);


    /**
     * 查询工单详情
     *
     * @param token
     * @param orderId 工单id
     * @return
     */

    @GET("order/{orderId}")
    Observable<BaseCommonResponse> getOrderDetail(@Header("token") String token, @Part("orderId") String orderId);

    /**
     * 【查询】获取派单操作详情（同处理中提示页面）
     *
     * @param token
     * @param orderId 工单id
     * @return
     */

    @GET("order/working/{orderId}")
    Observable<BaseCommonResponse> getOrderOperationDetail(@Header("token") String token, @Part("orderId") String orderId);

    /**
     * 【查询】查询工单运维详情
     *
     * @param token
     * @param orderId 工单id
     * @return
     */

    @GET("order/operation/{orderId}")
    Observable<BaseCommonResponse> getOrderWorkingDetail(@Header("token") String token, @Part("orderId") String orderId);

    /**
     * 【查询】查询工单审核记录
     *
     * @param token
     * @param orderId 工单id
     * @return
     */

    @GET("order/examineList/{orderId}")
    Observable<BaseCommonResponse> getOrderExamineList(@Header("token") String token, @Part("orderId") String orderId);

    /**
     * 【查询】查询工单下发候选用户列表
     *
     * @param token
     * @param orderId 工单id
     * @return
     */

    @GET("order/getUserList/{orderId}")
    Observable<BaseCommonResponse> getOrderUserList(@Header("token") String token, @Part("orderId") String orderId);

    /**
     * 【下发】下发工单
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/send")
    Observable<BaseCommonResponse> sendOrder(@Header("token") String token, @Body RequestBody body);

    /**
     * 【执行】点击导航前往执行工单
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/startToExe")
    Observable<BaseCommonResponse> startToExeOrder(@Header("token") String token, @Body RequestBody body);

    /**
     * 【执行】开始执行工单
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/start")
    Observable<BaseCommonResponse> startOrder(@Header("token") String token, @Body RequestBody body);

    /**
     * 【执行】完成执行工单
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/done")
    Observable<BaseCommonResponse> doneOrder(@Header("token") String token, @Body RequestBody body);

    /**
     * 【审核】审核工单
     *
     * @param token
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("order/done")
    Observable<BaseCommonResponse> examineOrder(@Header("token") String token, @Body RequestBody body);


    /**
     * 【上传】上传运维文件
     *
     * @param token
     * @param pathList
     * @return
     */
    @Multipart
    @POST("file/upload")
    Observable<BaseCommonResponse> uploadFile(@Header("token") String token,
                                                      @Part List<MultipartBody.Part> pathList);
}
