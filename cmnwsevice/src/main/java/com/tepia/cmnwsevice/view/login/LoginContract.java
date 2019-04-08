package com.tepia.cmnwsevice.view.login;

import android.content.Context;

import com.tepia.base.mvp.BasePresenter;
import com.tepia.base.mvp.BaseView;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/2 13:57
 * @修改人         ：      
 * @修改时间       :       2019/4/2 13:57
 * @功能描述       :
 **/

public class LoginContract {
    public interface View extends BaseView {

        void loginSuccess();
    }

    public interface  Presenter extends BasePresenter<View> {
        
    }
}
