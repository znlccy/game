package com.youda.interceptor;

import com.youda.annotation.CurrentUser;
import com.youda.model.Token;
import com.youda.service.TokenService;
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
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String url = request.getRequestURL().toString();
        if (url.endsWith("error")) {
            return true;
        }
        // 验证请求权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(CurrentUser.class) == null) {
            return true;
        }
        String id = request.getHeader("userId");
        String tokenText = request.getHeader("token");
        String channelGameId = request.getHeader("channelGameId");
        if (id != null && tokenText != null) {
            Token token = tokenService.findTokenByIDs(Long.valueOf(id), Long.valueOf(channelGameId));
            if (token != null && token.getAccessToken().equals(tokenText)) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}
