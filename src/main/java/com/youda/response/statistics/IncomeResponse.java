package com.youda.response.statistics;

import com.youda.response.BaseResponse;

/**
 * @Author Chencongye
 * @Date 2017/12/20 15:19
 * @Version 1.0.0
 * @Instructions 定义返回给客户端的收入的实体类
 */

public class IncomeResponse implements BaseResponse {

    /*定义返回日期*/
    private String ddate;

    /*定义返回收入金额*/
    private String incomeTotalMoney;

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

    /*实现收入金额的get方法*/
    public String getIncomeTotalMoney() {
        return incomeTotalMoney;
    }

    /*实现收入金额的set方法*/
    public void setIncomeTotalMoney(String incomeTotalMoney) {
        this.incomeTotalMoney = incomeTotalMoney;
    }
}
