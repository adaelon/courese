package com.zjx.courese.authserver.sec.filter;




import cn.hutool.core.util.StrUtil;

import com.alibaba.fastjson.JSON;
import com.zjx.courese.authserver.bean.ComUser;
import com.zjx.courese.authserver.utils.JwtUtils;
import com.zjx.courese.authserver.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Resource
    RedisUtil redisUtils;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //String jwt = request.getHeader(jwtUtils.getHeader());
        String jwt = request.getHeader("Authorization");
        if (StrUtil.isBlankOrUndefined(jwt)) {
            chain.doFilter(request, response);
            return;
        }

        Claims claim = jwtUtils.getClaimByToken(jwt);
        if (claim == null) {
            throw new JwtException("token 异常");
        }
        if (jwtUtils.isTokenExpired(claim)) {
            throw new JwtException("token已过期");
        }
        String tem = JSON.toJSONString(claim.get("user"));
        ComUser user = JSON.parseObject(tem, ComUser.class);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                redisUtils.get("login:" + user.getUserId()), null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication( authenticationToken);

        chain.doFilter(request, response);
    }
}
