package com.tepia.cmdbsevice.model.event;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.utils.StringUtil;

/**
 * Author:xch
 * Date:2019/4/22
 * Description:报警、故障总数
 */
public class TopTotalBean {

    private String alarmNum;
    private String faultNum;
    private String reportNum;
    private String total;
    private String name;
    private String superviseNum;
    private String vendorName;
    private String code;
    private String rate;
    private float faultRate;

    public String getReportNum() {
        return StringUtil.nullToDefault(reportNum, "0");
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum;
    }

    public float getFaultRate() {
        return faultRate;
    }

    public void setFaultRate(float faultRate) {
        this.faultRate = faultRate;
    }

    public String getRate() {
        return StringUtil.nullToDefault(rate, "0");
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlarmNum() {
        return StringUtil.nullToDefault(alarmNum, "0");
    }

    public void setAlarmNum(String alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getFaultNum() {
        return StringUtil.nullToDefault(faultNum, "0");
    }

    public void setFaultNum(String faultNum) {
        this.faultNum = faultNum;
    }

    public String getTotal() {
        return StringUtil.nullToDefault(total, "0");
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getName() {
        return Strings.nullToEmpty(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperviseNum() {
        return superviseNum;
    }

    public void setSuperviseNum(String superviseNum) {
        this.superviseNum = superviseNum;
    }

    public String getVendorName() {
        return Strings.nullToEmpty(vendorName);
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public String toString() {
        return "TopTotalBean{" +
                "alarmNum='" + alarmNum + '\'' +
                ", faultNum='" + faultNum + '\'' +
                ", reportNum='" + reportNum + '\'' +
                ", total='" + total + '\'' +
                ", name='" + name + '\'' +
                ", superviseNum='" + superviseNum + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", code='" + code + '\'' +
                ", rate='" + rate + '\'' +
                ", faultRate=" + faultRate +
                '}';
    }
}
