package com.youda.dao.statistics;

import com.youda.model.statistics.Download;
import com.youda.response.statistics.Response;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @CreateTime:2018/3/26 11:35
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 下载映射类
 */

@Mapper
public interface DownloadMapper {

    /**
     * @comment: addDownload 添加下载次数
     * @param: [download]
     * @return: void
     */
    @Insert("insert into tb_download(ip,uid,time,platform) values(#{download.ip},#{download.uid},#{download.time},#{download.platform})")
    void addDownload(@Param("download") Download download);

    /**
     * @comment: updateDownload 更新下载次数
     * @param: [download]
     * @return: void
     */
    @Update("update tb_download set ip=#{download.ip},uid=#{download.uid},time=#{download.time}")
    void updateDownload(@Param("download") Download download);

    /**
     * @comment: deleteDownload 删除下载次数
     * @param: [id]
     * @return: void
     */
    @Delete("delete from tb_download where id=#{id}")
    void deleteDownload(@Param("id") Long id);

    /**
     * @comment: findById 查询下载次数
     * @param: [id]
     * @return: com.ycplay.download.model.Download
     */
    @Select("select * from tb_download where id=#{id}")
    Download findById(@Param("id") Long id);

    /**
     * @comment: findAll 遍历所有下载次数
     * @param: []
     * @return: java.util.List<com.ycplay.download.model.Download>
     */
    @Select("select * from tb_download")
    List<Download> findAll();

    /**
     * @comment: statistics 统计下载量和访问量
     * @param: []
     * @return: com.ycplay.download.response.Response
     */
    @Select("SELECT COUNT(*) AS downloadCount, COUNT(DISTINCT ip) AS accessCount \n" +
            "FROM tb_download \n" +
            "WHERE TIME BETWEEN CONCAT(#{beginTime},' 00:00:00') and CONCAT(#{endTime},' 23:59:59')\n")
    Response statistics(@Param("beginTime") String beginTime, @Param("endTime") String endTime);
}
