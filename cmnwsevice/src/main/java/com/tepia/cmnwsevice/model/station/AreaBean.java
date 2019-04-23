package com.tepia.cmnwsevice.model.station;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/23
 * Time            :       16:02
 * Version         :       1.0
 * 功能描述        :
 **/
public class AreaBean extends DataSupport {

    /**
     * name : 城桥镇
     * code : 310230101
     * parentCode : 310230
     */

    private String name;
    @Column(unique = true)
    private String code;
    private String parentCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
