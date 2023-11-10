package com.zjx.courese.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//用于配置跨域策略的相关信息，如允许的来源、方法、头等。
import org.springframework.web.cors.CorsConfiguration;
//用于将CorsConfiguration与特定路径相关联，实现跨域策略的映射
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//Servlet过滤器，用于将跨域配置应用于请求和响应之中
import org.springframework.web.filter.CorsFilter;
//用于配置全局跨域策略的注册和管理
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//用于配置Spring MVC的一些全局设置，包括跨域设置
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//Spring Boot 中的跨域配置类，用于解决跨域请求的问题
//允许前端应用从任意地址发送跨域请求
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    //当前跨域请求最大有效时长，这里默认一天
    private static final long MAX_AGE = 24*60*60;

    //开启跨域
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping(("/**"))
                .allowedOriginPatterns("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置访问源地址
        corsConfiguration.addAllowedHeader("*");//设置访问源请求头
        corsConfiguration.addAllowedMethod("*");//设置访问源请求方法
        corsConfiguration.setMaxAge(MAX_AGE);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());//对接口配置跨域设置
        return new CorsFilter(source);
    }

}

