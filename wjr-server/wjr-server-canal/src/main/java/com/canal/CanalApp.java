package com.canal;

import com.canal.annotation.EnableCanalClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName CanalTestApp
 * @Description TODO
 * @Author 小温
 * @Date 2020/4/26 9:56
 * @Version 1.0
 */
@EnableCanalClient
@MapperScan(basePackages = "com.canal.mapper")
@SpringBootApplication
public class CanalApp {

    public static void main(String[] args) {
        SpringApplication.run(CanalApp.class, args);
    }

}
