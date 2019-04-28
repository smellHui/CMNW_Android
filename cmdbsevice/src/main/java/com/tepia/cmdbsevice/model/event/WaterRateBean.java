package com.tepia.cmdbsevice.model.event;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.utils.StringUtil;

/**
 * Author:xch
 * Date:2019/4/26
 * Description: 水质达标率
 */
public class WaterRateBean {

    private String sampleCount;
    private String code;
    private String rate;
    private String stationCount;
    private String name;
    private String targetCount;
    private String count;//站点总数

    public String getCount() {
        return StringUtil.nullToDefault(count, "0");
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSampleCount() {
        return StringUtil.nullToDefault(sampleCount, "0");
    }

    public void setSampleCount(String sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return StringUtil.nullToDefault(rate, "0");
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStationCount() {
        return StringUtil.nullToDefault(stationCount, "0");
    }

    public void setStationCount(String stationCount) {
        this.stationCount = stationCount;
    }

    public String getName() {
        return Strings.nullToEmpty(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetCount() {
        return StringUtil.nullToDefault(targetCount, "0");
    }

    public void setTargetCount(String targetCount) {
        this.targetCount = targetCount;
    }

    @Override
    public String toString() {
        return "WaterRateBean{" +
                "sampleCount='" + sampleCount + '\'' +
                ", code='" + code + '\'' +
                ", rate='" + rate + '\'' +
                ", stationCount='" + stationCount + '\'' +
                ", name='" + name + '\'' +
                ", targetCount='" + targetCount + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
