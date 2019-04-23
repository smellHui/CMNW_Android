package com.tepia.cmnwsevice.model.station;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/23
 * Time            :       16:01
 * Version         :       1.0
 * 功能描述        :
 **/
public class VenderBean extends DataSupport {

    /**
     * name : 上海电气
     * code : SHDQ
     */

    private String name;
    @Column(unique = true)
    private String code;

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
}
