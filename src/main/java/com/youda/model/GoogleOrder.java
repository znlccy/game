package com.youda.model;

public class GoogleOrder {


    /**
     * orderId : GPA.3375-5372-3056-73843
     * packageName : com.ycplay.tankmania
     * productId : com.tankmania.coin.1
     * purchaseTime : 1524404639700
     * purchaseState : 0
     * purchaseToken : ofdepmjiaoajjejnmeliaijk.AO-J1OwjdVCFur_fGsZTFGpl4f0LZy74ZIURp1VnFkcT3tPRmE3rXFQEqWw2uvEjtxXhEyXvC6l0AQtb9L-zOkqu8KCS5Xo9k2TAmxcPUy-vAeFlowylmaBwU3f4N-JaRRpq_1ww3xCq
     */

    private String orderId;
    private String packageName;
    private String productId;
    private long purchaseTime;
    private int purchaseState;
    private String purchaseToken;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public int getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(int purchaseState) {
        this.purchaseState = purchaseState;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public void setPurchaseToken(String purchaseToken) {
        this.purchaseToken = purchaseToken;
    }
}
