package com.common.annotation;

import com.common.config.WjrLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  定义一个@Enable类型注解，让该配置类WjrLettuceRedisConfigure生效
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WjrLettuceRedisConfigure.class)
public @interface EnableWjrLettuceRedis {

}