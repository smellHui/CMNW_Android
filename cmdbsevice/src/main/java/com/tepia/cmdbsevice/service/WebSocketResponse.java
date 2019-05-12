package com.tepia.cmdbsevice.service;

import com.tepia.base.http.BaseResponse;
import com.tepia.cmnwsevice.model.station.StationBean;

import java.util.Map;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/5/12
 * Time            :       19:13
 * Version         :       1.0
 * 功能描述        :
 **/
public class WebSocketResponse extends BaseResponse {
    private Map<String,StationBean> stationMonitor;

    public Map<String, StationBean> getStationMonitor() {
        return stationMonitor;
    }

    public void setStationMonitor(Map<String, StationBean> stationMonitor) {
        this.stationMonitor = stationMonitor;
    }
}
