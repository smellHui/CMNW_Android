package com.tepia.cmdbsevice.service;

import android.os.Handler;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.APPConst;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmnwsevice.model.user.UserManager;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/12
 * Time            :       18:23
 * Version         :       1.0
 * 功能描述        :
 **/
class InitSocketThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            initSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 心跳检测时间
     */
    private static final long HEART_BEAT_RATE = 15 * 1000;//每隔15秒进行一次对长连接的心跳检测
    private static final String WEBSOCKET_HOST_AND_PORT = "ws://47.110.70.104:10910/websocket?token=" + UserManager.getInstance().getToken();//可替换为自己的主机名和端口号
    public WebSocket mWebSocket;

    // 初始化socket
    private void initSocket() throws UnknownHostException, IOException {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {//开启长连接成功的回调
                super.onOpen(webSocket, response);
                mWebSocket = webSocket;
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {//接收消息的回调
                super.onMessage(webSocket, text);
                //收到服务器端传过来的消息text
                LogUtil.d(text);
                WebSocketResponse webSocketResponse = new Gson().fromJson(text,WebSocketResponse.class);
                Map<String,StationBean> stringStationBeanMap = webSocketResponse.getStationMonitor();
                if (stringStationBeanMap != null){
                    for (Map.Entry<String,StationBean> entry : stringStationBeanMap.entrySet()) {
                        entry.getValue().saveOrUpdate("code=?", entry.getKey());
                    }
                }
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {//长连接连接失败的回调
                super.onFailure(webSocket, t, response);
                if (mHandler != null) {
                    mHandler.removeCallbacks(heartBeatRunnable);
                }
                reconnect();//连接断开的时候调用重连方法 
            }
        });
        client.dispatcher().executorService().shutdown();
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
    }

    private void reconnect() {
        try {
            initSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long sendTime = 0L;
    // 发送心跳包
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                if (mWebSocket != null) {
                    boolean isSuccess = mWebSocket.send("");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                    if (!isSuccess) {//长连接已断开
                        mHandler.removeCallbacks(heartBeatRunnable);
                        mWebSocket.cancel();//取消掉以前的长连接
                        new InitSocketThread().start();//创建一个新的连接
                    } else {//长连接处于连接状态

                    }

                    sendTime = System.currentTimeMillis();
                }
            }
            mHandler.postDelayed(this, HEART_BEAT_RATE);//每隔一定的时间，对长连接进行一次心跳检测
        }
    };

}
