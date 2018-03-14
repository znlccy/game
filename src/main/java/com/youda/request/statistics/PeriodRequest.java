package com.youda.request.statistics;

import com.youda.request.BaseRequest;

public class PeriodRequest extends BaseRequest{

    /**
     * 声明统计日期
     */
    private String statisticsDate;

    /**
     * 声明统计游戏渠道
     */
    private Long gameChannelId;

    /**
     * @comment: getStatisticsDate 实现获得统计日期
     * @param: []
     * @return: java.lang.String
     */
    public String getStatisticsDate() {
        return statisticsDate;
    }

    /**
     * @comment: setStatisticsDate 实现设置统计日期
     * @param: [statisticsDate]
     * @return: void
     */
    public void setStatisticsDate(String statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    /**
     * @comment: getGameChannelId 实现获得游戏渠道
     * @param: []
     * @return: java.lang.Long
     */
    public Long getGameChannelId() {
        return gameChannelId;
    }

    /**
     * @comment: setGameChannelId 实现设置游戏渠道
     * @param: [gameChannelId]
     * @return: void
     */
    public void setGameChannelId(Long gameChannelId) {
        this.gameChannelId = gameChannelId;
    }

    /**
     * @comment: isEmpty 实现判断是否为空
     * @param: []
     * @return: boolean
     */
    @Override
    public boolean isEmpty() {
        return statisticsDate==null || statisticsDate.isEmpty()||
                gameChannelId==null || gameChannelId==0;
    }
}
