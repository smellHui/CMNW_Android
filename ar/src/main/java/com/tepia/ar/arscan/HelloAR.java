//================================================================================================================================
//
//  Copyright (c) 2015-2017 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
//  EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
//  and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
//
//================================================================================================================================

package com.tepia.ar.arscan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tepia.base.AppRoutePath;
import com.tepia.base.utils.DoubleClickUtil;

import java.util.ArrayList;
import java.util.HashSet;

import cn.easyar.CameraCalibration;
import cn.easyar.CameraDevice;
import cn.easyar.CameraDeviceFocusMode;
import cn.easyar.CameraDeviceType;
import cn.easyar.CameraFrameStreamer;
import cn.easyar.CloudRecognizer;
import cn.easyar.CloudStatus;
import cn.easyar.Frame;
import cn.easyar.FunctorOfVoidFromCloudStatus;
import cn.easyar.FunctorOfVoidFromCloudStatusAndListOfPointerOfTarget;
import cn.easyar.FunctorOfVoidFromPointerOfTargetAndBool;
import cn.easyar.ImageTarget;
import cn.easyar.ImageTracker;
import cn.easyar.Renderer;
import cn.easyar.Target;
import cn.easyar.TargetInstance;
import cn.easyar.TargetStatus;
import cn.easyar.Vec2I;
import cn.easyar.Vec4I;

public class HelloAR {
    private CameraDevice camera;
    private CameraFrameStreamer streamer;
    private ArrayList<ImageTracker> trackers;
    private Renderer videobg_renderer;
    private BoxRenderer box_renderer;
    private CloudRecognizer cloud_recognizer;
    private boolean viewport_changed = false;
    private Vec2I view_size = new Vec2I(0, 0);
    private int rotation = 0;
    private Vec4I viewport = new Vec4I(0, 0, 1280, 720);

    private CloudRecognizerCallBack cloudRecognizerCallBack;

    public void setCloudRecognizerCallBack(CloudRecognizerCallBack cloudRecognizerCallBack) {
        this.cloudRecognizerCallBack = cloudRecognizerCallBack;
    }

    public HelloAR() {
        trackers = new ArrayList<ImageTracker>();
    }

    public interface CloudRecognizerCallBack {
        public void success(ArrayList<Target> targets);
    }

    public boolean initialize(String cloud_server_address, String cloud_key, String cloud_secret) {
        camera = new CameraDevice();
        streamer = new CameraFrameStreamer();
        streamer.attachCamera(camera);
        cloud_recognizer = new CloudRecognizer();
        cloud_recognizer.attachStreamer(streamer);

        boolean status = true;
        status &= camera.open(CameraDeviceType.Default);
        camera.setSize(new Vec2I(1280, 720));
        cloud_recognizer.open(cloud_server_address, cloud_key, cloud_secret, new FunctorOfVoidFromCloudStatus() {
            @Override
            public void invoke(int status) {
                if (status == CloudStatus.Success) {
                    Log.i("HelloAR", "CloudRecognizerInitCallBack: Success");

                } else if (status == CloudStatus.Reconnecting) {
                    Log.i("HelloAR", "CloudRecognizerInitCallBack: Reconnecting");
                } else if (status == CloudStatus.Fail) {
                    Log.i("HelloAR", "CloudRecognizerInitCallBack: Fail");
                } else {
                    Log.i("HelloAR", "CloudRecognizerInitCallBack: " + Integer.toString(status));
                }
            }
        }, new FunctorOfVoidFromCloudStatusAndListOfPointerOfTarget() {
            private HashSet<String> uids = new HashSet<String>();

            @Override
            public void invoke(int status, ArrayList<Target> targets) {
                if (status == CloudStatus.Success) {
                    Log.i("HelloAR", "CloudRecognizerCallBack: Success" + targets.get(0).name());
                    if (!DoubleClickUtil.isFastDoubleClick()) {
                        if (cloudRecognizerCallBack != null) {
                            cloudRecognizerCallBack.success(targets);
                        }
                    }
                } else if (status == CloudStatus.Reconnecting) {
                    Log.i("HelloAR", "CloudRecognizerCallBack: Reconnecting");
                } else if (status == CloudStatus.Fail) {
                    Log.i("HelloAR", "CloudRecognizerCallBack: Fail");
                } else {
                    Log.i("HelloAR", "CloudRecognizerCallBack: " + Integer.toString(status));
                }
                synchronized (uids) {
                    for (Target t : targets) {
                        if (!uids.contains(t.uid())) {
                            Log.i("HelloAR", "add cloud target: " + t.uid());
                            uids.add(t.uid());
                            trackers.get(0).loadTarget(t, new FunctorOfVoidFromPointerOfTargetAndBool() {
                                @Override
                                public void invoke(Target target, boolean status) {
                                    Log.i("HelloAR", String.format("load target (%b): %s (%d)", status, target.name(), target.runtimeID()));

                                }
                            });
                        }
                    }
                }
            }
        });

        if (!status) {
            return status;
        }
        ImageTracker tracker = new ImageTracker();
        tracker.attachStreamer(streamer);
        trackers.add(tracker);

        return status;
    }


