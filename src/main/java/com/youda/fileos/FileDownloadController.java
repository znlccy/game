package com.youda.fileos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @CreateTime:2018/2/28 14:14
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 实现文件下载功能
 */

@Controller
@RequestMapping(value = "/file")
@CrossOrigin(maxAge = 3600, origins = "*")
public class FileDownloadController {

    /**
     * @comment: fileDownload实现文件下载函数
     * @param: []
     * @return: java.lang.String
     */
    @RequestMapping(value = "/download")
    @ResponseBody
    public String fileDownload() {
        return null;
    }

}
