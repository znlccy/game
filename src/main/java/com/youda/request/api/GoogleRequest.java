package com.youda.request.api;

import com.youda.request.BaseRequest;

/**
 * Created by chenshengyu
 * on 2017/12/25.
 */
public class GoogleRequest extends BaseRequest {
    private String signedData;
    private String signature;

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public boolean isEmpty() {
        return signedData == null || signedData.isEmpty()
                || gameChannelId == null || gameChannelId == 0
                || signature == null || signature.isEmpty();
    }
}
