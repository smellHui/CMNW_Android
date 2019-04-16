package com.tepia.cmdbsevice.model.station;

import com.tepia.base.http.BaseCommonResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("app/station/listStationList")
    Observable<BaseCommonResponse> getStationList(@Header("token") String token);
}
