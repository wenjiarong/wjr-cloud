package com.common.handler;

import com.common.entity.WjrResponse;
import com.common.exception.WjrAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理指的是全局处理Controller层抛出来的异常。
 * 因为全局异常处理器在各个微服务系统里都能用到，所以我们把它定义在wjr-common模块里
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WjrResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new WjrResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = WjrAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public WjrResponse handleFebsAuthException(WjrAuthException e) {
        log.error("系统错误", e);
        return new WjrResponse().message(e.getMessage());
    }
    
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public WjrResponse handleAccessDeniedException(){
        return new WjrResponse().message("没有权限访问该资源");
    }
}