package com.common.annotation;

import com.common.config.WjrOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 定义一个@Enable类型注解，让该配置类WjrOAuth2FeignConfigure生效
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WjrOAuth2FeignConfigure.class)
public @interface EnableWjrOauth2FeignClient {

}