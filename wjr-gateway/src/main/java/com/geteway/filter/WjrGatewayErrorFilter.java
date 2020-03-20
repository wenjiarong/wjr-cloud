package com.geteway.filter;

import com.common.entity.WjrResponse;
import com.common.utils.WjrUtil;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义Zuul异常处理可以通过继承Zuul的SendErrorFilter过滤器来实现
 *
 * 在该过滤器中，我们可以通过RequestContext获取到当前请求上下文，
 * 通过请求上下文可以获取到当前请求的服务名称serviceId和当前请求
 * 的异常对象ExceptionHolder等信息。通过异常对象我们可以继续获取到异常内容，
 * 根据不同的异常内容我们可以自定义想要的响应
 */
@Slf4j
@Component
public class WjrGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            WjrResponse wjrResponse = new WjrResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            wjrResponse = resolveExceptionMessage(message, serviceId, wjrResponse);

            HttpServletResponse response = ctx.getResponse();
            WjrUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, wjrResponse
            );
            log.error("Zull sendError：{}", wjrResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private WjrResponse resolveExceptionMessage(String message, String serviceId, WjrResponse wjrResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return wjrResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return wjrResponse.message(serviceId + "服务不可用");
        }
        return wjrResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}