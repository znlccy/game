package com.youda.response.statistics;

/**
 * @CreateTime:2018/3/26 13:44
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 响应数据实体
 */
public class Response {

    /**
     * 声明访问数量
     */
    public String accessCount;

    /**
     * 声明下载数量
     */
    public String downloadCount;

    /**
     * @comment: getAccessCount 实现获得访问数量
     * @param: []
     * @return: java.lang.String
     */
    public String getAccessCount() {
        return accessCount;
    }

    /**
     * @comment: setAccessCount 实现设置访问数量
     * @param: [accessCount]
     * @return: void
     */
    public void setAccessCount(String accessCount) {
        this.accessCount = accessCount;
    }

    /**
     * @comment: getDownloadCount 实现获取下载数量
     * @param: []
     * @return: java.lang.String
     */
    public String getDownloadCount() {
        return downloadCount;
    }

    /**
     * @comment: setDownloadCount 实现设置下载数量
     * @param: [downloadCount]
     * @return: void
     */
    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }
}
