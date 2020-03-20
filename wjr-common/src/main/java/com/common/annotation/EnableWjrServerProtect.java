package com.common.annotation;

import com.common.config.WjrServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 定义一个@Enable注解来驱动它,让该配置类WjrServerProtectConfigure生效
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WjrServerProtectConfigure.class)
public @interface EnableWjrServerProtect {

}