package com.tepia.cmnwsevice.view.api;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.cmnwsevice.model.RiverBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Author:xch
 * Date:2019/4/3
 * Do:
 */
public interface ApiService {

    /**
     * 获取河道下拉框数据
     *
     * @param token
     * @return
     */
    @GET("app/river/riverList")
    Observable<BaseCommonResponse<List<RiverBean>>> getRiverList(@Header("token") String token);

}
