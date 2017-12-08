package com.youda.serviceImpl;

import com.youda.dao.MessageAuthCodeMapper;
import com.youda.model.MessageAuthCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youda.service.MessageAuthCodeService;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-28
 * @introduce 定一短信验证码服务接口的实现类
 */

@Service
public class MessageAuthCodeServiceImpl implements MessageAuthCodeService {

    /*实现短信验证码接口的依赖注入*/
    @Autowired
    private MessageAuthCodeMapper messageAuthCodeMapper;

    /*通过电话来查询短信验证码*/
    @Override
    public MessageAuthCode findByPhone(String userName) {
        MessageAuthCode messageAuthCode = new MessageAuthCode();
        messageAuthCode = messageAuthCodeMapper.findByMacodeContent(userName);
        if (messageAuthCode==null)
        {
            return null;
        }
        else
        {
            return messageAuthCode;
        }
    }
}
