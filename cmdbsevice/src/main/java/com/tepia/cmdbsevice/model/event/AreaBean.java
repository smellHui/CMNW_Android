package com.tepia.cmdbsevice.model.event;

/**
 * Author:xch
 * Date:2019/4/23
 * Description:
 */
public class AreaBean {

    private String name;
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

    @Override
    public String toString() {
        return "AreaBean{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                '}';
    }
}
