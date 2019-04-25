package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/17
 * Time            :       9:37
 * Version         :       1.0
 * 功能描述        :
 **/
public class StationTypeBean {
    private String name;
    private String code;
    private Integer background;
    private Integer src;
    private Integer color;
    private boolean isSelected;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StationTypeBean(String name, Integer background) {
        this.name = name;
        this.background = background;
    }
    public StationTypeBean(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public StationTypeBean(String name, Integer background, boolean isSelected) {
        this.name = name;
        this.background = background;
        this.isSelected = isSelected;
    }
    public StationTypeBean(String name,String code, Integer background, boolean isSelected) {
        this.name = name;
        this.code = code;
        this.background = background;
        this.isSelected = isSelected;
    }

    public StationTypeBean() {

    }

    public StationTypeBean(String name) {
        this.name = name;
    }
    public StationTypeBean(String name,boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }
    public StationTypeBean(String name,String code,boolean isSelected) {
        this.name = name;
        this.code = code;
        this.isSelected = isSelected;
    }

    public StationTypeBean(Integer background, Integer src) {
        this.background = background;
        this.src = src;
    }


    public StationTypeBean(String name, Integer background, Integer src) {
        this.name = name;
        this.background = background;
        this.src = src;
    }

    public StationTypeBean(String name, Integer background, Integer src, boolean isSelected) {
        this.name = name;
        this.background = background;
        this.src = src;
        this.isSelected = isSelected;
    }
    public StationTypeBean(String name,String code, Integer background, Integer src, boolean isSelected) {
        this.name = name;
        this.code = code;
        this.background = background;
        this.src = src;
        this.isSelected = isSelected;
    }

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
