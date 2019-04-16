package com.tepia.cmdbsevice;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author : liying (from Center Of Wuhan)
 * 创建时间 : 2018-11-22
 * 更新时间 :
 * Version : 1.0
 * 功能描述 : 配置常量
 */

public class ConfigConst {
    public static final int code_200 = 200;
    /**
     * 动画伸缩时间
     */
    public static final int duration = 100;


    public static final float zoomf = 13f;
    public static final float rotation_value = 360f;
    public static final String LAST_LOCATION = "LAST_LOCATION";


    /**
     * evenbus更新用户信息标记
     */
    public static final int UPDATE_USERBEAN = 100;

    /**
     * 巡河日志和巡河统计列表分页请求时每次加载的数据量
     */
    public static final int pageSize = 10;

    public static final double LAT = 31.626;//
    public static final double LNG = 121.422;//121.422
    /**
     * 河流图层代码
     * 上海业主地址
     * http://101.230.199.223/arcgis/rest/services/bl/riversplit2017_county_WGS1984/MapServer/0
     * 武汉研发中心发布的地址
     * http://47.99.135.198:6080/arcgis/rest/services/riversplit2017_county_WGS1984/MapServer/0
     */
    public static final String riversMapUrl = "http://47.99.135.198:6080/arcgis/rest/services/riversplit2017_county_WGS1984/MapServer/0";
    /**
     * 底图地址
     * 上海业主地址
     * http://222.92.58.133:9999/ArcGIS/rest/services/ks_map_wgs84/MapServer
     * 武汉研发中心发布的地址
     * http://47.99.135.198:6080/arcgis/rest/services/tianditu_street_chongming/MapServer
     */
    public static final String baseMapUrl = "http://47.99.135.198:6080/arcgis/rest/services/tianditu_street_chongming/MapServer";

    public static final double scale = 9000;


}
