package com.system.controller;

import com.system.service.ITradeLogService;
import com.test.entity.TradeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @Autowired
    private ITradeLogService tradeLogService;

    @GetMapping("/info")
    public String test(){
        return "wjr-server-system";
    }

    @GetMapping("/currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello" + name;
    }

    @GetMapping("/pay")
    public void orderAndPay(TradeLog tradeLog) {
        this.tradeLogService.orderAndPay(tradeLog);
    }

}