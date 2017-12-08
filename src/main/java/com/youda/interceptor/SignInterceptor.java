package com.youda.interceptor;

import com.youda.encrypt.SHAEncrpt;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenshengyu
 * on 2017/11/23.
 */
@Component
public class SignInterceptor extends HandlerInterceptorAdapter {
    private static final String appKey = "sjghdhjfgdhgfhdgads";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 验证请求是否合法
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        if (url.endsWith("error")) {
            return true;
        }
        if ("true".equals(request.getHeader("debug"))) return true;
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");

        if (sign != null && timestamp != null && sign.equals(SHAEncrpt.SHAEncrption(method + url + timestamp + appKey))) {
            return true;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }
}
