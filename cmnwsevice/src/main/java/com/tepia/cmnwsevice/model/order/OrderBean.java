package com.tepia.cmnwsevice.model.order;

import com.google.common.base.Strings;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       17:00
 * Version         :       1.0
 * 功能描述        :        工单实体  ps 现在只是列表数据 可以根据详细数据添加
 **/
public class OrderBean {


    /**
     * id : 1114816161280811009
     * orderName : 向化示范处理站
     * areaName : 向化镇
     * createdTime : 2019-04-07
     * source : 3
     * deviceCode : null
     * deviceName : null
     * factor : null
     * partsUse : null
     */

    private String id;
    private String orderName;
    private String areaName;
    private String createdTime;
    private int source;
    private Object deviceCode;
    private String deviceName;
    private Object factor;
    private Object partsUse;

    public String getId() {
        return Strings.nullToEmpty(id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return Strings.nullToEmpty(orderName);
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getAreaName() {
        return Strings.nullToEmpty(areaName);
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Object getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Object deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return Strings.nullToEmpty(deviceName);
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Object getFactor() {
        return factor;
    }

    public void setFactor(Object factor) {
        this.factor = factor;
    }

    public Object getPartsUse() {
        return partsUse;
    }

    public void setPartsUse(Object partsUse) {
        this.partsUse = partsUse;
    }
}
