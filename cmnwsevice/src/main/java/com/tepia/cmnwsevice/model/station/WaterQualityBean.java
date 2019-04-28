package com.tepia.cmnwsevice.model.station;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/28
 * Time            :       10:19
 * Version         :       1.0
 * 功能描述        :
 **/
public class WaterQualityBean {

    /**
     * id : 7
     * stationId : 363
     * cod : 2.3
     * nh3n : 12.0
     * tp : 23.0
     * tn : 12.0
     * ss : 34.0
     * dzwy : 1.0
     * las : 23.0
     * status : 1
     * reachTarget : null
     * createdBy : 1
     * createdTime : 2018-04-01 20:06:26
     * updatedBy : null
     * updatedTime : 2019-04-27 10:34:51
     */

    private String id;
    private String stationId;
    private double cod;
    private double nh3n;
    private double tp;
    private double tn;
    private double ss;
    private double dzwy;
    private double las;
    private int status;
    private String reachTarget;
    private String createdBy;
    private String createdTime;
    private String updatedBy;
    private String updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
        this.cod = cod;
    }

    public double getNh3n() {
        return nh3n;
    }

    public void setNh3n(double nh3n) {
        this.nh3n = nh3n;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getTn() {
        return tn;
    }

    public void setTn(double tn) {
        this.tn = tn;
    }

    public double getSs() {
        return ss;
    }

    public void setSs(double ss) {
        this.ss = ss;
    }

    public double getDzwy() {
        return dzwy;
    }

    public void setDzwy(double dzwy) {
        this.dzwy = dzwy;
    }

    public double getLas() {
        return las;
    }

    public void setLas(double las) {
        this.las = las;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReachTarget() {
        return reachTarget;
    }

    public void setReachTarget(String reachTarget) {
        this.reachTarget = reachTarget;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
