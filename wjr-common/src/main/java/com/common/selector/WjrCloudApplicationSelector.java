package com.common.selector;

import com.common.config.WjrAuthExceptionConfigure;
import com.common.config.WjrOAuth2FeignConfigure;
import com.common.config.WjrServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class WjrCloudApplicationSelector implements ImportSelector {

    /**
     * @EnableWjrServerProtect，开启微服务防护，避免客户端绕过网关直接请求微服务；
     * @EnableWjrOauth2FeignClient，开启带令牌的Feign请求，避免微服务内部调用出现401异常；
     * @EnableWjrAuthExceptionHandler，认证类型异常翻译。
     * 这三个功能都是微服务提供者必备的功能，所以我们可以定义一个注解将这三个功能整合在一起。
     *
     * 因为这三个注解都是通过@Enable类型注解来将配置类注册到IOC容器中，
     * 所以我们现在要做的就是将这三个配置类一次性都注册到IOC容器中。
     * 在Spring中，要将多个类进行注册，可以使用selector的方式
     *
     * 通过selectImports方法，我们一次性导入了WjrAuthExceptionConfigure、WjrOAuth2FeignConfigure和WjrServerProtectConfigure这三个配置类
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                WjrAuthExceptionConfigure.class.getName(),
                WjrOAuth2FeignConfigure.class.getName(),
                WjrServerProtectConfigure.class.getName()
        };
    }
}