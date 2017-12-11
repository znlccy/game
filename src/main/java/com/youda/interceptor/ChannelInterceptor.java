package com.youda.interceptor;

import com.youda.annotation.CurrentChannel;
import com.youda.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-27
 * @introduce 定义并实现各种错误拦截器
 */

@Component
public class ChannelInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ChannelService channelService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 验证请求权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(CurrentChannel.class) == null) {
            return true;
        }
        String channelId = request.getHeader("channelId");
        String gameId = request.getHeader("gameId");
        String channelKey = request.getHeader("channelKey");

        if (channelId != null && gameId != null && channelKey != null) {
            //TODO: 2017/12/8 验证
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }


}
