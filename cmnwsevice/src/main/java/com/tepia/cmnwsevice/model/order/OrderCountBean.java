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

    private int toExamine;
    private int toSend;
    private int onExecute;
    private int toExecute;
    private int done;
    private int toBack;

    public int getToExamine() {
        return toExamine;
    }

    public void setToExamine(int toExamine) {
        this.toExamine = toExamine;
    }

    public int getToSend() {
        return toSend;
    }

    public void setToSend(int toSend) {
        this.toSend = toSend;
    }

    public int getOnExecute() {
        return onExecute;
    }

    public void setOnExecute(int onExecute) {
        this.onExecute = onExecute;
    }

    public int getToExecute() {
        return toExecute;
    }

    public void setToExecute(int toExecute) {
        this.toExecute = toExecute;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getToBack() {
        return toBack;
    }

    public void setToBack(int toBack) {
        this.toBack = toBack;
    }
}
