package com.youda.model;

public class Purchase {

    /**
     * kind : androidpublisher#productPurchase
     * purchaseTimeMillis : 1524404639700
     * purchaseState : 0
     * consumptionState : 1
     * developerPayload :
     * orderId : GPA.3375-5372-3056-73843
     * purchaseType : 0
     */

    private String kind;
    private String purchaseTimeMillis;
    private int purchaseState = -1;
    private int consumptionState;
    private String developerPayload;
    private String orderId;
    private int purchaseType;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPurchaseTimeMillis() {
        return purchaseTimeMillis;
    }

    public void setPurchaseTimeMillis(String purchaseTimeMillis) {
        this.purchaseTimeMillis = purchaseTimeMillis;
    }

    public int getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(int purchaseState) {
        this.purchaseState = purchaseState;
    }

    public int getConsumptionState() {
        return consumptionState;
    }

    public void setConsumptionState(int consumptionState) {
        this.consumptionState = consumptionState;
    }

    public String getDeveloperPayload() {
        return developerPayload;
    }

    public void setDeveloperPayload(String developerPayload) {
        this.developerPayload = developerPayload;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(int purchaseType) {
        this.purchaseType = purchaseType;
    }

    public boolean isPay() {
        return this.purchaseState == 0;
    }
}
