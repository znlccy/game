package com.youda.controller.api;

import com.youda.response.MessageCode;
import com.youda.response.ResponseStatusCode;
import com.youda.service.MessageAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定义短信验证码控制器
 */

@RestController
@RequestMapping(value = "/api/message")
@CrossOrigin(maxAge = 3600, origins = "*")
public class MessageAuthCodeController {

    @Autowired
    MessageAuthCodeService authCodeService;
    /*声明国内任信了账户号*/
    private String domesticUser = "13402040612";

    /*声明国内任信了账户密码*/
    private String domesticPassword = "abc85410d238d4b5bae2ea3830e3d787";

    /*声明国内任信了Mid编号*/
    private String domesticMid = "14341";

    /*声明国外任信了用户编号*/
    private String foreignUser = "13402040612";

    /*声明国外任信了用户密码*/
    private String foreignPassword = "abc85410d238d4b5bae2ea3830e3d787";

    /*声明国外任信了Mid编号*/
    private String foreignMid = "14337";

    /**
     * 实现发送短信验证码
     *
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/domestic", method = RequestMethod.GET)
    public ResponseEntity sendMessageCode(@RequestParam String phone) {

        if (phone == null || phone.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        } else {
            int messageAuthCode = (int) ((Math.random() * 9 + 1) * 100000);
            String sendUrl = "http://apis.renxinl.com:8080/smsgate/varsend.do?" + "user=" + domesticUser + "&" + "pwd=" + domesticPassword + "&" + "params=" + phone + "," + messageAuthCode + "&" + "mid=" + domesticMid;
            MessageCode code = new RestTemplate().postForObject(sendUrl, null, MessageCode.class);
            if ("0000".equals(code.getCode())) return authCodeService.saveCode(phone, messageAuthCode + "", "86");

            return ResponseStatusCode.verifyError();
        }
    }

    /**
     * 实现发送短信验证码
     *
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/foreign", method = RequestMethod.GET)
    public ResponseEntity sendMessageCode(@RequestParam String phone, String countryCode) {

        if (phone == null || phone.isEmpty()) {
            return ResponseStatusCode.nullPointerError();
        } else {
            int messageAuthCode = (int) ((Math.random() * 9 + 1) * 100000);
            String sendUrl = "http://apis.renxinl.com:8080/smsgate/wtemplatesend.do?" + "user=" + foreignUser + "&" + "pwd=" + foreignPassword + "&" + "phone=" + countryCode + phone + "," + messageAuthCode + "&" + "mid=" + foreignMid;
            MessageCode code = new RestTemplate().postForObject(sendUrl, null, MessageCode.class);
            if ("0000".equals(code.getCode()))
                return authCodeService.saveCode(phone, messageAuthCode + "", countryCode);
            return ResponseStatusCode.verifyError();
        }
    }
}
