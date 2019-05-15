package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationdetail;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/15
 * Time            :       13:58
 * Version         :       1.0
 * 功能描述        :
 **/
public class NavUtil {
    // 检索地图软件
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 指定地图
     * 百度地图包名：com.baidu.BaiduMap
     * 高德地图包名：com.autonavi.minimap
     * <p>
     * 腾讯地图包名：com.tencent.map
     * <p>
     * 谷歌地图 com.google.android.apps.maps
     */
    public static List<String> mapsList() {
        List<String> maps = new ArrayList<>();
        maps.add("com.baidu.BaiduMap");
        maps.add("com.autonavi.minimap");
        maps.add("com.tencent.map");
        return maps;
    }

    // 检索筛选后返回
    public static List<String> hasMap(Context context) {
        List<String> mapsList = mapsList();
        List<String> backList = new ArrayList<>();
        for (int i = 0; i < mapsList.size(); i++) {
            boolean avilible = isAvilible(context, mapsList.get(i));
            if (avilible) {
                backList.add(mapsList.get(i));
            }
        }
        return backList;


    }

    // 百度地图
    public static void toBaidu(Context context, String lat, String lon) {

        Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("baidumap://map/geocoder?location=" + lat + "," + lon));
        context.startActivity(naviIntent);
    }

    // 高德地图
    public static void toGaodeNavi(Context context, String lat, String lon) {
        Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://route?sourceApplication=appName&slat=&slon=&sname=我的位置&dlat=" + lat + "&dlon=" + lon + "&dname=目的地&dev=0&t=2"));
        context.startActivity(naviIntent);
    }

    // 腾讯地图
    public static void toTencent(Context context, String lat, String lon) {
        Intent naviIntent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to=目的地&tocoord=" + lat + "," + lon + "&policy=0&referer=appName"));
        context.startActivity(naviIntent);
    }
}
