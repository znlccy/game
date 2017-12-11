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

    /**
     *
     */
    @Autowired
    private TokenService tokenService;

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // TODO Auto-generated method stub
        if (!(handler instanceof HandlerMethod)) {
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
        String channelKey = request.getHeader("channelKey");

        if (id != null && tokenText != null) {
            Token token = tokenService.findTokenByIDs(Long.valueOf(id),Long.valueOf(channelGameId));
            if (token.getAccessToken().equals(tokenText)) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}
