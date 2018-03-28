package com.youda.request.statistics;
/**
 * @CreateTime:2018/3/26 14:28
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 请求实体类
 */
public class Request {

    /**
     * 声明开始时间
     */
    public String beginTime;

    /**
     * 声明结束时间
     */
    public String endTime;

    /**
     * @comment: getBeginTime 实现获得开始时间
     * @param: []
     * @return: java.lang.String
     */
    public String getBeginTime() {
        return beginTime;
    }

    /**
     * @comment: setBeginTime 实现设置开始时间
     * @param: [beginTime]
     * @return: void
     */
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @comment: getEndTime 实现获得结束时间
     * @param: []
     * @return: java.lang.String
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @comment: setEndTime 实现设置结束时间
     * @param: [endTime]
     * @return: void
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
