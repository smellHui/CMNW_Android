package com.tepia.cmdbsevice.model.event;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.common.base.Strings;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/24
 * Description:
 */
public class WarnDetailBean extends AbstractExpandableItem implements MultiItemEntity, Cloneable {

    private String recoverTime;
    private String stcd;
    private String handleDes;
    private String orderCode;
    private String faultTime;
    private String id;
    private int status;
    private String backTime;
    private String sendTime;
    private List<String> backImgUrls;

    public WarnDetailBean(int status) {
        this.status = status;
    }

    public String getSendTime() {
        return Strings.nullToEmpty(sendTime);
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(String faultTime) {
        this.faultTime = faultTime;
    }

    public String getRecoverTime() {
        return Strings.nullToEmpty(recoverTime);
    }

    public void setRecoverTime(String recoverTime) {
        this.recoverTime = recoverTime;
    }

    public String getStcd() {
        return Strings.nullToEmpty(stcd);
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getHandleDes() {
        return handleDes;
    }

    public void setHandleDes(String handleDes) {
        this.handleDes = handleDes;
    }

    public String getOrderCode() {
        return Strings.nullToEmpty(orderCode);
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public List<String> getBackImgUrls() {
        return backImgUrls;
    }

    public void setBackImgUrls(List<String> backImgUrls) {
        this.backImgUrls = backImgUrls;
    }

    @Override
    public String toString() {
        return "WarnDetailBean{" +
                "recoverTime='" + recoverTime + '\'' +
                ", stcd='" + stcd + '\'' +
                ", handleDes='" + handleDes + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", faultTime='" + faultTime + '\'' +
                ", id='" + id + '\'' +
                ", status=" + status +
                ", backTime='" + backTime + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", backImgUrls=" + backImgUrls +
                '}';
    }

    @Override
    public int getItemType() {
        return status;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public Object clone() {
        WarnDetailBean warn = null;
        try {
            warn = (WarnDetailBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return warn;
    }
}
