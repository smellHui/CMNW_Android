package com.tepia.cmnwsevice.model.user;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       10:31
 * Version         :       1.0
 * 功能描述        :        用户信息实体
 **/
public  class UserBean {
    /**
     * userId : 1   用户ID
     * nickName : admin 昵称
     * username : admin 用户名
     * password : it's a secret   密码
     * deptId : 1       部门ID
     * email : mrbird123@hotmail.com    邮箱
     * mobile : 13455533233 手机号
     * status : 1   状态 (0=锁定,1=有效)
     * createTime : 2019-04-04 21:08:11 创建时间
     * modifyTime : 2019-01-17 02:34:19 修改时间
     * lastLoginTime : 2019-01-28 01:53:58  最后一次登录时间
     * sex : 2  性别 (0=男,1=女,2=保密)
     * description : 我是mrbird   描述
     * avatar : ubnKSIfAJTxIgXOKlciN.png    头像地址
     * roleId : null    角色ID
     * areaCode : 310230000 行政区域code
     * roles : null 角色集合
     * roleIds : null
     * permissions : null   权限集合
     */

    private int userId;
    private String nickName;
    private String username;
    private String password;
    private int deptId;
    private String email;
    private String mobile;
    private String status;
    private String createTime;
    private String modifyTime;
    private String lastLoginTime;
    private String sex;
    private String description;
    private String avatar;
    private Object roleId;
    private String areaCode;
    private Object roles;
    private Object roleIds;
    private Object permissions;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getRoleId() {
        return roleId;
    }

    public void setRoleId(Object roleId) {
        this.roleId = roleId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Object getRoles() {
        return roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }

    public Object getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Object roleIds) {
        this.roleIds = roleIds;
    }

    public Object getPermissions() {
        return permissions;
    }

    public void setPermissions(Object permissions) {
        this.permissions = permissions;
    }
}
