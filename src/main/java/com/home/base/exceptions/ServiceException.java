package com.home.base.exceptions;

/**
 * @author ：chenxf
 * @date ：Created in 2019/1/24 14:50
 * @description：业务异常
 * @modified By：
 * @version: 1.0$
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ServiceException(String message) {
        super(message);
    }
}
