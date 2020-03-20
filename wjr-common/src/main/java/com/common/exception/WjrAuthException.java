package com.common.exception;

/**
 * 定义一个授权服务异常
 */
public class WjrAuthException  extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public WjrAuthException(String message){
        super(message);
    }
}