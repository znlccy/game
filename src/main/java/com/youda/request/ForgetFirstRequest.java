package com.youda.request;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class ForgetFirstRequest implements BaseRequest {
    private String userName;
    private String verificationCode;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public boolean isEmpty() {
        return userName == null || userName.isEmpty()
                || verificationCode == null || verificationCode.isEmpty();
    }
}
