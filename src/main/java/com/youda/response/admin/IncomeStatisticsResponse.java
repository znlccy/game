package com.youda.response.admin;

/**
 * @Author Chencongye
 * @Date 2017/12/20 15:19
 * @Version 1.0.0
 * @Instructions 定义返回给客户端的收入的实体类
 */

public class IncomeStatisticsResponse {

    /*定义返回日期*/
    private String ddate;

    /*定义返回收入金额*/
    private String incomeCount;

    /*实现返回日期的get方法*/
    public String getDdate() {
        return ddate;
    }

    /*实现返回日期的set方法*/
    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    /*实现返回收入的get方法*/
    public String getIncomeCount() {
        return incomeCount;
    }

    /*实现返回收入的set方法*/
    public void setIncomeCount(String incomeCount) {
        this.incomeCount = incomeCount;
    }

}
