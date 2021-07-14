package com.rookiestar.starmanager.exception;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
public class RequestParemeterException extends RuntimeException{
    public RequestParemeterException() {
    }

    public RequestParemeterException(String message) {
        super(message);
    }
}
