package com.tepia.cmnwsevice.model.order;

import java.io.Serializable;

/**
 * Author:xch
 * Date:2019/4/9
 * Do:执行工单参数
 */
public class OrderParamBean implements Serializable {

    private String id;
    private String executeRoute;
    private String handleDes;
    private String repairType;
    private String repairName;
    private String problemType;
    private String problemName;
    private String partsUse;
    private String partsUseName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecuteRoute() {
        return executeRoute;
    }

    public void setExecuteRoute(String executeRoute) {
        this.executeRoute = executeRoute;
    }

    public String getHandleDes() {
        return handleDes;
    }

    public void setHandleDes(String handleDes) {
        this.handleDes = handleDes;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getPartsUse() {
        return partsUse;
    }

    public void setPartsUse(String partsUse) {
        this.partsUse = partsUse;
    }

    public String getPartsUseName() {
        return partsUseName;
    }

    public void setPartsUseName(String partsUseName) {
        this.partsUseName = partsUseName;
    }

    @Override
    public String toString() {
        return "OrderParamBean{" +
                "id='" + id + '\'' +
                ", executeRoute='" + executeRoute + '\'' +
                ", handleDes='" + handleDes + '\'' +
                ", repairType='" + repairType + '\'' +
                ", repairName='" + repairName + '\'' +
                ", problemType='" + problemType + '\'' +
                ", problemName='" + problemName + '\'' +
                ", partsUse='" + partsUse + '\'' +
                ", partsUseName='" + partsUseName + '\'' +
                '}';
    }
}
