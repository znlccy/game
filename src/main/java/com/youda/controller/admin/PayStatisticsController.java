package com.youda.controller.admin;

import com.youda.service.admin.PayStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chencongye
 * @Date 2017/12/13 16:10
 * @Version 1.0.0
 * @Instructions 实现付费和收益统计
 */

@RestController
@RequestMapping(value = "/admin")
public class PayStatisticsController {

    /*实现支付统计的自动依赖注入*/
    @Autowired
    PayStatisticsService payStatisticsService;

    /*实现日付费率统计*/
    @ResponseBody
    @RequestMapping(value = "/daypayrate", method = RequestMethod.GET)
    public ResponseEntity dayPayRateStatistics() {
        return null;
    }

    /*实现日ARPU统计*/
    @ResponseBody
    @RequestMapping(value = "/dayarpu",method = RequestMethod.GET)
    public ResponseEntity dayArpuStatistics() {
        return null;
    }

    /*实现日ARPPU统计*/
    @ResponseBody
    @RequestMapping(value = "/dayarppu", method = RequestMethod.GET)
    public ResponseEntity dayArppuStatistics() {
        return null;
    }

}
