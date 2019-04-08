package com.tepia.base.model;

import com.tepia.base.http.BaseResponse;

import java.util.List;

/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public class PageBean<T> extends BaseResponse {

    private int pageIndex;
    private int pageSize;
    private int totals;
    private List<T> data;
    private int total;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }

    public List<T> getResult() {
        return data;
    }

    public void setResult(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", totals=" + totals +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
