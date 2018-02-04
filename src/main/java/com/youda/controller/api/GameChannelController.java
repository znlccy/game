package com.youda.controller.api;

import com.youda.request.api.GameChannelRequest;
import com.youda.response.api.GameChannelResponse;
import com.youda.service.GameChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime:2018/2/4 11:12
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 游戏渠道控制器,返回异步通知地址和gameChannelId
 */

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameChannelController {

    /*声明游戏渠道的自动依赖注入*/
    @Autowired
    private GameChannelService gameChannelService;

    @RequestMapping(value = "/app/key",method = RequestMethod.POST)
    @ResponseBody
    public GameChannelResponse generateGoogle(@RequestBody GameChannelRequest gameChannelRequest) {
        return gameChannelService.generateAppKey(gameChannelRequest);
    }

}
