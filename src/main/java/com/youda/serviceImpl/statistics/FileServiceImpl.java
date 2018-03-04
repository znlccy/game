package com.youda.serviceImpl.statistics;

import com.youda.dao.statistics.FileMapper;
import com.youda.model.FileOS;
import com.youda.service.statistics.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime:2018/3/4 13:53
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 文件服务实现类
 */

@Service
public class FileServiceImpl implements FileService {

    /**
     * 声明文件Dao层的自动依赖注入
     */
    @Autowired
    private FileMapper fileMapper;

    /**
     * @comment: addFile 实现添加文件
     * @param: [fileOS]
     * @return: void
     */
    @Override
    public void addFile(FileOS fileOS) {
        fileMapper.addFile(fileOS);
    }

    /**
     * @comment: updateFile 实现更新文件
     * @param: [fileOS]
     * @return: void
     */
    @Override
    public void updateFile(FileOS fileOS) {
        fileMapper.updateFile(fileOS);
    }

    /**
     * @comment: deleteFile 实现删除文件
     * @param: [fileId]
     * @return: void
     */
    @Override
    public void deleteFile(Long fileId) {
        fileMapper.deleteFile(fileId);
    }

    /**
     * @comment: findByFileId 实现通过主键查找文件
     * @param: [fileId]
     * @return: com.youda.model.FileOS
     */
    @Override
    public FileOS findByFileId(Long fileId) {
        return fileMapper.findByFileId(fileId);
    }

    /**
     * @comment: findByFileName 实现通过文件名查找文件
     * @param: [fileName]
     * @return: java.util.List<com.youda.model.FileOS>
     */
    @Override
    public List<FileOS> findByFileName(String fileName) {
        return fileMapper.findByFileName(fileName);
    }
    
    /**
     * @comment: findAll 实现查找所有文件
     * @param: []
     * @return: java.util.List<com.youda.model.FileOS>
     */
    @Override
    public List<FileOS> findAll() {
        return fileMapper.findAll();
    }
}
