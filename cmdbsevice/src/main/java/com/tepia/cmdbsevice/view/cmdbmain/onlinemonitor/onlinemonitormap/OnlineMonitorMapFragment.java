package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap;


import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.esri.android.map.Callout;
import com.esri.android.map.CalloutStyle;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.Layer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISDynamicMapServiceLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.map.event.OnZoomListener;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.google.gson.Gson;
import com.tepia.base.AppRoutePath;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.DoubleClickUtil;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.ConfigConst;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMapBinding;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmdbsevice.util.ARCGISUTIL;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author :       zhang xinhua
 * @Version :       1.0
 * @创建人 ：      zhang xinhua
 * @创建时间 :       2019/4/16 9:57
 * @修改人 ：
 * @修改时间 :       2019/4/16 9:57
 * @功能描述 :       在线监测中的地图
 **/

public class OnlineMonitorMapFragment extends MVPBaseFragment<OnlineMonitorMapContract.View, OnlineMonitorMapPresenter> implements OnlineMonitorMapContract.View {

    private TianDiTuTiledMapServiceLayer vecBaseLayer;
    private TianDiTuTiledMapServiceLayer cvaBaseLayer;
    private TianDiTuTiledMapServiceLayer imgLayer;
    private TianDiTuTiledMapServiceLayer ciaLayer;
    private ArcGISFeatureLayer riversFeatureLayer;
    private GraphicsLayer logGraphicsLayer;

    private GraphicsLayer townLayer;
    private GraphicsLayer pointLayer;

    /**
     * 附近我所属的河流图层
     */
    private GraphicsLayer riversNameGraphicsLayer;
    /**
     * 选中的河流图层
     */
    private GraphicsLayer selectRiversLayer;

    private FragmentOnlineMapBinding mBinding;

    private MapView mapView;
    private int mapHeight;
    private List<StationBean> stationList = new ArrayList<>();

    /**
     * point 图标点击事件
     */
    private OnPointClickListener onPointClickListener;
    private Runnable drawRunnable;


    public interface OnPointClickListener {
        void onPointClick(StationBean bean);
    }

    public void setOnPointClickListener(OnPointClickListener onPointClickListener) {
        this.onPointClickListener = onPointClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_online_map;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        mBinding = DataBindingUtil.bind(mRootView);
        initArcgisMap();
    }

