package com.tepia.cmnwsevice.view.login;

import android.widget.Toast;

import com.tepia.base.http.BaseCommonResponse;
import com.tepia.base.http.LoadingSubject;
import com.tepia.base.mvp.BasePresenterImpl;
import com.tepia.base.utils.ToastUtils;
import com.tepia.cmnwsevice.model.user.LoginBean;
import com.tepia.cmnwsevice.model.user.UserManager;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/2 13:59
 * @修改人         ：      
 * @修改时间       :       2019/4/2 13:59
 * @功能描述       :
 **/

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{

    public void login(Object... params) {
        UserManager.getInstance().appLogin(params).safeSubscribe(new LoadingSubject<BaseCommonResponse<LoginBean>>(true,"登录中") {
            @Override
            protected void _onNext(BaseCommonResponse<LoginBean> baseCommonResponse) {
                UserManager.getInstance().saveToken(baseCommonResponse.getData().getToken());
                UserManager.getInstance().saveUserInfo(baseCommonResponse.getData().getUser());
                mView.loginSuccess();
            }

            @Override
            protected void _onError(String message) {
                ToastUtils.shortToast(message);
            }
        });
    }
}
