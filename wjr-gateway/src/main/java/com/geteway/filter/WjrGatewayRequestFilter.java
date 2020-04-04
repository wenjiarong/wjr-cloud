package com.geteway.filter;

import com.alibaba.fastjson.JSONObject;
import com.common.constant.WjrConstant;
import com.common.entity.WjrResponse;
import com.geteway.properties.WjrGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

/**
 *  定义全局过滤器
 */
@Slf4j
@Component
public class WjrGatewayRequestFilter implements GlobalFilter {

    @Autowired
    private WjrGatewayProperties properties;
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 自定义逻辑
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 禁止客户端的访问资源逻辑
        Mono<Void> checkForbidUriResult = checkForbidUri(request, response);
        if (checkForbidUriResult != null) {
            return checkForbidUriResult;
        }

        //日志打印
        printLog(exchange);

        byte[] token = Base64Utils.encode((WjrConstant.GATEWAY_TOKEN_VALUE).getBytes());
        ServerHttpRequest build = request.mutate().header(WjrConstant.GATEWAY_TOKEN_HEADER, new String(token)).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }

    /**
     *  在过滤器中实现了控制客户端禁止访问的资源功能，比如禁止客户端访问微服务的/actuator/**资源
     * @param request
     * @param response
     * @return
     */
    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        //分割字符串过程中会按照每个分隔符进行分割，不忽略任何空白项；
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            WjrResponse wjrResponse = new WjrResponse().message("该URI不允许外部访问");
            return makeResponse(response, wjrResponse);
        }
        return null;
    }

    private Mono<Void> makeResponse(ServerHttpResponse response, WjrResponse febsResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(febsResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     *  在过滤器中打印一些网关转发的日志
     *
     *  printLog方法的主要逻辑是：通过ServerWebExchange对象的getAttribute方法获取各种信息，
     *  比如请求URI信息，路由信息等。可用的key值可以查看ServerWebExchangeUtils类中的属性值：
     * @param exchange
     */
    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }
    }
}