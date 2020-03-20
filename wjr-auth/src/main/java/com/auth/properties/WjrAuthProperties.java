package com.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 自动装配wjr-auth.properties文件的属性，自定义配置类
 */
@Data
@SpringBootConfiguration  //@Component的派生注解
@PropertySource(value = {"classpath:wjr-auth.properties"})
@ConfigurationProperties(prefix = "wjr.auth")
public class WjrAuthProperties {

    //认证服务器可以根据多种Client来发放对应的令牌
    private WjrClientsProperties[] clients = {};
    //access_token的有效时间
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    //refresh_token的有效时间
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;

    // 免认证路径
    private String anonUrl;

    //验证码配置类
    private WjrValidateCodeProperties code = new WjrValidateCodeProperties();
}