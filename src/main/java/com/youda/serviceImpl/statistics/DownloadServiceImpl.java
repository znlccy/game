package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.DownloadMapper;
import com.youda.model.statistics.Download;
import com.youda.response.statistics.Response;
import com.youda.service.statistics.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @CreateTime:2018/3/26 11:37
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 下载服务实现
 */

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private DownloadMapper downloadMapper;

    @Override
    public void addDownload(Download download) {
        downloadMapper.addDownload(download);
    }

    @Override
    public Response retainAccess(String beginTime, String endTime) {
        return downloadMapper.statistics(beginTime, endTime);
    }
}
