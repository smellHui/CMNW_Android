package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.onlinemonitormap;


import android.util.Log;

import com.esri.android.map.TiledServiceLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.io.UserCredentials;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/14
 * Time            :       15:54
 * Version         :       1.0
 * 功能描述        :
 **/
public class TianDiTuTiledMapServiceLayer extends TiledServiceLayer {
    private TianDiTuTiledMapServiceType _mapType;
    private TileInfo tiandituTileInfo;

    public TianDiTuTiledMapServiceLayer() {
        this(null, null, true);
    }

    public TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType mapType) {
        this(mapType, null, true);
    }

    public TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType mapType, UserCredentials usercredentials) {
        this(mapType, usercredentials, true);
    }

    public TianDiTuTiledMapServiceLayer(TianDiTuTiledMapServiceType mapType, UserCredentials usercredentials, boolean flag) {
        super("");
        this._mapType = mapType;
        setCredentials(usercredentials);

        if (flag)
            try {
                getServiceExecutor().submit(new Runnable() {

                    @Override
                    public final void run() {
                        a.initLayer();
                    }

                    final TianDiTuTiledMapServiceLayer a;


                    {
                        a = TianDiTuTiledMapServiceLayer.this;
                        //super();
                    }
                });
                return;
            } catch (RejectedExecutionException _ex) {
            }
    }

    public TianDiTuTiledMapServiceType getMapType() {
        return this._mapType;
    }

    @Override
    protected void initLayer() {
        if (getID() == 0L) {
            nativeHandle = create();
            changeStatus(com.esri.android.map.event.OnStatusChangedListener.STATUS
                    .fromInt(-1000));
        } else {
            this.buildTileInfo();
            this.setFullExtent(new Envelope(-22041257.773878,
                    -20851350.0432886, 22041257.773878, 20851350.0432886));
            this.setDefaultSpatialReference(SpatialReference.create(3857));
            super.initLayer();
        }
    }

    public void refresh() {
        try {
            getServiceExecutor().submit(new Runnable() {

                @Override
                public final void run() {
                    if (a.isInitialized()) {
                        try {
                            a.b();
                            a.clearTiles();
                            return;
                        } catch (Exception exception) {
                            Log.e("ArcGIS", "Re-initialization of the layer failed.", exception);
                        }
                    }
                }

                final TianDiTuTiledMapServiceLayer a;


                {
                    a = TianDiTuTiledMapServiceLayer.this;
                    //super();
                }
            });
            return;
        } catch (RejectedExecutionException _ex) {
            return;
        }
    }

    final void b()
            throws Exception {

    }

    @Override
    protected byte[] getTile(int level, int col, int row) throws Exception {
        /**
         *
         * */

        byte[] result = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            URL sjwurl = new URL(this.getTianDiMapUrl(level, col, row));
            HttpURLConnection httpUrl = null;
            BufferedInputStream bis = null;
            byte[] buf = new byte[1024];

            httpUrl = (HttpURLConnection) sjwurl.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());

            while (true) {
                int bytes_read = bis.read(buf);
                if (bytes_read > 0) {
                    bos.write(buf, 0, bytes_read);
                } else {
                    break;
                }
            }
            ;
            bis.close();
            httpUrl.disconnect();

            result = bos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }


    @Override
    public TileInfo getTileInfo() {
        return this.tiandituTileInfo;
    }

    /**
     *
     * */
    private String getTianDiMapUrl(int level, int col, int row) {

        String url = new TDTUrl(level, col, row, this._mapType).getUrl();
        return url;
    }

    private void buildTileInfo() {
        Point origin = new Point(-20037508.342787, 20037508.342787);

        double[] scales = new double[]{591657527.591555,
                295828763.79577702, 147914381.89788899, 73957190.948944002,
                36978595.474472001, 18489297.737236001, 9244648.8686180003,
                4622324.4343090001, 2311162.217155, 1155581.108577, 577790.554289,
                288895.277144, 144447.638572, 72223.819286, 36111.909643,
                18055.954822, 9027.9774109999998, 4513.9887049999998, 2256.994353,
                1128.4971760000001};
        double[] resolutions = new double[]{156543.03392800014,
                78271.516963999937, 39135.758482000092, 19567.879240999919,
                9783.9396204999593, 4891.9698102499797, 2445.9849051249898,
                1222.9924525624949, 611.49622628138, 305.748113140558,
                152.874056570411, 76.4370282850732, 38.2185141425366,
                19.1092570712683, 9.55462853563415, 4.7773142679493699,
                2.3886571339746849, 1.1943285668550503, 0.59716428355981721,
                0.29858214164761665};
        int levels = 21;
        int dpi = 96;
        int tileWidth = 256;
        int tileHeight = 256;
        this.tiandituTileInfo = new com.esri.android.map.TiledServiceLayer.TileInfo(origin, scales, resolutions, scales.length, dpi, tileWidth, tileHeight);
        this.setTileInfo(this.tiandituTileInfo);
    }
}