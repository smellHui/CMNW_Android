package com.tepia.cmnwsevice.model.station;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/25
 * Time            :       14:45
 * Version         :       1.0
 * 功能描述        :
 **/
public class HisDataBean {
    private List<WarningBean> warningList;
    private int sum;
    private int warningCount;

    public List<WarningBean> getWarningList() {
        return warningList;
    }

    public void setWarningList(List<WarningBean> warningList) {
        this.warningList = warningList;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }
}
