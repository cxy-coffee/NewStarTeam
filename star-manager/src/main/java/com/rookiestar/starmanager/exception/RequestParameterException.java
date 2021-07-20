package com.rookiestar.starmanager.exception;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
public class RequestParameterException extends RuntimeException{
    public RequestParameterException() {
    }

    public RequestParameterException(String message) {
        super(message);
    }
}
