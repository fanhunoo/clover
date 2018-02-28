package com.fanhunoo.clover.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CloverExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerforException(NoHandlerFoundException e){
        System.out.println("---------------NoHandlerFoundException:");
        e.printStackTrace();
        return "error/404";
    }
}
