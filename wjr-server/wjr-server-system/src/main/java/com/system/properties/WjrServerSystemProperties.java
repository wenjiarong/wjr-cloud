package com.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:wjr-server-system.properties"})
@ConfigurationProperties(prefix = "wjr.server.system")
public class WjrServerSystemProperties {

    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private WjrSwaggerProperties swagger = new WjrSwaggerProperties();
}