package com.dosx.javase.service.base.exception;

import com.dosx.javase.common.utils.UniResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author me and my Dokky
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public UniResponse error(Exception e) {
        e.printStackTrace();
        return UniResponse.error().message(e.getMessage());
    }
}
