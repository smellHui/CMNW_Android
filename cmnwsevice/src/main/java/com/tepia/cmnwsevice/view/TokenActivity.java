package com.tepia.cmnwsevice.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.AppManager;
import com.tepia.cmnwsevice.model.user.UserManager;
import com.tepia.cmnwsevice.view.login.LoginActivity;


@Route(path = AppRoutePath.token)
public class TokenActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserManager.getInstance().clearData();
        showdialog();
    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("登录令牌失效，请重新登录");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppManager.getInstance().finishAll();
                UserManager.getInstance().saveToken("");
                Intent intent = new Intent(TokenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.create().show();
    }
}
