package com.youda.request;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class ForgetSecondRequest extends BaseRequest {
    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean isEmpty() {
        return userName == null || userName.isEmpty()
                || gameChannelId == null|| gameChannelId ==0
                || userPassword == null || userPassword.isEmpty();
    }
}
