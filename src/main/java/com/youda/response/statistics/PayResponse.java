package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * @Author Chencongye
 * @Date 2017/12/15 11:34
 * @Version 1.0.0
 * @Instructions 实现返回给客户端的数据内容
 */

public class PayResponse implements BaseResponse {

    /*定义支付的用户总数*/
    private String payCount;

    /*定义返回的日期类型*/
    private String StatisticsDate;

    /*实现支付多少人的统计的get方法*/
    public String getPayCount() {
        return payCount;
    }

    /*实现支付多少人的统计的set方法*/
    public void setPayCount(String payCount) {
        this.payCount = payCount;
    }

    public String getStatisticsDate() {
        return StatisticsDate;
    }

    public void setStatisticsDate(String statisticsDate) {
        StatisticsDate = statisticsDate;
    }
}
