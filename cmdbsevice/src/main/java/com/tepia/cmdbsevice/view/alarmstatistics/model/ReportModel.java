package com.tepia.cmdbsevice.view.alarmstatistics.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.common.base.Strings;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/27
 * Description:群众上报model
 */
public class ReportModel extends AbstractExpandableItem implements MultiItemEntity, Cloneable {

    private String stnm;
    private String stcd;
    private int stationStatus;
    private String content;
    private List<String> imgUrls;
    private List<FlowModel> flowList;
    private int status;
    //事件id
    private String eventId;

    public ReportModel(int status, String eventId) {
        this.status = status;
        this.eventId = eventId;
    }

    public String getStcd() {
        return Strings.nullToEmpty(stcd);
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStnm() {
        return Strings.nullToEmpty(stnm);
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public int getStationStatus() {
        return stationStatus;
    }

    public String getStationStatusinfo(){
        switch (stationStatus){
            case 0:
                return "正常";
            case 1:
                return "异常";
            case 2:
                return "报警";
            case 3:
                return "故障";
        }
        return "正常";
    }

    public void setStationStatus(int stationStatus) {
        this.stationStatus = stationStatus;
    }

    public String getContent() {
        return Strings.nullToEmpty(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<FlowModel> getFlowList() {
        return flowList;
    }

    public void setFlowList(List<FlowModel> flowList) {
        this.flowList = flowList;
    }

    @Override
    public String toString() {
        return "ReportModel{" +
                "stnm='" + stnm + '\'' +
                ", stcd='" + stcd + '\'' +
                ", stationStatus=" + stationStatus +
                ", content='" + content + '\'' +
                ", imgUrls=" + imgUrls +
                ", flowList=" + flowList +
                ", status=" + status +
                ", eventId='" + eventId + '\'' +
                '}';
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return status;
    }

    @Override
    public Object clone() {
        ReportModel warn = null;
        try {
            warn = (ReportModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return warn;
    }
}
