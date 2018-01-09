package com.youda.controller.api;

import com.youda.request.channel.SignRequest;
import com.youda.request.api.ForgetFirstRequest;
import com.youda.request.api.ForgetSecondRequest;
import com.youda.request.api.LoginRequest;
import com.youda.request.api.RegisterRequest;
import com.youda.response.ResponseStatusCode;
import com.youda.service.MessageAuthCodeService;
import com.youda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Chencongye
 * @Date 2017/12/7 10:02
 * @Version 1.0.0
 * @Instructions 实现用户控制器功能
 */

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(maxAge = 3600, origins = "*")
public class UserController {

    /*实现短信验证码服务类的自动依赖注入*/
    @Autowired
    private MessageAuthCodeService messageAuthCodeService;

    @Autowired
    public UserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity userRegistered(@RequestBody RegisterRequest request, @RequestHeader("gameChannelId") String gameChannelId) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        if (!messageAuthCodeService.isSuccess(request.getUserName(), request.getVerificationCode())) {
            return ResponseStatusCode.verifyError();
        }
        messageAuthCodeService.delCodeByPhone(request.getUserName());
        return userService.register(request);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity userLogin(@RequestBody LoginRequest request, @RequestHeader("gameChannelId") String gameChannelId,@RequestHeader String userUseDevice) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return userService.login(request,Long.valueOf(gameChannelId),userUseDevice);
    }

    @RequestMapping(value = "/forget/first", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity forgetFirst(@RequestBody ForgetFirstRequest request, @RequestHeader("gameChannelId") String gameChannelId) {
        request.setGameChannelId(Long.valueOf(gameChannelId));
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return userService.forgotPasswordStart(request);
    }

    @RequestMapping(value = "/forget/second", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity forgetSecond(@RequestBody ForgetSecondRequest request, @RequestHeader("gameChannelId") String gameChannelId) {
        request.setGameChannelId(Long.valueOf(gameChannelId));

        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return userService.forgotPasswordEnd(request);
    }

    @RequestMapping(value = "/sign", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity sign(@RequestBody SignRequest request,
                               @RequestHeader("gameChannelId") Long gameChannelId) {
        request.setGameChannelId(gameChannelId);
        if (request.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        }
        return userService.signUser(request);
    }

}
