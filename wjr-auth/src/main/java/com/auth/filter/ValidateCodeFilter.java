package com.auth.filter;

import com.auth.service.ValidateCodeService;
import com.common.entity.WjrResponse;
import com.common.exception.ValidateCodeException;
import com.common.utils.WjrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义一个过滤器，用于拦截请求并校验验证码的正确性
 *
 * ValidateCodeFilter继承Spring Boot提供的OncePerRequestFilter，
 * 该过滤器实现了javax.servlet.filter接口，顾名思义，它可以确保我们的逻辑只被执行一次：
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 在ValidateCodeFilter的doFilterInternal方法中，我们编写了验证码校验逻辑：
     * 当拦截的请求URI为/oauth/token，请求方法为POST并且
     * 请求参数grant_type为password的时候（对应密码模式获取令牌请求），
     * 需要进行验证码校验。验证码校验调用的是之前定义的ValidateCodeService的check方法。
     * 当验证码调用通过时调用filterChain.doFilter(httpServletRequest, httpServletResponse)，
     * 让过滤器链继续往下执行，校验不通过时直接返回错误响应。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher("/oauth/token", HttpMethod.POST.toString());
        if (matcher.matches(httpServletRequest)
                && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter("grant_type"), "password")) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (ValidateCodeException e) {
                WjrResponse febsResponse = new WjrResponse();
                WjrUtil.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE,
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, febsResponse.message(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");
        validateCodeService.check(key, code);
    }
}