package com.flowable;

import com.flowable.config.AppDispatcherServletConfiguration;
import com.flowable.config.ApplicationConfiguration;
import com.flowable.config.DatabaseAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


/**
 * @ClassName: FlowableApp
 * @Description: alibabacloud
 * @Author: 温家荣-wjr
 * @Date: 2020/6/15 22:49
 * @Version: 1.0
 */

//启用全局异常拦截器
@Import(value = {
        // 引入修改的配置
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class,
        // 引入 DatabaseConfiguration 表更新转换
        DatabaseAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.flowable.*"})
// 移除 Security 自动配置
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FlowableApp {

    public static void main(String[] args) {
        SpringApplication.run(FlowableApp.class, args);
    }

}
