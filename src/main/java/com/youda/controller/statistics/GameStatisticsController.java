package com.youda.controller.statistics;

import com.youda.service.admin.GameStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class GameStatisticsController {

    /*实现游戏统计服务的自动依赖注入*/
    @Autowired
    GameStatisticsService gameStatisticsService;


}
