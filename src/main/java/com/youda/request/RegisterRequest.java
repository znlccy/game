package com.youda.request;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class RegisterRequest extends BaseRequest {
    private String userName;
    private String userPassword;
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

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
                || userPassword == null || userPassword.isEmpty()
                || gameChannelId == null || gameChannelId == 0
                || verificationCode == null || verificationCode.isEmpty();
    }
}
