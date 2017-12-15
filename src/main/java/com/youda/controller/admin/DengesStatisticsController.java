package com.youda.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/15 14:28
 * @Version 1.0.0
 * @Instructions 实现当日统计的效果
 */

@RestController
@RequestMapping(value = "/admin")
public class DengesStatisticsController {

    /*当日活跃玩家统计，当日有开启过游戏的玩家数*/
    @ResponseBody
    @RequestMapping(value = "dengesactive", method = RequestMethod.GET)
    public ResponseEntity dengesActiveStatistics() {
        return null;
    }

    /*当日付费玩家统计，当日进行充值的玩家数*/
    @ResponseBody
    @RequestMapping(value = "dengespay", method = RequestMethod.GET)
    public ResponseEntity dengesPaymentStatistics() {
        return null;
    }

    /*当日收入统计，当日玩家充值的货币总额*/
    @ResponseBody
    @RequestMapping(value = "dengesincome",method = RequestMethod.GET)
    public ResponseEntity dengesIncomeStatistics(){
        return null;
    }

}
