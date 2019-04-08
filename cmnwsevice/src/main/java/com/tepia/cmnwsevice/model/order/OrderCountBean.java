package com.tepia.cmnwsevice.model.order;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       16:55
 * Version         :       1.0
 * 功能描述        :       不同状态 数量 实体
 **/
public class OrderCountBean {

    /**
     * toExamine : 1
     * toSend : 112
     * onExecute : 0
     * toExecute : 1
     * done : 0
     * toBack : 0
     */

    private String toExamine;
    private String toSend;
    private String onExecute;
    private String toExecute;
    private String done;
    private String toBack;

    public String getToExamine() {
        return toExamine;
    }

    public void setToExamine(String toExamine) {
        this.toExamine = toExamine;
    }

    public String getToSend() {
        return toSend;
    }

    public void setToSend(String toSend) {
        this.toSend = toSend;
    }

    public String getOnExecute() {
        return onExecute;
    }

    public void setOnExecute(String onExecute) {
        this.onExecute = onExecute;
    }

    public String getToExecute() {
        return toExecute;
    }

    public void setToExecute(String toExecute) {
        this.toExecute = toExecute;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getToBack() {
        return toBack;
    }

    public void setToBack(String toBack) {
        this.toBack = toBack;
    }

    @Override
    public String toString() {
        return "OrderCountBean{" +
                "toExamine='" + toExamine + '\'' +
                ", toSend='" + toSend + '\'' +
                ", onExecute='" + onExecute + '\'' +
                ", toExecute='" + toExecute + '\'' +
                ", done='" + done + '\'' +
                ", toBack='" + toBack + '\'' +
                '}';
    }
}
