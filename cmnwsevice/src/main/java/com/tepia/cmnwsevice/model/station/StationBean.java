package com.tepia.cmnwsevice.model.station;

import com.google.common.base.Strings;
import com.google.gson.annotations.SerializedName;
import com.tepia.cmnwsevice.utils.StringUtil;

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
    private String administrativeDivision;
    private String enterpriseCode;
    private String administrativeDivisionName;
    private String stationType;
    private String stationStatus;
    private String enterpriseName;
    private String address;
    private String name;
    private String lgtd;
    private String lttd;
    private WaterQualityBean waterQuality;
    @SerializedName(value = "handingStation",alternate = {"pumpingStation"})
    private StationBaseInfoBean handingStation;
    private CurrentDataBean currentData;

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getAdministrativeDivisionName() {
        return administrativeDivisionName;
    }

    public void setAdministrativeDivisionName(String administrativeDivisionName) {
        this.administrativeDivisionName = administrativeDivisionName;
    }

    public String getStationType() {
        return StringUtil.nullToDefault(stationType);
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getStationStatus() {
        return stationStatus;
    }

    public void setStationStatus(String stationStatus) {
        this.stationStatus = stationStatus;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

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

    public WaterQualityBean getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(WaterQualityBean waterQuality) {
        this.waterQuality = waterQuality;
    }

    public StationBaseInfoBean getHandingStation() {
        return handingStation;
    }

    public void setHandingStation(StationBaseInfoBean handingStation) {
        this.handingStation = handingStation;
    }

    public CurrentDataBean getCurrentData() {
        return currentData;
    }

    public void setCurrentData(CurrentDataBean currentData) {
        this.currentData = currentData;
    }
}
