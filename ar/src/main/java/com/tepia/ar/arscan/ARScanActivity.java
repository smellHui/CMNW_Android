package com.tepia.ar.arscan;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.opengl.GLException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import cn.easyar.Engine;
import cn.easyar.Target;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.tepia.ar.R;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseActivity;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/3/18 17:37
 * @修改人 ：
 * @修改时间 :       2019/3/18 17:37
 * @功能描述 :        AR 扫描界面
 **/

public class ARScanActivity extends MVPBaseActivity<ARScanContract.View, ARScanPresenter> implements ARScanContract.View {

    private static String key = "cfTuIdEoKn9VFG4PBE416X5mIHkdTihTv4A2hjDM43GfCSd6uQqd81dBVvvydHvQ4oSN8powen4qCMA3TEx2cJR4wNLugJoADeiEWROipiqWGq6xYVJHgeCzDezs4ic8z5pxNJAhkRKHzUBoybNmlcdivy7XnpSA4TbUWYBQyoBXisxNPMibyl6IXZoA6t8aZBVzOlqR";
    private static String cloud_server_address = "4e97c86470c862bde2f9a2e131d7ca5e.cn1.crs.easyar.com:8080";
    private static String cloud_key = "0c6903f19e42e15e86b123742ec8e734";
    private static String cloud_secret = "C4ubov6rzyL05OvkXtsJhNCOi1Iu6FkdtECPqnRDJu93wFqLOfUE1ltZmYriO1RkrqdWk1F4AnCdj2fZJG3ed0NpE0GMW8NqNhkNp9rpFVDHwsjcqClyVawBtltPPfKb";
    private GLView glView;
    private Bitmap snapshotBitmap;
    private interface BitmapReadyCallbacks {
        void onBitmapReady(Bitmap bitmap);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_ar_scan;
    }

    @Override
    public void initView() {
        setCenterTitle("AR智能识别");
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initRequestData() {

    }
    private void captureBitmap(final BitmapReadyCallbacks bitmapReadyCallbacks) {
        glView.queueEvent(new Runnable() {
            @Override
            public void run() {
                EGL10 egl = (EGL10) EGLContext.getEGL();
                GL10 gl = (GL10)egl.eglGetCurrentContext().getGL();
                snapshotBitmap = createBitmapFromGLSurface(0, 0, glView.getWidth(), glView.getHeight(), gl);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bitmapReadyCallbacks.onBitmapReady(snapshotBitmap);
                    }
                });

            }
        });

    }
    private Bitmap createBitmapFromGLSurface(int x, int y, int w, int h, GL10 gl)
            throws OutOfMemoryError {
        int bitmapBuffer[] = new int[w * h];
        int bitmapSource[] = new int[w * h];
        IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
        intBuffer.position(0);

        try {
            gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, intBuffer);
            int offset1, offset2;
            for (int i = 0; i < h; i++) {
                offset1 = i * w;
                offset2 = (h - i - 1) * w;
                for (int j = 0; j < w; j++) {
                    int texturePixel = bitmapBuffer[offset1 + j];
                    int blue = (texturePixel >> 16) & 0xff;
                    int red = (texturePixel << 16) & 0x00ff0000;
                    int pixel = (texturePixel & 0xff00ff00) | red | blue;
                    bitmapSource[offset2 + j] = pixel;
                }
            }
        } catch (GLException e) {
            return null;
        }

        return Bitmap.createBitmap(bitmapSource, w, h, Bitmap.Config.ARGB_8888);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        closeAndroidPDialog();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (!Engine.initialize(this, key)) {
            Log.e("HelloAR", "Initialization Failed.");
        }

        glView = new GLView(this, cloud_server_address, cloud_key, cloud_secret);

        requestCameraPermission(new PermissionCallback() {
            @Override
            public void onSuccess() {
                ((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

            @Override
            public void onFailure() {
            }
        });
        glView.setCloudRecognizerCallBack(new HelloAR.CloudRecognizerCallBack() {
            @Override
            public void success(ArrayList<Target> targets) {
                captureBitmap(new BitmapReadyCallbacks() {

                    @Override
                    public void onBitmapReady(Bitmap bitmap) {
                        ARouter.getInstance().build(AppRoutePath.app_ar_scan_result)
                                .withString("result", targets.get(0).name())
                                .withString("bitmap",new Gson().toJson(bitmap).toString())
                                .withString("uids",targets.get(0).uid())
                                .navigation();
                    }
                });



            }
        });
    }

    private void closeAndroidPDialog(){
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private interface PermissionCallback {
        void onSuccess();

        void onFailure();
    }

    private HashMap<Integer, PermissionCallback> permissionCallbacks = new HashMap<Integer, PermissionCallback>();
    private int permissionRequestCodeSerial = 0;

    @TargetApi(23)
    private void requestCameraPermission(PermissionCallback callback) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                int requestCode = permissionRequestCodeSerial;
                permissionRequestCodeSerial += 1;
                permissionCallbacks.put(requestCode, callback);
                requestPermissions(new String[]{Manifest.permission.CAMERA}, requestCode);
            } else {
                callback.onSuccess();
            }
        } else {
            callback.onSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissionCallbacks.containsKey(requestCode)) {
            PermissionCallback callback = permissionCallbacks.get(requestCode);
            permissionCallbacks.remove(requestCode);
            boolean executed = false;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    executed = true;
                    callback.onFailure();
                }
            }
            if (!executed) {
                callback.onSuccess();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glView != null) {
            glView.onResume();
        }
    }

    @Override
    protected void onPause() {
        if (glView != null) {
            glView.onPause();
        }
        super.onPause();
    }
}
