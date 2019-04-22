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
    private Integer background;
    private Integer src;
    private Integer color;
    private boolean isSelected;

    public StationTypeBean(String name, Integer color) {
        this.name = name;
        this.color = color;
    }
    public StationTypeBean() {

    }

    public StationTypeBean(String name) {
        this.name = name;
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
