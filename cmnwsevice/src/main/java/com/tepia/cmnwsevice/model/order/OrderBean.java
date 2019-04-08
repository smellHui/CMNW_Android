package com.tepia.cmnwsevice.model.order;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       17:00
 * Version         :       1.0
 * 功能描述        :        工单实体  ps 现在只是列表数据 可以根据详细数据添加
 **/
public class OrderBean {

    /**
     * areaName : 向化镇
     * executeStatus : 0
     * createdTime : 2019-04-07 17:04:20
     * id : 1114816161280811009
     * source : 3
     * orderName : 向化示范处理站
     */

    private String areaName;
    private int executeStatus;
    private String createdTime;
    private String id;
    private int source;
    private String orderName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(int executeStatus) {
        this.executeStatus = executeStatus;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
