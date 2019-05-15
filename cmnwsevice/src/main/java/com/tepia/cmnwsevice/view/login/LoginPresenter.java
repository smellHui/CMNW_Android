package com.tepia.cmnwsevice.view.login;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.AppManager;
import com.tepia.base.utils.ToastUtils;

import com.tepia.base.view.dialog.loading.SimpleLoadDialog;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.model.station.AreaBean;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmnwsevice.model.station.StationManager;
import com.tepia.cmnwsevice.model.station.VenderBean;
import com.tepia.cmnwsevice.model.user.LoginBean;
import com.tepia.cmnwsevice.model.user.UserManager;

import org.litepal.crud.DataSupport;

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

    private SimpleLoadDialog simpleLoadDialog;

    public void login(Object... params) {
        simpleLoadDialog = new SimpleLoadDialog(AppManager.getInstance().getCurrentActivity(), "登录中", true);
        if (simpleLoadDialog != null) {
            simpleLoadDialog.show();
        }
        UserManager.getInstance().appLogin(params).safeSubscribe(new LoadingSubject<BaseCommonResponse<LoginBean>>() {
            @Override
            protected void _onNext(BaseCommonResponse<LoginBean> baseCommonResponse) {
                UserManager.getInstance().saveToken(baseCommonResponse.getData().getToken());
                UserManager.getInstance().saveUserInfo(baseCommonResponse.getData().getUser());
                getDictArray();

            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });
    }

    private void getDictArray() {
        simpleLoadDialog.setMessage("获取数据字典中...");
        DictManager.getInstance().getServerDict().safeSubscribe(new LoadingSubject<BaseCommonResponse>(true, "获取数据字典中...") {
            @Override
            protected void _onNext(BaseCommonResponse baseCommonResponse) {
                getStationList();

            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });
    }

    public void getStationList() {
        simpleLoadDialog.setMessage("获取站点数据中...");
        StationManager.getInstance().getStationList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<StationBean>>>() {
            @Override
            protected void _onNext(BaseCommonResponse<List<StationBean>> baseCommonResponse) {
                if (!CollectionsUtil.isEmpty(baseCommonResponse.getData())) {
                    DataSupport.deleteAll(StationBean.class);
                    DataSupport.saveAll(baseCommonResponse.getData());
//                    for (StationBean bean : baseCommonResponse.getData()) {
//                        bean.saveOrUpdate("code=?", bean.getCode());
//                    }
                }
                getAreaList();

            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });

    }

    private void getAreaList() {
        simpleLoadDialog.setMessage("获取数据中...");
        UserManager.getInstance().getAreaList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<AreaBean>>>() {
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
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });
    }

    private void getVenderList() {
        simpleLoadDialog.setMessage("获取数据中...");
        UserManager.getInstance().getVenderList().safeSubscribe(new LoadingSubject<BaseCommonResponse<List<VenderBean>>>() {
            @Override
            protected void _onNext(BaseCommonResponse<List<VenderBean>> baseCommonResponse) {
                if (!CollectionsUtil.isEmpty(baseCommonResponse.getData())) {
                    for (VenderBean bean : baseCommonResponse.getData()) {
                        bean.saveOrUpdate("code=?", bean.getCode());
                    }
                }
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                mView.loginSuccess();
            }

            @Override
            protected void _onError(String message) {
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                ToastUtils.shortToast(message);
            }
        });
    }

}
