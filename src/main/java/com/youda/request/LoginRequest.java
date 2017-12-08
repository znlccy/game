package com.youda.request;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class LoginRequest implements BaseRequest {
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
                || userPassword == null || userPassword.isEmpty();
    }
}
