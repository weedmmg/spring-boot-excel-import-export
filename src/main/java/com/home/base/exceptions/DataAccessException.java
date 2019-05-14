package com.home.base.exceptions;

/**
 * @author ：chenxf
 * @date ：Created in 2019/1/24 14:49
 * @description：权限异常
 * @modified By：
 * @version: 1.0$
 */
public class  DataAccessException extends RuntimeException {

    public DataAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DataAccessException(String message) {
        super(message);
    }
}