    public void dispose() {
        for (ImageTracker tracker : trackers) {
            tracker.dispose();
        }
        trackers.clear();
        if (cloud_recognizer != null) {
            cloud_recognizer.dispose();
            cloud_recognizer = null;
        }
        box_renderer = null;
        if (videobg_renderer != null) {
            videobg_renderer.dispose();
            videobg_renderer = null;
        }
        if (streamer != null) {
            streamer.dispose();
            streamer = null;
        }
        if (camera != null) {
            camera.dispose();
            camera = null;
        }
    }

    public boolean start() {
        boolean status = true;
        status &= (camera != null) && camera.start();
        status &= (streamer != null) && streamer.start();
        status &= (cloud_recognizer != null) && cloud_recognizer.start();
        camera.setFocusMode(CameraDeviceFocusMode.Continousauto);
        for (ImageTracker tracker : trackers) {
            status &= tracker.start();
        }
        return status;
    }

    public boolean stop() {
        boolean status = true;
        for (ImageTracker tracker : trackers) {
            status &= tracker.stop();
        }
        status &= (cloud_recognizer != null) && cloud_recognizer.stop();
        status &= (streamer != null) && streamer.stop();
        status &= (camera != null) && camera.stop();
        return status;
    }

    public void initGL() {
        if (videobg_renderer != null) {
            videobg_renderer.dispose();
        }
        videobg_renderer = new Renderer();
        box_renderer = new BoxRenderer();
        box_renderer.init();
    }

    public void resizeGL(int width, int height) {
        view_size = new Vec2I(width, height);
        viewport_changed = true;
    }

    private void updateViewport() {
        CameraCalibration calib = camera != null ? camera.cameraCalibration() : null;
        int rotation = calib != null ? calib.rotation() : 0;
        if (rotation != this.rotation) {
            this.rotation = rotation;
            viewport_changed = true;
        }
        if (viewport_changed) {
            Vec2I size = new Vec2I(1, 1);
            if ((camera != null) && camera.isOpened()) {
                size = camera.size();
            }
            if (rotation == 90 || rotation == 270) {
                size = new Vec2I(size.data[1], size.data[0]);
            }
            float scaleRatio = Math.max((float) view_size.data[0] / (float) size.data[0], (float) view_size.data[1] / (float) size.data[1]);
            Vec2I viewport_size = new Vec2I(Math.round(size.data[0] * scaleRatio), Math.round(size.data[1] * scaleRatio));
            viewport = new Vec4I((view_size.data[0] - viewport_size.data[0]) / 2, (view_size.data[1] - viewport_size.data[1]) / 2, viewport_size.data[0], viewport_size.data[1]);

            if ((camera != null) && camera.isOpened())
                viewport_changed = false;
        }
    }

    public void render() {
        GLES20.glClearColor(1.f, 1.f, 1.f, 1.f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        if (videobg_renderer != null) {
            Vec4I default_viewport = new Vec4I(0, 0, view_size.data[0], view_size.data[1]);
            GLES20.glViewport(default_viewport.data[0], default_viewport.data[1], default_viewport.data[2], default_viewport.data[3]);
            if (videobg_renderer.renderErrorMessage(default_viewport)) {
                return;
            }
        }

        if (streamer == null) {
            return;
        }
        Frame frame = streamer.peek();

        try {
            updateViewport();
            GLES20.glViewport(viewport.data[0], viewport.data[1], viewport.data[2], viewport.data[3]);

            if (videobg_renderer != null) {
                videobg_renderer.render(frame, viewport);
            }

            for (TargetInstance targetInstance : frame.targetInstances()) {
                int status = targetInstance.status();
                if (status == TargetStatus.Tracked) {
                    Target target = targetInstance.target();
                    ImageTarget imagetarget = target instanceof ImageTarget ? (ImageTarget) (target) : null;
                    if (imagetarget == null) {
                        continue;
                    }
                    if (box_renderer != null) {
                        box_renderer.render(camera.projectionGL(0.2f, 500.f), targetInstance.poseGL(), imagetarget.size());
                    }
                }
            }
        } finally {
            frame.dispose();
        }
    }
}
