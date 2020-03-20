package com.common.exception;

/**
 *  WJR系统通用业务异常
 */
public class WjrException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public WjrException(String message){
        super(message);
    }
}