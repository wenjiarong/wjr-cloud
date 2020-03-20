package com.test.fegin;

import com.common.constant.WjrServerConstant;
import com.test.fegin.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = WjrServerConstant.WJR_SERVER_SYSTEM , contextId = "helloServiceClient",
        fallbackFactory = HelloServiceFallback.class)
public interface IHelloFegin {

    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);
}