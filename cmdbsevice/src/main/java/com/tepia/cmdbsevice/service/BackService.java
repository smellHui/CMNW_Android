package com.tepia.cmdbsevice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;
import java.net.UnknownHostException;

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
    private InitSocketThread initSocketThread;

    @Override
    public void onCreate() {
        super.onCreate();
        initSocketThread = new InitSocketThread();
        initSocketThread.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (initSocketThread.mWebSocket != null) {
            initSocketThread.mWebSocket.close(1000, null);
        }
    }

}
