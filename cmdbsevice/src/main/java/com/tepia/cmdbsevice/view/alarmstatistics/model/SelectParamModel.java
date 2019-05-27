package com.tepia.cmdbsevice.view.alarmstatistics.model;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/27
 * Description:事件督办右侧筛选请求参数字段
 */
public class SelectParamModel {
    /**
     * @param areaNames   乡镇区划编码数组
     * @param vendorNames 企业编码数组
     * @param stationType (0-提升井 1-处理站)
     * @param startDate   开始时间
     * @param endDate     结束时间
     * @param status      工单状态
     */
    private String startDate;
    private String endDate;
    private List<String> areaNames;
    private List<String> vendorNames;
    private String stationType;
    private String status;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<String> getAreaNames() {
        return areaNames;
    }

    public void setAreaNames(List<String> areaNames) {
        this.areaNames = areaNames;
    }

    public List<String> getVendorNames() {
        return vendorNames;
    }

    public void setVendorNames(List<String> vendorNames) {
        this.vendorNames = vendorNames;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SelectParamModel{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", areaNames=" + areaNames +
                ", vendorNames=" + vendorNames +
                ", stationType='" + stationType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
