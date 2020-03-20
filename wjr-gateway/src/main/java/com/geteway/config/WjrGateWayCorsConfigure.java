package com.geteway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 前后分离架构下肯定会遇到跨域的问题，
 * 因为我们的请求都是通过微服务网关来转发的，
 * 所以我们可以在网关处统一处理跨域
 */
@Configuration
public class WjrGateWayCorsConfigure {

    /**
     * 该配置类里注册了CorsFilter:
     *
     * setAllowCredentials(true)表示允许cookie跨域；
     * addAllowedHeader(CorsConfiguration.ALL)表示请求头部允许携带任何内容；
     * addAllowedOrigin(CorsConfiguration.ALL)表示允许任何来源；
     * addAllowedMethod(CorsConfiguration.ALL)表示允许任何HTTP方法。
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}