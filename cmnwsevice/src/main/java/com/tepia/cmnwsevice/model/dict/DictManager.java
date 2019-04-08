package com.tepia.cmnwsevice.model.dict;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.utils.SPUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.APPConst;
import com.tepia.cmnwsevice.model.user.UserManager;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-12-13
 * Time            :       15:17
 * Version         :       1.0
 * 功能描述        :        数据字典管理器
 **/
public class DictManager {
    private static final DictManager ourInstance = new DictManager();
    private final DictHttpService mRetrofitService;
    private Map<String, Map<String, String>> dictMap;

    public static DictManager getInstance() {
        return ourInstance;
    }

    public Map<String, Map<String, String>> getDictMap() {
        if (dictMap == null) {
            String temp = SPUtils.getInstance().getString("dictMap", "");

            getServerDict();
            if (!TextUtils.isEmpty(temp)) {
                return new Gson().fromJson(temp, new TypeToken<Map<String, Map<String, String>>>() {
                }.getType());
            } else {
                return new HashMap<>();
            }
        }
        return dictMap;
    }


    public void setDictMap(Map<String, Map<String, String>> dictMap) {
        this.dictMap = dictMap;
    }

    private DictManager() {
        mRetrofitService = RetrofitManager.getRetrofit(APPConst.ROOT_URL).create(DictHttpService.class);
        getServerDict().safeSubscribe(new LoadingSubject<BaseCommonResponse<Map<String, Map<String, String>>>>() {
            @Override
            protected void _onNext(BaseCommonResponse<Map<String, Map<String, String>>> response) {
                dictMap = response.getData();
                SPUtils.getInstance().putString("dictMap", new Gson().toJson(dictMap).toString());
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public Observable<BaseCommonResponse<Map<String, Map<String, String>>>> getServerDict() {
        String token = UserManager.getInstance().getToken();
        return mRetrofitService.getServerDict(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
