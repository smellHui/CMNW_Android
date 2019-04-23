package com.tepia.cmnwsevice.model.station;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/22
 * Time            :       15:11
 * Version         :       1.0
 * 功能描述        :
 **/
public class CurrentDataBean {

    /**
     * durationTime : 5天22h55min
     * isCommunication : true
     * conductivityStatus : 0
     * deviceStatus : 0
     * conductivity : 500.0
     * deviceMonitorDataList : [{"id":1118063980383748099,"code":"HS20190401161319183428","deviceCode":"MZ-1#TSB","deviceName":"1#提升泵","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-16 16:10:00"},{"id":1118063980434079745,"code":"HS20190401161319183428","deviceCode":"MZ-2#TSB","deviceName":"2#提升泵","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-16 16:10:00"},{"id":1118063980480217091,"code":"HS20190401161319183428","deviceCode":"MZ-1#FJ","deviceName":"1#风机","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-16 16:10:00"}]
     * startTime : 2019-04-16 16:10:00
     * endTime : 2019-04-22 15:05:28
     */

    private String durationTime;
    private boolean isCommunication;
    private int conductivityStatus;
    private int deviceStatus;
    private double conductivity;
    private String startTime;
    private String endTime;
    private List<DeviceMonitorDataListBean> deviceMonitorDataList;

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public boolean isIsCommunication() {
        return isCommunication;
    }

    public void setIsCommunication(boolean isCommunication) {
        this.isCommunication = isCommunication;
    }

    public int getConductivityStatus() {
        return conductivityStatus;
    }

    public void setConductivityStatus(int conductivityStatus) {
        this.conductivityStatus = conductivityStatus;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public double getConductivity() {
        return conductivity;
    }

    public void setConductivity(double conductivity) {
        this.conductivity = conductivity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<DeviceMonitorDataListBean> getDeviceMonitorDataList() {
        return deviceMonitorDataList;
    }

    public void setDeviceMonitorDataList(List<DeviceMonitorDataListBean> deviceMonitorDataList) {
        this.deviceMonitorDataList = deviceMonitorDataList;
    }


}
