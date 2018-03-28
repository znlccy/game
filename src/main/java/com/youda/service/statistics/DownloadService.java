package com.youda.service.statistics;

import com.youda.model.statistics.Download;
import com.youda.response.statistics.Response;

/**
 * @CreateTime:2018/3/26 11:37
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 下载服务接口
 */
public interface DownloadService {
    
    /**
     * @comment: addDownload 添加下载记录
     * @param: [download]
     * @return: void
     */
    void addDownload(Download download);

    /**
     * @comment: retainAccess 获取访问量和下载量
     * @param: []
     * @return: com.ycplay.download.response.Response
     */
    Response retainAccess(String beginTime, String endTime);

}
