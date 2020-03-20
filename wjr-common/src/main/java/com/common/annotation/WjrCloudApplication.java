package com.common.annotation;

import com.common.selector.WjrCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WjrCloudApplicationSelector.class)
public @interface WjrCloudApplication {

}