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
     * communicationResult : {"communicationStatus":0}
     * conductivityResult : {"conductivity":1375,"conductivityStatus":0}
     * deviceMonitorDataList : [{"id":1122324612891123718,"code":"HS20190401161320483133","deviceCode":"100A-1#JSJB","deviceName":"1#集水井泵","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-28 10:20:13"},{"id":1122324612954038274,"code":"HS20190401161320483133","deviceCode":"100A-2#JSJB","deviceName":"2#集水井泵","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-28 10:20:13"},{"id":1122324612995981314,"code":"HS20190401161320483133","deviceCode":"100A-FXB","deviceName":"反洗泵","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-28 10:20:13"},{"id":1122324613025341442,"code":"HS20190401161320483133","deviceCode":"100A-QTFJ","deviceName":"气提风机","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-28 10:20:13"},{"id":1122324613054701573,"code":"HS20190401161320483133","deviceCode":"100A-BQFJ1B","deviceName":"曝气风机B1","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-28 10:20:13"},{"id":1122324613151170564,"code":"HS20190401161320483133","deviceCode":"100A-BQFJ2B","deviceName":"曝气风机B2","isFault":0,"isRunning":0,"conductivity":null,"tm":"2019-04-28 10:20:13"}]
     * deviceResult : {"deviceStatus":0}
     */

    private CommunicationResultBean communicationResult;
    private ConductivityResultBean conductivityResult;
    private DeviceResultBean deviceResult;
    private List<DeviceMonitorDataListBean> deviceMonitorDataList;

    public CommunicationResultBean getCommunicationResult() {
        return communicationResult;
    }

    public void setCommunicationResult(CommunicationResultBean communicationResult) {
        this.communicationResult = communicationResult;
    }

    public ConductivityResultBean getConductivityResult() {
        return conductivityResult;
    }

    public void setConductivityResult(ConductivityResultBean conductivityResult) {
        this.conductivityResult = conductivityResult;
    }

    public DeviceResultBean getDeviceResult() {
        return deviceResult;
    }

    public void setDeviceResult(DeviceResultBean deviceResult) {
        this.deviceResult = deviceResult;
    }

    public List<DeviceMonitorDataListBean> getDeviceMonitorDataList() {
        return deviceMonitorDataList;
    }

    public void setDeviceMonitorDataList(List<DeviceMonitorDataListBean> deviceMonitorDataList) {
        this.deviceMonitorDataList = deviceMonitorDataList;
    }

    public static class CommunicationResultBean {
        /**
         * communicationStatus : 0
         */

        private int communicationStatus;
        private String duration;

        public int getCommunicationStatus() {
            return communicationStatus;
        }

        public void setCommunicationStatus(int communicationStatus) {
            this.communicationStatus = communicationStatus;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }
    }

    public static class ConductivityResultBean {
        /**
         * conductivity : 1375.0
         * conductivityStatus : 0
         */

        private double conductivity;
        private int conductivityStatus;

        public double getConductivity() {
            return conductivity;
        }

        public void setConductivity(double conductivity) {
            this.conductivity = conductivity;
        }

        public int getConductivityStatus() {
            return conductivityStatus;
        }

        public void setConductivityStatus(int conductivityStatus) {
            this.conductivityStatus = conductivityStatus;
        }
    }

    public static class DeviceResultBean {
        /**
         * deviceStatus : 0
         */

        private int deviceStatus;

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }
    }


}
