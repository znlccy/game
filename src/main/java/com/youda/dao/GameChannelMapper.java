package com.youda.dao;

import com.youda.model.GameChannel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author Chencongye
 * @Date 2017/12/11 9:45
 * @Version 1.0.0
 * @Instructions
 */

@Mapper
public interface GameChannelMapper {

    // TODO: 2017/12/11 通过 id  查询
    GameChannel findById(Long gameChannelId);
}
