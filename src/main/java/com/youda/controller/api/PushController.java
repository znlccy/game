package com.youda.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @CreateTime:2018/2/23 15:13
 * @Author:Administrator
 * @Version:v-1.0.0
 * @Comment: 实现Apple推送的功能
 */
@RestController
@RequestMapping(value = "/api/push")
@CrossOrigin(maxAge = 3600, origins = "*")
public class PushController {

    

}
