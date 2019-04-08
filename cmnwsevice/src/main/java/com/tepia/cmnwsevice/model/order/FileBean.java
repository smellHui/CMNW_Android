package com.tepia.cmnwsevice.model.order;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       20:44
 * Version         :       1.0
 * 功能描述        :
 **/
public class FileBean {

    /**
     * src : http://cmnw.oss-cn-hangzhou.aliyuncs.com/order/timg.jpg
     * fileType : jpg
     * bizId : null
     * bizType : start
     */

    private String src;
    private String fileType;
    private Object bizId;
    private String bizType;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Object getBizId() {
        return bizId;
    }

    public void setBizId(Object bizId) {
        this.bizId = bizId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
}
