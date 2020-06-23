package com.flowable;

import org.flowable.ui.common.conf.DevelopmentConfiguration;
import org.flowable.ui.common.rest.idm.remote.RemoteAccountResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


/**
 * @ClassName: FlowableApp
 * @Description: alibabacloud
 * @Author: 温家荣-wjr
 * @Date: 2020/6/15 22:49
 * @Version: 1.0
 */

// 移除 Security 自动配置
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class,
                UserDetailsServiceAutoConfiguration.class,
                LiquibaseAutoConfiguration.class
        }
)
@ComponentScan(
        basePackages = {"com.flowable.*", "org.flowable.ui"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {RemoteAccountResource.class, DevelopmentConfiguration.class})
)
public class FlowableApp {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApp.class, args);
    }

}
