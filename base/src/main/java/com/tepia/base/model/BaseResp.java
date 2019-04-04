package com.tepia.base.model;

import com.tepia.base.http.BaseResponse;

/**
 * Author:xch
 * Date:2019/4/4
 * Do:
 */
public class BaseResp<T> extends BaseResponse {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