    /**
     * 配置arcgis地图
     */
    private void initArcgisMap() {
        mapView = mBinding.mvArcgisRiverLog;

        ViewTreeObserver viewTreeObserver = mapView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mapHeight = mapView.getHeight();
            }
        });
        //去水印
        ArcGISRuntime.setClientId("9yNxBahuPiGPbsdi");
        mBinding.mvArcgisRiverLog.setMapBackground(ContextCompat.getColor(Utils.getContext(), R.color.white), ContextCompat.getColor(Utils.getContext(), R.color.white), 0f, 0f);

        //设置底图

        {
            /**
             * 天地图矢量
             * */
            vecBaseLayer = new TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType.VEC_C);
            mapView.addLayer(vecBaseLayer, 0);
            /**
             * 天地图矢量标注
             * */
            cvaBaseLayer = new TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType.CVA_C);
            mapView.addLayer(cvaBaseLayer, 1);
        }

        {
            /**
             * 天地图影像
             * */
            imgLayer = new TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType.IMG_C);
            mapView.addLayer(imgLayer, 0);
            /**
             * 天地图影像标注
             * */
            ciaLayer = new TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType.CIA_C);
            mapView.addLayer(ciaLayer, 1);
        }

        cvaBaseLayer.setVisible(false);
        vecBaseLayer.setVisible(false);

        riversFeatureLayer = new ArcGISFeatureLayer(ConfigConst.riversMapUrl, ArcGISFeatureLayer.MODE.ONDEMAND);
        townLayer = new ArcGISFeatureLayer(ConfigConst.townMapUrl, ArcGISFeatureLayer.MODE.ONDEMAND);
        riversNameGraphicsLayer = new GraphicsLayer();
        selectRiversLayer = new GraphicsLayer();
        logGraphicsLayer = new GraphicsLayer();
        pointLayer = new GraphicsLayer();
        mBinding.mvArcgisRiverLog.addLayer(townLayer);
        mBinding.mvArcgisRiverLog.addLayer(pointLayer);

        mBinding.mvArcgisRiverLog.addLayer(riversFeatureLayer);
        mBinding.mvArcgisRiverLog.addLayer(selectRiversLayer);
        mBinding.mvArcgisRiverLog.addLayer(logGraphicsLayer);
        final boolean[] isDrawed = {false};
        mBinding.mvArcgisRiverLog.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                if (status == STATUS.INITIALIZED) {

                }
                if (status == STATUS.LAYER_LOADED) {
                    mBinding.layoutLoading.loadingLayout.setVisibility(View.GONE);
//                    stationList = DataSupport.where("stationStatus in ('1','2','3')").find(StationBean.class);
                    stationList = DataSupport.findAll(StationBean.class);
                    if (DoubleClickUtil.isFastDoubleClick()) {
                        return;
                    }
                    if (!isDrawed[0]) {
                        isDrawed[0] = true;
                        drawMapPoint(stationList);
                    }
                    centerAndZoom(stationList);
                }
                if (status == STATUS.LAYER_LOADING_FAILED) {
                    mBinding.layoutLoading.tvLoading.setText("地图加载失败");
                }
            }
        });
        //设置地图点击事件
        mapView.setOnSingleTapListener((OnSingleTapListener) (x, y) -> {
            handleSingleTap(x, y);
        });
        mapView.setOnZoomListener(new OnZoomListener() {
            @Override
            public void preAction(float v, float v1, double v2) {
                checkScale();
            }

            @Override
            public void postAction(float v, float v1, double v2) {
                checkScale();
            }
        });
    }

    private void checkScale() {
        if (mapView.getScale()>400000){
            logGraphicsLayer.setVisible(false);
            pointLayer.setVisible(true);
        }else {
            logGraphicsLayer.setVisible(true);
            pointLayer.setVisible(false);
        }
    }

    /**
     * 地图点击事件
     *
     * @param x
     * @param y
     */
    private void handleSingleTap(float x, float y) {
        if (null != callout) {
            callout.hide();
        }
        //事件要素图层的点击事件
        int[] graphicIDs = logGraphicsLayer.getGraphicIDs(x, y, 1);
        if (null != graphicIDs) {
            if (graphicIDs.length > 0) {

                Graphic graphic = logGraphicsLayer.getGraphic(graphicIDs[0]);
                Map<String, Object> attributes = graphic.getAttributes();
                String info = (String) attributes.get("pos");
                StationBean infoModel = new Gson().fromJson(info, StationBean.class);
                if (infoModel != null) {
                    if (!infoModel.getName().equals("")) {
                        if (onPointClickListener != null) {
                            onPointClickListener.onPointClick(infoModel);
                        }
                        markerPoint(infoModel);
                    }
                }


            }
        }
    }

    private Callout callout;

    private void initCallout(String text) {
        if (mapView != null) {
            callout = mapView.getCallout();
            callout.setMaxWidth(1200);
            callout.setMaxHeight(300);
            TextView textView = new TextView(Utils.getContext());
            textView.setText(text);
            callout.setContent(textView);
            CalloutStyle calloutStyle = new CalloutStyle();
            calloutStyle.setAnchor(Callout.ANCHOR_POSITION_LOWER_LEFT_CORNER);
            callout.setStyle(calloutStyle);
        }
    }


    private void drawMapPoint(List<StationBean> stationList) {
        LogUtil.d("drawMapPoint ");
        pointLayer.removeGraphics(pointLayer.getGraphicIDs());
        logGraphicsLayer.removeGraphics(logGraphicsLayer.getGraphicIDs());
        if (!CollectionsUtil.isEmpty(stationList)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stationList.parallelStream().forEach(OnlineMonitorMapFragment.this::drawMapPointOne);
                    }
                }).start();

            } else {
                for (int i = 0; i < 10; i++) {
                    int finalI = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = stationList.size() / 10 * finalI; j < stationList.size() / 10 * finalI; j++) {
                                drawMapPointOne(stationList.get(j));
                            }
                        }
                    });
                }

            }
        }
    }

    private void drawMapPointOne(StationBean bean) {
        if (bean == null) {
            return;
        }
        if (TextUtils.isEmpty(bean.getLttd()) && TextUtils.isEmpty(bean.getLttd())) {
            return;
        }
        Point point = transStationBeanTOpoint(bean);
        if (point == null) {
            return;
        }
        if (TextUtils.isEmpty(bean.getStationType())) {
            return;
        } else {
            Integer color = Color.parseColor("#4FCFFA");
            Integer imageRes = R.mipmap.icon_clz_0;
            switch (bean.getStationType()) {
                case "1":
                    if (TextUtils.isEmpty(bean.getStationStatus())) {
                        imageRes = R.mipmap.icon_clz_0;
                    } else {
                        switch (bean.getStationStatus()) {
                            case "0":
                                imageRes = R.mipmap.icon_clz_0;
                                color = Color.parseColor("#4FCFFA");
                                break;
                            case "1":
                                imageRes = R.mipmap.icon_clz_1;
                                color = Color.parseColor("#FFE42D");
                                break;
                            case "2":
                                imageRes = R.mipmap.icon_clz_2;
                                color = Color.parseColor("#FFAA53");
                                break;
                            case "3":
                                imageRes = R.mipmap.icon_clz_3;
                                color = Color.parseColor("#F43334");
                                break;
                            default:
                                imageRes = R.mipmap.icon_clz_0;
                                break;
                        }
                    }

                    break;
                case "2":
                    if (TextUtils.isEmpty(bean.getStationStatus())) {
                        imageRes = R.mipmap.icon_gz_0;

                    } else {
                        switch (bean.getStationStatus()) {
                            case "0":
                                color = Color.parseColor("#4FCFFA");
                                imageRes = R.mipmap.icon_gz_0;
                                break;
                            case "1":
                                imageRes = R.mipmap.icon_gz_1;
                                color = Color.parseColor("#FFE42D");
                                break;
                            case "2":
                                imageRes = R.mipmap.icon_gz_2;
                                color = Color.parseColor("#FFAA53");
                                break;
                            case "3":
                                imageRes = R.mipmap.icon_gz_3;
                                color = Color.parseColor("#F43334");
                                break;
                            default:
                                imageRes = R.mipmap.icon_gz_0;
                                break;
                        }
                    }
                    break;
                default:
                    imageRes = R.mipmap.icon_gz_0;
                    break;
            }
            LogUtil.d("map");
            ARCGISUTIL.addPoint(color,point,pointLayer);
            ARCGISUTIL.addPicForPos2(imageRes, point, logGraphicsLayer, new Gson().toJson(bean).toString());
        }
    }

    /**
     * 转化bean 到 point
     *
     * @param bean
     * @return
     */
    private Point transStationBeanTOpoint(StationBean bean) {
        try {
            Double lttdrv = Double.parseDouble(bean.getLttd());
            Double lgtdrv = Double.parseDouble(bean.getLgtd());
            if (lgtdrv <= lttdrv || lgtdrv == 0 || lttdrv == 0) {
                return null;
            }
            Point point1 = new Point(lgtdrv, lttdrv);
            Point point2 = (Point) GeometryEngine.project(point1, SpatialReference.create(SpatialReference.WKID_WGS84),
                    SpatialReference.create(SpatialReference.WKID_WGS84_WEB_MERCATOR));

            return point2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void initRequestData() {

    }

    /**
     * 赛选出 需要显示的 点 ，并缩放到 对应级别
     *
     * @param conditions
     */
    public void saixuan(String... conditions) {
        clearMarker();
        List<StationBean> stationList = DataSupport.where(conditions).find(StationBean.class);
        centerAndZoom(stationList);
        drawMapPoint(stationList);

    }

    /**
     * 将这些点 为中心 并缩放
     *
     * @param stationList
     */
    public void centerAndZoom(List<StationBean> stationList) {
        try {
            ArrayList<Point> points = new ArrayList<>();
            if (stationList != null && stationList.size() > 0) {
                for (int i = 0; i < stationList.size(); i++) {
                    Point point = transStationBeanTOpoint(stationList.get(i));
                    if (point != null) {
                        points.add(point);
                    }
                }
                Point maxPoint = new Point();
                Point minPoint = new Point();
                double numx = points.get(0).getX();
                double numy = points.get(0).getY();
                double minx = points.get(0).getX();
                double miny = points.get(0).getY();
                for (int i = 0; i < points.size(); i++) {
                    double x = (double) points.get(i).getX();
                    double y = (double) points.get(i).getY();
                    numx = x < numx ? numx : x;
                    numy = y < numy ? numy : y;
                    minx = x > minx ? minx : x;
                    miny = y > miny ? miny : y;
                    maxPoint.setX(numx);
                    maxPoint.setY(numy);
                    minPoint.setX(minx);
                    minPoint.setY(miny);
                }
                double xcen = (numx - minx) > 0 ? (numx - minx) : 0;
                double ycen = (numy - miny) > 0 ? (numy - miny) : 0;
                Envelope envelope = new Envelope();
                envelope.setXMin(minPoint.getX() - xcen / 10);
                envelope.setYMin(minPoint.getY() - ycen / 10);
                envelope.setXMax(maxPoint.getX() + xcen / 10);
                envelope.setYMax(maxPoint.getY() + ycen / 10);
                mapView.setExtent(envelope);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        checkScale();
    }

    /**
     * 将这些点 为中心 并缩放
     *
     * @param bean
     */
    public void centerAndZoom(StationBean bean) {
        if (mapView != null) {
            Point point = transStationBeanTOpoint(bean);
            if (point != null) {
                mapView.centerAt(point, true);
                mapView.setScale(ConfigConst.scale, false);
            }
            checkScale();
        }
    }

    /**
     * 标记这个这个点
     *
     * @param bean
     */
    public void markerPoint(StationBean bean) {
        if (bean != null) {
            Point point = transStationBeanTOpoint(bean);
            if (point != null) {
                //获取选中的经度
                double lgtdrv = point.getX();
                //获取选中的纬度
                double lttdrv = point.getY();
                String eventName = bean.getName();
                initCallout(eventName);
                callout.show(new Point(lgtdrv, lttdrv));
//                                initIllegalEventDetailFragment(infoModel);
//                                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

            }
        }

    }

    public void clearMarker() {
        if (callout != null) {
            callout.animatedHide();
        }
    }

    /**
     * 上移地图
     */
    public void moveUpMap() {
        moveMap(true);
    }

    /**
     * 下移地图
     */
    public void moveDownMap() {
        moveMap(false);
    }

    /**
     * 地图滑动
     *
     * @param status true上移  false 下移
     */
    public void moveMap(Boolean status) {
        android.graphics.Point screenPoint = new android.graphics.Point(0, 0);
        if (mapView.toMapPoint(0, 0) != null) {
            Point point = mapView.toMapPoint(0, 0);
            double y = point.getY();
            Point pointEnd = mapView.toMapPoint(0, mapHeight);
            double bottomY = pointEnd.getY();
            double translationY = (bottomY - y) / 2 - (bottomY - y) / 4;
//                mapView.visibleArea   返回一个Polygon，表示当前在MapView中可见的ArcGISMap区域。
            Point center = mapView.getCenter();
            if (status) {
                //中心点上移
//                mapView.setViewpointCenterAsync(new Point(centerPoint.getX(), centerPoint.getY() + translationY));
                mapView.centerAt(new Point(center.getX(), center.getY() + translationY), true);
            } else {
                //下移
                mapView.centerAt(new Point(center.getX(), center.getY() - translationY), true);
            }
        }
    }

    /**
     * 放大地图
     */
    public void zoomin() {
        if (mapView != null) {
            mapView.zoomin();
        }
    }

    /**
     * 缩小地图
     */
    public void zoomout() {
        if (mapView != null) {
            mapView.zoomout();
        }
    }

    /**
     * 切换图层
     *
     * @param maptype
     */
    public void changLayer(String maptype) {
        switch (maptype) {
            /** 影像图层*/
            case "image":
                if (imgLayer != null) {
                    imgLayer.setVisible(true);

                }
                if (ciaLayer != null) {
                    ciaLayer.setVisible(true);
                }
                if (cvaBaseLayer != null) {
                    cvaBaseLayer.setVisible(false);
                }
                if (vecBaseLayer != null) {
                    vecBaseLayer.setVisible(false);
                }
                break;
            case "vender":
                if (imgLayer != null) {
                    imgLayer.setVisible(false);

                }
                if (ciaLayer != null) {
                    ciaLayer.setVisible(false);
                }
                if (cvaBaseLayer != null) {
                    cvaBaseLayer.setVisible(true);
                }
                if (vecBaseLayer != null) {
                    vecBaseLayer.setVisible(true);
                }
                break;
            default:
                if (imgLayer != null) {
                    imgLayer.setVisible(true);

                }
                if (ciaLayer != null) {
                    ciaLayer.setVisible(true);
                }
                if (cvaBaseLayer != null) {
                    cvaBaseLayer.setVisible(false);
                }
                if (vecBaseLayer != null) {
                    vecBaseLayer.setVisible(false);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
