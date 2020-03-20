package com.auth;

import com.common.annotation.EnableWjrAuthExceptionHandler;
import com.common.annotation.EnableWjrLettuceRedis;
import com.common.annotation.EnableWjrServerProtect;
import com.common.annotation.WjrCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: auth
 * @Description: 认证授权中心
 * @Author: 温家荣-wjr
 * @Date: 2020/3/18 15:54
 * @Version: 1.0
 */
@EnableWjrLettuceRedis
@WjrCloudApplication
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.auth.mapper")
public class AuthApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class,args);
    }

}
