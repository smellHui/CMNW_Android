package com.tepia.cmnwsevice.model.station;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/25
 * Time            :       14:47
 * Version         :       1.0
 * 功能描述        :
 **/
public class WarningBean {
    private String alarmCode;
    private String startTime;
    private String endTime;
    private String duration;
    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
