package com.youda.controller.pay;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "wechat")
@CrossOrigin(maxAge = 3600, origins = "*")
public class WeChatPayController {


}
