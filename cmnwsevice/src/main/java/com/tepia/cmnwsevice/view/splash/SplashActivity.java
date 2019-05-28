package com.tepia.cmnwsevice.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.cmnwsevice.R;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.view.main.MainActivity;

/**
 * Author:xch
 * Date:2019/5/28
 * Description:
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            if (!TextUtils.isEmpty(UserManager.getInstance().getToken())) {
                ARouter.getInstance().build(AppRoutePath.app_cmdb_main_tab).navigation();
            } else {
                ARouter.getInstance().build(AppRoutePath.app_cmnw_login).navigation();
            }
            finish();
        }, 1000 * 3);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
