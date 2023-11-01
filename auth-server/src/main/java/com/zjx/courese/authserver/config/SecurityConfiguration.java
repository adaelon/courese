package com.zjx.courese.authserver.config;






import com.zjx.courese.authserver.sec.UserServices;
import com.zjx.courese.authserver.sec.filter.JwtAuthenticationFilter;
import com.zjx.courese.authserver.sec.handler.CustomAuthenticationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    UserServices userServices;

    @Resource
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    CustomAuthenticationHandler customAuthenticationHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        //开启加密
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServices);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有接口都需要登录才能访问
                .anyRequest().authenticated()
                .and()
                //开启表单登录
                .formLogin()
                //登录成功的处理方法
                .successHandler(customAuthenticationHandler)
                //登录失败的处理方法
                .failureHandler(customAuthenticationHandler);
        //关闭csrf
        http.csrf().disable();
        //开启过滤器，并将其置于UsernamePasswordAuthenticationFilter过滤器之前
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

