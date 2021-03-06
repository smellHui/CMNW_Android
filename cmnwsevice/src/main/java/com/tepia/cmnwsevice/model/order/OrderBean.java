package com.tepia.cmnwsevice.model.order;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.utils.StringUtil;

import java.util.Map;

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
    private String deviceCode;
    private String deviceName;
    private String factor;
    private String partsUse;
    private int executeStatus;

    public String getCreateData() {
        if (Strings.isNullOrEmpty(createdTime)) return "";
        String[] arr = createdTime.split("\\s+");
        return arr[0];
    }

    public String getDataAndName() {
        return getCreateData() + "  " + getOrderName();
    }

    public int getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(int executeStatus) {
        this.executeStatus = executeStatus;
    }

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
        return StringUtil.nullToDefault(areaName);
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCreatedTime() {
        return StringUtil.nullToDefault(createdTime);
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

    public String getDeviceCode() {
        return StringUtil.nullToDefault(deviceCode);
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return StringUtil.nullToDefault(deviceName);
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFactor() {
        return StringUtil.nullToDefault(factor);
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getPartsUse() {
        return Strings.nullToEmpty(partsUse);
    }

    public void setPartsUse(String partsUse) {
        this.partsUse = partsUse;
    }

    public String getSourceName() {
        Map<String, String> sourceMap = DictManager.getInstance().getDictMap().get("source");
        if (sourceMap == null) return "";
        return sourceMap.get(source + "");
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id='" + id + '\'' +
                ", orderName='" + orderName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", source=" + source +
                ", deviceCode='" + deviceCode + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", factor='" + factor + '\'' +
                ", partsUse='" + partsUse + '\'' +
                ", executeStatus=" + executeStatus +
                '}';
    }
}
