package com.youda.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义支付宝支付的控制器
 */

@RestController
@RequestMapping(value = "/alipay")
@CrossOrigin(maxAge = 3600, origins = "*")
public class AlipayController {

}
