package com.tepia.cmnwsevice.model.station;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/22
 * Time            :       15:08
 * Version         :       1.0
 * 功能描述        :
 **/
public class StationMessageBean {

    /**
     * stationName : 江镇村11号处理站
     * address : 江镇十一队江民支路南400米
     * vendorName : 其它企业
     * picture : []
     */

    private String stationName;
    private String address;
    private String vendorName;
    private List<PictureBean> picture;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<PictureBean> getPicture() {
        return picture;
    }

    public void setPicture(List<PictureBean> picture) {
        this.picture = picture;
    }
}
