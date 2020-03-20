package com.system.config;

import com.common.handler.WjrAccessDeniedHandler;
import com.common.handler.WjrAuthExceptionEntryPoint;
import com.system.properties.WjrServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置类
 * 表示所有访问wjr-server-system的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问。
 * 然后在wjr-server-system的入口类WjrServerSystemApplication上
 * 添加@EnableGlobalMethodSecurity(prePostEnabled = true)注解，表示开启Spring Cloud Security权限注解
 */

@Configuration
@EnableResourceServer
public class WjrServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private WjrAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private WjrAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private WjrServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                //在资源服务器配置类里添加swagger免认证路径配置
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}