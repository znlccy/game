package com.youda.request.channel;

import com.youda.request.BaseRequest;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class LoginRequest extends BaseRequest {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEmpty() {
        return phone == null || phone.isEmpty()
                || password == null || password.isEmpty();
    }
}
