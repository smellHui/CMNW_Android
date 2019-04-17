package com.tepia.cmdbsevice.model.station;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/16
 * Time            :       16:18
 * Version         :       1.0
 * 功能描述        :
 **/
public class StationBean extends DataSupport {
    @Column(unique = true)
    private String code;
    private String address;
    private String name;
    private String lgtd;
    private String lttd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLgtd() {
        return lgtd;
    }

    public void setLgtd(String lgtd) {
        this.lgtd = lgtd;
    }

    public String getLttd() {
        return lttd;
    }

    public void setLttd(String lttd) {
        this.lttd = lttd;
    }
}
