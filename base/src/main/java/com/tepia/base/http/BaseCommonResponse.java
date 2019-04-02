package com.tepia.base.http;

import com.google.gson.annotations.SerializedName;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-12-14
 * Time            :       11:17
 * Version         :       1.0
 * 功能描述        :       通用接口数据接收实体
 **/
public class BaseCommonResponse<T> extends BaseResponse {
    @SerializedName(value = "data", alternate = {"result", "datalist"})
    private T data;
    private  Integer total;
    private  Integer pageNum;
    private  Integer pageSize;
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
