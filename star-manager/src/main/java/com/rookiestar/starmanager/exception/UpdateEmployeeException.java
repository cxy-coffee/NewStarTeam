package com.rookiestar.starmanager.exception;

/**
 * Description
 *
 * @author 曹向阳
 * @date 2021/7/22
 */
public class UpdateEmployeeException extends RuntimeException{
    public UpdateEmployeeException() {
    }

    public UpdateEmployeeException(String message) {
        super(message);
    }
}
