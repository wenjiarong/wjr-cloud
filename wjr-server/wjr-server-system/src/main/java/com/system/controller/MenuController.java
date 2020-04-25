package com.system.controller;

import com.common.response.WjrResponse;
import com.system.entity.Menu;
import com.system.entity.router.VueRouter;
import com.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/{username}")
    public WjrResponse getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        Map<String, Object> result = new HashMap<>();
        // 构建用户路由对象
        List<VueRouter<Menu>> userRouters = this.menuService.getUserRouters(username);
        // 获取用户权限信息
        Set<String> userPermissions = this.menuService.findUserPermissions(username);
        // 组装数据
        result.put("routes", userRouters);
        result.put("permissions", userPermissions);
        return new WjrResponse().data(result);
    }

}