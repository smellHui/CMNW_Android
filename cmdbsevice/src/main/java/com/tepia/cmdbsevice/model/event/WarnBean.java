package com.tepia.cmdbsevice.model.event;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.utils.StringUtil;

/**
 * Author:xch
 * Date:2019/4/22
 * Description:实时督办报警、故障统计列表
 */
public class WarnBean {

    private String dispatchTime;
    private String alarmType;
    private String stnm;
    private String vendorName;
    private String handleMinute;
    private String status;


    public String getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(String dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getAlarmType() {
        return Strings.nullToEmpty(alarmType);
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getStnm() {
        return Strings.nullToEmpty(stnm);
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getVendorName() {
        return Strings.nullToEmpty(vendorName);
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getHandleMinute() {
        return StringUtil.nullToDefault(handleMinute, "0");
    }

    public void setHandleMinute(String handleMinute) {
        this.handleMinute = handleMinute;
    }

    public String getStatus() {
        return Strings.nullToEmpty(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WarnBean{" +
                "dispatchTime='" + dispatchTime + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", stnm='" + stnm + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", handleMinute='" + handleMinute + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
