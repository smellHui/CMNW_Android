package com.tepia.cmnwsevice.model.user;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.RetrofitManager;
import com.tepia.base.utils.SPUtils;
import com.tepia.cmnwsevice.APPConst;
import com.tepia.cmnwsevice.model.api.ApiService;
import com.tepia.cmnwsevice.model.api.Urls;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/4
 * Time            :       17:02
 * Version         :       1.0
 * 功能描述        :        用户相关数据管理
 **/
public class UserManager {
    private static final UserManager ourInstance = new UserManager();
    private UserService mRetrofitService;

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        this.mRetrofitService = RetrofitManager.getRetrofit(APPConst.ROOT_URL).create(UserService.class);
    }

    public Observable<BaseCommonResponse<LoginBean>> appLogin(Object... params) {
        RequestBody body = RetrofitManager.convertToRequestBodyForJson(params);
        return mRetrofitService.appLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void saveToken(String token) {
        SPUtils.getInstance().putString("token", token);
    }

    public String getToken() {
        return SPUtils.getInstance().getString("token", "");
    }

    public void saveUserInfo(UserBean user) {
        SPUtils.getInstance().putString("UserInfo", new Gson().toJson(user));
    }

    public UserBean getUserInfo() {
        String temp = SPUtils.getInstance().getString("UserInfo", "");
        if (TextUtils.isEmpty(temp)) {
            return null;
        } else {
            UserBean userBean = new Gson().fromJson(temp, UserBean.class);
            return userBean;
        }
    }
}
