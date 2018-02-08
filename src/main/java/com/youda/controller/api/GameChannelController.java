package com.youda.controller.api;

import com.youda.dao.ApplePayConfMapper;
import com.youda.dao.GooglePayConfMapper;
import com.youda.model.ApplePayConf;
import com.youda.model.GooglePayConf;
import com.youda.request.api.GameChannelRequest;
import com.youda.response.api.GameChannelResponse;
import com.youda.service.ApplePayConfService;
import com.youda.service.GameChannelService;
import com.youda.service.GooglePayConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /*声明google配置的自动依赖注入*/
    @Autowired
    private GooglePayConfService googlePayConfService;

    /*声明apple配置的自动依赖注入*/
    @Autowired
    private ApplePayConfService applePayConfService;

    /**
     * @comment: generateGoogle 生成相应的appkey
     * @param: [gameChannelRequest]
     * @return: com.youda.response.api.GameChannelResponse
     */
    @RequestMapping(value = "/app/key",method = RequestMethod.POST)
    @ResponseBody
    public GameChannelResponse generateGoogle(@RequestBody GameChannelRequest gameChannelRequest) {
        return gameChannelService.generateAppKey(gameChannelRequest);
    }

    /**
     * @comment: addGoogle添加google配置
     * @param: [googlePayConf]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/add/google",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addGoogle(@RequestBody GooglePayConf googlePayConf,@RequestHeader String gameChannelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(googlePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)) == null) {
            googlePayConf.setGameChannelId(Long.valueOf(gameChannelId));
            googlePayConfService.addGoogle(googlePayConf);
            map.put("status","0200");
            map.put("message","添加成功！");
            map.put("googlePayConfId",googlePayConf.getGooglePayConfId());
        }
        else {
            map.put("status","0208");
            map.put("message","该Google配置已经存在了！");
            map.put("googlePayConfId",googlePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)).getGooglePayConfId());
        }
        return map;
    }

    /**
     * @comment: updateGoogle更新google配置
     * @param: [googlePayConf]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/update/google",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateGoogle(@RequestBody GooglePayConf googlePayConf) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (googlePayConfService.findById(googlePayConf.getGooglePayConfId()) ==null) {
            map.put("status","0404");
            map.put("message","该Google配置不存在！");
        } else {
            googlePayConfService.updateGoogle(googlePayConf);
            map.put("status","0201");
            map.put("message","更新成功！");
            map.put("googlePayConfId",googlePayConf.getGooglePayConfId());
        }
        return map;
    }

    /**
     * @comment: findOneGoogle查找单独一个Google配置
     * @param: [gameChannelId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/one/google/{gameChannelId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findOneGoogle(@PathVariable String gameChannelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (googlePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)) == null) {
            map.put("status","0404");
            map.put("message","该Google配置不存在！");
        } else {
            map.put("status","0204");
            map.put("message","查询成功！");
            map.put("data",googlePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)));
        }
        return map;
    }

    /**
     * @comment: deleteGoogle删除google配置
     * @param: [googlePayConfId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/delete/google/{googlePayConfId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteGoogle(@PathVariable Long googlePayConfId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (googlePayConfService.findById(googlePayConfId) == null) {
            map.put("status","0404");
            map.put("message","该Google配置不存在！");
        } else {
            googlePayConfService.deleteGoogle(googlePayConfId);
            map.put("status","0202");
            map.put("message","删除成功！");
        }
        return map;
    }

    /**
     * @comment: findAllGoogle查看所有google配置
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/all/google",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllGoogle() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<GooglePayConf> googlePayConfs = googlePayConfService.findAll();
        map.put("data",googlePayConfs);
        map.put("status","0203");
        map.put("message","遍历成功");
        return map;
    }

    /**
     * @comment: addApple添加apple配置
     * @param: [applePayConf, gameChannelId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/add/apple",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addApple(@RequestBody ApplePayConf applePayConf,@RequestHeader String gameChannelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (applePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)) == null) {
            applePayConf.setGameChannelId(Long.valueOf(gameChannelId));
            applePayConfService.addApple(applePayConf);
            map.put("status","0200");
            map.put("message","添加成功！");
            map.put("googlePayConfId",applePayConf.getApplePayConfId());
        } else {
            map.put("status","0208");
            map.put("message","该Apple配置已经存在了！");
            map.put("applePayConfId",applePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)).getApplePayConfId());
        }
        return map;
    }

    /**
     * @comment: findOneApple查找一个单独的Apple配置
     * @param: [gameChannelId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/one/apple/{gameChannelId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findOneApple(@PathVariable String gameChannelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (applePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)) == null) {
            map.put("status","0404");
            map.put("message","该Google配置不存在！");
        } else {
            map.put("status","0204");
            map.put("message","查询成功！");
            map.put("data",applePayConfService.findByGameChannelId(Long.valueOf(gameChannelId)));
        }
        return map;
    }

    /**
     * @comment: updateApple
     * @param: [applePayConf]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/update/apple",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateApple(@RequestBody ApplePayConf applePayConf) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (applePayConfService.findById(applePayConf.getApplePayConfId()) == null) {
            map.put("status","0404");
            map.put("message","该Apple配置不存在！");
        } else {
            applePayConfService.updateApple(applePayConf);
            map.put("status","0201");
            map.put("message","更新成功！");
            map.put("applePayConfId",applePayConf.getApplePayConfId());
        }
        return map;
    }

    /**
     * @comment: deleteApple删除apple配置
     * @param: [applePayConfId]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/delete/apple/{applePayConfId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteApple(@PathVariable Long applePayConfId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (applePayConfService.findById(applePayConfId) == null) {
            map.put("status","0404");
            map.put("message","该Apple配置不存在！");
        } else {
            applePayConfService.deleteApple(applePayConfId);
            map.put("status","0202");
            map.put("message","删除成功！");
        }
        return map;
    }

    /**
     * @comment: findAllApple查找所有苹果配置
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/all/apple",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllApple() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<ApplePayConf> applePayConfs = applePayConfService.findAll();
        map.put("data",applePayConfs);
        map.put("status","0203");
        map.put("message","遍历成功");
        return map;
    }
}
