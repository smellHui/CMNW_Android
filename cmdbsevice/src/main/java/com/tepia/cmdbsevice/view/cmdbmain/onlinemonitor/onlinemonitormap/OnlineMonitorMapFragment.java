package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap;


import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.ags.ArcGISFeatureLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.Point;
import com.tepia.base.mvp.MVPBaseFragment;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.base.view.floatview.CollectionsUtil;
import com.tepia.cmdbsevice.ConfigConst;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMapBinding;
import com.tepia.cmnwsevice.model.station.StationBean;
import com.tepia.cmdbsevice.util.ARCGISUTIL;

import org.litepal.crud.DataSupport;

import java.util.List;

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

    private FragmentOnlineMapBinding mBinding;
    private int count = 0;
    private ArcGISTiledMapServiceLayer imgLayer;

    /**
     * point 图标点击事件
     */
    private OnPointClickListener onPointClickListener;


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
        //去水印
        ArcGISRuntime.setClientId("9yNxBahuPiGPbsdi");
        mBinding.mvArcgisRiverLog.setMapBackground(ContextCompat.getColor(Utils.getContext(), R.color.white), ContextCompat.getColor(Utils.getContext(), R.color.white), 0f, 0f);

        //设置底图
        tiledMapLayer = new ArcGISTiledMapServiceLayer(ConfigConst.baseMapUrl);
        imgLayer = new ArcGISTiledMapServiceLayer("https://services.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer");
        riversFeatureLayer = new ArcGISFeatureLayer(ConfigConst.riversMapUrl, ArcGISFeatureLayer.MODE.ONDEMAND);
        riversNameGraphicsLayer = new GraphicsLayer();
        selectRiversLayer = new GraphicsLayer();
        logGraphicsLayer = new GraphicsLayer();
        mBinding.mvArcgisRiverLog.addLayer(tiledMapLayer);
        mBinding.mvArcgisRiverLog.addLayer(imgLayer);
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
                    List<StationBean> stationList = DataSupport.findAll(StationBean.class);
                    drawMapPoint(stationList);
                    if (count == 1) {
                        mBinding.mvArcgisRiverLog.centerAt(new Point(ConfigConst.LNG, ConfigConst.LAT), true);
//                        mBinding.mvArcgisRiverLog.setScale(ConfigConst.scale, true);
                    }
                }
                if (status == STATUS.LAYER_LOADING_FAILED) {
                    mBinding.layoutLoading.tvLoading.setText("地图加载失败");
                }
            }
        });

    }

    private void drawMapPoint(List<StationBean> stationList) {
        logGraphicsLayer.removeGraphics(logGraphicsLayer.getGraphicIDs());
        if (!CollectionsUtil.isEmpty(stationList)) {
            count = 0;
            mBinding.mvArcgisRiverLog.post(new Runnable() {
                @Override
                public void run() {
                    if (count < stationList.size()) {
                        StationBean bean = stationList.get(count++);
                        if (bean == null) {
                            mBinding.mvArcgisRiverLog.postDelayed(this, 100);
                            return;
                        }
                        if (TextUtils.isEmpty(bean.getLttd()) && TextUtils.isEmpty(bean.getLttd())) {
                            mBinding.mvArcgisRiverLog.postDelayed(this, 100);
                            return;
                        }
                        Double lttdrv = Double.parseDouble(bean.getLttd());
                        Double lgtdrv = Double.parseDouble(bean.getLgtd());
                        if (lgtdrv <= lttdrv || lgtdrv == 0 || lttdrv == 0) {
                            ToastUtils.shortToast("测试阶段的部分数据不标准");
                            mBinding.mvArcgisRiverLog.centerAt(new Point(ConfigConst.LNG, ConfigConst.LAT), true);
                            mBinding.mvArcgisRiverLog.setScale(ConfigConst.scale, true);
                            return;
                        }
                        if (TextUtils.isEmpty(bean.getStationType())) {
                            mBinding.mvArcgisRiverLog.postDelayed(this, 100);

                        } else {
                            switch (bean.getStationType()) {
                                case "1":
                                    if (TextUtils.isEmpty(bean.getStationStatus())) {
                                        ARCGISUTIL.addPic(R.mipmap.icon_clz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                    } else {
                                        switch (bean.getStationStatus()) {
                                            case "0":
                                                ARCGISUTIL.addPic(R.mipmap.icon_clz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            case "1":
                                                ARCGISUTIL.addPic(R.mipmap.icon_clz_1, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            case "2":
                                                ARCGISUTIL.addPic(R.mipmap.icon_clz_2, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            case "3":
                                                ARCGISUTIL.addPic(R.mipmap.icon_clz_3, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            default:
                                                ARCGISUTIL.addPic(R.mipmap.icon_clz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                        }
                                    }

                                    break;
                                case "2":
                                    if (TextUtils.isEmpty(bean.getStationStatus())) {
                                        ARCGISUTIL.addPic(R.mipmap.icon_gz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                    } else {
                                        switch (bean.getStationStatus()) {
                                            case "0":
                                                ARCGISUTIL.addPic(R.mipmap.icon_gz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            case "1":
                                                ARCGISUTIL.addPic(R.mipmap.icon_gz_1, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            case "2":
                                                ARCGISUTIL.addPic(R.mipmap.icon_gz_2, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            case "3":
                                                ARCGISUTIL.addPic(R.mipmap.icon_gz_3, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                            default:
                                                ARCGISUTIL.addPic(R.mipmap.icon_gz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                                break;
                                        }
                                    }
                                    break;
                                default:
                                    ARCGISUTIL.addPic(R.mipmap.icon_gz_0, new Point(lgtdrv, lttdrv), logGraphicsLayer);
                                    break;
                            }
                        }


                        mBinding.mvArcgisRiverLog.postDelayed(this, 100);
                    }
                }
            });
        }
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
        List<StationBean> stationList = DataSupport.where(conditions).find(StationBean.class);
        drawMapPoint(stationList);
        centerAndZoom(stationList);
    }

    /**
     * 将这些点 为中心 并缩放
     * @param stationList
     */
    private void centerAndZoom(List<StationBean> stationList) {
    }

    /**
     * 将这些点 为中心 并缩放
     * @param bean
     */
    private void centerAndZoom(StationBean bean) {
    }

    /**
     * 放大地图
     */
    public void zoomin() {
    }

    /**
     * 缩小地图
     */
    public void zoomout() {
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
                break;
            case "veder":
                break;
            default:
                break;
        }
    }
}
