package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
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
import com.tepia.cmdbsevice.ConfigConst;
import com.tepia.cmdbsevice.R;
import com.tepia.cmdbsevice.databinding.FragmentOnlineMapBinding;
import com.tepia.cmnwsevice.databinding.FragmentExamineListDetailBinding;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/4/16 9:57
 * @修改人         ：
 * @修改时间       :       2019/4/16 9:57
 * @功能描述       :       在线监测中的地图
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

    private boolean has_loading;
    private FragmentOnlineMapBinding mBinding;

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

                }
                if (status == STATUS.LAYER_LOADING_FAILED) {
                    mBinding.layoutLoading.tvLoading.setText("地图加载失败");
                }
            }
        });





    }
    @Override
    protected void initRequestData() {

    }
}
