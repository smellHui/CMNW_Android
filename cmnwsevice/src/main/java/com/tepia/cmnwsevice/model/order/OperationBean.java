package com.tepia.cmnwsevice.model.order;

import com.tepia.cmnwsevice.utils.StringUtil;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       19:54
 * Version         :       1.0
 * 功能描述        :        运维记录实体
 **/
public class OperationBean {

    /**
     * id : 1114816161280811009
     * executeId : null
     * executeUserName : admin
     * executeUserPhone : 13455533233
     * executeStartTime : null
     * executeEndTime : null
     * delayHours : null
     * handleHours : null
     * overHours : null
     * problemType : null
     * repairType : null
     * partsUse : null
     * handleDes : null
     * sendTime : null
     * limitHours : null
     * fileList : []
     */

    private Long id;
    private Long executeId;
    private String executeUserName;
    private String executeUserPhone;
    private String executeStartTime;
    private String executeEndTime;
    private String delayHours;
    private String handleHours;
    private String overHours;
    private String problemType;
    private String repairType;
    private String partsUse;
    private String handleDes;
    private String sendTime;
    private String limitHours;
    private List<FileBean> fileList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExecuteId() {
        return executeId;
    }

    public void setExecuteId(Long executeId) {
        this.executeId = executeId;
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public String getExecuteUserPhone() {
        return StringUtil.nullToDefault(executeUserPhone);
    }

    public void setExecuteUserPhone(String executeUserPhone) {
        this.executeUserPhone = executeUserPhone;
    }

    public String getExecuteStartTime() {
        return StringUtil.nullToDefault(executeStartTime);
    }

    public void setExecuteStartTime(String executeStartTime) {
        this.executeStartTime = executeStartTime;
    }

    public String getExecuteEndTime() {
        return executeEndTime;
    }

    public void setExecuteEndTime(String executeEndTime) {
        this.executeEndTime = executeEndTime;
    }

    public String getDelayHours() {
        return delayHours;
    }

    public void setDelayHours(String delayHours) {
        this.delayHours = delayHours;
    }

    public String getHandleHours() {
        return handleHours;
    }

    public void setHandleHours(String handleHours) {
        this.handleHours = handleHours;
    }

    public String getOverHours() {
        return overHours;
    }

    public void setOverHours(String overHours) {
        this.overHours = overHours;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getPartsUse() {
        return partsUse;
    }

    public void setPartsUse(String partsUse) {
        this.partsUse = partsUse;
    }

    public String getHandleDes() {
        return handleDes;
    }

    public void setHandleDes(String handleDes) {
        this.handleDes = handleDes;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getLimitHours() {
        return limitHours;
    }

    public void setLimitHours(String limitHours) {
        this.limitHours = limitHours;
    }

    public List<FileBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileBean> fileList) {
        this.fileList = fileList;
    }
}
