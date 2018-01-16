package com.youda.dao.statistics;

import com.youda.response.statistics.ModelsResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author Chencongye
 * @Date 2018/1/17 0:03
 * @Version 1.0.0
 * @Instructions 实现机型统计
 */

@Mapper
public interface ModelsMapper {

    @Select("")
    List<ModelsResponse> customModels();

    @Select("")
    List<ModelsResponse> allModels();
}
