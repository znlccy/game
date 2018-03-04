package com.youda.dao.statistics;

import com.youda.model.FileOS;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime:2018/3/4 13:55
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 文件Mapper类
 */

@Mapper
public interface FileMapper {

    /**
     * @comment: addFile添加文件
     * @param: [fileOS]
     * @return: void
     */
    @Insert("insert into tb_file values(#{fileOS.fileUrl},#{fileOS.fileName},#{fileOS.createTime},#{fileOS.gameChannelId})")
    void addFile(@Param("fileOS") FileOS fileOS);

    /**
     * @comment: updateFile更新文件
     * @param: [fileOS]
     * @return: void
     */
    @Update("update tb_file set fileUrl=#{fileOS.fileUrl},fileName=#{fileOS.fileName},crateTime=#{fileOS.createTime},gameChannelId=#{fileOS.gameChannelId} where fileId=#{fileOS.fileId}")
    void updateFile(@Param("fileOS") FileOS fileOS);

    /**
     * @comment: deleteFile删除文件
     * @param: [fileId]
     * @return: void
     */
    @Delete("delete from tb_file where fileId=#{fileId}")
    void deleteFile(@Param("fileId") Long fileId);

    /**
     * @comment: findByFileId通过文件主键查找文件
     * @param: [fileId]
     * @return: com.youda.model.FileOS
     */
    @Select("select * from tb_file where fileId=#{fileId}")
    FileOS findByFileId(@Param("fileId") Long fileId);

    /**
     * @comment: findByFileName通过文件名查找文件
     * @param: [fileName]
     * @return: java.util.List<com.youda.model.FileOS>
     */
    @Select("select * from tb_file where fileName like %#{fileName}%")
    List<FileOS> findByFileName(String fileName);

    /**
     * @comment: findAll遍历所有文件
     * @param: []
     * @return: java.util.List<com.youda.model.FileOS>
     */
    @Select("select * from tb_file")
    List<FileOS> findAll();

}
