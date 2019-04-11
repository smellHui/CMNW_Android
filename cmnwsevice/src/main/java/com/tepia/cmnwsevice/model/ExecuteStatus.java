package com.tepia.cmnwsevice.model;

import java.io.Serializable;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public enum ExecuteStatus implements Serializable {

    //0-待派单 1-待处理 2-执行中 3-待审核 4-已退回 5-已完结
    WAITING(0, "待派单"),
    PENDING(1, "待处理"),
    EXECUTING(2, "处理中"),
    WAITCONFIRM(3, "待审核"),
    RETURNED(4, "已退回"),
    COMPLETE(5, "已完结");

    private int type;
    private String name;

    ExecuteStatus(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
