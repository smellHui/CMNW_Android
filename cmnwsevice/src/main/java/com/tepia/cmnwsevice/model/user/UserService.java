package com.tepia.cmnwsevice.model.user;

import com.tepia.base.http.BaseCommonResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("/user/appLogin")
    Observable<BaseCommonResponse<LoginBean>> appLogin(@Body RequestBody body);
}
