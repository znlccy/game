package com.youda.fileos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.Buffer;

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
     * 声明ServletContext自动依赖注入
     */
    @Autowired
    private ServletContext servletContext;

    /**
     * @comment: fileDownload实现文件下载函数
     * @param: [response]
     * @return: java.lang.String
     */
    @GetMapping(value = "/download")
    public String fileDownload(HttpServletResponse response, @RequestParam("fileName") String fileName) {

        /*获取真实路径*/
        String realPath = servletContext.getRealPath("/");

        /*获取文件路径*/
        String filePath = realPath + "WEB-INF"+ File.separator + "classes" + File.separator +"static" + File.separator+"upload";
        File file = new File(filePath + "/" + fileName);
        /*判断文件父目录是否存在*/
        if (file.exists()) {

            //设置文件输出类型
            /*response.setContentType("application/octet-stream");*/
            //设置输出长度
            response.setContentType("application/force-download");
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            response.setHeader("Content-Disposition", "attachment:fileName="+fileName+"."+suffix);

            byte[] buffer = new byte[1024];
            //文件输入流
            FileInputStream fis = null;
            BufferedInputStream bis = null;

            /*输出流*/
            OutputStream os = null;

            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);

                int i = bis.read(buffer);
                if (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("----------File Download----------" + fileName);

            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("文件下载失败");
        }
        return "文件下载成功";
    }


}
