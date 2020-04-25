package com.common.handler;

import com.common.response.WjrResponse;
import com.common.utils.WjrUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  资源服务器令牌不正确返回401异常
 */
public class WjrAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    //HttpServletResponse.SC_UNAUTHORIZED，即401。
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        WjrResponse wjrResponse = new WjrResponse();
        WjrUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, wjrResponse.message("token无效")
        );
    }

}