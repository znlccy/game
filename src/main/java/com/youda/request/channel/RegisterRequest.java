package com.youda.request.channel;

import com.youda.request.BaseRequest;

/**
 * Created by chenshengyu
 * on 2017/12/8.
 */
public class RegisterRequest extends BaseRequest {
    private String phone;
    private String password;
    private String channelLabel;
    private String channelName;
    private String channelWebSite;

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

    public String getChannelLabel() {
        return channelLabel;
    }

    public void setChannelLabel(String channelLabel) {
        this.channelLabel = channelLabel;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelWebSite() {
        return channelWebSite;
    }

    public void setChannelWebSite(String channelWebSite) {
        this.channelWebSite = channelWebSite;
    }

    @Override
    public boolean isEmpty() {
        return phone == null || phone.isEmpty()
                || password == null || password.isEmpty()
                || channelName == null || channelName.isEmpty()
                || channelLabel == null || channelLabel.isEmpty();
    }
}
