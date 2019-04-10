package com.tepia.cmnwsevice.model.order;

import com.google.common.base.Strings;
import com.tepia.cmnwsevice.utils.StringUtil;

/**
 * Author:xch
 * Date:2019/4/9
 * Do:派单操作详情
 */
public class WorkDetailBean {

    /**
     * limitHours : 24
     * userName : 王一
     * phone : null
     * sendTime : 2019-04-08 20:34:20
     */

    private String limitHours;
    private String userName;
    private String phone;
    private String sendTime;

    public String getLimitHours() {
        return StringUtil.nullToDefault(limitHours);
    }

    public void setLimitHours(String limitHours) {
        this.limitHours = limitHours;
    }

    public String getUserName() {
        return StringUtil.nullToDefault(userName);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return StringUtil.nullToDefault(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSendTime() {
        return StringUtil.nullToDefault(sendTime);
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "WorkDetailBean{" +
                "limitHours=" + limitHours +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }
}
