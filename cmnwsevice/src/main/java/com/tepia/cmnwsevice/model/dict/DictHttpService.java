package com.tepia.cmnwsevice.model.dict;


import com.tepia.base.http.BaseCommonResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-12-13
 * Time            :       15:17
 * Version         :       1.0
 * 功能描述        :
 **/
public interface DictHttpService {
    @GET("dict/getDictMap")
    Observable<BaseCommonResponse<Map<String, Map<String, String>>>> getServerDict(@Header("token") String token);
}
