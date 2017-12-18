package com.youda.controller.admin;

import com.youda.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author chencongyeE
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义游戏控制器
 */

@RestController
@RequestMapping(value = "/game")
@CrossOrigin(maxAge = 3600, origins = "*")
public class GameController {


    @Autowired
    private GameService gameService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public boolean addGame() {
        return false;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public boolean getAllGame() {
        return false;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public boolean getAllGameForChannel() {
        return false;
    }
}
