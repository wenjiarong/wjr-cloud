package com.common.constant;

public interface WjrConstant {

    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    String GATEWAY_TOKEN_HEADER = "gatewayToken";
    /**
     * Zuul请求头TOKEN值
     */
    String GATEWAY_TOKEN_VALUE = "wjr:gateway:123456";

    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "wjr.captcha.";
}