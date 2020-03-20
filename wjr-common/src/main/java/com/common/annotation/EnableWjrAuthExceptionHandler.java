package com.common.annotation;

import com.common.config.WjrAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  定义一个@Enable类型注解，让该配置类WjrAuthExceptionConfigure生效
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WjrAuthExceptionConfigure.class)
public @interface EnableWjrAuthExceptionHandler {

}