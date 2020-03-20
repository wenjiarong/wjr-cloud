package com.test.fegin.fallback;

import com.test.fegin.IHelloFegin;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloFegin> {
    @Override
    public IHelloFegin create(Throwable throwable) {
        return name -> {
            log.error("调用wjr-server-system服务出错", throwable);
            return "调用出错";
        };
    }
}