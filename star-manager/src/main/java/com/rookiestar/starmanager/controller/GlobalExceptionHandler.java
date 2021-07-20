package com.rookiestar.starmanager.controller;

import com.rookiestar.starmanager.exception.CheckVerificationCodeException;
import com.rookiestar.starmanager.exception.HireException;
import com.rookiestar.starmanager.exception.RequestParameterException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handle exception
 *
 * @author 曹向阳
 * @date 2021/7/12
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * shiro异常
     */
    @ExceptionHandler(value = UnknownAccountException.class)
    @ResponseBody
    public String unknownAccountException(Exception exception){
        logger.info(exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(value = LockedAccountException.class)
    @ResponseBody
    public String lockedAccountException(Exception exception){
        logger.info(exception.getMessage());
        return "账户被锁定";
    }

    @ExceptionHandler(value = IncorrectCredentialsException.class)
    @ResponseBody
    public String incorrectCredentialsException(Exception exception){
        logger.info(exception.getMessage());
        return "用户名或密码错误";
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String unauthorizedException(Exception exception){
        logger.info(exception.getMessage());
        return  exception.getMessage();
    }

    /**
     * 验证码异常
     */
    @ExceptionHandler(value = CheckVerificationCodeException.class)
    @ResponseBody
    public String checkVerificationCodeException(Exception exception){
        logger.info(exception.getMessage());
        return  exception.getMessage();
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler(value = RequestParameterException.class)
    @ResponseBody
    public String requestParameterException(Exception exception){
        logger.info(exception.getMessage());
        return  exception.getMessage();
    }

    /**
     * 雇用异常
     */
    @ExceptionHandler(value = HireException.class)
    @ResponseBody
    public String hireException(Exception exception){
        logger.info(exception.getMessage());
        return  exception.getMessage();
    }
}
