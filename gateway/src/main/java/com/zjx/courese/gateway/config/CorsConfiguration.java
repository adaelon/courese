package com.zjx.courese.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class CorsConfiguration {
    @Bean
    public CorsWebFilter corsWebFilter(){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =new UrlBasedCorsConfigurationSource();

        org.springframework.web.cors.CorsConfiguration corsConfiguration =new org.springframework.web.cors.CorsConfiguration();

       corsConfiguration.addAllowedHeader("*");
       corsConfiguration.addAllowedMethod("*");
       //corsConfiguration.addAllowedOrigin("*");
       corsConfiguration.addAllowedOriginPattern("*");
       corsConfiguration.setAllowCredentials(true);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }
}
