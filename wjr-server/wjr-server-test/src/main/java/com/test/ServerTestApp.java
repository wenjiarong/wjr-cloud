package com.test;

import com.common.annotation.EnableWjrAuthExceptionHandler;
import com.common.annotation.EnableWjrOauth2FeignClient;
import com.common.annotation.EnableWjrServerProtect;
import com.common.annotation.WjrCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableWjrServerProtect，开启微服务防护，避免客户端绕过网关直接请求微服务；
 * @EnableWjrOauth2FeignClient，开启带令牌的Feign请求，避免微服务内部调用出现401异常；
 * @EnableWjrAuthExceptionHandler，认证类型异常翻译。
 */

@WjrCloudApplication
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.test.mapper")
@EnableTransactionManagement
public class ServerTestApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerTestApp.class, args);
    }
}