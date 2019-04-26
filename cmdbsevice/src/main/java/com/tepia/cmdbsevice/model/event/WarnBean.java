package com.tepia.cmdbsevice.model.event;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.utils.StringUtil;

import java.util.Map;

/**
 * Author:xch
 * Date:2019/4/22
 * Description:实时督办报警、故障统计列表
 */
public class WarnBean {

    //站点名称
    private String stnm;
    //运维企业
    private String vendorName;
    //报警类型（设备、水质、通讯
    private String alarmType;
    //报警状态（数据字典获取 label ：warningStatus）
    private String status;
    //剩余时长
    private String surplusHours;
    //超出时长
    private String overHours;
    //开始时间
    private String startTime;
    //站点类型（1-处理站 0-提升井）
    private String sttp;
    //站点编码
    private String stcd;

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getStatus() {
        Map<String, String> sourceMap = DictManager.getInstance().getDictMap().get("warningStatus");
        if (sourceMap == null) return "";
        return sourceMap.get(status + "");
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSurplusHours() {
        return StringUtil.nullToDefault(surplusHours, "0");
    }

    public void setSurplusHours(String surplusHours) {
        this.surplusHours = surplusHours;
    }

    public String getOverHours() {
        return StringUtil.nullToDefault(overHours, "0");
    }

    public void setOverHours(String overHours) {
        this.overHours = overHours;
    }

    public String getStartTime() {
        return StringUtil.nullToDefault(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSttp() {
        if (!Strings.isNullOrEmpty(sttp)) {
            if (sttp.equals("0"))
                return "提升井";
            if (sttp.equals("1"))
                return "处理站";
        }
        return "提升井";
    }

    public void setSttp(String sttp) {
        this.sttp = sttp;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    @Override
    public String toString() {
        return "WarnBean{" +
                "stnm='" + stnm + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", status='" + status + '\'' +
                ", surplusHours='" + surplusHours + '\'' +
                ", overHours='" + overHours + '\'' +
                ", startTime='" + startTime + '\'' +
                ", sttp='" + sttp + '\'' +
                ", stcd='" + stcd + '\'' +
                '}';
    }
}
