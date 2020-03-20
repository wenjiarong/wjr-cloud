package com.auth.config;

import com.auth.properties.WjrAuthProperties;
import com.common.handler.WjrAccessDeniedHandler;
import com.common.handler.WjrAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

//认证服务器本身也可以对外提供REST服务
@Configuration
@EnableResourceServer
public class WjrResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private WjrAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private WjrAuthExceptionEntryPoint exceptionEntryPoint;
    @Autowired
    private WjrAuthProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //anonUrls为免认证资源数组，是从WjrAuthProperties配置中读取出来的值经过逗号分隔后的结果
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                // 放行验证码权限，免认证资源
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

}