package com.tepia.cmnwsevice.model;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2018-12-17
 * Time            :       16:46
 * Version         :       1.0
 * 功能描述        :
 **/
public class RiverBean {


    /**
     * id : 2059
     * name : 巢湖农场
     * farmerType : 农场主
     * flag : 0
     * isDefault : 1
     */

    private int id;
    private String name;
    private String farmerType;
    private String flag;
    private String isDefault;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFarmerType() {
        return farmerType;
    }

    public void setFarmerType(String farmerType) {
        this.farmerType = farmerType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
