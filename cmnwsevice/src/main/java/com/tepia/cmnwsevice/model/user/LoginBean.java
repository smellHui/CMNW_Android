package com.tepia.cmnwsevice.model.user;

/**
 * Created by      android studio
 *
 * @author :      zhang xinhua
 * Date            :       2019/4/8
 * Time            :       10:27
 * Version         :       1.0
 * 功能描述        :        登录返回的实体
 **/
public class LoginBean {

    /**
     * expireTime : 20190408122539
     * user : {"userId":1,"nickName":"admin","username":"admin","password":"it's a secret","deptId":1,"email":"mrbird123@hotmail.com","mobile":"13455533233","status":"1","createTime":"2019-04-04 21:08:11","modifyTime":"2019-01-17 02:34:19","lastLoginTime":"2019-01-28 01:53:58","sex":"2","description":"我是mrbird","avatar":"ubnKSIfAJTxIgXOKlciN.png","roleId":null,"areaCode":"310230000","roles":null,"roleIds":null,"permissions":null}
     * token : b25e39b47e774b4a05b3cb1555fc377f209457c3fd339d373d3fca7b1ea8be56fdc6ed05b7ffb0700e7300d242fb83b518aaccee9cd9debeb6d2b536c35b2d90c2f2c5bb1aee06519295ffe708edd8a98a467157cb0955df01e8b37965b62610d815c781b74c2441052c759bc8e6f2ec4a47a970396a9b1753143bb4106c2551ff42480e995a9ce7
     */

    private String expireTime;
    private UserBean user;
    private String token;

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
