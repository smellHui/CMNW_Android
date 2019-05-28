package com.tepia.cmdbsevice.view.alarmstatistics.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.common.base.Strings;

import java.util.List;

/**
 * Author:xch
 * Date:2019/5/27
 * Description:事件操作流水
 */
public class FlowModel implements MultiItemEntity {
    //步骤索引(1-已派单 2-已反馈 3-已审核 4-已完结)
    private int stepIndex;
    private String stepName;
    private int resultType;
    private String resultDes;
    private String createdTime;
    private List<String> feedImgUrls;

    public int getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }

    public String getStepName() {
        return Strings.nullToEmpty(stepName);
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getResultType() {
        return resultType;
    }

    public String getResult() {
        return resultType == 0 ? "通过" : "退回";
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public String getResultDes() {
        return Strings.nullToEmpty(resultDes);
    }

    public void setResultDes(String resultDes) {
        this.resultDes = resultDes;
    }

    public String getCreatedTime() {
        return Strings.nullToEmpty(createdTime);
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<String> getFeedImgUrls() {
        return feedImgUrls;
    }

    public void setFeedImgUrls(List<String> feedImgUrls) {
        this.feedImgUrls = feedImgUrls;
    }

    @Override
    public String toString() {
        return "FlowModel{" +
                "stepIndex=" + stepIndex +
                ", stepName='" + stepName + '\'' +
                ", resultType=" + resultType +
                ", resultDes='" + resultDes + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", feedImgUrls=" + feedImgUrls +
                '}';
    }

    @Override
    public int getItemType() {
        return stepIndex;
    }
}
