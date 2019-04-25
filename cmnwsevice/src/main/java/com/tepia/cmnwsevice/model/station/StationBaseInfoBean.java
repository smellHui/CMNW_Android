package com.tepia.cmnwsevice.model.station;

import java.util.List;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/24
 * Time            :       16:42
 * Version         :       1.0
 * 功能描述        :
 **/
public class StationBaseInfoBean {

    /**
     * id : 354
     * code : HS20190401161320269668
     * name : 东平镇11号处理站
     * address : 前柴路海燕新村东区南
     * administrativeDivision : 310230122
     * lgtd : 121.597476
     * lttd : 31.646558
     * powerSupplier : null
     * vendorCode : null
     * deviceNum : 1
     * drainageCapacity : 60
     * areaCovered : null
     * serviceHouseholds : null
     * serviceRange : null
     * productionDate : null
     * completionDate : null
     * startRunningDate : null
     * imei : null
     * stationModuleCode : null
     * status : null
     * createdBy : null
     * createdTime : null
     * updatedBy : null
     * updatedTime : null
     * administrativeDivisionName : 东平镇
     * vendorName : 上海电气
     * orderCount : null
     * referenceFileList : []
     * supportList : [{"id":null,"stationCode":null,"stationType":null,"enterpriseCode":"SHDQ","enterpriseName":"上海电气","contactName":"运维人员","contactPhone":"15878485478","status":null,"createdBy":null,"createdTime":null,"updatedBy":null,"updatedTime":null}]
     */

    private int id;
    private String code;
    private String name;
    private String address;
    private String administrativeDivision;
    private double lgtd;
    private double lttd;
    private Object powerSupplier;
    private Object vendorCode;
    private int deviceNum;
    private int drainageCapacity;
    private Object areaCovered;
    private Object serviceHouseholds;
    private Object serviceRange;
    private Object productionDate;
    private Object completionDate;
    private Object startRunningDate;
    private Object imei;
    private Object stationModuleCode;
    private Object status;
    private Object createdBy;
    private Object createdTime;
    private Object updatedBy;
    private Object updatedTime;
    private String administrativeDivisionName;
    private String vendorName;
    private Object orderCount;
    private List<PictureBean> referenceFileList;
    private List<SupportListBean> supportList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public double getLgtd() {
        return lgtd;
    }

    public void setLgtd(double lgtd) {
        this.lgtd = lgtd;
    }

    public double getLttd() {
        return lttd;
    }

    public void setLttd(double lttd) {
        this.lttd = lttd;
    }

    public Object getPowerSupplier() {
        return powerSupplier;
    }

    public void setPowerSupplier(Object powerSupplier) {
        this.powerSupplier = powerSupplier;
    }

    public Object getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(Object vendorCode) {
        this.vendorCode = vendorCode;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }

    public int getDrainageCapacity() {
        return drainageCapacity;
    }

    public void setDrainageCapacity(int drainageCapacity) {
        this.drainageCapacity = drainageCapacity;
    }

    public Object getAreaCovered() {
        return areaCovered;
    }

    public void setAreaCovered(Object areaCovered) {
        this.areaCovered = areaCovered;
    }

    public Object getServiceHouseholds() {
        return serviceHouseholds;
    }

    public void setServiceHouseholds(Object serviceHouseholds) {
        this.serviceHouseholds = serviceHouseholds;
    }

    public Object getServiceRange() {
        return serviceRange;
    }

    public void setServiceRange(Object serviceRange) {
        this.serviceRange = serviceRange;
    }

    public Object getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Object productionDate) {
        this.productionDate = productionDate;
    }

    public Object getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Object completionDate) {
        this.completionDate = completionDate;
    }

    public Object getStartRunningDate() {
        return startRunningDate;
    }

    public void setStartRunningDate(Object startRunningDate) {
        this.startRunningDate = startRunningDate;
    }

    public Object getImei() {
        return imei;
    }

    public void setImei(Object imei) {
        this.imei = imei;
    }

    public Object getStationModuleCode() {
        return stationModuleCode;
    }

    public void setStationModuleCode(Object stationModuleCode) {
        this.stationModuleCode = stationModuleCode;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Object updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getAdministrativeDivisionName() {
        return administrativeDivisionName;
    }

    public void setAdministrativeDivisionName(String administrativeDivisionName) {
        this.administrativeDivisionName = administrativeDivisionName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public Object getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Object orderCount) {
        this.orderCount = orderCount;
    }

    public List<PictureBean> getReferenceFileList() {
        return referenceFileList;
    }

    public void setReferenceFileList(List<PictureBean> referenceFileList) {
        this.referenceFileList = referenceFileList;
    }

    public List<SupportListBean> getSupportList() {
        return supportList;
    }

    public void setSupportList(List<SupportListBean> supportList) {
        this.supportList = supportList;
    }

    public static class SupportListBean {
        /**
         * id : null
         * stationCode : null
         * stationType : null
         * enterpriseCode : SHDQ
         * enterpriseName : 上海电气
         * contactName : 运维人员
         * contactPhone : 15878485478
         * status : null
         * createdBy : null
         * createdTime : null
         * updatedBy : null
         * updatedTime : null
         */

        private Object id;
        private Object stationCode;
        private Object stationType;
        private String enterpriseCode;
        private String enterpriseName;
        private String contactName;
        private String contactPhone;
        private Object status;
        private Object createdBy;
        private Object createdTime;
        private Object updatedBy;
        private Object updatedTime;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getStationCode() {
            return stationCode;
        }

        public void setStationCode(Object stationCode) {
            this.stationCode = stationCode;
        }

        public Object getStationType() {
            return stationType;
        }

        public void setStationType(Object stationType) {
            this.stationType = stationType;
        }

        public String getEnterpriseCode() {
            return enterpriseCode;
        }

        public void setEnterpriseCode(String enterpriseCode) {
            this.enterpriseCode = enterpriseCode;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(Object createdTime) {
            this.createdTime = createdTime;
        }

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Object getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(Object updatedTime) {
            this.updatedTime = updatedTime;
        }
    }
}
