package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.esri.android.map.Callout;
import com.esri.android.map.CalloutStyle;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.google.gson.Gson;
import com.tepia.base.mvp.BaseCommonFragment;
import com.tepia.base.utils.Utils;
import com.tepia.cmdbsevice.APPConst;
import com.tepia.cmdbsevice.ConfigConst;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentHeaderMapPointBinding;
import com.tepia.cmdbsevice.databinding.FragmentStationDetailBinding;
import com.tepia.cmdbsevice.util.ARCGISUTIL;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.TianDiTuTiledMapServiceLayer;
import com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap.TianDiTuTiledMapServiceType;
import com.tepia.cmnwsevice.model.station.StationBean;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/28
 * Time            :       14:52
 * Version         :       1.0
 * 功能描述        :
 **/
public class HeaderMapFragment extends BaseCommonFragment {
    public StationBean stationBean;
    private FragmentHeaderMapPointBinding mBinding;
    private MapView mapView;
    private TianDiTuTiledMapServiceLayer imgLayer;
    private TianDiTuTiledMapServiceLayer ciaLayer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_header_map_point;
    }

    @Override
    protected void initData() {
        mBinding = DataBindingUtil.bind(mRootView);
        mapView = findView(R.id.mv_arcgis_river_log);
        initArcgisMap();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initRequestData() {

    }

    private ArcGISTiledMapServiceLayer tiledMapLayer;
    private ArcGISFeatureLayer riversFeatureLayer;
    private GraphicsLayer logGraphicsLayer;
    /**
     * 附近我所属的河流图层
     */
    private GraphicsLayer riversNameGraphicsLayer;
    /**
     * 选中的河流图层
     */
    private GraphicsLayer selectRiversLayer;

    private boolean has_loading;


    /**
     * 配置arcgis地图
     */
    private void initArcgisMap() {
        //去水印
        ArcGISRuntime.setClientId("9yNxBahuPiGPbsdi");
        mBinding.mvArcgisRiverLog.setMapBackground(ContextCompat.getColor(Utils.getContext(), R.color.white), ContextCompat.getColor(Utils.getContext(), R.color.white), 0f, 0f);

        //设置底图
        {
            /**
             * 天地图矢量
             * */
            imgLayer = new TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType.IMG_C);
            mapView.addLayer(imgLayer, 0);
            /**
             * 天地图矢量标注
             * */
            ciaLayer = new TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType.CIA_C);
            mapView.addLayer(ciaLayer, 1);
        }
        riversFeatureLayer = new ArcGISFeatureLayer(ConfigConst.riversMapUrl, ArcGISFeatureLayer.MODE.ONDEMAND);
        riversNameGraphicsLayer = new GraphicsLayer();
        selectRiversLayer = new GraphicsLayer();
        logGraphicsLayer = new GraphicsLayer();
        mBinding.mvArcgisRiverLog.addLayer(tiledMapLayer);
        mBinding.mvArcgisRiverLog.addLayer(riversFeatureLayer);
        mBinding.mvArcgisRiverLog.addLayer(riversNameGraphicsLayer);
        mBinding.mvArcgisRiverLog.addLayer(selectRiversLayer);
        mBinding.mvArcgisRiverLog.addLayer(logGraphicsLayer);
        mBinding.mvArcgisRiverLog.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                if (status == STATUS.INITIALIZED) {

                }
                if (status == STATUS.LAYER_LOADED) {
                    mBinding.layoutLoading.loadingLayout.setVisibility(View.GONE);
                    drawMapPoint(stationBean);

                }
                if (status == STATUS.LAYER_LOADING_FAILED) {
                    mBinding.layoutLoading.tvLoading.setText("地图加载失败");
                }
            }
        });


    }

    private void drawMapPoint(StationBean bean) {
        Point point = transStationBeanTOpoint(bean);
        if (point == null) {
            return;
        }
        switch (bean.getStationType()) {
            case "1":
                if (TextUtils.isEmpty(bean.getStationStatus())) {
                    ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_0, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                } else {
                    switch (bean.getStationStatus()) {
                        case "0":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_0, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        case "1":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_1, point, logGraphicsLayer, new Gson().toJson(bean).toString());
//                                                ARCGISUTIL.addPic(R.mipmap.icon_clz_1, point, logGraphicsLayer);
                            break;
                        case "2":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_2, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        case "3":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_3, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        default:
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_0, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                    }
                }

                break;
            case "2":
                if (TextUtils.isEmpty(bean.getStationStatus())) {
                    ARCGISUTIL.addPic(R.mipmap.icon_gz_0, point, logGraphicsLayer);
                } else {
                    switch (bean.getStationStatus()) {
                        case "0":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_gz_0, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        case "1":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_gz_1, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        case "2":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_gz_2, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        case "3":
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_gz_3, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                        default:
                            ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_0, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                            break;
                    }
                }
                break;
            default:
                ARCGISUTIL.addPicForPos(R.mipmap.icon_clz_0, point, logGraphicsLayer, new Gson().toJson(bean).toString());
                break;
        }
        mBinding.mvArcgisRiverLog.centerAt(point, false);
        mBinding.mvArcgisRiverLog.setScale(ConfigConst.scale, true);

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
}
