package com.tepia.cmnwsevice.model.user;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.cmnwsevice.model.station.AreaBean;
import com.tepia.cmnwsevice.model.station.VenderBean;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/4
 * Time            :       17:06
 * Version         :       1.0
 * 功能描述        :        用户相关接口
 **/
public interface UserService {
    /**
     * 用户登录
     *
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/user/appLogin")
    Observable<BaseCommonResponse<LoginBean>> appLogin(@Body RequestBody body);

    /**
     * 【查询】获取乡镇列表
     *
     * @return
     */
    @GET("area/list")
    Observable<BaseCommonResponse<List<AreaBean>>> getAreaList(@Header("token") String token);

    /**
     * 【查询】获取企业列表
     *
     * @param token
     * @return
     */
    @GET("vendor/list")
    Observable<BaseCommonResponse<List<VenderBean>>> getVenderList(@Header("token") String token);

}
