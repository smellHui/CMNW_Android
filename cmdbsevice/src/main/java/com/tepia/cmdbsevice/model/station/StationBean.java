package com.tepia.cmdbsevice.model.station;

import com.google.common.base.Strings;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/16
 * Time            :       16:18
 * Version         :       1.0
 * 功能描述        :
 **/
public class StationBean extends DataSupport {
    @Column(unique = true)
    private String code;
    private String address;
    private String name;
    private String lgtd;
    private String lttd;
    private Object waterQuality;
    private StationMessageBean stationMessage;
    private CurrentDataBean currentData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLgtd() {
        return Strings.isNullOrEmpty(lgtd) ? "0" : lgtd;
    }

    public void setLgtd(String lgtd) {
        this.lgtd = lgtd;
    }

    public String getLttd() {
        return Strings.isNullOrEmpty(lttd) ? "0" : lttd;
    }

    public void setLttd(String lttd) {
        this.lttd = lttd;
    }

    public Object getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(Object waterQuality) {
        this.waterQuality = waterQuality;
    }

    public StationMessageBean getStationMessage() {
        return stationMessage;
    }

    public void setStationMessage(StationMessageBean stationMessage) {
        this.stationMessage = stationMessage;
    }

    public CurrentDataBean getCurrentData() {
        return currentData;
    }

    public void setCurrentData(CurrentDataBean currentData) {
        this.currentData = currentData;
    }
}
