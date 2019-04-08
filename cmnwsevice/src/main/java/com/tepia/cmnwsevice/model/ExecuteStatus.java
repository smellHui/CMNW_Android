package com.tepia.cmnwsevice.model;

/**
 * Author:xch
 * Date:2019/4/8
 * Do:
 */
public enum ExecuteStatus {

    //0-待派单 1-待处理 2-执行中 3-待审核 4-已退回 5-已完结
    WAITING(0),
    PENDING(1),
    EXECUTING(2),
    WAITCONFIRM(3),
    RETURNED(4),
    COMPLETE(5);

    private int type;

    ExecuteStatus(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
