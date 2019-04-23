package com.tepia.cmnwsevice.model.station;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/22
 * Time            :       15:12
 * Version         :       1.0
 * 功能描述        :
 **/
public class DeviceMonitorDataListBean {
    /**
     * id : 1118063980383748099
     * code : HS20190401161319183428
     * deviceCode : MZ-1#TSB
     * deviceName : 1#提升泵
     * isFault : 0
     * isRunning : 0
     * conductivity : null
     * tm : 2019-04-16 16:10:00
     */

    private long id;
    private String code;
    private String deviceCode;
    private String deviceName;
    private int isFault;
    private int isRunning;
    private Object conductivity;
    private String tm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getIsFault() {
        return isFault;
    }

    public void setIsFault(int isFault) {
        this.isFault = isFault;
    }

    public int getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(int isRunning) {
        this.isRunning = isRunning;
    }

    public Object getConductivity() {
        return conductivity;
    }

    public void setConductivity(Object conductivity) {
        this.conductivity = conductivity;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }
}
