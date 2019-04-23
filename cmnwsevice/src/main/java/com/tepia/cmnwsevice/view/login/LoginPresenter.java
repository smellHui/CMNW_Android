package com.tepia.cmnwsevice.view.login;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;

import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.station.AreaBean;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmnwsevice.model.station.StationManager;
import com.tepia.cmnwsevice.model.station.VenderBean;
import com.tepia.cmnwsevice.model.user.LoginBean;
import com.tepia.cmnwsevice.model.user.UserManager;

import java.util.List;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/2 13:59
 * @修改人 ：
 * @修改时间 :       2019/4/2 13:59
 * @功能描述 :
 **/

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {

    public void login(Object... params) {
        UserManager.getInstance().appLogin(params).safeSubscribe(new LoadingSubject<BaseCommonResponse<LoginBean>>(true, "登录中") {
            @Override
            protected void _onNext(BaseCommonResponse<LoginBean> baseCommonResponse) {
                UserManager.getInstance().saveToken(baseCommonResponse.getData().getToken());
                UserManager.getInstance().saveUserInfo(baseCommonResponse.getData().getUser());
                getDictArray();

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    private void getDictArray() {
        DictManager.getInstance().getServerDict().safeSubscribe(new LoadingSubject<BaseCommonResponse>(true, "获取数据字典中...") {
            @Override
            protected void _onNext(BaseCommonResponse baseCommonResponse) {
                getStationList();

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getStationList() {
        StationManager.getInstance().getStationList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<StationBean>>>(true, "获取站点数据中...") {
            @Override
            protected void _onNext(BaseCommonResponse<List<StationBean>> baseCommonResponse) {
                if (!CollectionsUtil.isEmpty(baseCommonResponse.getData())) {
                    for (StationBean bean : baseCommonResponse.getData()) {
                        bean.saveOrUpdate("code=?", bean.getCode());
                    }
                }
                getAreaList();

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });

    }

    private void getAreaList() {
        UserManager.getInstance().getAreaList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<AreaBean>>>(true, "获取数据中...") {
            @Override
            protected void _onNext(BaseCommonResponse<List<AreaBean>> baseCommonResponse) {
                getVenderList();
                if (!CollectionsUtil.isEmpty(baseCommonResponse.getData())) {
                    for (AreaBean bean : baseCommonResponse.getData()) {
                        bean.saveOrUpdate("code=?", bean.getCode());
                    }
                }

            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

    private void getVenderList() {
        UserManager.getInstance().getVenderList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<VenderBean>>>(true, "获取数据中...") {
            @Override
            protected void _onNext(BaseCommonResponse<List<VenderBean>> baseCommonResponse) {
                if (!CollectionsUtil.isEmpty(baseCommonResponse.getData())) {
                    for (VenderBean bean : baseCommonResponse.getData()) {
                        bean.saveOrUpdate("code=?", bean.getCode());
                    }
                }
                mView.loginSuccess();
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }

}
