package com.home.base.exceptions;

import com.home.base.msg.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：chenxf
 * @date ：Created in 2019/1/24 14:51
 * @description：异常处理
 * @modified By：
 * @version: 1.0$
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ObjectRestResponse serviceExceptionExceptionHandler(ServiceException ex) {
        log.error("系统业务异常", ex);
        return ObjectRestResponse.error(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public ObjectRestResponse serviceExceptionExceptionHandler(DataAccessException ex) {
        log.error("触发权限异常", ex);
        return  ObjectRestResponse.error(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public  ObjectRestResponse serviceExceptionExceptionHandler(Exception ex) {
        log.error("系统未知异常", ex);
        return ObjectRestResponse.error(ex.getMessage());

    }
}
