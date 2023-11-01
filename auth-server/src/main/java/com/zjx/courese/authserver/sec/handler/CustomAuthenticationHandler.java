package com.zjx.courese.authserver.sec.handler;

import com.alibaba.fastjson.JSONObject;

import com.zjx.courese.authserver.bean.ComUser;
import com.zjx.courese.authserver.utils.JwtUtil;
import com.zjx.courese.authserver.utils.RedisUtils;
import com.zjx.courese.authserver.utils.result.SystemResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Resource
    RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ComUser user = (ComUser) authentication.getPrincipal();
        String jwt = JwtUtil.generateToken(user);
        if (redisUtils.exists("login:" + user.getUserId())) {
            redisUtils.remove("login:" + user.getUserId());
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        redisUtils.set("login:" + user.getUserId(), jwt);
        response.setHeader("token", jwt);
        response.getWriter().write(JSONObject.toJSONString(SystemResult.success("登录成功")));
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(SystemResult.fail(401, exception.getMessage())));
    }
}
