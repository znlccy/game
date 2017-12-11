package com.youda.interceptor;

import com.youda.annotation.CurrentChannel;
import com.youda.model.GameChannel;
import com.youda.service.GameChannelService;
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
    private GameChannelService channelService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String url = request.getRequestURL().toString();
        if (url.endsWith("error")){
            return  true;
        }
        // 验证请求权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(CurrentChannel.class) == null) {
            return true;
        }
        String channelGameId = request.getHeader("channelGameId");
        String channelKey = request.getHeader("channelKey");

        if (channelGameId != null && channelKey != null) {
            GameChannel gameChannel = channelService.findByIds(Long.valueOf(channelGameId));
            if (gameChannel != null && gameChannel.getAppKey().equals(channelKey)) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }


}
