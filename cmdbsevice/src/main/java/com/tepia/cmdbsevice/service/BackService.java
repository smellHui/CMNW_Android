package com.tepia.cmdbsevice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/9
 * Time            :       14:47
 * Version         :       1.0
 * 功能描述        :
 **/
public class BackService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
