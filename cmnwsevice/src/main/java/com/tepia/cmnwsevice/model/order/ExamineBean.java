package com.tepia.cmnwsevice.model.order;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       21:36
 * Version         :       1.0
 * 功能描述        :       审核记录实体
 **/
public class ExamineBean {


    /**
     * id : 1115233050189185025
     * workOrderId : 1114816067512950786
     * executeRoleId : null
     * executeUserId : 29
     * stepIndex : 2
     * stepName : 审核工单
     * executeResult : 0
     * executeResultDes : 通过
     * limitHours : null
     * startTime : 2019-04-08 20:42:34
     * endTime : 2019-04-08 20:42:34
     * createdTime : 2019-04-08 20:40:55
     * executeUserName : null
     * executeUserPhone : null
     */

    private Long id;
    private Long workOrderId;
    private Long executeRoleId;
    private Long executeUserId;
    private Long stepIndex;
    private String stepName;
    private Long executeResult;
    private String executeResultDes;
    private String limitHours;
    private String startTime;
    private String endTime;
    private String createdTime;
    private String executeUserName;
    private String executeUserPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Long getExecuteRoleId() {
        return executeRoleId;
    }

    public void setExecuteRoleId(Long executeRoleId) {
        this.executeRoleId = executeRoleId;
    }

    public Long getExecuteUserId() {
        return executeUserId;
    }

    public void setExecuteUserId(Long executeUserId) {
        this.executeUserId = executeUserId;
    }

    public Long getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(Long stepIndex) {
        this.stepIndex = stepIndex;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Long getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(Long executeResult) {
        this.executeResult = executeResult;
    }

    public String getExecuteResultDes() {
        return executeResultDes;
    }

    public void setExecuteResultDes(String executeResultDes) {
        this.executeResultDes = executeResultDes;
    }

    public String getLimitHours() {
        return limitHours;
    }

    public void setLimitHours(String limitHours) {
        this.limitHours = limitHours;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public String getExecuteUserPhone() {
        return executeUserPhone;
    }

    public void setExecuteUserPhone(String executeUserPhone) {
        this.executeUserPhone = executeUserPhone;
    }
}
