package com.tepia.cmdbsevice.model.event;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.common.base.Strings;
import com.tepia.cmnwsevice.model.dict.DictManager;
import com.tepia.cmnwsevice.utils.StringUtil;

import java.util.Map;

/**
 * Author:xch
 * Date:2019/4/22
 * Description:实时督办报警、故障统计列表
 */
public class WarnBean extends AbstractExpandableItem implements MultiItemEntity {

    public static final int ITEM_NORMAL = -1;
    public static final int ITEM_HISTORY = ITEM_NORMAL << 1;

    //事件id
    private String eventId;
    //站点名称
    private String stnm;
    //运维企业
    private String vendorName;
    //报警类型（设备、水质、通讯
    private String alarmType;
    //报警时间
    private String sendTime;
    //报警状态（数据字典获取 label ：warningStatus） 状态（1-已派单 3-已督办 4-未反馈 5-已完结）
    private int status;
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
    //站点当前状态(0-正常；1-异常；2-报警；3-故障)
    private int stationStatus;
    //行政区划
    private String areaName;
    //地址
    private String addr;
    //督办时间
    private String superviseTime;
    //督办单号
    private String orderCode;
    //故障时间
    private String faultTime;
    //异常设备名称
    private String deviceName;

    private int itemType = ITEM_NORMAL;

    public WarnBean() {
//        addSubItem(new ReportDetailBean(1));
    }

    public WarnBean(int itemType) {
        this.itemType = itemType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFaultTime() {
        return Strings.nullToEmpty(faultTime);
    }

    public void setFaultTime(String faultTime) {
        this.faultTime = faultTime;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getOrderCode() {
        return Strings.nullToEmpty(orderCode);
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddr() {
        return Strings.nullToEmpty(addr);
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSuperviseTime() {
        return Strings.nullToEmpty(superviseTime);
    }

    public void setSuperviseTime(String superviseTime) {
        this.superviseTime = superviseTime;
    }

    public String getAreaName() {
        return Strings.nullToEmpty(areaName);
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getStationStatus() {
        return stationStatus;
    }

    public String getStationInfo() {
        switch (stationStatus) {
            case 0:
                return "正常状态";
            case 1:
                return "异常状态";
            case 2:
                return "报警状态";
            case 3:
                return "故障状态";
        }
        return "正常状态";
    }

    public void setStationStatus(int stationStatus) {
        this.stationStatus = stationStatus;
    }

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

    /**
     * 故障status
     *
     * @return
     */
    public String getFaultStatus() {
        switch (status) {
            case 1:
                return "已派单";
            case 3:
                return "已督办";
            case 4:
                return "待反馈";
            case 5:
                return "已完结";
        }
        return "已派单";
    }

    /**
     * 群众上报status
     *
     * @return
     */
    public String getReportStatus() {
        switch (status) {
            case 1:
                return "待反馈";
            case 2:
                return "待审核";
            case 3:
                return "退回中";
            case 4:
                return "已完结";
        }
        return "待反馈";
    }

    public int getIntStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSurplusHours() {
        return surplusHours;
    }

    public void setSurplusHours(String surplusHours) {
        this.surplusHours = surplusHours;
    }

    public String getOverHours() {
        return overHours;
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
                "eventId='" + eventId + '\'' +
                ", stnm='" + stnm + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", status=" + status +
                ", surplusHours='" + surplusHours + '\'' +
                ", overHours='" + overHours + '\'' +
                ", startTime='" + startTime + '\'' +
                ", sttp='" + sttp + '\'' +
                ", stcd='" + stcd + '\'' +
                ", stationStatus=" + stationStatus +
                ", areaName='" + areaName + '\'' +
                ", addr='" + addr + '\'' +
                ", superviseTime='" + superviseTime + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", faultTime='" + faultTime + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", itemType=" + itemType +
                '}';
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @Override
    public int getLevel() {
        return 0;
    }

}
