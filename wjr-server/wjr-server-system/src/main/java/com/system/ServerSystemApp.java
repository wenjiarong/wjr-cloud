package com.system;

import com.common.annotation.EnableWjrAuthExceptionHandler;
import com.common.annotation.EnableWjrServerProtect;
import com.common.annotation.WjrCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@WjrCloudApplication
@MapperScan("com.system.mapper")
@EnableTransactionManagement  //开启事务管理
public class ServerSystemApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerSystemApp.class, args);
    }
}