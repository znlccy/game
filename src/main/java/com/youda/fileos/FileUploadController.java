package com.youda.fileos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @CreateTime:2018/2/28 14:06
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 实现文件上传功能
 */

@Controller
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping(value = "/file")
public class FileUploadController {

    @Autowired
    private ServletContext servletContext;

    /**
     * @comment: fileUpload实现文件上传功能
     * @param: [file]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }

        //保存时的文件名
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime())+file.getOriginalFilename();

        //保存文件的绝对路径
        /*WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();*/
        System.out.println("servletContext"+servletContext);
        String realPath = servletContext.getRealPath("/");
        String filePath = realPath + "WEB-INF"+File.separator + "classes" + File.separator +"static" + File.separator+"upload";
        System.out.println("绝对路径:"+filePath);

        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        /*String path = "upload";*/
        File dest = new File(filePath + "/" + fileName);
        //判断文件父目录是否存在
        if ( !dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return "true";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }

    /**
     * @comment: multiFileUpload实现多文件上传功能
     * @param: [request]
     * @return: java.lang.String
     */
    @RequestMapping(value = "/multi/upload", method = RequestMethod.POST)
    @ResponseBody
    public String multiFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        if (files.isEmpty()) {
            return "false";
        }

        String path = "upload";

        for (MultipartFile file:files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" +size);

            //保存时的文件名
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar calendar = Calendar.getInstance();
            String dateName = df.format(calendar.getTime())+file.getOriginalFilename();

            //保存文件的绝对路径
            /*WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();*/
            String realPath = servletContext.getRealPath("/");
            String filePath = realPath + "WEB-INF"+File.separator + "classes" + File.separator +"static" + File.separator+"upload";
            /*String filePath = realPath + "WEB-INF"+File.separator + "classes" + File.separator +"static" + File.separator + "resource" + File.separator+dateName;*/
            System.out.println("绝对路径:"+filePath);

            if (file.isEmpty()) {
                return "false";
            } else {
                File dest = new File(filePath + "/" + fileName);
                //判断文件父目录是否存在
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }

                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }
}
