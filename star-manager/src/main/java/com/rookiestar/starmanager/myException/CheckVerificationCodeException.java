package com.rookiestar.starmanager.myException;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/13
 */
public class CheckVerificationCodeException extends RuntimeException{
    public CheckVerificationCodeException() {
    }

    public CheckVerificationCodeException(String message) {
        super(message);
    }
}
