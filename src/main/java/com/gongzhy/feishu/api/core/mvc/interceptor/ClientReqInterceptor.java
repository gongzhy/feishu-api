package com.gongzhy.feishu.api.core.mvc.interceptor;

import com.gongzhy.feishu.api.util.IpAddressUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 客户端请求拦截器
 */
@Slf4j
@Component
public class ClientReqInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 设置消息体
        request.setAttribute("clientIp", IpAddressUtil.getIp(request));
        return true;
    }

}