package com.tepia.base.http;

/**
 * @author        :       zhang xinhua
 * @Version       :       1.0
 * @创建人         ：      zhang xinhua
 * @创建时间       :       2019/3/18 17:03
 * @修改人         ：
 * @修改时间       :       2019/3/18 17:03
 * @功能描述       :        基础接口数据接收实体
 **/

public class BaseResponse {
    private int code;
    private int count;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
