package com.geteway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WjrGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 因为wjr-gateway引入了febs-common模块，febs-common模块
         * 包含了Spring Cloud Security依赖，所以我们需要定义一个
         * 自己的WebSecurity配置类，来覆盖默认的。
         * 这里主要是关闭了csrf功能，否则会报csrf相关异常。
         * csrf(cross-site request forgetery)即跨站请求伪造
         */
        http.csrf().disable();
    }
}