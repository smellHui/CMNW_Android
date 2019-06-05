package com.tepia.cmdbsevice.view.alarmstatistics.model;

import com.tepia.cmdbsevice.model.event.WarnBean;
import com.tepia.cmdbsevice.model.event.WarnDetailBean;

/**
 * Author:xch
 * Date:2019/6/4
 * Description:故障详情
 */
public class FaultInfoModel {

    private WarnBean top;
    private WarnDetailBean info;

    public WarnBean getTop() {
        return top;
    }

    public void setTop(WarnBean top) {
        this.top = top;
    }

    public WarnDetailBean getInfo() {
        return info;
    }

    public void setInfo(WarnDetailBean info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "FaultInfoModel{" +
                "top=" + top +
                ", info=" + info +
                '}';
    }
}
