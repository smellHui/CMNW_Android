package com.tepia.cmdbsevice.view.cmdbmain.onlinemonitor.stationbaseinfodetail;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/25
 * Time            :       10:02
 * Version         :       1.0
 * 功能描述        :
 **/
public class KeyValueBean {
    private String key;
    private String value;

    public KeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
