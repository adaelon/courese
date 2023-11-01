package com.zjx.courese.authserver.sec.filter;


import com.alibaba.fastjson.JSON;
;
import com.zjx.courese.authserver.bean.ComUser;
import com.zjx.courese.authserver.utils.JwtUtil;
import com.zjx.courese.authserver.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    RedisUtils redisUtils;

    public JwtAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头或请求参数中获取JWT token
        String token = request.getHeader("token");
        try {
            Claims claimByToken = JwtUtil.getClaimByToken(token);
            assert claimByToken != null;
            String tem = JSON.toJSONString(claimByToken.get("user"));
            ComUser user = JSON.parseObject(tem, ComUser.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    redisUtils.get("login" + user.getUserId()), null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception e) {
            // 验证失败，可以进行一些处理
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.error(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
