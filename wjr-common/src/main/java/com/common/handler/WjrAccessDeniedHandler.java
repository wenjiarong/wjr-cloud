package com.common.handler;

import com.common.response.R;
import com.common.response.ResultCode;
import com.common.utils.WjrUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 资源服务器,用户无权限返回403异常
 */
public class WjrAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        WjrUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, R.fail(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "没有权限访问该资源"));
    }

}