package com.tepia.cmnwsevice.view.login;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.databinding.ActivityLoginBinding;


/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/2 15:41
 * @修改人         ：
 * @修改时间       :       2019/4/2 15:41
 * @功能描述       :        登录页面
 **/
@Route(path = AppRoutePath.app_cmnw_login)
public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

    private ActivityLoginBinding mBinding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
       mBinding =  DataBindingUtil.bind(mRootView);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {
        mBinding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DoubleClickUtil.isFastDoubleClick()){
                    return;
                }
                mPresenter.login("username","admin","password","123456");
            }
        });
    }

    @Override
    protected void initRequestData() {
    }

    @Override
    public void loginSuccess() {

    }
}
