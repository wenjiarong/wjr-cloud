package com.auth.controller;

import com.auth.service.ValidateCodeService;
import com.common.entity.WjrResponse;
import com.common.exception.ValidateCodeException;
import com.common.exception.WjrAuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 用于客户端调用生成验证码
     * @param request
     * @param response
     * @throws IOException
     * @throws ValidateCodeException
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }

    @GetMapping("/oauth/test")
    public String testOauth() {
        return "oauth";
    }

    /**
     * 获取用户信息
     * @param principal
     * @return
     */
    @GetMapping("/user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * 退出登录
     * @param request
     * @return
     * @throws WjrAuthException
     */
    @DeleteMapping("/signout")
    public WjrResponse signout(HttpServletRequest request) throws WjrAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        WjrResponse wjrResponse = new WjrResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new WjrAuthException("退出登录失败");
        }
        return wjrResponse.message("退出登录成功");
    }
}