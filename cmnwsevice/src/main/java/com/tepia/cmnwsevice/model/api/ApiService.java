package com.tepia.cmnwsevice.model.api;

import com.tepia.base.model.BaseResp;
import com.tepia.base.model.PageBean;
import com.tepia.cmnwsevice.model.RiverBean;

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
    @GET("m/farm/queryFarmList")
    Observable<BaseResp<PageBean<RiverBean>>> getRiverList(@Header("_gt") String token);

}
