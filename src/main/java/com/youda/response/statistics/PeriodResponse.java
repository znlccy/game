package com.youda.response.statistics;

/**
 * @CreateTime:2018/3/14 15:04
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 24小时时间统计
 */
public class PeriodResponse {

    /**
     * 声明统计区间
     */
    private String period;

    /**
     * 声明统计数量
     */
    private String amount;

    /**
     * @comment: getPeriod 实现获得统计区间
     * @param: []
     * @return: java.lang.String
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @comment: setPeriod 实现设置统计区间
     * @param: [period]
     * @return: void
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @comment: getAmount 实现获得统计数量
     * @param: []
     * @return: java.lang.String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @comment: setAmount 实现设置统计数量
     * @param: [amount]
     * @return: void
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
