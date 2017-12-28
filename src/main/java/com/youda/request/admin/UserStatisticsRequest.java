package com.youda.request.admin;

import com.youda.request.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author Chencongye
 * @Date 2017/12/26 18:26
 * @Version 1.0.0
 * @Instructions 实现新增用户统计
 */

public class UserStatisticsRequest extends BaseRequest {

    /*声明自定义日期的开始时间*/
    private String beginTime;

    /*声明自定义日期的结束时间*/
    private String endTime;

    /*声明要查询的游戏名称*/
    private String gameName;

    /*声明要查询什么平台*/
    private String userUseDevice;

    /*实现自定义开始日期的get方法*/
    @NotBlank(message = "开始时间不能为空")
    public String getBeginTime() {
        return beginTime;
    }

    /*实现自定义开始日期的set方法*/
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    /*实现自定义结束日期的get方法*/
    @NotBlank(message = "结束时间不能为空")
    public String getEndTime() {
        return endTime;
    }

    /*实现自定义结束日期的set方法*/
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /*实现游戏名称的get方法*/
    @NotBlank(message = "游戏名称不能为空")
    public String getGameName() {
        return gameName;
    }

    /*实现游戏名称的set方法*/
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /*实现平台的get方法*/
    @NotBlank(message = "平台不能为空")
    public String getUserUseDevice() {
        return userUseDevice;
    }

    /*实现平台的set方法*/
    public void setUserUseDevice(String userUseDevice) {
        this.userUseDevice = userUseDevice;
    }

    @Override
    public boolean isEmpty() {
        return beginTime==null || beginTime.isEmpty()||
                endTime==null || endTime.isEmpty() ||
                gameName==null || gameName.isEmpty() ||
            userUseDevice==null || userUseDevice.isEmpty();
    }
}
