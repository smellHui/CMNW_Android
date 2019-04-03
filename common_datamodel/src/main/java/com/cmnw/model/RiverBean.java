package com.cmnw.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-12-17
 * Time            :       16:46
 * Version         :       1.0
 * 功能描述        :
 **/
public class RiverBean {

    /**
     * riverId : 100000
     * rcCd : CMQ001
     * rvCd : CMQ001
     * rcNm : 新建港
     * rcBLv : 4001
     * "mapId": "41934"  对应arcgis河流地图FID
     */

    private String riverId;
    private String rcCd;
    private String rvCd;
    @SerializedName(value = "rcnm",alternate = {"rcNm"})
    private String rcNm;
    @SerializedName(value = "rcBLv",alternate = {"rcBlv"})
    private String rcBLv;
    private List<RiverBean> childList;
    private String mapId;
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getRiverId() {
        return riverId;
    }

    public void setRiverId(String riverId) {
        this.riverId = riverId;
    }

    public String getRcCd() {
        return rcCd;
    }

    public void setRcCd(String rcCd) {
        this.rcCd = rcCd;
    }

    public String getRvCd() {
        return rvCd;
    }

    public void setRvCd(String rvCd) {
        this.rvCd = rvCd;
    }

    public String getRcNm() {
        return rcNm;
    }

    public void setRcNm(String rcNm) {
        this.rcNm = rcNm;
    }

    public String getRcBLv() {
        return rcBLv;
    }

    public void setRcBLv(String rcBLv) {
        this.rcBLv = rcBLv;
    }

    public List<RiverBean> getChildList() {
        return childList;
    }

    public void setChildList(List<RiverBean> childList) {
        this.childList = childList;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }
}
