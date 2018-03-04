package com.youda.service.statistics;

import com.youda.model.FileOS;

import java.util.List;

/**
 * @CreateTime:2018/3/4 13:53
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 文件服务类
 */

public interface FileService {

    /**
     * @comment: addFile添加文件
     * @param: [fileOS]
     * @return: void
     */
    void addFile(FileOS fileOS);

    /**
     * @comment: updateFile更新文件
     * @param: [fileOS]
     * @return: void
     */
    void updateFile(FileOS fileOS);

    /**
     * @comment: deleteFile删除文件
     * @param: [fileId]
     * @return: void
     */
    void deleteFile(Long fileId);

    /**
     * @comment: findByFileId通过文件主键查找文件
     * @param: [fileId]
     * @return: com.youda.model.FileOS
     */
    FileOS findByFileId(Long fileId);

    /**
     * @comment: findByFileName通过文件名查找文件
     * @param: [fileName]
     * @return: java.util.List<com.youda.model.FileOS>
     */
    List<FileOS> findByFileName(String fileName);

    /**
     * @comment: findAll遍历所有文件
     * @param: []
     * @return: java.util.List<com.youda.model.FileOS>
     */
    List<FileOS> findAll();

}
